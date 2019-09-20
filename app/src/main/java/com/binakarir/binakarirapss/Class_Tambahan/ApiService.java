package com.binakarir.binakarirapss.Class_Tambahan;

import com.bk.binakarir.binakarir.Model.Model_Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("tampil_event.php")
    Call<ResEvent> getEvent();
}
