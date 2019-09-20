package com.binakarir.binakarirapss;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.bk.binakarir.binakarir.Login_google.Atur_Akun;

public class Setting extends AppCompatActivity {
    Button btn_atur_akun;
    Spinner language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        btn_atur_akun = findViewById(R.id.btn_atur_akun);
        language = findViewById(R.id.SP_language);

        btn_atur_akun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aturakun = new Intent(Setting.this, Atur_Akun.class);
                startActivity(aturakun);
            }
        });

    }
}
