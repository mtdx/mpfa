package com.mma.mma.service.respdto;

import java.io.Serializable;
import java.util.HashMap;

/**
 * A DTO for the api.steamapis.com response item.
 */
public class SaPrices implements Serializable {
    private HashMap<String, Integer> sold;

    private HashMap<String, Double> safe_ts;

    private Double safe;

    private Double mean;

    private Double max;

    private Double avg;

    private Double min;

    private Double latest;

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
}
