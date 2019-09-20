package com.binakarir.binakarirapss;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Pendaftaran extends AppCompatActivity {
Button btnnext;
EditText namalengkap, emailPendaftaran,no_hp,id_instagram,jumlah_peserta;
Spinner kotadomisili;
String KD;
    String URL="https://onlinetestindonesia.com/pkl/api_android/get_provinsi.php";
    ArrayList<String> CountryName;
    ArrayList<String> Countryid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendaftaran);
        namalengkap = findViewById(R.id.ET_nama);
        emailPendaftaran = findViewById(R.id.ET_email);
        no_hp = findViewById(R.id.ET_hp);
        id_instagram = findViewById(R.id.ET_ig);
        kotadomisili = findViewById(R.id.SP_kotadomisili);
        KD = String.valueOf(kotadomisili.getSelectedItem());



        btnnext = findViewById(R.id.Btn_next);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent innext = new Intent(Pendaftaran.this, Pendaftaran2.class);
                innext.putExtra("namalengkap", namalengkap.getText().toString());
                innext.putExtra("emailpendaftaran", emailPendaftaran.getText().toString());
                innext.putExtra("no_hp", no_hp.getText().toString());
                innext.putExtra("id_instagram", id_instagram.getText().toString());
                innext.putExtra("kotadomisili", kotadomisili.getSelectedItem().toString());
                startActivity(innext);
            }
        });

        //spinner array
        CountryName=new ArrayList<>();
        Countryid=new ArrayList<>();
        loadSpinnerData(URL);
        kotadomisili.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                String country = kotadomisili.getItemAtPosition(kotadomisili.getSelectedItemPosition()).toString();
                Toast.makeText(getApplicationContext(),country, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                // DO Nothing here

            }

        });


    }
    private void loadSpinnerData(String url) {

        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                try{

                    JSONObject jsonObject=new JSONObject(response);

                    if(jsonObject.getInt("status_code")==200){

                        JSONArray jsonArray=jsonObject.getJSONArray("result");

                        for(int i=0;i<jsonArray.length();i++){

                            JSONObject jsonObject1=jsonArray.getJSONObject(i);

                            String country=jsonObject1.getString("nama_provinsi");

                            CountryName.add(country);

                        }

                    }

                    kotadomisili.setAdapter(new ArrayAdapter<String>(Pendaftaran.this, android.R.layout.simple_spinner_dropdown_item, CountryName));

                }catch (JSONException e){e.printStackTrace();}

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
}
