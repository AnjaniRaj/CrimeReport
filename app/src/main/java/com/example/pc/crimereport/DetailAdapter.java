package com.example.pc.crimereport;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

 class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailViewholder> {

     private ArrayList<DetailRow> drows;

     DetailAdapter(ArrayList<DetailRow> drows) {
         this.drows = drows;
     }

     @Override
     public DetailViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
         return new DetailViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_row, parent, false));
     }

     @Override
     public void onBindViewHolder(final DetailViewholder holder, final int position) {
         holder.attribute.setText(drows.get(position).attribute);
         holder.value.setText(drows.get(position).value);
     }

     @Override
     public int getItemCount() {
         if (drows == null)
             return 0;
         else
             return drows.size();
     }


     class DetailViewholder extends RecyclerView.ViewHolder {

         TextView attribute, value;

         DetailViewholder(View itemView) {
             super(itemView);
             attribute = (TextView) itemView.findViewById(R.id.attribute);
             value = (TextView) itemView.findViewById(R.id.values);

         }
     }
 }
