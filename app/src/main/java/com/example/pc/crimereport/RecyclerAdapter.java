package com.example.pc.crimereport;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {

    ArrayList<MainRow> mainRows;

    RecyclerAdapter(ArrayList<MainRow> mainRows) {
        this.mainRows = mainRows;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.Type.setText(mainRows.get(position).Type);
        holder.Status.setText(mainRows.get(position).Status);
        holder.Date.setText(mainRows.get(position).Date);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if(view.getClass().toString().equals("MainActivity")){

                //}

                // Toast.makeText(holder.itemView.getContext(),"jhv",Toast.LENGTH_SHORT).show();
                //this is how you get context
                Intent intent = new Intent(holder.itemView.getContext(),AReport.class);
                intent.putExtra("Type",holder.Type.getText().toString());
                intent.putExtra("Status",holder.Status.getText().toString());
                intent.putExtra("Date",holder.Date.getText().toString());
                intent.putExtra("id", "0000" + mainRows.get(holder.getAdapterPosition()).Fir_No);
                intent.putExtra("location",mainRows.get(holder.getAdapterPosition()).Location);
                intent.putExtra("doc",mainRows.get(holder.getAdapterPosition()).DOC);
                holder.itemView.getContext().startActivity(intent);
                // Toast.makeText(holder.itemView.getContext(),view.getClass().toString(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        if(mainRows==null)
            return 0;
        else
        return mainRows.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new MyViewHolder(v);
    }


}
