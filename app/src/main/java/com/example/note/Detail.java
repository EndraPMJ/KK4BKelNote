package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Detail extends AppCompatActivity implements View.OnClickListener {

    EditText etContent;
    TextView tvTitle;
    String title, content;
    Integer id;
    Button btn_save, btn_hapus;
    RealmHelper realmHelper;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        etContent = findViewById(R.id.et_Content2);
        tvTitle = findViewById(R.id.tv_title);
        btn_save = findViewById(R.id.btn_save);
        btn_hapus = findViewById(R.id.btn_delete);

        id = Integer.parseInt(getIntent().getStringExtra("id"));
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");

        tvTitle.setText(title);
        etContent.setText(content);

        btn_hapus.setOnClickListener(this);
        btn_save.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == btn_save){
            realmHelper.update(id, tvTitle.getText().toString(),etContent.getText().toString());
            Toast.makeText(Detail.this, "Update Success", Toast.LENGTH_SHORT).show();
            tvTitle.setText("");
            etContent.setText("");
            finish();
        }else if (view == btn_hapus) {
            realmHelper.delete(id);
            Toast.makeText(Detail.this, "Delete Success", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
