package com.sadestorm.iesblab;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.List;




public class AdapterLaboratorioNovaReserva extends RecyclerView.Adapter<AdapterLaboratorioNovaReserva.ViewHolderLabs> {
    private List<Auxiliar> listaLab;

    public AdapterLaboratorioNovaReserva(List<Auxiliar> lista) {
        this.listaLab = lista;
    }

    @NonNull
    @Override
    public ViewHolderLabs onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_laboratorio_nova_reserva, viewGroup,false);


        return new ViewHolderLabs(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLabs viewHolderLabs, int i) {
        Auxiliar lab = listaLab.get(i);

        viewHolderLabs.laboratorioTxt.setText(lab.getLab());
        viewHolderLabs.osTxt.setText(lab.getOs());
        //viewHolderLabs.qtdMaquinasTxt.setText(lab.getQtdMaquinas());
    }

    @Override
    public int getItemCount() {
        return listaLab.size();
    }


    public class ViewHolderLabs extends RecyclerView.ViewHolder {

        TextView laboratorioTxt;
        TextView osTxt;
        TextView qtdMaquinasTxt;

        public ViewHolderLabs(@NonNull View itemView) {
            super(itemView);

            laboratorioTxt = itemView.findViewById(R.id.txtLaboratorio);
            osTxt = itemView.findViewById(R.id.txtOs);
            qtdMaquinasTxt = itemView.findViewById(R.id.txtMaquinas);

        }
    }


}