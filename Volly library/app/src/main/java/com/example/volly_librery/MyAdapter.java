package com.example.volly_librery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<ViewHolder> {

    Model[] model;
    Context context;

    public MyAdapter(Model[] model, Context context) {
        this.model = model;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Model list = model[position];

        Glide.with(holder.t1_img.getContext()).load(list.getAvatarUrl()).into(holder.t1_img);
        holder.t2_name.setText(list.getLogin());
        holder.t3.setText(String.valueOf(list.getId()));

    }

    @Override
    public int getItemCount() {
        return model.length;
    }
}
