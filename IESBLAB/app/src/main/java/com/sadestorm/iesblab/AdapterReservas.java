package com.sadestorm.iesblab;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.sql.ResultSet;
import java.util.List;


public class AdapterReservas extends RecyclerView.Adapter<AdapterReservas.MyViewHolder>{

    private List<Reservas> listReservas;


    public AdapterReservas(List<Reservas> listReservas) {
        this.listReservas = listReservas;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemLista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_reservas,viewGroup,false);

        return new MyViewHolder(itemLista);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        Reservas reserva = listReservas.get(i);

        viewHolder.labTxt.setText(reserva.getLab());
        viewHolder.statusTxt.setText(reserva.getStatus());
        viewHolder.dataTxt.setText(reserva.getData());
        viewHolder.horaTxt.setText(reserva.getEntrada());


    }

    @Override
    public int getItemCount() {
        return listReservas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView labTxt;
        TextView statusTxt;
        TextView dataTxt;
        TextView horaTxt;

        public MyViewHolder(View itemView) {
            super(itemView);

            labTxt = itemView.findViewById(R.id.txtLab);
            statusTxt = itemView.findViewById(R.id.txtReseva);
            dataTxt = itemView.findViewById(R.id.txtData);
            horaTxt = itemView.findViewById(R.id.txtHora);


        }
    }


}

