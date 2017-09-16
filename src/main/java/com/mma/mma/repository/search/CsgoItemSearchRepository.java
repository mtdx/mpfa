package com.mma.mma.repository.search;

import com.mma.mma.domain.CsgoItem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the CsgoItem entity.
 */
public interface CsgoItemSearchRepository extends ElasticsearchRepository<CsgoItem, Long> {
}
