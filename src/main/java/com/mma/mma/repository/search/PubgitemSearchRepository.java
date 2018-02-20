package com.mma.mma.repository.search;

import com.mma.mma.domain.Pubgitem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Pubgitem entity.
 */
public interface PubgitemSearchRepository extends ElasticsearchRepository<Pubgitem, Long> {
}
