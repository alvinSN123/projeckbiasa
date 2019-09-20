package com.binakarir.binakarirapss.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.binakarir.binakarirapss.Adapter.Adapter_Event;
import com.binakarir.binakarirapss.ChattingPersonal.Chat;
import com.binakarir.binakarirapss.Class_Tambahan.ApiEndPoint;
import com.binakarir.binakarirapss.Class_Tambahan.ApiService;
import com.binakarir.binakarirapss.Class_Tambahan.ResEvent;
import com.binakarir.binakarirapss.HelpActivity;
import com.binakarir.binakarirapss.Model.Model_Event;
import com.binakarir.binakarirapss.R;
import com.binakarir.binakarirapss.Setting;
import com.bk.binakarir.binakarir.Adapter.Adapter_Event;
import com.bk.binakarir.binakarir.ChattingPersonal.Chat;
import com.bk.binakarir.binakarir.Class_Tambahan.ApiEndPoint;
import com.bk.binakarir.binakarir.Class_Tambahan.ApiService;
import com.bk.binakarir.binakarir.Class_Tambahan.ResEvent;
import com.bk.binakarir.binakarir.HelpActivity;
import com.bk.binakarir.binakarir.Model.BrandingModel;
import com.bk.binakarir.binakarir.Model.Model_Event;
import com.bk.binakarir.binakarir.R;
import com.bk.binakarir.binakarir.Setting;

import java.util.ArrayList;
import java.util.List;


public class BerandaFragment extends Fragment {

    private LinearLayoutManager gridLayoutManager;
    private Adapter_Event brandingAdapter;
    private ArrayList<Model_Event> mItems = new ArrayList<>();
    private RecyclerView rView;
    ProgressBar progressBar;
    ImageView imsetting, imchat, imhelp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);

        imchat = view.findViewById(R.id.img_chat);
        imchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inchat = new Intent(getActivity(), Chat.class);
                startActivity(inchat);
            }
        });
        imsetting = view.findViewById(R.id.img_setting);
        imsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent insetting = new Intent(getActivity(), Setting.class);
                startActivity(insetting);
            }
        });
        imhelp = view.findViewById(R.id.img_help);
        imhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inhelp = new Intent(getActivity(), HelpActivity.class);
                startActivity(inhelp);
            }
        });
//        ButterKnife.bind(this);

        progressBar = view.findViewById(R.id.progress_bar_fragmenberanda);
        gridLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,
                false);
        rView = (RecyclerView) view.findViewById(R.id.Recyclerview_event);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(gridLayoutManager);

        brandingAdapter = new Adapter_Event(mItems, getContext());
        rView.setAdapter(brandingAdapter);


        loadDataBranding();
        return view;
    }

    public void loadDataBranding() {
        ApiService api = ApiEndPoint.getClient().create(ApiService.class);
        Call<ResEvent> call = api.getEvent();
        call.enqueue(new Callback<ResEvent>() {
            @Override
            public void onResponse(Call<ResEvent> call, Response<ResEvent> response) {
//                List<Model_Event> statusCode = response.body().getResult();
//                progressBar.setVisibility(View.GONE);
//                if (statusCode.equals("200")) {
//                    mItems = (ArrayList<Model_Event>) response.body().getResult();
//                    brandingAdapter = new Adapter_Event(mItems, getContext());
//                    rView.setAdapter(brandingAdapter);
//
//                }
                generateListEvent(response.body().getResult());

            }

            @Override
            public void onFailure(Call<ResEvent> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateListEvent(ArrayList<Model_Event> result) {

        brandingAdapter = new Adapter_Event(result,getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rView.setLayoutManager(layoutManager);
        rView.setAdapter(brandingAdapter);

    }
}
