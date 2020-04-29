package com.example.note;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DbNote extends Application {
        public void onCreate(){
            super.onCreate();
            Realm.init(this);
            RealmConfiguration configuration =new RealmConfiguration.Builder()
                    .name("note.db")
                    .schemaVersion(0)
                    .build();
            Realm.setDefaultConfiguration(configuration);
        }
}
