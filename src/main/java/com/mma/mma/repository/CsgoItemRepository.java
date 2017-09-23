package com.mma.mma.repository;

import com.mma.mma.domain.CsgoItem;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the CsgoItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CsgoItemRepository extends JpaRepository<CsgoItem, Long>, JpaSpecificationExecutor<CsgoItem> {

    @Query("select csgoItem from CsgoItem csgoItem where " +
        "csgoItem.sp > 0 and " +
        "csgoItem.opm = false and " +
        "csgoItem.vol >= 50 and " +
        "csgoItem.vol30 >= 5")
    List<CsgoItem> findAllForDeposit();
}
