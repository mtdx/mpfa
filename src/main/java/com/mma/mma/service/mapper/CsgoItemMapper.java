package com.mma.mma.service.mapper;

import com.mma.mma.domain.*;
import com.mma.mma.service.dto.CsgoItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CsgoItem and its DTO CsgoItemDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CsgoItemMapper extends EntityMapper <CsgoItemDTO, CsgoItem> {
    
    
    default CsgoItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        CsgoItem csgoItem = new CsgoItem();
        csgoItem.setId(id);
        return csgoItem;
    }
}
