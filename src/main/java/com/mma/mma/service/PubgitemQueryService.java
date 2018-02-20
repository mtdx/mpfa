package com.mma.mma.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.mma.mma.domain.Pubgitem;
import com.mma.mma.domain.*; // for static metamodels
import com.mma.mma.repository.PubgitemRepository;
import com.mma.mma.repository.search.PubgitemSearchRepository;
import com.mma.mma.service.dto.PubgitemCriteria;

import com.mma.mma.service.dto.PubgitemDTO;
import com.mma.mma.service.mapper.PubgitemMapper;

/**
 * Service for executing complex queries for Pubgitem entities in the database.
 * The main input is a {@link PubgitemCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {%link PubgitemDTO} or a {@link Page} of {%link PubgitemDTO} which fulfills the criterias
 */
@Service
@Transactional(readOnly = true)
public class PubgitemQueryService extends QueryService<Pubgitem> {

    private final Logger log = LoggerFactory.getLogger(PubgitemQueryService.class);


    private final PubgitemRepository pubgitemRepository;

    private final PubgitemMapper pubgitemMapper;

    private final PubgitemSearchRepository pubgitemSearchRepository;

    public PubgitemQueryService(PubgitemRepository pubgitemRepository, PubgitemMapper pubgitemMapper, PubgitemSearchRepository pubgitemSearchRepository) {
        this.pubgitemRepository = pubgitemRepository;
        this.pubgitemMapper = pubgitemMapper;
        this.pubgitemSearchRepository = pubgitemSearchRepository;
    }

    /**
     * Return a {@link List} of {%link PubgitemDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PubgitemDTO> findByCriteria(PubgitemCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Pubgitem> specification = createSpecification(criteria);
        return pubgitemMapper.toDto(pubgitemRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {%link PubgitemDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PubgitemDTO> findByCriteria(PubgitemCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Pubgitem> specification = createSpecification(criteria);
        final Page<Pubgitem> result = pubgitemRepository.findAll(specification, page);
        return result.map(pubgitemMapper::toDto);
    }

    /**
     * Function to convert PubgitemCriteria to a {@link Specifications}
     */
    private Specifications<Pubgitem> createSpecification(PubgitemCriteria criteria) {
        Specifications<Pubgitem> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Pubgitem_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Pubgitem_.name));
            }
            if (criteria.getUns() != null) {
                specification = specification.and(buildSpecification(criteria.getUns(), Pubgitem_.uns));
            }
            if (criteria.getUnsr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUnsr(), Pubgitem_.unsr));
            }
            if (criteria.getRank() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRank(), Pubgitem_.rank));
            }
            if (criteria.getSp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSp(), Pubgitem_.sp));
            }
            if (criteria.getMeanp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMeanp(), Pubgitem_.meanp));
            }
            if (criteria.getMaxp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxp(), Pubgitem_.maxp));
            }
            if (criteria.getAvgp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAvgp(), Pubgitem_.avgp));
            }
            if (criteria.getMinp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMinp(), Pubgitem_.minp));
            }
            if (criteria.getLp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLp(), Pubgitem_.lp));
            }
            if (criteria.getSavgd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSavgd(), Pubgitem_.savgd));
            }
            if (criteria.gets24h() != null) {
                specification = specification.and(buildRangeSpecification(criteria.gets24h(), Pubgitem_.s24h));
            }
            if (criteria.gets7d() != null) {
                specification = specification.and(buildRangeSpecification(criteria.gets7d(), Pubgitem_.s7d));
            }
            if (criteria.gets30d() != null) {
                specification = specification.and(buildRangeSpecification(criteria.gets30d(), Pubgitem_.s30d));
            }
            if (criteria.gets90d() != null) {
                specification = specification.and(buildRangeSpecification(criteria.gets90d(), Pubgitem_.s90d));
            }
            if (criteria.getNid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNid(), Pubgitem_.nid));
            }
            if (criteria.getUat() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUat(), Pubgitem_.uat));
            }
        }
        return specification;
    }

}
