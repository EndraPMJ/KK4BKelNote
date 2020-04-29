package com.example.note;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {
    Realm realm;

    public RealmHelper(Realm realm){
        this.realm = realm;
    }

    public void save(final NoteModel noteModel){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm!= null){
                    Log.e("Created","Database was Created");
                    Number currentIdNum = realm.where(NoteModel.class).max("id");
                    int nextid;
                    if (currentIdNum == null){
                        nextid = 1;
                    }else {
                        nextid = currentIdNum.intValue() + 1;
                    }
                    noteModel.setId(nextid);
                    NoteModel model = realm.copyToRealm(noteModel);
                }else {
                    Log.e("pppp","execute : Database not Exist");
                }
            }
        });
    }
    public List<NoteModel> getAllNote(){
        RealmResults<NoteModel> results = realm.where(NoteModel.class).findAll();
        return  results;
    }
    public void update(final Integer id, final String Title, final String Content){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                NoteModel model = realm.where(NoteModel.class)
                        .equalTo("id", id)
                        .findFirst();
                model.setTitle(Title);
                model.setContent(Content);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.e("pppppp", "onSuccess : Update Successfully");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
            }
        });
    }
    public void delete(Integer id){
        final RealmResults<NoteModel> model = realm.where(NoteModel.class).equalTo("id", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }
}


