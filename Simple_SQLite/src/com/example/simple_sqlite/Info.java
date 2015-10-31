package com.example.simple_sqlite;

public class Info {
    int id;
    String name;
    String address;
     
    // Empty constructor
    public Info(){
         
    }
    // constructor
    public Info(int id, String name, String address){
        this.id = id;
        this.name = name;
        this.address = address;
    }
     
    // constructor
    public Info(String name, String address){
        this.name = name;
        this.address = address;
    }
    
    /***********************************/
    
    public int getID(){
        return this.id;
    }

    public void setID(int id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getAddress(){
        return this.address;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
}