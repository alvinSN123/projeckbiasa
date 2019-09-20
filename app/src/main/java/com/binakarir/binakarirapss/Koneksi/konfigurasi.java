package com.binakarir.binakarirapss.Koneksi;

public class konfigurasi {
    //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan
    //Pada tutorial Kali ini, karena kita membuat localhost maka alamatnya tertuju ke IP komputer
    //dimana File PHP tersebut berada
    //PENTING! JANGAN LUPA GANTI IP SESUAI DENGAN IP KOMPUTER DIMANA DATA PHP BERADA
    public static final String URL_ADD="http://onlinetestindonesia.com/pkl/api_android/tambah_pendaftaran.php";
    public static final String URL_GET_ALL = "http://192.168.1.9/Android/pegawai/tampilSemuaPgw.php";
    public static final String URL_GET_EMP = "http://192.168.1.9/Android/pegawai/tampilPgw.php?id=";
    public static final String URL_UPDATE_EMP = "http://192.168.1.9/Android/pegawai/updatePgw.php";
    public static final String URL_DELETE_EMP = "http://192.168.1.9/Android/pegawai/hapusPgw.php?id=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
//    public static final String KEY_EMP_ID = "id_pendaftaran";
    public static final String KEY_EMP_NAMA = "nama_lengkap";
    public static final String KEY_EMP_EMAIL = "email"; //desg itu variabel untuk posisi
    public static final String KEY_EMP_NOHP = "no_handphone"; //salary itu variabel untuk gajih
    public static final String KEY_EMP_ID_IG = "id_instagram";
    public static final String KEY_EMP_KOTA = "kota_domisili";
    public static final String KEY_EMP_JURKUL_S1 = "jurusan_kuliah_s1";
    public static final String KEY_EMP_UNIV_S1 = "universitas_s1";
    public static final String KEY_EMP_JURKUL_S2 = "jurusan_kuliah_s2";
    public static final String KEY_EMP_UNIV_S2 = "universitas_s2";
    public static final String KEY_EMP_PEKERJAAN = "pekerjaan";
    public static final String KEY_EMP_PERUSAHAAN = "perusahaan";
    //JSON Tags

    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_NAMA = "nama_lengkap";
    public static final String TAG_EMAIL = "email"; //desg itu variabel untuk posisi
    public static final String TAG_NOHP = "no_handphone"; //salary itu variabel untuk gajih
    public static final String TAG_ID_IG = "id_instagram";
    public static final String TAG_KOTA = "kota_domisili";
    public static final String TAG_JURKUL_S1 = "jurusan_kuliah_s1";
    public static final String TAG_UNIV_S1 = "universitas_s1";
    public static final String TAG_JURKUL_S2 = "jurusan_kuliah_s2";
    public static final String TAG_UNIV_S2 = "universitas_s2";
    public static final String TAG_PEKERJAAN = "pekerjaan";
    public static final String TAG_PERUSAHAAN = "perusahaan";

    //ID karyawan
    //emp itu singkatan dari Employee
    public static final String EMP_ID = "emp_id_pendaftaran";
}
