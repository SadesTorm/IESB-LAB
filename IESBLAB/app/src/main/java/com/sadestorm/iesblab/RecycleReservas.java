package com.sadestorm.iesblab;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecycleReservas extends AppCompatActivity {

    private RecyclerView recycleReserva;
    private List<Reservas> listReserva;
    private AdapterReservas adapterReserva;
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference("Iesb/Reservas");
    private DatabaseReference referenciaUsuario = FirebaseDatabase.getInstance().getReference("Iesb/Usuario");
    private String mat;
    FirebaseAuth verificaEmail = FirebaseAuth.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_reservas);

        recycleReserva = findViewById(R.id.recyclerReservas);
        listReserva = new ArrayList<Reservas>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycleReserva.setLayoutManager(layoutManager);
        recycleReserva.setHasFixedSize(true);
        recycleReserva.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        adapterReserva = new AdapterReservas(listReserva);
        recycleReserva.setAdapter(adapterReserva);
        verificaUsuario();

    }

    public void verificaUsuario() {

        final String email;
        DatabaseReference dbUsuario = referenciaUsuario;
        email = verificaEmail.getCurrentUser().getEmail(); // verifica email logado.

        Query consulta = dbUsuario.orderByChild("email").equalTo(email);
        consulta.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dt : dataSnapshot.getChildren()) {

                    if (dt.child("email") != null && dt.child("email").getValue().equals(email)) {


                        mat = dt.child("matricula").getValue().toString();
                        String fun = dt.child("funcao").getValue().toString();
                        if(fun=="Professor") {
                            carregaDados(mat, 1);
                        }else{
                            carregaDados(mat,2);
                        }
                        return;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void carregaDados(final String matricula,int opcao) {
      final  String matriucla = matricula;
      final int op = opcao;
        Query consulta = referencia.orderByChild("Reservas");

        consulta.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listReserva.clear();
                for (DataSnapshot dt : dataSnapshot.getChildren()) {

                    if (dt.child("matricula").getValue() != null && dt.child("matricula").getValue().equals(matricula) && op==1) {
                        Reservas u = dt.getValue(Reservas.class);
                        listReserva.add(u);
                    }else{
                        Reservas u = dt.getValue(Reservas.class);
                        listReserva.add(u);
                    }
                }
                adapterReserva.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
