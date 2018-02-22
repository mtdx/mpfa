package com.mma.mma.service.respdto;


import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;

;

/**
 * A DTO for the csgo.steamlytics.xyz response.
 */
public class SaDTO implements Serializable {

    private String appID;

    private SaItem[] items;

    private Integer __v;

    private Instant created_at;

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public SaItem[] getItems() {
        return items;
    }

    public void setItems(SaItem[] items) {
        this.items = items;
    }

    public Integer get__v() {
        return __v;
    }

    public void set__v(Integer __v) {
        this.__v = __v;
    }

    public Instant getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Instant created_at) {
        this.created_at = created_at;
    }
}
