package com.mma.mma.service;

import com.mma.mma.domain.CsgoItem;
import com.mma.mma.repository.CsgoItemRepository;
import com.mma.mma.repository.search.CsgoItemSearchRepository;
import com.mma.mma.service.dto.CsgoItemDTO;
import com.mma.mma.service.mapper.CsgoItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * Service Implementation for managing CsgoItem.
 */
@Service
@Transactional
public class CsgoItemService {

    private final Logger log = LoggerFactory.getLogger(CsgoItemService.class);

    private final CsgoItemRepository csgoItemRepository;

    private final CsgoItemMapper csgoItemMapper;

    private final CsgoItemSearchRepository csgoItemSearchRepository;

    public CsgoItemService(CsgoItemRepository csgoItemRepository, CsgoItemMapper csgoItemMapper, CsgoItemSearchRepository csgoItemSearchRepository) {
        this.csgoItemRepository = csgoItemRepository;
        this.csgoItemMapper = csgoItemMapper;
        this.csgoItemSearchRepository = csgoItemSearchRepository;
    }

    /**
     * Save a csgoItem.
     *
     * @param csgoItemDTO the entity to save
     * @return the persisted entity
     */
    public CsgoItemDTO save(CsgoItemDTO csgoItemDTO) {
        log.debug("Request to save CsgoItem : {}", csgoItemDTO);
        CsgoItem csgoItem = csgoItemMapper.toEntity(csgoItemDTO);
        csgoItem = csgoItemRepository.save(csgoItem);
        CsgoItemDTO result = csgoItemMapper.toDto(csgoItem);
        csgoItemSearchRepository.save(csgoItem);
        return result;
    }

    /**
     * Get all the csgoItems.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<CsgoItemDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CsgoItems");
        return csgoItemRepository.findAll(pageable)
            .map(csgoItemMapper::toDto);
    }

    /**
     * Get all the csgoItems.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<CsgoItem> findAll() {
        log.debug("Request to get all CsgoItems");
        return csgoItemRepository.findAll();
    }

    /**
     * Get all the csgoItems for deposit list.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public HashMap<String, Double> findAllForDeposit() {
        log.debug("Request to get all CsgoItems for deposit");
        List<CsgoItem> items = csgoItemRepository.findAllForDeposit();
        HashMap<String, Double> prices = new HashMap<>();
        for (CsgoItem item : items) {
            try {
                if (item.getCfp() == null || item.getDcx() == null) {
                    continue;
                }
                if (nameBlacklisted(item.getName())) {
                    continue;
                }
                BigDecimal sp = null;
                if (item.getAvg7() != null) {
                    sp = item.getAvg7();
                } else if (item.getAvg30() != null) {
                    sp = item.getAvg30();
                } else if (item.getAvgAll() != null) {
                    sp = item.getAvgAll();
                }
                if (sp != null) {
                    if (sp.doubleValue() >= 20 && (item.getDcx() < -25 || item.getDcx() > 25)) {
                        continue;
                    }
                    prices.put(item.getName(), item.getAvg7().doubleValue());
                }
            } catch (Exception ex) {
                log.error("Error adding item to deposit list {}", ex.getMessage());
            }
        }
        return prices;
    }

    /**
     * Get one csgoItem by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public CsgoItemDTO findOne(Long id) {
        log.debug("Request to get CsgoItem : {}", id);
        CsgoItem csgoItem = csgoItemRepository.findOne(id);
        return csgoItemMapper.toDto(csgoItem);
    }

    /**
     * Delete the  csgoItem by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete CsgoItem : {}", id);
        csgoItemRepository.delete(id);
        csgoItemSearchRepository.delete(id);
    }

    /**
     * Delete the all csgo items
     */
    public void deleteAll() {
        log.debug("Request to delete all CsgoItems : {}");
        csgoItemRepository.deleteAll();
        csgoItemSearchRepository.deleteAll();
    }

    /**
     * Search for the csgoItem corresponding to the query.
     *
     * @param query    the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<CsgoItemDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of CsgoItems for query {}", query);
        Page<CsgoItem> result = csgoItemSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(csgoItemMapper::toDto);
    }


    /**
     * Refresh/Reindex elastic search
     */
    @Transactional(readOnly = true)
    public void refreshsearch() {
        log.debug("Refresh/Reindex CsgoItems elastic search {}");
        csgoItemSearchRepository.refresh();
    }

    private boolean nameBlacklisted(String marketName) {
        String name = marketName.toLowerCase();
        return name.contains("music") || name.contains("sticker") || name.contains("graffiti")
            || (!name.contains("case hardened") && !name.contains("case key") && name.contains("case"));
    }
}
