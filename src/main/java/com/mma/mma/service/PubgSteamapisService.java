package com.mma.mma.service;

import com.mma.mma.domain.Pubgitem;
import com.mma.mma.service.mapper.PubgitemMapper;
import com.mma.mma.service.respdto.OpskinsDTO;
import com.mma.mma.service.respdto.SaDTO;
import com.mma.mma.service.respdto.SaItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Transactional
public class PubgSteamapisService {

    @Value("${SA_API_KEY}")
    private String SA_API_KEY;

    @Value("${IO_API_KEY}")
    private String IO_API_KEY;

    @Value("${OP_API_KEY}")
    private String OP_API_KEY;

    private final Logger log = LoggerFactory.getLogger(PubgSteamapisService.class);

    private final PubgitemService pubgItemService;

    private final PubgitemMapper pubgitemMapper;

    private final CsgoXyzService csgoXyzService;

    public PubgSteamapisService(PubgitemService pubgItemService, PubgitemMapper pubgitemMapper, CsgoXyzService csgoXyzService) {
        this.pubgItemService = pubgItemService;
        this.pubgitemMapper = pubgitemMapper;
        this.csgoXyzService = csgoXyzService;
    }

    /**
     * We check for api.steamapis.com item price updates
     * <p>
     * This is scheduled to get fired every 10 minutes.
     * </p>
     */
    @Async
    @Scheduled(cron = "0 */10 * * * *") // 10
    public void updateItems() {
        log.debug("Run scheduled api.steamapis.com  update items {}");

        final String SA_API_URL = "https://api.steamapis.com/market/items/578080?api_key=" + SA_API_KEY;

        RestTemplate restTemplate = restTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN));
        headers.set("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Safari/537.36");

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<SaDTO> respEntity;

        try {
            respEntity = restTemplate.exchange(SA_API_URL, HttpMethod.GET, entity, SaDTO.class);
        } catch (Exception ex) {
            log.error("Failed to fetch api.steamapis.com data {}", ex.getMessage());
            return;
        }

        HashMap<String, Pubgitem> existingItems = existingItems();
        SaDTO saresp = respEntity.getBody();

        if (saresp.getData() == null || saresp.getData().size() == 0) {
            log.error("Failed to fetch api.steamapis.com fata {}");
            return;
        }

        Pubgitem item;
        List<SaItem> saitems = saresp.getData();
        Map<String, Object> cfPriceData = cfPriceData(restTemplate, headers);
        Map<String, String> ioPriceData = ioPriceData(restTemplate, headers);
        OpskinsDTO opPriceData = opPriceData(restTemplate, headers);

        for (SaItem saItem : saitems) {
            String markethashname = saItem.getMarket_hash_name();

            if (existingItems.containsKey(markethashname)) {
                item = existingItems.get(markethashname);
            } else {
                item = new Pubgitem();
                item.setName(saItem.getMarket_hash_name());
            }
            try {
                Double ioPrice = null;
                Double cfPrice = null;
                Double opPrice = null;
                Integer opQty = null;
                item.setCfp(null);
                SaItem newItem = findSaItem(saitems, markethashname);
                if (newItem == null) {
                    return;
                }
                if (cfPriceData.containsKey(markethashname)) {
                    try {
                        cfPrice = Double.valueOf(cfPriceData.get(markethashname).toString());
                    } catch (Exception ex) {
                        log.error("Failed to cast to Double cf price {}", ex.getMessage());
                        cfPrice = null;
                    }
                }
                if (ioPriceData.containsKey(markethashname)) {
                    try {
                        ioPrice = Double.valueOf(ioPriceData.get(markethashname));
                    } catch (Exception ex) {
                        log.error("Failed to cast to Double io price {}", ex.getMessage());
                        ioPrice = null;
                    }
                }
                if (opPriceData != null && opPriceData.getStatus() == 1
                    && opPriceData.getResponse().containsKey(markethashname)) {
                    try {
                        opPrice = (double) opPriceData.getResponse().get(markethashname).getPrice() / 100;
                        opQty = opPriceData.getResponse().get(markethashname).getQuantity();
                    } catch (Exception ex) {
                        log.error("Failed to cast to Double op price {}", ex.getMessage());
                        opPrice = null;
                        opQty = null;
                    }
                }
                mapNewItemData(item, newItem, cfPrice, ioPrice, opPrice, opQty);
                pubgItemService.save(pubgitemMapper.toDto(item));
            } catch (Exception ex) {
                log.error("Failed to save/map csgo.steamlytics.xyz new item {}", ex.getMessage());
                log.error("{}", markethashname, saitems.size());
            }
        }
        pubgItemService.refreshsearch();
    }

    private void mapNewItemData(Pubgitem item, SaItem newitem, Double cfPrice, Double ioPrice, Double opPrice, Integer opQty) {

        if (newitem.getPrices() != null) {
            item.setSp(newitem.getPrices().getSafe());
            item.setUns(newitem.getPrices().getUnstable());
            item.setUnsr(String.valueOf(newitem.getPrices().getUnstable_reason()));
            item.setMaxp(newitem.getPrices().getMax());
            item.setAvgp(newitem.getPrices().getAvg());
            item.setMinp(newitem.getPrices().getMin());
            item.setSavgd(newitem.getPrices().getSold().get("avg_daily_volume"));
            item.sets24h(newitem.getPrices().getSold().get("last_24h"));
            item.sets7d(newitem.getPrices().getSold().get("last_7d"));
            item.sets30d(newitem.getPrices().getSold().get("last_30d"));
            item.sets90d(newitem.getPrices().getSold().get("last_90d"));
            item.setNid(newitem.getNameID());
        }

        if (cfPrice != null && cfPrice > 0) {
            item.setCfp(cfPrice);
        }
        if (ioPrice != null && ioPrice > 0) {
            item.setIop(ioPrice);
        }
        try {
            if (cfPrice != null && cfPrice > 0) {
                Double sp =  null;
                if (newitem.getPrices().getSafe() != null) {
                    sp = newitem.getPrices().getSafe();
                }
                if (sp != null && sp > 0) {
                    long diff = Math.round((sp - cfPrice) / sp * 100);
                    item.setDcx((double) diff);
                }
            }
        } catch (Exception ex) {
            log.error("Failed to calc diff {}", ex.getMessage());
        }
        try {
            if (opPrice != null && opPrice > 0) {
                Double sp =  null;
                if (newitem.getPrices().getSafe() != null) {
                    sp = newitem.getPrices().getSafe();
                }
                if (sp != null && sp > 0) {
                    long diff = Math.round((sp - opPrice) / sp * 100);
                    item.setDopx((double) diff);
                }
            }
        } catch (Exception ex) {
            log.error("Failed to calc dopx diff {}", ex.getMessage());
        }

        try {
            Double sp =  null;
            Integer vol30 = null;
            Integer opq = null;
            if (newitem.getPrices().getSafe() != null && newitem.getPrices().getSafe() > 0) {
                sp = newitem.getPrices().getSafe();
            }
            if (newitem.getPrices().getSold() != null && newitem.getPrices().getSold().get("last_30d") > 0) {
                vol30 = newitem.getPrices().getSold().get("last_30d");
            }
            if (opQty != null && opQty > 0) {
                opq = opQty;
            }
            if (sp != null && vol30 != null && opq != null) {
                int rank = (int)(sp * vol30 * opq);
                item.setRank(rank);
            }
        } catch (Exception ex) {
            log.error("Failed to calc rank {}", ex.getMessage());
        }

        if (opPrice != null && opPrice > 0) {
            item.setOplp(opPrice);
        }
        if (opQty != null && opQty > 0) {
            item.setOpq(opQty);
        }

        item.setUat(newitem.getUpdated_at());
    }
    private RestTemplate restTemplate() {
        return csgoXyzService.restTemplate();
    }

    private HashMap<String, Pubgitem> existingItems() {
        HashMap<String, Pubgitem> map = new HashMap<>();
        List<Pubgitem> items = pubgItemService.findAll();
        for (Pubgitem item : items) {
            map.put(item.getName(), item);
        }
        return map;
    }

    public Map<String, Object> cfPriceData(RestTemplate restTemplate, HttpHeaders headers) {
        final String CF_API_URL = "https://api.dapubg.com/price/all";
        HttpEntity<String> entityCF = new HttpEntity<>("parameters", headers);
        ResponseEntity<Map> respEntityCF;
        Map<String, Object> cfresp = new HashMap<>();
        try {
            respEntityCF = restTemplate.exchange(CF_API_URL, HttpMethod.GET, entityCF, Map.class);
            cfresp = respEntityCF.getBody();
        } catch (Exception ex) {
            log.error("Failed to fetch CF data", ex.getMessage());
        }
        return cfresp;
    }

    private Map<String, String> ioPriceData(RestTemplate restTemplate, HttpHeaders headers) {
        final String IO_API_URL = "https://api.steamapi.io/market/prices/578080?key=" + IO_API_KEY;
        HttpEntity<String> entityIO = new HttpEntity<>("parameters", headers);
        ResponseEntity<Map> respEntityIO;
        Map<String, String> ioresp = new HashMap<>();
        try {
            respEntityIO = restTemplate.exchange(IO_API_URL, HttpMethod.GET, entityIO, Map.class);
            ioresp = respEntityIO.getBody();
        } catch (Exception ex) {
            log.error("Failed to fetch CF data", ex.getMessage());
        }
        return ioresp;
    }

    private OpskinsDTO opPriceData(RestTemplate restTemplate, HttpHeaders headers) {
        final String OP_API_URL = "https://api.opskins.com/IPricing/GetAllLowestListPrices/v1/?appid=578080&key=" + OP_API_KEY;
        HttpEntity<String> entityOP = new HttpEntity<>("parameters", headers);
        ResponseEntity<OpskinsDTO> respEntityOP;
        OpskinsDTO opresp = null;
        try {
            respEntityOP = restTemplate.exchange(OP_API_URL, HttpMethod.GET, entityOP, OpskinsDTO.class);
            opresp = respEntityOP.getBody();
        } catch (Exception ex) {
            log.error("Failed to fetch OPSkins data", ex.getMessage());
        }
        return opresp;
    }

    private SaItem findSaItem(List<SaItem> items, String name) {
        SaItem item = null;
        for(SaItem i : items) {
            if (i.getMarket_hash_name().equals(name)) {
                item = i;
                break;
            }
        }
        return  item;
    }
}
