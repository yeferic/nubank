package com.yeferic.nubankshorter.domain.model;

public class LinkShorter {
    private String alias;
    private Links links;

    public LinkShorter() {
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
