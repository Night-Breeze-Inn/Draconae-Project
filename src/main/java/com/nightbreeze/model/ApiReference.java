package com.nightbreeze.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiReference {
    private String index;
    private String name;
    private String url;

    // Getters and Setters
    public String getIndex() { return index; }
    public void setIndex(String index) { this.index = index; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}