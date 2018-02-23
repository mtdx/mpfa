package com.mma.mma.service;

import com.mma.mma.domain.Pubgitem;
import com.mma.mma.repository.PubgitemRepository;
import com.mma.mma.repository.search.PubgitemSearchRepository;
import com.mma.mma.service.dto.PubgitemDTO;
import com.mma.mma.service.mapper.PubgitemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashMap;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Pubgitem.
 */
@Service
@Transactional
public class PubgitemService {

    private final Logger log = LoggerFactory.getLogger(PubgitemService.class);

    private final PubgitemRepository pubgitemRepository;

    private final PubgitemMapper pubgitemMapper;

    private final PubgitemSearchRepository pubgitemSearchRepository;

    public PubgitemService(PubgitemRepository pubgitemRepository, PubgitemMapper pubgitemMapper, PubgitemSearchRepository pubgitemSearchRepository) {
        this.pubgitemRepository = pubgitemRepository;
        this.pubgitemMapper = pubgitemMapper;
        this.pubgitemSearchRepository = pubgitemSearchRepository;
    }

    /**
     * Save a pubgitem.
     *
     * @param pubgitemDTO the entity to save
     * @return the persisted entity
     */
    public PubgitemDTO save(PubgitemDTO pubgitemDTO) {
        log.debug("Request to save Pubgitem : {}", pubgitemDTO);
        Pubgitem pubgitem = pubgitemMapper.toEntity(pubgitemDTO);
        pubgitem = pubgitemRepository.save(pubgitem);
        PubgitemDTO result = pubgitemMapper.toDto(pubgitem);
        pubgitemSearchRepository.save(pubgitem);
        return result;
    }

    /**
     *  Get all the pubgitems.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<PubgitemDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Pubgitems");
        return pubgitemRepository.findAll(pageable)
            .map(pubgitemMapper::toDto);
    }


    /**
     *  Get all the pubgitems.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Pubgitem> findAll() {
        log.debug("Request to get all Pubgitems");
        return pubgitemRepository.findAll();
    }

    /**
     *  Get one pubgitem by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public PubgitemDTO findOne(Long id) {
        log.debug("Request to get Pubgitem : {}", id);
        Pubgitem pubgitem = pubgitemRepository.findOne(id);
        return pubgitemMapper.toDto(pubgitem);
    }

    /**
     *  Delete the  pubgitem by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Pubgitem : {}", id);
        pubgitemRepository.delete(id);
        pubgitemSearchRepository.delete(id);
    }

    /**
     * Search for the pubgitem corresponding to the query.
     *
     *  @param query the query of the search
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<PubgitemDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Pubgitems for query {}", query);
        Page<Pubgitem> result = pubgitemSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(pubgitemMapper::toDto);
    }

    /**
     * Refresh/Reindex elastic search
     */
    @Transactional(readOnly = true)
    public void refreshsearch() {
        log.debug("Refresh/Reindex PubgItems elastic search {}");
        pubgitemSearchRepository.refresh();
    }

    /**
     * Get all the csgoItems for deposit list.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public HashMap<String, Double> findAllForDeposit() {
        log.debug("Request to get all CsgoItems for deposit");
        List<Pubgitem> items = pubgitemRepository.findAllForDeposit();
        HashMap<String, Double> prices = new HashMap<>();
        for (Pubgitem item : items) {
            try {
                if (item.getCfp() == null || item.getDcx() == null || item.getDopx() == null) {
                    continue;
                }
                Double sp = item.getSp();
                if (sp != null && sp > 0) {
                    if (sp > 2 && (item.getDcx() > 30 || item.getDopx() > 50)) {
                        continue;
                    }
                    prices.put(item.getName(), sp);
                }
            } catch (Exception ex) {
                log.error("Error adding item to deposit list {}", ex.getMessage());
            }
        }
        return prices;
    }


    /**
     * Get all the csgoItems for deposit list.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public HashMap<String, Double> findAllForPlaceholder() {
        log.debug("Request to get all CsgoItems for Placeholder");
        List<Pubgitem> items = pubgitemRepository.findAllForPlaceholder();
        HashMap<String, Double> prices = new HashMap<>();
        for (Pubgitem item : items) {
            try {
                if (item.getCfp() == null || item.getDcx() == null || item.getDopx() == null) {
                    continue;
                }
                Double sp = item.getSp();
                if (sp != null && sp > 0) {
                    if (sp > 2 && (item.getDcx() < -30 || item.getDopx() < 0)) {
                        continue;
                    }
                    prices.put(item.getName(), sp);
                }
            } catch (Exception ex) {
                log.error("Error adding item to placeholder list {}", ex.getMessage());
            }
        }
        return prices;
    }
}
