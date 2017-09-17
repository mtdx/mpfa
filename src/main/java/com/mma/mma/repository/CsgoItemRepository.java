package com.mma.mma.repository;

import com.mma.mma.domain.CsgoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the CsgoItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CsgoItemRepository extends JpaRepository<CsgoItem, Long>, JpaSpecificationExecutor<CsgoItem> {

}
