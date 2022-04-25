package com.yeferic.nubankshorter.domain.model;

import com.google.gson.annotations.SerializedName;

public class LinkShorter {
    @SerializedName("alias")
    private String alias;
    @SerializedName("_links")
    private Links links;

    public LinkShorter() {
    }

    public LinkShorter(String alias, Links links) {
        this.alias = alias;
        this.links = links;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }
}
