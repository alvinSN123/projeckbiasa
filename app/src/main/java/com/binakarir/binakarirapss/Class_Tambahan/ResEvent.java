package com.binakarir.binakarirapss.Class_Tambahan;

import com.bk.binakarir.binakarir.Model.BrandingModel;
import com.bk.binakarir.binakarir.Model.Model_Event;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResEvent {

    @SerializedName("result")
    private ArrayList<Model_Event> listResult;
//    @SerializedName("status_code")
//    private String status_code;
//    @SerializedName("message")
//    private String message;

    public ArrayList<Model_Event> getResult() {
        return listResult;
    }

    public void setListEvent(ArrayList<Model_Event> listEvent) {
        this.listResult = listResult;
    }
}
