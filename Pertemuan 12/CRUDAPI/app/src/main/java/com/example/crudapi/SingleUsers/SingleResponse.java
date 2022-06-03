
package com.example.crudapi.SingleUsers;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class SingleResponse {

    @SerializedName("data")
    private DataItemSingle mData;
    @SerializedName("support")
    private Support mSupport;

    public DataItemSingle getData() {
        return mData;
    }

    public void setData(DataItemSingle data) {
        mData = data;
    }

    public Support getSupport() {
        return mSupport;
    }

    public void setSupport(Support support) {
        mSupport = support;
    }

}
