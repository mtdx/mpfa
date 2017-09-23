package com.mma.mma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mma.mma.domain.CsgoItem;
import com.mma.mma.service.CsgoItemService;
import com.mma.mma.web.rest.util.HeaderUtil;
import com.mma.mma.web.rest.util.PaginationUtil;
import com.mma.mma.service.dto.CsgoItemDTO;
import com.mma.mma.service.dto.CsgoItemCriteria;
import com.mma.mma.service.CsgoItemQueryService;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing CsgoItem.
 */
@RestController
@RequestMapping("/api")
public class CsgoItemResource {

    private final Logger log = LoggerFactory.getLogger(CsgoItemResource.class);

    private static final String ENTITY_NAME = "csgoItem";

    private final CsgoItemService csgoItemService;

    private final CsgoItemQueryService csgoItemQueryService;

    @Value("${NS_API_KEY}")
    private String NS_API_KEY;

    public CsgoItemResource(CsgoItemService csgoItemService, CsgoItemQueryService csgoItemQueryService) {
        this.csgoItemService = csgoItemService;
        this.csgoItemQueryService = csgoItemQueryService;
    }

    /**
     * POST  /csgo-items : Create a new csgoItem.
     *
     * @param csgoItemDTO the csgoItemDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new csgoItemDTO, or with status 400 (Bad Request) if the csgoItem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
//    @PostMapping("/csgo-items")
//    @Timed
//    public ResponseEntity<CsgoItemDTO> createCsgoItem(@Valid @RequestBody CsgoItemDTO csgoItemDTO) throws URISyntaxException {
//        log.debug("REST request to save CsgoItem : {}", csgoItemDTO);
//        if (csgoItemDTO.getId() != null) {
//            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new csgoItem cannot already have an ID")).body(null);
//        }
//        CsgoItemDTO result = csgoItemService.save(csgoItemDTO);
//        CsgoItemDTO result = csgoItemDTO;
//        return ResponseEntity.created(new URI("/api/csgo-items/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }

    /**
     * PUT  /csgo-items : Updates an existing csgoItem.
     *
     * @param csgoItemDTO the csgoItemDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated csgoItemDTO,
     * or with status 400 (Bad Request) if the csgoItemDTO is not valid,
     * or with status 500 (Internal Server Error) if the csgoItemDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
//    @PutMapping("/csgo-items")
//    @Timed
//    public ResponseEntity<CsgoItemDTO> updateCsgoItem(@Valid @RequestBody CsgoItemDTO csgoItemDTO) throws URISyntaxException {
//        log.debug("REST request to update CsgoItem : {}", csgoItemDTO);
//        if (csgoItemDTO.getId() == null) {
//            return createCsgoItem(csgoItemDTO);
//        }
//        CsgoItemDTO result = csgoItemService.save(csgoItemDTO);
//        CsgoItemDTO result = csgoItemDTO;
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, csgoItemDTO.getId().toString()))
//            .body(result);
//    }

    /**
     * GET  /csgo-items : get all the csgoItems.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of csgoItems in body
     */
    @GetMapping("/csgo-items")
    @Timed
    public ResponseEntity<List<CsgoItemDTO>> getAllCsgoItems(CsgoItemCriteria criteria,@ApiParam Pageable pageable) {
        log.debug("REST request to get CsgoItems by criteria: {}", criteria);
        Page<CsgoItemDTO> page = csgoItemQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/csgo-items");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /csgo-items/:id : get the "id" csgoItem.
     *
     * @param id the id of the csgoItemDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the csgoItemDTO, or with status 404 (Not Found)
     */
    @GetMapping("/csgo-items/{id}")
    @Timed
    public ResponseEntity<CsgoItemDTO> getCsgoItem(@PathVariable Long id) {
        log.debug("REST request to get CsgoItem : {}", id);
        CsgoItemDTO csgoItemDTO = csgoItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(csgoItemDTO));
    }

    /**
     * DELETE  /csgo-items/:id : delete the "id" csgoItem.
     *
     * @param id the id of the csgoItemDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
//    @DeleteMapping("/csgo-items/{id}")
//    @Timed
//    public ResponseEntity<Void> deleteCsgoItem(@PathVariable Long id) {
//        log.debug("REST request to delete CsgoItem : {}", id);
//        csgoItemService.delete(id);
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
//    }

    /**
     * SEARCH  /_search/csgo-items?query=:query : search for the csgoItem corresponding
     * to the query.
     *
     * @param query the query of the csgoItem search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/csgo-items")
    @Timed
    public ResponseEntity<List<CsgoItemDTO>> searchCsgoItems(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of CsgoItems for query {}", query);
        Page<CsgoItemDTO> page = csgoItemService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/csgo-items");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /csgo-items : get all the deposit csgoItems.
     *
     * @return the prices list with status 200 (OK) and 400 if missing api key
     */
    @GetMapping("/csgo-deposit-items")
    @Timed
    public ResponseEntity<HashMap<String, Double>> getAllDepositCsgoItems(@RequestParam String apiKey) {
        log.debug("REST request to get All CsgoItems for deposit: {}");
        if (!apiKey.equals(NS_API_KEY)) {
            log.error("REST request to get All CsgoItems for deposit: {}");
            return ResponseEntity.badRequest().body(null);
        }
        HashMap<String, Double> items = csgoItemService.findAllForDeposit();
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(items, headers, HttpStatus.OK);
    }
}
