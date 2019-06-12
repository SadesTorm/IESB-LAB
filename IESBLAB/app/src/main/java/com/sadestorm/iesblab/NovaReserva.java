package com.sadestorm.iesblab;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.YuvImage;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class NovaReserva extends AppCompatActivity {


    TextView dataTxt;
    Button dataBtn;
    Button labBtn;
    Button horaEntradaBtn;
    Button horaSaidaBtn;


    TimePickerDialog timePickerDialog;

    DatePickerDialog datePickerDialog;
    Calendar calendario;
    public Auxiliar aux = new Auxiliar();

    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference("Iesb");

    public String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_reserva);

        dataTxt = findViewById(R.id.txtData);

        dataBtn = findViewById(R.id.btnData);
        labBtn = findViewById(R.id.btnLab);
        horaEntradaBtn = findViewById(R.id.btnHoraEntrada);
        horaSaidaBtn = findViewById(R.id.btnHoraSaida);

        horaEntradaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clockDialogEntrada(v);
            }
        });

        horaSaidaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clockDialogSaida(v);

            }
        });
       // String a;

        labBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iRecycleViewLabs = new Intent(NovaReserva.this, RecycleLaboratorio.class);


                //  carregarLaboratorios();

                startActivity(iRecycleViewLabs);



            }
        });



        dataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarioDialog(v);


            }
        });
        Intent i = getIntent();
        Bundle bd = i.getExtras();
        if(bd != null)
        {
            String getName = (String) bd.get("labss");
            dataTxt.setText(getName);
        }

    }

    public void calendarioDialog(View view) {

        calendario = Calendar.getInstance();


        int day = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH);
        int ano = calendario.get(Calendar.YEAR);


        datePickerDialog = new DatePickerDialog(NovaReserva.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dataBtn.setText(dayOfMonth + " / " + (month + 1) + "/" + year);
            }
        }, ano, mes, day);

        datePickerDialog.show();
    }

    public void clockDialogEntrada(View view) {

        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));

        int h = cal.get(Calendar.HOUR_OF_DAY);
        int m = cal.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(NovaReserva.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                horaEntradaBtn.setText(hourOfDay + " : " + minute);

            }
        }, h, m, true);
        timePickerDialog.show();
    }

    public void clockDialogSaida(View view) {

        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));

        int h = cal.get(Calendar.HOUR_OF_DAY);
        int m = cal.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(NovaReserva.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                horaSaidaBtn.setText(hourOfDay + " : " + minute);

            }
        }, h, m, true);
        timePickerDialog.show();
    }

/*
    public void carregarLaboratorios() {

        Query consulta = referencia.orderByChild("Iesb");

        final String a;

        consulta.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
String a;

                for (DataSnapshot dt : dataSnapshot.getChildren()) {

                    if( dt.child("auxiliar").getValue() != null) {

                       a   = dt.child("auxiliar").getValue().toString();

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

*/
}

