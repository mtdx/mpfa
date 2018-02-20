package com.mma.mma.repository;

import com.mma.mma.domain.Pubgitem;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Pubgitem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PubgitemRepository extends JpaRepository<Pubgitem, Long>, JpaSpecificationExecutor<Pubgitem> {

}
