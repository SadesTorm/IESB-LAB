package com.sadestorm.iesblab;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecycleReclame extends AppCompatActivity {

    private RecyclerView recycleReclame;
    private List<Reclame> listReclame;
    private AdapterReclame adapterReclame;
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference("Iesb/Reclamacoes");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_reclame);

        recycleReclame = findViewById(R.id.recyclerReclame);
        listReclame = new ArrayList<Reclame>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycleReclame.setLayoutManager(layoutManager);
        recycleReclame.setHasFixedSize(true);
        recycleReclame.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        adapterReclame = new AdapterReclame(listReclame);
        recycleReclame.setAdapter(adapterReclame);
        carregaDados();
    }
    public void carregaDados(){

        Query consulta = referencia.orderByChild("Reclamacoes");

        consulta.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listReclame.clear();
                for (DataSnapshot dt : dataSnapshot.getChildren()) {


                    Reclame u = dt.getValue(Reclame.class);
                    listReclame.add(u);
                }


                adapterReclame.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

