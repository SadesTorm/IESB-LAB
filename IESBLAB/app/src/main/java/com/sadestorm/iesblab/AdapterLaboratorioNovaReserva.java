package com.sadestorm.iesblab;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterLaboratorioNovaReserva extends RecyclerView.Adapter<AdapterUsuario.ViewHolderNova> {
    private List<Auxiliar> listaLab;

    public AdapterLaboratorioNovaReserva(List<Auxiliar> lista) {
        this.listaLab= lista;

    }

    @NonNull
    @Override
    public AdapterUsuario.ViewHolderGerenciaUsuario onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) { // Cria visualização na tela do cel.


        View labLista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_laboratorio_nova_reserva, viewGroup, false);


        return new AdapterUsuario.ViewHolderLaboratorio(labLista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUsuario.ViewHolderGerenciaUsuario viewHolderGerenciaUsuario, int i) {

        Usuario usuario = listaUsuarios.get(i);


        viewHolderGerenciaUsuario.matriculaTxt.setText(usuario.getMatricula());
        viewHolderGerenciaUsuario.funcaoTxt.setText(usuario.getFuncao());
        viewHolderGerenciaUsuario.nomeTxt.setText(usuario.getNome());

    }

    @Override
    public int getItemCount() {

        return listaUsuarios.size();
    }

    public class ViewHolderGerenciaUsuario extends RecyclerView.ViewHolder {

        TextView matriculaTxt;
        TextView nomeTxt;
        TextView funcaoTxt;


        public ViewHolderGerenciaUsuario(@NonNull View itemView) {
            super(itemView);

            matriculaTxt = itemView.findViewById(R.id.txtMatricula);
            nomeTxt = itemView.findViewById(R.id.txtNome);
            funcaoTxt = itemView.findViewById(R.id.txtFuncao);
        }
    }

}
