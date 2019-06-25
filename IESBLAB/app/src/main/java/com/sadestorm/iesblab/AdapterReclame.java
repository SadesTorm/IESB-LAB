package com.sadestorm.iesblab;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterReclame extends RecyclerView.Adapter<AdapterReclame.ViewHolderReclame>{

    private List<Reclame> listReclame;

    public AdapterReclame(List<Reclame> listReclame) {
        this.listReclame = listReclame;
    }

    @NonNull
    @Override

    public AdapterReclame.ViewHolderReclame onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_reclame, viewGroup,false);
        return new ViewHolderReclame(itemLista) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderReclame viewHolderReclame, int i) {

        Reclame aux = listReclame.get(i);
        viewHolderReclame.matriculaTxt.setText(aux.getMatricula());
        viewHolderReclame.reclamacaoTxt.setText(aux.getReclame());

    }

    @Override
    public int getItemCount() {
        return listReclame.size();
    }
    public class ViewHolderReclame extends RecyclerView.ViewHolder {

        TextView matriculaTxt;
        TextView reclamacaoTxt;


        public ViewHolderReclame(@NonNull View itemView) {
            super(itemView);

            matriculaTxt = itemView.findViewById(R.id.txtMatricula);
            reclamacaoTxt = itemView.findViewById(R.id.txtReclamacao);

        }
    }
}
