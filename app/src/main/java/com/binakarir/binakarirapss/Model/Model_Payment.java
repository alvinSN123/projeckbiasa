package com.binakarir.binakarirapss.Model;

public class Model_Payment {
    private String email_payment;
    private String atasnama_payment;
    private String jumlah_transfer_payment;
    private String date_time_payment;
    private String payment_method;
    private String notes_payment;
    private String gambar_pembuktian_payment;

    public void setEmail_payment(String email_payment) {
        this.email_payment = email_payment;
    }

    public void setAtasnama_payment(String atasnama_payment) {
        this.atasnama_payment = atasnama_payment;
    }

    public void setJumlah_transfer_payment(String jumlah_transfer_payment) {
        this.jumlah_transfer_payment = jumlah_transfer_payment;
    }

    public void setDate_time_payment(String date_time_payment) {
        this.date_time_payment = date_time_payment;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public void setNotes_payment(String notes_payment) {
        this.notes_payment = notes_payment;
    }

    public void setGambar_pembuktian_payment(String gambar_pembuktian_payment) {
        this.gambar_pembuktian_payment = gambar_pembuktian_payment;
    }
}
