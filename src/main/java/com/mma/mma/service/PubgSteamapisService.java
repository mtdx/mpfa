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
public class PubgSteamapisService {

    @Value("${SA_API_KEY}")
    private String SA_API_KEY;

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
    @Scheduled(cron = "0 */1 * * * *") // 10 TODO
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

        if (saresp.getItems().length == 0) {
            log.error("Failed to fetch api.steamapis.com fata {}");
            return;
        }

        Pubgitem item;
        SaItem[] saitems = saresp.getItems();
        Map<String, Object> cfPriceData = cfPriceData(restTemplate, headers);
        Map<String, String> ioPriceData = csgoXyzService.ioPriceData(restTemplate, headers);
        OpskinsDTO opPriceData = csgoXyzService.opPriceData(restTemplate, headers);

        for (SaItem saItem : saitems) {
            if (existingItems.containsKey(saItem.getMarket_hash_name())) {
                item = existingItems.get(saItem.getMarket_hash_name());
            } else {
                item = new Pubgitem();
                item.setName(saItem.getMarket_hash_name());
            }
        }
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

}
