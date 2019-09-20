package com.binakarir.binakarirapss.Model;

public class Model_Artikel {
    public String getNama_artikel() {
        return nama_artikel;
    }

    public void setNama_artikel(String nama_artikel) {
        this.nama_artikel = nama_artikel;
    }

    public String getGambar_artikel() {
        return gambar_artikel;
    }

    public void setGambar_artikel(String gambar_artikel) {
        this.gambar_artikel = gambar_artikel;
    }

    public String getLink_artikel() {
        return link_artikel;
    }

    public void setLink_artikel(String link_artikel) {
        this.link_artikel = link_artikel;
    }

    private String nama_artikel;
    private String gambar_artikel;
    private String link_artikel;
}
