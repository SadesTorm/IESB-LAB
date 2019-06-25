package com.sadestorm.iesblab;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.YuvImage;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;
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
    Button salvarBtn;
    Button cancelarBtn;
    public String entrada, saida,data,matricula,lab;
    public Usuario usuario;
    public Reservas resevar;

    TimePickerDialog timePickerDialog;

    DatePickerDialog datePickerDialog;
    Calendar calendario;
    public Auxiliar aux = new Auxiliar();

    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference("Iesb");
    DatabaseReference referenciaUs = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth verificaEmail = FirebaseAuth.getInstance();

    public String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_reserva);



        dataBtn = findViewById(R.id.btnData);
        labBtn = findViewById(R.id.btnLab);
        horaEntradaBtn = findViewById(R.id.btnHoraEntrada);
        horaSaidaBtn = findViewById(R.id.btnHoraSaida);
        salvarBtn = findViewById(R.id.btnSalvar);
        cancelarBtn = findViewById(R.id.btnCancelar);

        horaEntradaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clockDialogEntrada(v);

            }
        });
        dataBtn.setEnabled(false);
        horaSaidaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clockDialogSaida(v);

            }
        });


        labBtn.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v) {
                 Intent i = new Intent(NovaReserva.this, RecycleLaboratorio.class);
                 startActivityForResult(i, 1);
                 dataBtn.setEnabled(true);
                 }
             }

        );


        dataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarioDialog(v);


            }
        });

        carregaDadosUs();


        salvarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matricula = usuario.getMatricula();
                resevar = new Reservas(matricula,data,entrada,lab,saida,"Reservado");

                DatabaseReference dbUsuario = referencia.child("Reservas");
                //dbUsuario.child(matricula).setValue(resevar);
                dbUsuario.push().setValue(resevar);
                Alerta(v);

            }
        });

        cancelarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NovaReserva.this, PerfilUsuario.class);
                startActivity(i);
            }
        });

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
                data = (dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, ano, mes, day);

        datePickerDialog.show();
    }

    public void clockDialogEntrada(View view) {

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());

        int h = cal.get(Calendar.HOUR_OF_DAY);
        int m = cal.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(NovaReserva.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                horaEntradaBtn.setText(hourOfDay + " : " + minute);
                entrada = (hourOfDay + " : " + minute);

            }
        }, h, m, true);
        timePickerDialog.show();
    }

    public void clockDialogSaida(View view) {

        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));

        int h = cal.get(Calendar.HOUR_OF_DAY);
        int m = cal.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(NovaReserva.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                horaSaidaBtn.setText(hourOfDay + " : " + minute);
                saida = (hourOfDay + " : " + minute);
            }
        }, h, m, true);
        timePickerDialog.show();
    }

    public void carregaDadosUs() {

        final String email;
        DatabaseReference dbUsuario = referenciaUs.child("Iesb").child("Usuario");
        email = verificaEmail.getCurrentUser().getEmail(); // verifica email logado.

        Query consulta = dbUsuario.orderByChild("email").equalTo(email);
        consulta.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dt : dataSnapshot.getChildren()) {

                    if (dt.child("email") != null && dt.child("email").getValue().equals(email)) {
                        usuario = dt.getValue(Usuario.class);


                        return;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void Alerta(View view){


        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("Efetuar Reserva");
        dialog.setMessage("Deseja efetuar uma nova reserva ?");

        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(NovaReserva.this, NovaReserva.class);
                startActivity(i);

            }
        });

        dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent i = new Intent(NovaReserva.this, PerfilUsuario.class);
                startActivity(i);
            }
        });

        dialog.create();
        dialog.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){

               lab = data.getStringExtra("labss");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}

