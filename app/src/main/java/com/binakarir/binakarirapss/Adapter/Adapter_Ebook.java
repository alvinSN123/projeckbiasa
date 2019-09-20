package com.binakarir.binakarirapss.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bk.binakarir.binakarir.Model.Model_Ebook;
import com.bk.binakarir.binakarir.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter_Ebook extends RecyclerView.Adapter<Adapter_Ebook.ViewHolder>{
    private List<Model_Ebook> ebookModels;
    private Context context;

    public Adapter_Ebook(List<Model_Ebook> ebookModels, Context context) {
        this.ebookModels = ebookModels;
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

        viewHolder.nama_ebook.setText(ebookModels.get(i).getNama_ebook());
        Glide.with(context).load(ebookModels.get(i).getCover_Ebook()).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.cover_ebook);

    }

    @Override
    public int getItemCount() {
        return ebookModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cover_ebook;
        TextView nama_ebook;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cover_ebook = itemView.findViewById(R.id.IV_img_ebook_item);
            nama_ebook = itemView.findViewById(R.id.TV_judul_ebook_item);

        }
    }
}
