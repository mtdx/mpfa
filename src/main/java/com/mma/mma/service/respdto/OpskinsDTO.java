package com.mma.mma.service.respdto;


import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;

/**
 * A DTO for the opskins response.
 */
public class OpskinsDTO implements Serializable {

    private int status;

    private HashMap<String, OpskinsItem> response;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public HashMap<String, OpskinsItem> getResponse() {
        return response;
    }

    public void setResponse(HashMap<String, OpskinsItem> response) {
        this.response = response;
    }
}
