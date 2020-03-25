package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    int hari,tahun;
    String bulan;

    public static String bulan(int month){
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return monthNames[month];
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar calendar = Calendar.getInstance();
        hari = calendar.get(Calendar.DAY_OF_MONTH);
        bulan = bulan(Calendar.MONTH);
        tahun = calendar.get(Calendar.YEAR);

        String Tanggal = hari+" "+bulan+" "+tahun;

        textView = findViewById(R.id.date);
        textView.setText(Tanggal);
    }

    protected void Tanggal(){

    }
}
