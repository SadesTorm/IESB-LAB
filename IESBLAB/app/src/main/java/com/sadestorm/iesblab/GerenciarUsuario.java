package com.sadestorm.iesblab;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
                        Toast.makeText(getApplicationContext(),"Item precionado" + u.getConfirma(),Toast.LENGTH_SHORT).show();




                        DatabaseReference dbUsuario = referencia.child(u.getMatricula());

                        dbUsuario.child("confirma").setValue("1");

                    }


                    @Override
                    public void onLongItemClick(View view, int position) {
                        Toast.makeText(getApplicationContext(),"Click longo",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }));

        carregaDados();

    }

    public void carregaDados(){

        final String email;
        //DatabaseReference dbUsuario = referencia.child("Iesb").child("Usuario");



       Query consulta = referencia.orderByChild("matricula");




        consulta.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listaUsuario.clear();
                for (DataSnapshot dt : dataSnapshot.getChildren()) {


                    if(dt.hasChild("confirma") && dt.child("confirma").getValue().equals("0")) {
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
}
