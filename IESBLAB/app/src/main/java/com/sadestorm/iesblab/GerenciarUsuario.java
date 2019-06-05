package com.sadestorm.iesblab;


import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
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
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference("Iesb/Usuario");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_usuario);


        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("Concessão de acesso");
        dialog.setMessage("Listar usuarios ativos ou bloqueados ?");

        dialog.setCancelable(false);

        dialog.setPositiveButton("Ativos", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Item precionado", Toast.LENGTH_SHORT).show();

                carregaDados("1");

            }
        });

        dialog.setNegativeButton("Pendentes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Item nao precionado", Toast.LENGTH_SHORT).show();

                carregaDados("0");

            }
        });
        dialog.create();
        dialog.show();


        viewRecycle = findViewById(R.id.recyclerView);

        listaUsuario = new ArrayList<Usuario>();

        RecyclerView.LayoutManager layaoutUsuario = new LinearLayoutManager(getApplicationContext());
        viewRecycle.setLayoutManager(layaoutUsuario);
        viewRecycle.setHasFixedSize(true);
        viewRecycle.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        adapter = new AdapterUsuario(listaUsuario);


        viewRecycle.setAdapter(adapter);
        viewRecycle.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(),
                viewRecycle,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Usuario u = listaUsuario.get(position);

                        Alerta(view,u);

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Toast.makeText(getApplicationContext(),"Click longo",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }

                }));

    }

    public void carregaDados(String opcao){

        final String email;
        final String confirma = opcao;
        Query consulta = referencia.orderByChild("matricula");




        consulta.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listaUsuario.clear();
                for (DataSnapshot dt : dataSnapshot.getChildren()) {


                    if(dt.hasChild("confirma") && dt.child("confirma").getValue().equals(confirma)) {
                        Usuario u = dt.getValue(Usuario.class);
                        listaUsuario.add(u);
                    }

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void Alerta(View view,Usuario usuario){

        final String matricula = usuario.getMatricula();
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);




            dialog.setTitle("Concessão de acesso");
            dialog.setMessage("Conceder acesso ao usuario ? ");

            dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                    DatabaseReference dbUsuario = referencia.child(matricula);

                    dbUsuario.child("confirma").setValue("1");
                }
            });

            dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                    DatabaseReference dbUsuario = referencia.child(matricula);

                    dbUsuario.child("confirma").setValue("0");
                }
            });

        dialog.create();
        dialog.show();

        }
}
