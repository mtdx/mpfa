package com.mma.mma.service.respdto;


import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;

;

/**
 * A DTO for the csgo.steamlytics.xyz response.
 */
public class XyzDTO implements Serializable {

    private boolean success;

    private HashMap<String, XyzItem> items;

    private int build_time;

    private Instant updated_at;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public HashMap<String, XyzItem> getItems() {
        return items;
    }

    public void setItems(HashMap<String, XyzItem> items) {
        this.items = items;
    }

    public int getBuild_time() {
        return build_time;
    }

    public void setBuild_time(int build_time) {
        this.build_time = build_time;
    }

    public Instant getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Instant updated_at) {
        this.updated_at = updated_at;
    }
}
