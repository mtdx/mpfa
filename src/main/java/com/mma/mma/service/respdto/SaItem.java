package com.mma.mma.service.respdto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;

/**
 * A DTO for the api.steamapis.com response item.
 */
public class SaItem implements Serializable {

    private Instant updated_at;

    private HashMap<String, Object> prices;

    private HashMap<String, Integer> sold;

    private HashMap<String, Double> safe_ts;

    private Double safe;

    private Double mean;

    private Double max;

    private Double avg;

    private Double min;

    private Double latest;

    public Instant getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Instant updated_at) {
        this.updated_at = updated_at;
    }

    public HashMap<String, Object> getPrices() {
        return prices;
    }

    public void setPrices(HashMap<String, Object> prices) {
        this.prices = prices;
    }

    public HashMap<String, Integer> getSold() {
        return sold;
    }

    public void setSold(HashMap<String, Integer> sold) {
        this.sold = sold;
    }

    public HashMap<String, Double> getSafe_ts() {
        return safe_ts;
    }

    public void setSafe_ts(HashMap<String, Double> safe_ts) {
        this.safe_ts = safe_ts;
    }

    public Double getSafe() {
        return safe;
    }

    public void setSafe(Double safe) {
        this.safe = safe;
    }

    public Double getMean() {
        return mean;
    }

    public void setMean(Double mean) {
        this.mean = mean;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getLatest() {
        return latest;
    }

    public void setLatest(Double latest) {
        this.latest = latest;
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
