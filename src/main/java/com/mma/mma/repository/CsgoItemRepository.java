package com.mma.mma.repository;

import com.mma.mma.domain.CsgoItem;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CsgoItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CsgoItemRepository extends JpaRepository<CsgoItem, Long>, JpaSpecificationExecutor<CsgoItem> {

}
