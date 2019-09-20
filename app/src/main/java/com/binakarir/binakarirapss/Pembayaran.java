package com.binakarir.binakarirapss;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bk.binakarir.binakarir.Upload_Foto.AppController;
import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Pembayaran extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    SimpleDateFormat simpleDateFormat;
    private EditText emailpembayaran, atasnama_pem,transferpembayaran,tanggalPembayaran,notespembayaran;
    private Spinner paymentmethod;
    private ImageView buktipembayaran;
    private String spinnerpayment;
    private Button choosefile_pem, send_pem;
    private ImageButton tanggal_pem;
    Bitmap bitmap, decoded;
    int success;
    int PICK_IMAGE_REQUEST = 1;
    int bitmap_size = 60;
    private  static  final String TAG = Pembayaran.class.getSimpleName();

    private String UPLOAD_URL = "http://onlinetestindonesia.com/pkl/api_android/tambah_pembayaran.php";
    private static  final String TAG_SUCCESS = "success";
    private static  final String TAG_MESSAGE = "message";
    private String KEY_IMAGE = "image";
    private String KEY_NAMA = "nama";
    private String KEY_EMAIL_PEM = "email";
    private String KEY_TANGGAL_PEM = "tanggal_pembayaran";
    private String KEY_NOTES = "notes";
    private String KEY_PAYMENT_METHODE = "metode_pembayaran";
    private String KEY_TRANSFER_PEMBAYARAN = "jumlah_transfer";

    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        emailpembayaran = (EditText) findViewById(R.id.ET_emailPembayaran);
        atasnama_pem = (EditText) findViewById(R.id.ET_atasnama_pembayaran);
        transferpembayaran = (EditText) findViewById(R.id.ET_transferJumlah);
        tanggalPembayaran = (EditText) findViewById(R.id.ET_date_pembayaran);
        notespembayaran = (EditText)findViewById(R.id.ET_notes);
        paymentmethod = (Spinner) findViewById(R.id.Sp_pembayaran);
        buktipembayaran = (ImageView) findViewById(R.id.image_bukti_TF);
        spinnerpayment = String.valueOf(paymentmethod.getSelectedItem());
        tanggal_pem = (ImageButton) findViewById(R.id.Btn_tanggal);
        choosefile_pem= (Button) findViewById(R.id.Btn_choosefile_pembayaran);
        send_pem = (Button) findViewById(R.id.Btn_send_pembayaran);
        choosefile_pem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });
        send_pem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

        tanggal_pem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate(2019, 0, 1, R.style.NumberPickerStyle);
            }
        });
    }
    // tanggal spinner
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        tanggalPembayaran.setText(simpleDateFormat.format(calendar.getTime()));
    }
    public void onCancelled(DatePicker view) {
        tanggalPembayaran.setText("cancelled");
    }

    @VisibleForTesting
    void showDate(int year, int monthOfYear, int dayOfMonth, int spinnerTheme) {
        new SpinnerDatePickerDialogBuilder()
                .context(Pembayaran.this)
                .callback((DatePickerDialog.OnDateSetListener) Pembayaran.this)
                .spinnerTheme(R.style.NumberPickerStyle)
                .showTitle(true)
                .showDaySpinner(true)
                .defaultDate(2019, 0, 1)
                .maxDate(2021, 12, 31)
                .minDate(2018, 0, 1)
                .build()
                .show();

    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage() {
        //menampilkan progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Uploading...", "Please wait...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "Response: " + response.toString());

                        try {
                            JSONObject jObj = new JSONObject(response);
                            success = jObj.getInt(TAG_SUCCESS);

                            if (success == 1) {
                                Log.e("v Add", jObj.toString());

                                Toast.makeText(Pembayaran.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                                kosong();

                            } else {
                                Toast.makeText(Pembayaran.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //menghilangkan progress dialog
                        loading.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //menghilangkan progress dialog
                        loading.dismiss();

                        //menampilkan toast
                        Toast.makeText(Pembayaran.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                        Log.e(TAG, error.getMessage().toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                //membuat parameters
                Map<String, String> params = new HashMap<>();

                //menambah parameter yang di kirim ke web servis
                params.put(KEY_IMAGE, getStringImage(decoded));
                params.put(KEY_EMAIL_PEM, emailpembayaran.getText().toString().trim());
                params.put(KEY_NAMA, atasnama_pem.getText().toString().trim());
                params.put(KEY_TRANSFER_PEMBAYARAN, transferpembayaran.getText().toString().trim());
                params.put(KEY_TANGGAL_PEM, tanggalPembayaran.getText().toString().trim());
                params.put(KEY_PAYMENT_METHODE, spinnerpayment.trim());
                params.put(KEY_NOTES, notespembayaran.getText().toString().trim());



                //kembali ke parameters
                Log.e(TAG, "" + params);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //mengambil fambar dari Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                // 512 adalah resolusi tertinggi setelah image di resize, bisa di ganti.
                setToImageView(getResizedBitmap(bitmap, 512));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void kosong() {
        buktipembayaran.setImageResource(0);
        atasnama_pem.setText(null);
        emailpembayaran.setText(null);
        transferpembayaran.setText(null);
        tanggalPembayaran.setText(null);
        notespembayaran.setText(null);
    }

    private void setToImageView(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));

        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        buktipembayaran.setImageBitmap(decoded);
    }

    // fungsi resize image
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

}
