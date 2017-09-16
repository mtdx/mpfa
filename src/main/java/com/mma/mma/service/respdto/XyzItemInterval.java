package com.mma.mma.service.respdto;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the csgo.steamlytics.xyz response item.
 */
public class XyzItemInterval implements Serializable {

    private BigDecimal median_price;

    private BigDecimal median_net_price;

    private BigDecimal average_price;

    private BigDecimal average_net_price;

    private BigDecimal lowest_price;

    private BigDecimal lowest_net_price;

    private BigDecimal highest_price;

    private BigDecimal highest_net_price;

    private BigDecimal mean_absolute_deviation;

    private BigDecimal deviation_percentage;

    private BigDecimal trend;

    private int volume;

    public BigDecimal getMedian_price() {
        return median_price;
    }

    public void setMedian_price(BigDecimal median_price) {
        this.median_price = median_price;
    }

    public BigDecimal getMedian_net_price() {
        return median_net_price;
    }

    public void setMedian_net_price(BigDecimal median_net_price) {
        this.median_net_price = median_net_price;
    }

    public BigDecimal getAverage_price() {
        return average_price;
    }

    public void setAverage_price(BigDecimal average_price) {
        this.average_price = average_price;
    }

    public BigDecimal getAverage_net_price() {
        return average_net_price;
    }

    public void setAverage_net_price(BigDecimal average_net_price) {
        this.average_net_price = average_net_price;
    }

    public BigDecimal getLowest_price() {
        return lowest_price;
    }

    public void setLowest_price(BigDecimal lowest_price) {
        this.lowest_price = lowest_price;
    }

    public BigDecimal getLowest_net_price() {
        return lowest_net_price;
    }

    public void setLowest_net_price(BigDecimal lowest_net_price) {
        this.lowest_net_price = lowest_net_price;
    }

    public BigDecimal getHighest_price() {
        return highest_price;
    }

    public void setHighest_price(BigDecimal highest_price) {
        this.highest_price = highest_price;
    }

    public BigDecimal getHighest_net_price() {
        return highest_net_price;
    }

    public void setHighest_net_price(BigDecimal highest_net_price) {
        this.highest_net_price = highest_net_price;
    }

    public BigDecimal getMean_absolute_deviation() {
        return mean_absolute_deviation;
    }

    public void setMean_absolute_deviation(BigDecimal mean_absolute_deviation) {
        this.mean_absolute_deviation = mean_absolute_deviation;
    }

    public BigDecimal getDeviation_percentage() {
        return deviation_percentage;
    }

    public void setDeviation_percentage(BigDecimal deviation_percentage) {
        this.deviation_percentage = deviation_percentage;
    }

    public BigDecimal getTrend() {
        return trend;
    }

    public void setTrend(BigDecimal trend) {
        this.trend = trend;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
