package com.example.volly_librery;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    ImageView t1_img;
    TextView t2_name,t3;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        t1_img = itemView.findViewById(R.id.t1_imgid);
        t2_name = itemView.findViewById(R.id.t2_name_id);
        t3 = itemView.findViewById(R.id.t3_id);
    }
}
