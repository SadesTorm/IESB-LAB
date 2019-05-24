package com.sadestorm.iesblab;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.List;

public class NovaReserva extends AppCompatActivity {



    TextView dataTxt;
    Button dataBtn;
    Button labBtn;



    DatePickerDialog datePickerDialog;
    Calendar calendario;

    private RecyclerView viewRecycle;
    private List<Usuario> listalab;
    //private AdapterUsuario adapter;
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference("Iesb/laboratorio");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_reserva);

        dataTxt = findViewById(R.id.txtData);

        dataBtn = findViewById(R.id.btnData);
        labBtn = findViewById(R.id.btnLab);

        labBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        dataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarioDialog(v);
            }
        });




    }

    public void calendarioDialog (View view){

        calendario = Calendar.getInstance();

        int day = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH);
        int ano = calendario.get(Calendar.YEAR);


        datePickerDialog = new DatePickerDialog(NovaReserva.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dataBtn.setText(dayOfMonth + " / " + (month + 1) + "/" + year);
            }
        },10,12,2016) ;


        datePickerDialog.show();






    }

    public void carregarLaboratorios (){

        Query consulta = referencia.orderByChild("laboratorio");




        consulta.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listalab.clear();
                for (DataSnapshot dt : dataSnapshot.getChildren()) {



                        Usuario u = dt.getValue(Usuario.class);
                        listalab.add(u);

                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
