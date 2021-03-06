
package com.vogella.android.usinglibs.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InstagramProfileData {

    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("more_available")
    @Expose
    private Boolean moreAvailable;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Boolean getMoreAvailable() {
        return moreAvailable;
    }

    public void setMoreAvailable(Boolean moreAvailable) {
        this.moreAvailable = moreAvailable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
