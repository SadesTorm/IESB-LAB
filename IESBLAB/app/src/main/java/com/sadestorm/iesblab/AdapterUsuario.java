package com.sadestorm.iesblab;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;


import java.util.List;

public class AdapterUsuario extends RecyclerView.Adapter<AdapterUsuario.ViewHolderGerenciaUsuario> {

    private List<Usuario> listaUsuarios;

    public AdapterUsuario(List<Usuario> lista) {
        this.listaUsuarios = lista;

    }

    @NonNull
    @Override
    public ViewHolderGerenciaUsuario onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) { // Cria visualização na tela do cel.


        View usuarioLista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_usuario, viewGroup, false);


        return new ViewHolderGerenciaUsuario(usuarioLista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderGerenciaUsuario viewHolderGerenciaUsuario, int i) {

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
