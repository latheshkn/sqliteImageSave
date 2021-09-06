package com.example.sqlitedatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Vholder> {
    ArrayList<ModelClass> list;

    public Adapter(ArrayList<ModelClass> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Vholder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_recycler,parent,false);
        return new Vholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.Vholder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Vholder extends RecyclerView.ViewHolder {

        public Vholder(@NonNull  View itemView) {
            super(itemView);
        }
    }
}
