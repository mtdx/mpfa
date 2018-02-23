package com.mma.mma.repository;

import com.mma.mma.domain.Pubgitem;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the Pubgitem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PubgitemRepository extends JpaRepository<Pubgitem, Long>, JpaSpecificationExecutor<Pubgitem> {

    @Query("select pubgitem from Pubgitem pubgitem where pubgitem.sp >= 0.50 ")
    List<Pubgitem> findAllForDeposit();

    @Query("select pubgitem from Pubgitem pubgitem where pubgitem.rank >= 0 and pubgitem.sp >= 5")
    List<Pubgitem> findAllForPlaceholder();
}
