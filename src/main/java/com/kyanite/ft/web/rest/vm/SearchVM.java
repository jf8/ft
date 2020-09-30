package com.kyanite.ft.web.rest.vm;

public class SearchVM {

    private Long id;
    private String parentNamePath;
    private Long firstLevelId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParentNamePath() {
        return parentNamePath;
    }

    public void setParentNamePath(String parentNamePath) {
        this.parentNamePath = parentNamePath;
    }

    public Long getFirstLevelId() {
        return firstLevelId;
    }

    public void setFirstLevelId(Long firstLevelId) {
        this.firstLevelId = firstLevelId;
    }
}
