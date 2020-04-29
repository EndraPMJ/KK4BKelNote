package com.example.note;

import android.content.Intent;

import io.realm.RealmObject;

public class NoteModel extends RealmObject {


    private Integer id;
    private String Title;
    private String Content;

    public void setId(Integer id){
        this.id = id;
    }
    public int getId(){
        return id;
    }

    public void setTitle(String Title){
        this.Title = Title;
    }
    public String getTitle() {
        return Title;
    }

    public void setContent(String content) {
        this.Content = content;
    }
    public String getContent() {
        return Content;
    }
}
