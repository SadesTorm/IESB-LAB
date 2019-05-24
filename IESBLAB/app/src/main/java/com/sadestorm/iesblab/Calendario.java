package com.sadestorm.iesblab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;


import java.util.Calendar;

public class Calendario extends AppCompatActivity {
       private CalendarView calendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange( CalendarView view, int year, int month, int dayOfMonth) {

                Auxiliar  aux = new Auxiliar(year,month,dayOfMonth);

                Intent i = new Intent(Calendario.this, NovaReserva.class);
                startActivity(i);
            }
        });
    }
}
