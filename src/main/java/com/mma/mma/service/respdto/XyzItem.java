package com.mma.mma.service.respdto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * A DTO for the csgo.steamlytics.xyz response item.
 */
public class XyzItem implements Serializable {

    @NotNull
    private String name;

    private BigDecimal safe_price;

    private BigDecimal safe_net_price;

    private boolean ongoing_price_manipulation;

    private int total_volume;

    private XyzItemInterval _7_days;

    private XyzItemInterval _30_days;

    private XyzItemInterval all_time;

    private Instant first_seen;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSafe_price() {
        return safe_price;
    }

    public void setSafe_price(BigDecimal safe_price) {
        this.safe_price = safe_price;
    }

    public BigDecimal getSafe_net_price() {
        return safe_net_price;
    }

    public void setSafe_net_price(BigDecimal safe_net_price) {
        this.safe_net_price = safe_net_price;
    }

    public boolean isOngoing_price_manipulation() {
        return ongoing_price_manipulation;
    }

    public void setOngoing_price_manipulation(boolean ongoing_price_manipulation) {
        this.ongoing_price_manipulation = ongoing_price_manipulation;
    }

    public int getTotal_volume() {
        return total_volume;
    }

    public void setTotal_volume(int total_volume) {
        this.total_volume = total_volume;
    }

    public XyzItemInterval get7_days() {
        return _7_days;
    }

    public void set7_days(XyzItemInterval _7_days) {
        this._7_days = _7_days;
    }

    public XyzItemInterval get30_days() {
        return _30_days;
    }

    public void set30_days(XyzItemInterval _30_days) {
        this._30_days = _30_days;
    }

    public XyzItemInterval getAll_time() {
        return all_time;
    }

    public void setAll_time(XyzItemInterval all_time) {
        this.all_time = all_time;
    }

    public Instant getFirst_seen() {
        return first_seen;
    }

    public void setFirst_seen(Instant first_seen) {
        this.first_seen = first_seen;
    }
}
