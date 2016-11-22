package org.datasays.wes.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by watano on 2016/11/19.
 */
public class Sort {
    private String field;
    private String order;
    private String missing;
    @SerializedName("ignore_unmapped")
    private Boolean unmapped;

    public Sort(String field){
        this.field = field;
    }

    public Sort orderAsc(){
        order = "asc";
        return this;
    }

    public Sort orderDesc(){
        order = "desc";
        return this;
    }

    public Sort missingLast(){
        missing = "_last";
        return this;
    }

    public Sort missingFirst(){
        missing = "_first";
        return this;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getMissing() {
        return missing;
    }

    public void setMissing(String missing) {
        this.missing = missing;
    }

    public Boolean getUnmapped() {
        return unmapped;
    }

    public void setUnmapped(Boolean unmapped) {
        this.unmapped = unmapped;
    }
}
