package com.mma.mma.service;

import com.mma.mma.domain.CsgoItem;
import com.mma.mma.service.mapper.CsgoItemMapper;
import com.mma.mma.service.respdto.XyzDTO;
import com.mma.mma.service.respdto.XyzItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;

@Service
@Transactional
public class CsgoXyzService {

    @Value("${XYZ_API_KEY}")
    private String XYZ_API_KEY;

    @Value("${IO_API_KEY}")
    private String IO_API_KEY;

    private final Logger log = LoggerFactory.getLogger(CsgoXyzService.class);

    private final CsgoItemService csgoItemService;

    private final CsgoItemMapper csgoItemMapper;

    public CsgoXyzService(CsgoItemService csgoItemService, CsgoItemMapper csgoItemMapper) {
        this.csgoItemService = csgoItemService;
        this.csgoItemMapper = csgoItemMapper;
    }

    /**
     * We check for csgo.steamlytics.xyz item price updates
     * <p>
     * This is scheduled to get fired every 11 minutes.
     * </p>
     */
    @Async
    @Scheduled(cron = "0 */1 * * * *") // 11 TODO
    public void updateItems() {
        log.debug("Run scheduled csgo.steamlytics.xyz  update items {}");

        final String XYZ_API_URL = "http://api.csgo.steamlytics.xyz/v2/pricelist?key=" + XYZ_API_KEY;

        RestTemplate restTemplate = restTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN));
        headers.set("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Safari/537.36");

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<XyzDTO> respEntity;

        try {
            respEntity = restTemplate.exchange(XYZ_API_URL, HttpMethod.GET, entity, XyzDTO.class);
        } catch (Exception ex) {
            log.error("Failled to fetch csgo.steamlytics.xyz data {}", ex.getMessage());
            return;
        }

        HashMap<String, CsgoItem> existingItems = existingItems();
        XyzDTO xyzresp = respEntity.getBody();

         if (!xyzresp.isSuccess()) {
            log.error("Failled to fetch csgo.steamlytics.xyz fata {}");
            return;
        }

        CsgoItem item;
        HashMap<String, XyzItem> xyzitems = xyzresp.getItems();
        Map<String, Double> cfPriceData = cfPriceData(restTemplate, headers);
        Map<String, String> ioPriceData = ioPriceData(restTemplate, headers);
        Set<String> keySet = xyzitems.keySet();
        for (String markethashname : keySet) {
            if (existingItems.containsKey(markethashname)) {
                item = existingItems.get(markethashname);
            } else {
                item = new CsgoItem();
                item.setName(markethashname);
            }
            try {
                Double ioPrice = null;
                if (ioPriceData.containsKey(markethashname)) {
                    ioPrice = Double.valueOf(ioPriceData.get(markethashname));
                }
                mapNewItemData(item, xyzitems.get(markethashname), cfPriceData.get(markethashname), ioPrice);
                csgoItemService.save(csgoItemMapper.toDto(item));
            }catch (Exception ex) {
                log.error("Failed to save/map csgo.steamlytics.xyz new item {}", ex.getMessage());
            }
        }
        csgoItemService.refreshsearch();
    }

    private RestTemplate restTemplate() {
        RestTemplate template = new RestTemplate();
        List<HttpMessageConverter<?>> converters = template.getMessageConverters();
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                try {
                    ((MappingJackson2HttpMessageConverter) converter)
                        .setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN));
                } catch (Exception ex) {
                    log.error("Failled to add text converter", ex.getMessage());
                }
            }
        }
        return template;
    }

    private HashMap<String, CsgoItem> existingItems() {
        HashMap<String, CsgoItem> map = new HashMap<>();
        List<CsgoItem> items = csgoItemService.findAll();
        for (CsgoItem item : items) {
            map.put(item.getName(), item);
        }
        return map;
    }

    private void mapNewItemData(CsgoItem item, XyzItem newitem, Double cfPrice, Double ioPrice) {
        item.setSp(newitem.getSafe_price());
        item.setOpm(newitem.isOngoing_price_manipulation());
        item.setVol(newitem.getTotal_volume());

        item.setMp7(newitem.get7_days().getMedian_price());
        item.setAvg7(newitem.get7_days().getAverage_price());
        item.setLp7(newitem.get7_days().getLowest_price());
        item.setHp7(newitem.get7_days().getHighest_price());
        item.setMad7(newitem.get7_days().getMean_absolute_deviation());
        item.setDp7(newitem.get7_days().getDeviation_percentage().round(new MathContext(2, RoundingMode.HALF_UP)));
        item.setTrend7(newitem.get7_days().getTrend().round(new MathContext(4, RoundingMode.HALF_UP)));
        item.setVol7(newitem.get7_days().getVolume());

        item.setMp30(newitem.get30_days().getMedian_price());
        item.setAvg30(newitem.get30_days().getAverage_price());
        item.setLp30(newitem.get30_days().getLowest_price());
        item.setHp30(newitem.get30_days().getHighest_price());
        item.setMad30(newitem.get30_days().getMean_absolute_deviation());
        item.setDp30(newitem.get30_days().getDeviation_percentage().round(new MathContext(2, RoundingMode.HALF_UP)));
        item.setTrend30(newitem.get30_days().getTrend().round(new MathContext(4, RoundingMode.HALF_UP)));
        item.setVol30(newitem.get30_days().getVolume());

        item.setMpAll(newitem.getAll_time().getMedian_price());
        item.setAvgAll(newitem.getAll_time().getAverage_price());
        item.setLpAll(newitem.getAll_time().getLowest_price());
        item.setHpAll(newitem.getAll_time().getHighest_price());
        item.setMadAll(newitem.getAll_time().getMean_absolute_deviation());
        item.setDpAll(newitem.getAll_time().getDeviation_percentage().round(new MathContext(2, RoundingMode.HALF_UP)));
        item.setTrendAll(newitem.getAll_time().getTrend().round(new MathContext(4, RoundingMode.HALF_UP)));
        item.setVolAll(newitem.getAll_time().getVolume());

        if (cfPrice != null && cfPrice > 0) {
            item.setCfp(cfPrice);
        }
        if (ioPrice != null && ioPrice > 0) {
            item.setIop(ioPrice);
        }
        if (cfPrice != null && cfPrice > 0 && newitem.getSafe_price() != null) {
            Double sp = newitem.getSafe_price().doubleValue();
            if (sp > 0) {
                long diff = Math.round((sp - cfPrice) / sp * 100);
                item.setDcx((double) diff);
            }
        }

        item.setAdded(newitem.getFirst_seen());
    }

    private Map<String, Double> cfPriceData(RestTemplate restTemplate, HttpHeaders headers) {
        final String CF_API_URL = "https://api.csgofast.com/price/all";
        HttpEntity<String> entityCF = new HttpEntity<>("parameters", headers);
        ResponseEntity<Map> respEntityCF;
        Map<String, Double> cfresp = new HashMap<>();
        try {
            respEntityCF = restTemplate.exchange(CF_API_URL, HttpMethod.GET, entityCF, Map.class);
            cfresp = respEntityCF.getBody();
        } catch (Exception ex) {
            log.error("Failled to fetch CF data", ex.getMessage());
        }
        return cfresp;
    }


    private Map<String, String> ioPriceData(RestTemplate restTemplate, HttpHeaders headers) {
        final String IO_API_URL = "https://api.steamapi.io/market/prices/730?key=" + IO_API_KEY;
        HttpEntity<String> entityIO = new HttpEntity<>("parameters", headers);
        ResponseEntity<Map> respEntityIO;
        Map<String, String> ioresp = new HashMap<>();
        try {
            respEntityIO = restTemplate.exchange(IO_API_URL, HttpMethod.GET, entityIO, Map.class);
            ioresp = respEntityIO.getBody();
        } catch (Exception ex) {
            log.error("Failled to fetch CF data", ex.getMessage());
        }
        return ioresp;
    }
}
