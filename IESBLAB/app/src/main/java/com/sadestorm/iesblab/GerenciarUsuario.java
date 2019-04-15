package com.sadestorm.iesblab;


import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GerenciarUsuario extends AppCompatActivity {

    private RecyclerView viewRecycle;
    private List<Usuario> listaUsuario;
    private AdapterUsuario adapter;
    DatabaseReference referencia = FirebaseDatabase.getInstance().getReference("Usuario");
    TextView txtteste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_usuario);
        carregaDados();
        viewRecycle = findViewById(R.id.recyclerView);

        txtteste = findViewById(R.id.txttudocerto);
        listaUsuario = new ArrayList<>();
        //listagem de filmes
        // this.adicionaUsuariosLista();
        //configurando um adapter

        RecyclerView.LayoutManager layaoutUsuario = new LinearLayoutManager(getApplicationContext());
        viewRecycle.setLayoutManager(layaoutUsuario);
        viewRecycle.setHasFixedSize(true);
        adapter = new AdapterUsuario(listaUsuario);
        viewRecycle.setAdapter(adapter);


        //configurando recicleView para receber um layout personalizado


    }

    public void carregaDados(){

        final String email;
        DatabaseReference dbUsuario = referencia.child("Iesb").child("Usuario");


        Query consulta = dbUsuario.orderByChild("matricula");

        consulta.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dt : dataSnapshot.getChildren()) {
                    txtteste.setText("funcionando");
                    //listaUsuario.add()




                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
