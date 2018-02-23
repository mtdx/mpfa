package com.mma.mma.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mma.mma.service.PubgitemService;
import com.mma.mma.web.rest.util.HeaderUtil;
import com.mma.mma.web.rest.util.PaginationUtil;
import com.mma.mma.service.dto.PubgitemDTO;
import com.mma.mma.service.dto.PubgitemCriteria;
import com.mma.mma.service.PubgitemQueryService;
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
import java.net.URI;
import java.net.URISyntaxException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Pubgitem.
 */
@RestController
@RequestMapping("/api")
public class PubgitemResource {

    private final Logger log = LoggerFactory.getLogger(PubgitemResource.class);

    private static final String ENTITY_NAME = "pubgitem";

    private final PubgitemService pubgitemService;

    private final PubgitemQueryService pubgitemQueryService;

    @Value("${NS_API_KEY}")
    private String NS_API_KEY;

    public PubgitemResource(PubgitemService pubgitemService, PubgitemQueryService pubgitemQueryService) {
        this.pubgitemService = pubgitemService;
        this.pubgitemQueryService = pubgitemQueryService;
    }

    /**
     * POST  /pubgitems : Create a new pubgitem.
     *
     * @param pubgitemDTO the pubgitemDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pubgitemDTO, or with status 400 (Bad Request) if the pubgitem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
//    @PostMapping("/pubgitems")
//    @Timed
//    public ResponseEntity<PubgitemDTO> createPubgitem(@Valid @RequestBody PubgitemDTO pubgitemDTO) throws URISyntaxException {
//        log.debug("REST request to save Pubgitem : {}", pubgitemDTO);
//        if (pubgitemDTO.getId() != null) {
//            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new pubgitem cannot already have an ID")).body(null);
//        }
//        PubgitemDTO result = pubgitemService.save(pubgitemDTO);
//        return ResponseEntity.created(new URI("/api/pubgitems/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }

    /**
     * PUT  /pubgitems : Updates an existing pubgitem.
     *
     * @param pubgitemDTO the pubgitemDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pubgitemDTO,
     * or with status 400 (Bad Request) if the pubgitemDTO is not valid,
     * or with status 500 (Internal Server Error) if the pubgitemDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
//    @PutMapping("/pubgitems")
//    @Timed
//    public ResponseEntity<PubgitemDTO> updatePubgitem(@Valid @RequestBody PubgitemDTO pubgitemDTO) throws URISyntaxException {
//        log.debug("REST request to update Pubgitem : {}", pubgitemDTO);
//        if (pubgitemDTO.getId() == null) {
//            return createPubgitem(pubgitemDTO);
//        }
//        PubgitemDTO result = pubgitemService.save(pubgitemDTO);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pubgitemDTO.getId().toString()))
//            .body(result);
//    }

    /**
     * GET  /pubgitems : get all the pubgitems.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of pubgitems in body
     */
    @GetMapping("/pubgitems")
    @Timed
    public ResponseEntity<List<PubgitemDTO>> getAllPubgitems(PubgitemCriteria criteria,@ApiParam Pageable pageable) {
        log.debug("REST request to get Pubgitems by criteria: {}", criteria);
        Page<PubgitemDTO> page = pubgitemQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pubgitems");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /pubgitems/:id : get the "id" pubgitem.
     *
     * @param id the id of the pubgitemDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pubgitemDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pubgitems/{id}")
    @Timed
    public ResponseEntity<PubgitemDTO> getPubgitem(@PathVariable Long id) {
        log.debug("REST request to get Pubgitem : {}", id);
        PubgitemDTO pubgitemDTO = pubgitemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(pubgitemDTO));
    }

    /**
     * DELETE  /pubgitems/:id : delete the "id" pubgitem.
     *
     * @param id the id of the pubgitemDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
//    @DeleteMapping("/pubgitems/{id}")
//    @Timed
//    public ResponseEntity<Void> deletePubgitem(@PathVariable Long id) {
//        log.debug("REST request to delete Pubgitem : {}", id);
//        pubgitemService.delete(id);
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
//    }

    /**
     * SEARCH  /_search/pubgitems?query=:query : search for the pubgitem corresponding
     * to the query.
     *
     * @param query the query of the pubgitem search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/pubgitems")
    @Timed
    public ResponseEntity<List<PubgitemDTO>> searchPubgitems(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of Pubgitems for query {}", query);
        Page<PubgitemDTO> page = pubgitemService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/pubgitems");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /pubg-items : get all the deposit csgoItems.
     *
     * @return the prices list with status 200 (OK) and 400 if missing api key
     */
    @GetMapping("/pubg-deposit-items")
    @Timed
    public ResponseEntity<HashMap<String, Double>> getAllDepositCsgoItems(@RequestParam String apiKey) {
        log.debug("REST request to get All Pubg for deposit: {}");
        if (!apiKey.equals(NS_API_KEY)) {
            log.error("REST request to get All Pubg for deposit: {}");
            return ResponseEntity.badRequest().body(null);
        }
        HashMap<String, Double> items = pubgitemService.findAllForDeposit();
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(items, headers, HttpStatus.OK);
    }

    /**
     * GET  /pubg-items-placeholder : get all the deposit csgoItems.
     *
     * @return the prices list with status 200 (OK) and 400 if missing api key
     */
    @GetMapping("/pubg-items-placeholder")
    @Timed
    public ResponseEntity<HashMap<String, Double>> getAllPlaceholderCsgoItems(@RequestParam String apiKey) {
        log.debug("REST request to get All Pubg for placeholder: {}");
        if (!apiKey.equals(NS_API_KEY)) {
            log.error("REST request to get All Pubg for placeholder: {}");
            return ResponseEntity.badRequest().body(null);
        }
        HashMap<String, Double> items = pubgitemService.findAllForPlaceholder();
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(items, headers, HttpStatus.OK);
    }
}
