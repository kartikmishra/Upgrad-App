package com.example.upgradapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TagsItem {

    @SerializedName("has_synonyms")
    @Expose
    private Boolean hasSynonyms;
    @SerializedName("is_moderator_only")
    @Expose
    private Boolean isModeratorOnly;
    @SerializedName("is_required")
    @Expose
    private Boolean isRequired;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("name")
    @Expose
    private String name;

    private boolean isSelected = false;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Boolean getHasSynonyms() {
        return hasSynonyms;
    }

    public void setHasSynonyms(Boolean hasSynonyms) {
        this.hasSynonyms = hasSynonyms;
    }

    public Boolean getIsModeratorOnly() {
        return isModeratorOnly;
    }

    public void setIsModeratorOnly(Boolean isModeratorOnly) {
        this.isModeratorOnly = isModeratorOnly;
    }

    public Boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
