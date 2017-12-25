package com.mma.mma.service;


import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.mma.mma.domain.CsgoItem;
import com.mma.mma.domain.*; // for static metamodels
import com.mma.mma.repository.CsgoItemRepository;
import com.mma.mma.repository.search.CsgoItemSearchRepository;
import com.mma.mma.service.dto.CsgoItemCriteria;

import com.mma.mma.service.dto.CsgoItemDTO;
import com.mma.mma.service.mapper.CsgoItemMapper;

/**
 * Service for executing complex queries for CsgoItem entities in the database.
 * The main input is a {@link CsgoItemCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {%link CsgoItemDTO} or a {@link Page} of {%link CsgoItemDTO} which fulfills the criterias
 */
@Service
@Transactional(readOnly = true)
public class CsgoItemQueryService extends QueryService<CsgoItem> {

    private final Logger log = LoggerFactory.getLogger(CsgoItemQueryService.class);


    private final CsgoItemRepository csgoItemRepository;

    private final CsgoItemMapper csgoItemMapper;

    private final CsgoItemSearchRepository csgoItemSearchRepository;

    public CsgoItemQueryService(CsgoItemRepository csgoItemRepository, CsgoItemMapper csgoItemMapper, CsgoItemSearchRepository csgoItemSearchRepository) {
        this.csgoItemRepository = csgoItemRepository;
        this.csgoItemMapper = csgoItemMapper;
        this.csgoItemSearchRepository = csgoItemSearchRepository;
    }

    /**
     * Return a {@link List} of {%link CsgoItemDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CsgoItemDTO> findByCriteria(CsgoItemCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<CsgoItem> specification = createSpecification(criteria);
        return csgoItemMapper.toDto(csgoItemRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {%link CsgoItemDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CsgoItemDTO> findByCriteria(CsgoItemCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<CsgoItem> specification = createSpecification(criteria);
        final Page<CsgoItem> result = csgoItemRepository.findAll(specification, page);
        return result.map(csgoItemMapper::toDto);
    }

    /**
     * Function to convert CsgoItemCriteria to a {@link Specifications}
     */
    private Specifications<CsgoItem> createSpecification(CsgoItemCriteria criteria) {
        Specifications<CsgoItem> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CsgoItem_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), CsgoItem_.name));
            }
            if (criteria.getSp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSp(), CsgoItem_.sp));
            }
            if (criteria.getOpm() != null) {
                specification = specification.and(buildSpecification(criteria.getOpm(), CsgoItem_.opm));
            }
            if (criteria.getVol() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVol(), CsgoItem_.vol));
            }
            if (criteria.getMp7() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMp7(), CsgoItem_.mp7));
            }
            if (criteria.getAvg7() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAvg7(), CsgoItem_.avg7));
            }
            if (criteria.getLp7() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLp7(), CsgoItem_.lp7));
            }
            if (criteria.getHp7() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHp7(), CsgoItem_.hp7));
            }
            if (criteria.getMad7() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMad7(), CsgoItem_.mad7));
            }
            if (criteria.getDp7() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDp7(), CsgoItem_.dp7));
            }
            if (criteria.getTrend7() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrend7(), CsgoItem_.trend7));
            }
            if (criteria.getVol7() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVol7(), CsgoItem_.vol7));
            }
            if (criteria.getMp30() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMp30(), CsgoItem_.mp30));
            }
            if (criteria.getAvg30() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAvg30(), CsgoItem_.avg30));
            }
            if (criteria.getLp30() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLp30(), CsgoItem_.lp30));
            }
            if (criteria.getHp30() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHp30(), CsgoItem_.hp30));
            }
            if (criteria.getMad30() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMad30(), CsgoItem_.mad30));
            }
            if (criteria.getDp30() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDp30(), CsgoItem_.dp30));
            }
            if (criteria.getTrend30() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrend30(), CsgoItem_.trend30));
            }
            if (criteria.getVol30() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVol30(), CsgoItem_.vol30));
            }
            if (criteria.getMpAll() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMpAll(), CsgoItem_.mpAll));
            }
            if (criteria.getAvgAll() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAvgAll(), CsgoItem_.avgAll));
            }
            if (criteria.getLpAll() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLpAll(), CsgoItem_.lpAll));
            }
            if (criteria.getHpAll() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHpAll(), CsgoItem_.hpAll));
            }
            if (criteria.getMadAll() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMadAll(), CsgoItem_.madAll));
            }
            if (criteria.getDpAll() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDpAll(), CsgoItem_.dpAll));
            }
            if (criteria.getTrendAll() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrendAll(), CsgoItem_.trendAll));
            }
            if (criteria.getVolAll() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVolAll(), CsgoItem_.volAll));
            }
            if (criteria.getCfp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCfp(), CsgoItem_.cfp));
            }
            if (criteria.getIop() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIop(), CsgoItem_.iop));
            }
            if (criteria.getDcx() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDcx(), CsgoItem_.dcx));
            }
            if (criteria.getDopx() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDopx(), CsgoItem_.dopx));
            }
            if (criteria.getOplp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOplp(), CsgoItem_.oplp));
            }
            if (criteria.getOpq() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOpq(), CsgoItem_.opq));
            }
            if (criteria.getD() != null) {
                specification = specification.and(buildSpecification(criteria.getD(), CsgoItem_.d));
            }
            if (criteria.getAdded() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAdded(), CsgoItem_.added));
            }
        }
        return specification;
    }

}
