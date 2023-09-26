package com.example.rxjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//Newly commented
//Simple adapter
public class ItemAdaptor extends RecyclerView.Adapter<ItemAdaptor.ViewHolder> {
    ArrayList<String> itemList1;
    Context context;

    //adapter constructor
    public ItemAdaptor(ArrayList animals, Context context1) {
        itemList1 = animals;
        context = context1;
    }

    // item adapter
    @NonNull
    @Override
    public ItemAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdaptor.ViewHolder holder, int position) {
        holder.name.setText(itemList1.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList1.size();
    }

    //view holder inner class
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //setting up the view, initializing it
            name = itemView.findViewById(R.id.name);
        }
    }
}
