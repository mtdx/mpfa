package com.mma.mma.service;

import com.mma.mma.service.respdto.XyzDTO;
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

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class CsgoXyzService {

    private final Logger log = LoggerFactory.getLogger(CsgoXyzService.class);

    @Value("${XYZ_API_KEY}")
    private String XYZ_API_KEY;

    /**
     * We check for csgo.steamlytics.xyz item price updates
     * <p>
     * This is scheduled to get fired every 11 minutes.
     * </p>
     */
    @Async
    @Scheduled(cron = "0 */1 * * * *") // 11 TODO:
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
            log.error("Failled to fetch csgo.steamlytics.xyz fata {}", ex.getMessage());
            return;
        }

        XyzDTO xyzresp = respEntity.getBody();
        System.out.println(xyzresp);
    }

    public RestTemplate restTemplate() {
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

}
