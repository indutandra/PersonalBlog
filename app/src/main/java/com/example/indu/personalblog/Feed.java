package com.example.indu.personalblog;

public class Feed {
    int _id;
    String _title;
    String _description;
    public Feed(){   }
    public Feed(int id, String name, String _phone_number){
        this._id = id;
        this._title = name;
        this._description = _phone_number;
    }

    public Feed(String name, String _phone_number){
        this._title = name;
        this._description = _phone_number;
    }
    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getTitle(){
        return this._title;
    }

    public void setTitle(String title){
        this._title = title;
    }

    public String getDescription(){
        return this._description;
    }

    public void setDescription(String description){
        this._description = description;
    }
}