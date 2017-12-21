package com.example.pc.crimereport;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;


class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.AdminViewHolder> {

    private ArrayList<Row> rows;
    private Context context;
    private String Aadhar;
    private String Police_id;
    private String Station_Location;

    AdminAdapter(ArrayList<Row> rows, Context ctx, String aadhar, String Police_id, String Station_Location) {
        this.rows = rows;
        context = ctx;
        Aadhar = aadhar;
        this.Station_Location = Station_Location;
        this.Police_id = Police_id;
    }

    @Override
    public void onBindViewHolder(final AdminViewHolder holder, int position) {

        if (holder.aSwitch == null) {

            holder.attribute.setText(rows.get(position).attribute);
            holder.value.setText(rows.get(position).value);

            if (holder.attribute.getText().toString().equals("Police")) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(holder.itemView.getContext(), PoliceInfo.class);
                        intent.putExtra("Police_id", Police_id);
                        holder.itemView.getContext().startActivity(intent);
                    }
                });
            } else if (holder.attribute.getText().toString().equals("Complainer")) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(holder.itemView.getContext(), ComplainerInfo.class);
                        intent.putExtra("Aadhar", Aadhar);
                        holder.itemView.getContext().startActivity(intent);
                    }
                });

            } else if (holder.attribute.getText().toString().equals("Police station")) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(holder.itemView.getContext(), Police_station.class);
                        intent.putExtra("station_location", Station_Location);
                        holder.itemView.getContext().startActivity(intent);
                    }
                });
            }
        } else {


            holder.attribute.setText(rows.get(position).attribute);


            if (rows.get(position).value.equals("Ongoing"))
                holder.aSwitch.setChecked(true);
            else if (rows.get(position).value.equals("Closed"))
                holder.aSwitch.setChecked(false);

            holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    final String Id;
                    if (rows.get(0).value.length() == 5)
                        Id = Character.toString(rows.get(0).value.charAt(4));
                    else if (rows.get(0).value.length() == 6)
                        Id = Character.toString(rows.get(0).value.charAt(4)) + Character.toString(rows.get(0).value.charAt(5));
                    else if (rows.get(0).value.length() == 7)
                        Id = Character.toString(rows.get(0).value.charAt(4)) + Character.toString(rows.get(0).value.charAt(5)) +
                                Character.toString(rows.get(0).value.charAt(6));
                    else
                        Id = null;
                    BackGround backGround = new BackGround(context);
                    if (compoundButton.isChecked())
                        backGround.execute("Status_true", Id);
                    else
                        backGround.execute("Status_false", Id);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (rows == null)
            return 0;
        else
            return rows.size();
    }

    @Override
    public AdminViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_row, parent, false);
            return new AdminViewHolder(v);
        } else if (viewType == 1) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.status_setter_row, parent, false);
            return new AdminViewHolder(v);
        } else
            return null;
    }

    class AdminViewHolder extends RecyclerView.ViewHolder {

        TextView attribute, value;
        Switch aSwitch;

        AdminViewHolder(View view) {
            super(view);
            attribute = (TextView) view.findViewById(R.id.attribute);
            value = (TextView) view.findViewById(R.id.values);
            aSwitch = (Switch) view.findViewById(R.id.admin_status_switch);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (rows.get(position).attribute.equals("Status"))
            return 1;
        else
            return 0;
    }

}
