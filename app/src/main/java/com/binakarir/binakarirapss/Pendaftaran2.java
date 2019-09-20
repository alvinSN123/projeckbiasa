package com.binakarir.binakarirapss;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bk.binakarir.binakarir.Koneksi.RequestHandler;
import com.bk.binakarir.binakarir.Koneksi.konfigurasi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Pendaftaran2 extends AppCompatActivity implements View.OnClickListener {

    private TextView namalengkap, emailpendaftaran, no_hp, idinstagram, kotadomisili;
    private String nama, email1, nohp, insta, kota;
    private EditText jurusan_kuliah_s1, jurusan_kuliah_s2, pekerjaan, perusahaan;
    private Spinner universitas_s1, universitas_s2;
    private Button submit_data;
    private String Univs1,Univs2;

    String URL="https://onlinetestindonesia.com/pkl/api_android/get_univ.php";
    ArrayList<String> univName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendaftaran2);

        submit_data = findViewById(R.id.Btn_submit);
        //intent
        namalengkap = findViewById(R.id.TV_nama_lengkap);
        emailpendaftaran = findViewById(R.id.TV_Email_P1);
        no_hp = findViewById(R.id.TV_notelp);
        idinstagram = findViewById(R.id.TV_idinstagram);
        kotadomisili = findViewById(R.id.TV_kota_domisili);
        jurusan_kuliah_s1 = findViewById(R.id.ET_jurusan_s1);
        universitas_s1 = findViewById(R.id.SP_universitas_s1);
        jurusan_kuliah_s2 = findViewById(R.id.ET_jurusan_s2);
        universitas_s2 = findViewById(R.id.SP_universitas_s2);
        pekerjaan = findViewById(R.id.ET_pekerjaan);
        perusahaan = findViewById(R.id.ET_perusahaan);
        Univs1 = String.valueOf(universitas_s1.getSelectedItem());
        Univs2 = String.valueOf(universitas_s2.getSelectedItem());

        Bundle bundle = getIntent().getExtras();
        nama = bundle.getString("namalengkap");
        email1 = bundle.getString("emailpendaftaran");
        nohp = bundle.getString("no_hp");
        insta = bundle.getString("id_instagram");
        kota = bundle.getString("kotadomisili");

        submit_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent insub = new Intent(Pendaftaran2.this, Home.class);
                startActivity(insub);
            }
        });

        namalengkap.setText(nama);
        emailpendaftaran.setText(email1);
        no_hp.setText(nohp);
        idinstagram.setText(insta);
        kotadomisili.setText(kota);
        //end
        submit_data.setOnClickListener(this);
        //spinner univ1
        univName=new ArrayList<>();
        loadSpinnerData(URL);
        universitas_s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                String country=   universitas_s1.getItemAtPosition(universitas_s1.getSelectedItemPosition()).toString();
                Toast.makeText(getApplicationContext(),country, Toast.LENGTH_LONG).show();

            }

            @Override

            public void onNothingSelected(AdapterView<?> adapterView) {

                // DO Nothing here

            }

        });

        //spinner array
        univName=new ArrayList<>();
        loadSpinnerData(URL);
        universitas_s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                String country=   universitas_s2.getItemAtPosition(universitas_s2.getSelectedItemPosition()).toString();
                Toast.makeText(getApplicationContext(),country, Toast.LENGTH_LONG).show();

            }

            @Override

            public void onNothingSelected(AdapterView<?> adapterView) {

                // DO Nothing here

            }

        });
    }
    //perintah dibawah merupakan isian spinner univ s1
    private void loadSpinnerData(String url) {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.getInt("status_code") == 200) {

                        JSONArray jsonArray = jsonObject.getJSONArray("result");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            String univ = jsonObject1.getString("nama_universitas");

                            univName.add(univ);

                        }

                    }

                    universitas_s1.setAdapter(new ArrayAdapter<String>(Pendaftaran2.this, android.R.layout.simple_spinner_dropdown_item, univName));
                    universitas_s2.setAdapter(new ArrayAdapter<String>(Pendaftaran2.this, android.R.layout.simple_spinner_dropdown_item, univName));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {

            @Override

            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();

            }

        });

        int socketTimeout = 30000;

        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);

        requestQueue.add(stringRequest);
    }
        //Dibawah ini merupakan perintah untuk Menambahkan Pendaftaran (CREATE)
        private void tambah_data1(){

            final String nama_lengkap = namalengkap.getText().toString().trim();
            final String email = emailpendaftaran.getText().toString().trim();
            final String no_hp1 = no_hp.getText().toString().trim();
            final String id_instagram = idinstagram.getText().toString().trim();
            final String kota_domisili = kotadomisili.getText().toString().trim();
            final String jurusan_k_s1 = jurusan_kuliah_s1.getText().toString().trim();
            final String univ_s1 = universitas_s1.getSelectedItem().toString().trim();
            final String jurusan_k_s2 = jurusan_kuliah_s2.getText().toString().trim();
            final String univ_s2 = universitas_s2.getSelectedItem().toString().trim();
            final String pekerjaan1 = pekerjaan.getText().toString().trim();
            final String perushaan1 = perusahaan.getText().toString().trim();


            class tambah_data extends AsyncTask<Void, Void, String> {

                ProgressDialog loading;

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    loading = ProgressDialog.show(Pendaftaran2.this, "Menambahkan...", "Tunggu...", false, false);
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    loading.dismiss();
                    Toast.makeText(Pendaftaran2.this, s, Toast.LENGTH_LONG).show();
                }

                @Override
                protected String doInBackground(Void... v) {
                    HashMap<String, String> params = new HashMap<>();
                    params.put(konfigurasi.KEY_EMP_NAMA, nama_lengkap);
                    params.put(konfigurasi.KEY_EMP_EMAIL, email);
                    params.put(konfigurasi.KEY_EMP_NOHP, no_hp1);
                    params.put(konfigurasi.KEY_EMP_ID_IG, id_instagram);
                    params.put(konfigurasi.KEY_EMP_KOTA, kota_domisili);
                    params.put(konfigurasi.KEY_EMP_JURKUL_S1, jurusan_k_s1);
                    params.put(konfigurasi.KEY_EMP_UNIV_S1, univ_s1);
                    params.put(konfigurasi.KEY_EMP_JURKUL_S2, jurusan_k_s2);
                    params.put(konfigurasi.KEY_EMP_UNIV_S2, univ_s2);
                    params.put(konfigurasi.KEY_EMP_PEKERJAAN, pekerjaan1);
                    params.put(konfigurasi.KEY_EMP_PERUSAHAAN, perushaan1);


                    RequestHandler rh = new RequestHandler();
                    String res = rh.sendPostRequest(konfigurasi.URL_ADD, params);
                    return res;
                }
            }

            tambah_data ae = new tambah_data();
            ae.execute();
        }
    @Override
    public void onClick(View v) {
        if (v == submit_data) {
            tambah_data1();


        }
    }

}