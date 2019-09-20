package com.binakarir.binakarirapss.Model;

import com.google.gson.annotations.SerializedName;

public class Model_Event {

    @SerializedName("nama_event")
    private String nama_event;
    @SerializedName("tanggal_event")
    private String Tanggal_event;
    @SerializedName("poster")
    private String poster_event;
    @SerializedName("lokasi_event")
    private String Lokasi_event;

    public Model_Event() {
    }

    public String getLokasi_event() {
        return Lokasi_event;
    }

    public String getNama_event() {
        return nama_event;
    }

    public String getTanggal_event() {
        return Tanggal_event;
    }

    public String getPoster_event() {
        return poster_event;
    }

    public void setNama_event(String nama_event) {
        this.nama_event = nama_event;
    }

    public void setTanggal_event(String tanggal_event) {
        Tanggal_event = tanggal_event;
    }

    public void setPoster_event(String poster_event) {
        this.poster_event = poster_event;
    }

    public void setLokasi_event(String lokasi_event) {
        Lokasi_event = lokasi_event;
    }

}
