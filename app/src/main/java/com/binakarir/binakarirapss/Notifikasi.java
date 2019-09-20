package com.binakarir.binakarirapss;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class Notifikasi extends AppCompatActivity {

    // deklarasi kode request
    public static final int notifikasi = 1;
    private static final String CHANNEL_ID ="channel1";

    // deklarasi tombol
    Button btnkirim;
    // deklarasi edittext
    EditText txtjudul, txtpesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifikasi);

        // inisialisasi komponen
        btnkirim = (Button) findViewById(R.id.btn_kirim);
        txtjudul = (EditText) findViewById(R.id.txt_judul);
        txtpesan = (EditText) findViewById(R.id.txt_pesan);

        // aksi letika tombol diklik
        btnkirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // membuat komponen Inten
                Intent intent = new Intent(getApplicationContext(), Notifikasi.class);
                // memanggil method untuk menampilkan notifikasi
                // dengan mengirimkan data yang dikirim dari komponen EditText
                tampilNotifikasi(txtjudul.getText().toString()
                        , txtpesan.getText().toString(), intent);
            }
        });
    }

    private void tampilNotifikasi(String s, String s1, Intent intent) {
        // membuat komponen pending intent
        PendingIntent pendingIntent = PendingIntent.getActivity(Notifikasi.this
                , notifikasi, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // membuat komponen notifikasi
        NotificationCompat.Builder builder = new NotificationCompat.Builder(Notifikasi.this, CHANNEL_ID);
        Notification notification;
        notification = builder.setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentTitle(s)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(Notifikasi.this.getResources()
                        , R.mipmap.ic_launcher))
                .setContentText(s1)
                .build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager = (NotificationManager) Notifikasi.this
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notifikasi, notification);
    }

}
