package com.mma.mma.service.respdto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the api.steamapis.com response item.
 */
public class SaItem implements Serializable {

    private Instant updated_at;

    private SaPrices prices;


    public Instant getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Instant updated_at) {
        this.updated_at = updated_at;
    }

    public SaPrices getPrices() {
        return prices;
    }

    public void setPrices(SaPrices prices) {
        this.prices = prices;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMarket_hash_name() {
        return market_hash_name;
    }

    public void setMarket_hash_name(String market_hash_name) {
        this.market_hash_name = market_hash_name;
    }

    public String getMarket_name() {
        return market_name;
    }

    public void setMarket_name(String market_name) {
        this.market_name = market_name;
    }

    public String getNameID() {
        return nameID;
    }

    public void setNameID(String nameID) {
        this.nameID = nameID;
    }

    public Object getBorder_color() {
        return border_color;
    }

    public void setBorder_color(Object border_color) {
        this.border_color = border_color;
    }

    private String image;

    @NotNull
    private String market_hash_name;

    private String market_name;

    private String nameID;

    private Object border_color;
}
