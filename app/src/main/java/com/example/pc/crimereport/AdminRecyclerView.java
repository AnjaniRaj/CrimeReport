package com.example.pc.crimereport;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class AdminRecyclerView extends RecyclerView.Adapter<MyViewHolder> {

    ArrayList<MainRow> mainRows;
    Context context;

    AdminRecyclerView(Context context,ArrayList<MainRow> mainRows) {
        this.mainRows = mainRows;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.Type.setText(mainRows.get(holder.getAdapterPosition()).Type);
        holder.Status.setText(mainRows.get(holder.getAdapterPosition()).Status);
        holder.Date.setText(mainRows.get(holder.getAdapterPosition()).Date);
               holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(holder.itemView.getContext(), new_admin_report.class);
                intent.putExtra("Type", holder.Type.getText().toString());
                intent.putExtra("Status", holder.Status.getText().toString());
                intent.putExtra("Date", holder.Date.getText().toString());
                intent.putExtra("id", "0000" + mainRows.get(holder.getAdapterPosition()).Fir_No);
                intent.putExtra("location", mainRows.get(holder.getAdapterPosition()).Location);
                intent.putExtra("name", mainRows.get(holder.getAdapterPosition()).Name);
                intent.putExtra("doc", mainRows.get(holder.getAdapterPosition()).DOC);
                intent.putExtra("police_name", mainRows.get(holder.getAdapterPosition()).Police_Name);
                intent.putExtra("police_id", mainRows.get(holder.getAdapterPosition()).Police_Id);
                intent.putExtra("aadhar", mainRows.get(holder.getAdapterPosition()).Aadhar);
                intent.putExtra("station_location", mainRows.get(holder.getAdapterPosition()).Station_Location);


                holder.itemView.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        if (mainRows != null)
            return mainRows.size();
        else
            return 0;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new MyViewHolder(v);
    }


}
