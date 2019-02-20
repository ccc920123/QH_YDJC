package com.scxd.beans.biz;

public class Q02Return {

    private String bmbh;

    private String version;

    private String url;

    private Object description;

    private String dictionary ;

    public String getBmbh() {
        return bmbh;
    }

    public void setBmbh(String bmbh) {
        this.bmbh = bmbh;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public String getDictionary() {
        return dictionary;
    }

    public void setDictionary(String dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public String toString() {
        return "Q02Return{" +
                "bmbh='" + bmbh + '\'' +
                ", version='" + version + '\'' +
                ", url='" + url + '\'' +
                ", description=" + description +
                ", dictionary='" + dictionary + '\'' +
                '}';
    }
}
