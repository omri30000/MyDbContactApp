package com.example.mydbcontactapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class Dal extends SQLiteAssetHelper {
    public Dal(Context context) {

        super(context, "my_db_contacts.db", null, 1);
    }

    public void addContact(String name, String email, String phone,
                           String street, String city) {


        SQLiteDatabase db = getWritableDatabase();
        String sql_INSERT = "INSERT INTO contacts (name ,email ,phone ,street ,city) values(?,?,?,?,?)";
        SQLiteStatement statment = db.compileStatement(sql_INSERT);

        statment.bindString(1, name);
        statment.bindString(2, email);
        statment.bindString(3, phone);
        statment.bindString(4, street);
        statment.bindString(5, city);
        statment.execute();
    }

    // inserts a new contact in the database
    public void addContact_withimg(String name, String email, String phone,
                                   String street, String city, byte[] img) {

        SQLiteDatabase db = this.getWritableDatabase();
        String sql_INSERT = "INSERT INTO contacts (name ,email ,phone ,street ,city,img) values(?,?,?,?,?,?)";
        SQLiteStatement statment = db.compileStatement(sql_INSERT);

        statment.bindString(1, name);
        statment.bindString(2, email);
        statment.bindString(3, phone);
        statment.bindString(4, street);
        statment.bindString(5, city);
        statment.bindBlob(6, img);//<------------------img
        statment.execute();
    }

    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> ary = new ArrayList<>();

        String st = "select * from contacts";
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery(st, null);
        while (cursor.moveToNext()) {
            Contact c = new Contact();
            c.setName(cursor.getString(cursor.getColumnIndex("name")));
            c.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            c.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
            c.setStreet(cursor.getString(cursor.getColumnIndex("street")));
            c.setCity(cursor.getString(cursor.getColumnIndex("city")));
            c.setContactImage(cursor.getBlob(cursor.getColumnIndex("img")));

            ary.add(c);
        }
        return ary;

    }
}
