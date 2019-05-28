package com.sadestorm.iesblab;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecycleLaboratorio extends AppCompatActivity {

    private RecyclerView recyclelab;
    private List<Auxiliar> listaLabs;
    private AdapterLaboratorioNovaReserva adapterLabs;
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference("Iesb/laboratorio");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_laboratorio);

        recyclelab = findViewById(R.id.RecycleViewLabs);
        listaLabs = new ArrayList<Auxiliar>();


        //configurando Adapter
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclelab.setLayoutManager(layoutManager);
        recyclelab.setHasFixedSize(true);

        AdapterLaboratorioNovaReserva adapterLabs = new AdapterLaboratorioNovaReserva(listaLabs);

        recyclelab.setAdapter( adapterLabs);
    }

    public void carregaDados(){

        Query consulta = referencia.orderByChild("laboratorio");




        consulta.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listaLabs.clear();
                for (DataSnapshot dt : dataSnapshot.getChildren()) {

                        Auxiliar u = dt.getValue(Auxiliar.class);
                        listaLabs.add(u);

                }
                adapterLabs.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
