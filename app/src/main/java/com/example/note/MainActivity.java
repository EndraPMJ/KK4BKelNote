package com.example.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView date;
    int hari,tahun;
    String bulan;
    Button btn_Create;
    Intent pindah_create, pindah_calender;
    Realm realm;
    RealmHelper realmHelper;
    RecyclerView recyclerView;
    NoteAdapter noteAdapter;
    List<NoteModel> noteModels;

    public static String bulan(int month){
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return monthNames[month];
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Calendar calendar = Calendar.getInstance();
        hari = calendar.get(Calendar.DAY_OF_MONTH);
        bulan = bulan(Calendar.MONTH);
        tahun = calendar.get(Calendar.YEAR);

        String Tanggal = hari+" "+bulan+" "+tahun;

        date = findViewById(R.id.date);
        date.setText(Tanggal);


        date = findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pindah_calender = new Intent(MainActivity.this, com.example.note.calendar.class);
                startActivity(pindah_calender);
            }
        });

        btn_Create = findViewById(R.id.btn_create);
        btn_Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pindah_create = new Intent(MainActivity.this, note.class);
                startActivity(pindah_create);
            }
        });


        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        realmHelper = new RealmHelper(realm);
        noteModels = new ArrayList<>();

        noteModels = realmHelper.getAllNote();
        show();
    }

    public void onRestart(){
        super.onRestart();
        noteAdapter.notifyDataSetChanged();
        show();
    }


    private void show() {
        noteAdapter = new NoteAdapter(this,noteModels);
        recyclerView.setAdapter(noteAdapter);
    }

    protected void Tanggal(){

    }

    @Override
    public void onClick(View view) {


    }
}
