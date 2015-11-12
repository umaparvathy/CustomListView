package com.uv.customlistview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<ContactDetails> contactsAdapter;

    ArrayList<ContactDetails> contacts;
    ListView contactList;
    Button asc, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contacts = constructContacts();
        initializeViews();
    }


    private void initializeViews() {

        /*asc = (Button) findViewById(R.id.asc);
        desc = (Button) findViewById(R.id.desc);*/
        contactList = (ListView) findViewById(R.id.contactList);
        contactsAdapter = new ArrayAdapter<ContactDetails>(getApplicationContext(), R.layout.contact_item, contacts) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView == null){
                    convertView = getLayoutInflater().inflate(R.layout.contact_item, parent, false);
                }
                ContactDetails currentContact = contacts.get(position);
                TextView contactName = (TextView)convertView.findViewById(R.id.contactName);
                TextView phoneNumber = (TextView)convertView.findViewById(R.id.contactNumber);
                contactName.setText(currentContact.getName());
                phoneNumber.setText(currentContact.getPhoneNumber());

                return convertView;
            }
        };


        contactList.setAdapter(contactsAdapter);
    }

    private ArrayList<ContactDetails> constructContacts() {
        ArrayList<ContactDetails> contacts = new ArrayList<ContactDetails>(10);
        contacts.add(new ContactDetails("Friend1", "9999999999"));
        contacts.add(new ContactDetails("Friend2", "8888888888"));
        contacts.add(new ContactDetails("Friend3", "7777777777"));
        contacts.add(new ContactDetails("Friend4", "6666666666"));
        contacts.add(new ContactDetails("Friend5", "5555555555"));
        contacts.add(new ContactDetails("Friend6", "4444444444"));
        contacts.add(new ContactDetails("Friend7", "3333333333"));
        contacts.add(new ContactDetails("Friend8", "2222222222"));
        contacts.add(new ContactDetails("Friend9", "1111111111"));
        contacts.add(new ContactDetails("Friend10", "1010101010"));
        return contacts;
    }


}
