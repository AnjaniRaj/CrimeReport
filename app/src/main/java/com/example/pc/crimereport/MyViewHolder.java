package com.example.pc.crimereport;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView Type, Date, Status;

    public MyViewHolder(View itemView) {
        super(itemView);
        Type = (TextView) itemView.findViewById(R.id.type);
        Date = (TextView) itemView.findViewById(R.id.date);
        Status = (TextView) itemView.findViewById(R.id.status);
    }


}
