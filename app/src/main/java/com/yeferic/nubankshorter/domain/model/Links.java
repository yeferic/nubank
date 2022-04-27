package com.yeferic.nubankshorter.domain.model;

import com.google.gson.annotations.SerializedName;

public class Links {
    @SerializedName("self")
    private String self;
    @SerializedName("short")
    private String url;

    public Links() {
    }

    public Links(String self, String url) {
        this.self = self;
        this.url = url;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String _short) {
        this.url = _short;
    }
}
