package com.binakarir.binakarirapss.Model;

import com.google.gson.annotations.SerializedName;

public class BrandingModel {
    @SerializedName("nama_branding")
    private String nama_branding;
    @SerializedName("desc_branding")
    private String desc_branding;
    @SerializedName("foto_branding")
    private String foto_branding;

    public String getNama_branding() {
        return nama_branding;
    }

    public void setNama_branding(String nama_branding) {
        this.nama_branding = nama_branding;
    }

    public String getDesc_branding() {
        return desc_branding;
    }

    public void setDesc_branding(String desc_branding) {
        this.desc_branding = desc_branding;
    }

    public String getFoto_branding() {
        return foto_branding;
    }

    public void setFoto_branding(String foto_branding) {
        this.foto_branding = foto_branding;
    }
}
