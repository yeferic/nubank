package com.yeferic.nubankshorter.domain.model;

import com.google.gson.annotations.SerializedName;

public class Links {
    @SerializedName("self")
    private String self;
    @SerializedName("short")
    private String _short;

    public Links() {
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getShort() {
        return _short;
    }

    public void setShort(String _short) {
        this._short = _short;
    }
}
