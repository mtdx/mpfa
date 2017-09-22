package com.mma.mma.service.dto;


import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the CsgoItem entity.
 */
public class CsgoItemPriceDTO implements Serializable {

    @NotNull
    private String name;

    @NotNull
    private BigDecimal sp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSp() {
        return sp;
    }

    public void setSp(BigDecimal sp) {
        this.sp = sp;
    }
}
