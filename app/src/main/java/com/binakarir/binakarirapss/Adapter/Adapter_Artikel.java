package com.binakarir.binakarirapss.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bk.binakarir.binakarir.Model.Model_Artikel;
import com.bk.binakarir.binakarir.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter_Artikel extends RecyclerView.Adapter<Adapter_Artikel.ViewHolder> {
    private List<Model_Artikel> artikelModels;
    private Context context;

    public Adapter_Artikel(List<Model_Artikel> artikelModels, Context context) {
        this.artikelModels = artikelModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_beranda, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.nama_branding.setText(artikelModels.get(i).getNama_artikel());
        Glide.with(context).load(artikelModels.get(i).getGambar_artikel()).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.foto_branding);

    }

    @Override
    public int getItemCount() {
        return artikelModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView foto_branding;
        TextView desc_banding,nama_branding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            foto_branding = itemView.findViewById(R.id.IV_img_artikel_item);
            nama_branding = itemView.findViewById(R.id.TV_judul_artikel_item);

        }
    }
}
