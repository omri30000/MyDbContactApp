package com.example.mydbcontactapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class DisplayAllContacts extends Activity {
    ListView lvContacts;
    ArrayList<Contact> ary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all_contacts);
        lvContacts = findViewById(R.id.listViewContacts);
        Dal dal = new Dal(this);
        ary = dal.getAllContacts();
        ContactsAdapter ad = new ContactsAdapter(
                this,
                R.layout.contact_layout, ary);

        lvContacts.setAdapter(ad);
    }
}