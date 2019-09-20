package com.binakarir.binakarirapss.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bk.binakarir.binakarir.Model.Model_Event;
import com.bk.binakarir.binakarir.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter_Event extends RecyclerView.Adapter<Adapter_Event.ViewHolder> {

    private ArrayList<Model_Event> EventModels = null;
    private Context context;

    public Adapter_Event(ArrayList<Model_Event> EventModels, Context context) {
        this.EventModels = EventModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_event, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.nama_event.setText(EventModels.get(i).getNama_event());
        viewHolder.tanggal_event.setText(EventModels.get(i).getTanggal_event());
        viewHolder.tempat_event.setText(EventModels.get(i).getLokasi_event());
        Glide.with(context).load(EventModels.get(i).getPoster_event()).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.poster_event);


    }

    @Override
    public int getItemCount() {
        return (EventModels == null) ? 0 : EventModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView poster_event;
        TextView tanggal_event, nama_event, tempat_event;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            poster_event = itemView.findViewById(R.id.IV_img_event_item);
            tanggal_event = itemView.findViewById(R.id.TV_tanggal_event_item);
            nama_event = itemView.findViewById(R.id.TV_judul_event_item);
            tempat_event = itemView.findViewById(R.id.TV_lokasi_event_item);

        }
    }
}