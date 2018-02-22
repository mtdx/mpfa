package com.mma.mma.service.respdto;


import java.io.Serializable;
import java.time.Instant;
import java.util.List;

;

/**
 * A DTO for the csgo.steamlytics.xyz response.
 */
public class SaDTO implements Serializable {

    private String appID;

    private List<SaItem> data;

    private Integer __v;

    private Instant createdAt;

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public List<SaItem> getData() {
        return data;
    }

    public void setData(List<SaItem> data) {
        this.data = data;
    }

    public Integer get__v() {
        return __v;
    }

    public void set__v(Integer __v) {
        this.__v = __v;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
