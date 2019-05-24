package com.sadestorm.iesblab;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Tela1 extends AppCompatActivity {
    private AlertDialog alerta;

    Button teste;
    TextView testeView;

    DatePickerDialog dpd;
    Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela1);

       teste = findViewById(R.id.btnTeste);
       testeView = findViewById(R.id.testeTxtView);



        teste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                criarCalendario(v);
            }
        });

    }

    private void criarCalendario(View view) {




        //-------------------------- calendario date PIKER -------------------
        c  = Calendar.getInstance();
         c.set(25,02,2019);
        c.set(Calendar.MONTH,05);
        c.set(Calendar.YEAR,2013);


        int day = c.get(Calendar.DAY_OF_MONTH);
        int mes = c.get(Calendar.MONTH);
        int ano = c.get(Calendar.YEAR);


        dpd = new DatePickerDialog(Tela1.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                testeView.setText(dayOfMonth + " / " + (month + 1) + "/" + year);


            }
        }, day, mes, ano);

        dpd.show();

        //


        /*  //*//*/*//*//*//*/****** CRIANDO ALERT DIALOG *************
        View v = LayoutInflater.from(this).inflate(R.layout.alert_dialog_calendario, null);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this, R.style.Theme_AppCompat_DayNight_Dialog_Alert);

        mBuilder.setView(v);
        mBuilder.create();

        final AlertDialog dialog = mBuilder.create();
        dialog.show();
    }*/
    }
}
