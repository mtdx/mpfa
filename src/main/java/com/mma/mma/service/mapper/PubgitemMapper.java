package com.mma.mma.service.mapper;

import com.mma.mma.domain.*;
import com.mma.mma.service.dto.PubgitemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Pubgitem and its DTO PubgitemDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PubgitemMapper extends EntityMapper <PubgitemDTO, Pubgitem> {
    
    
    default Pubgitem fromId(Long id) {
        if (id == null) {
            return null;
        }
        Pubgitem pubgitem = new Pubgitem();
        pubgitem.setId(id);
        return pubgitem;
    }
}
