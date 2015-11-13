package com.uv.customlistview;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
        registerForContextMenu(contactList);
        contactsAdapter = new ArrayAdapter<ContactDetails>(getApplicationContext(), R.layout.contact_item, contacts) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.contact_item, parent, false);
                }
                ContactDetails currentContact = contacts.get(position);
                TextView contactName = (TextView) convertView.findViewById(R.id.contactName);
                TextView phoneNumber = (TextView) convertView.findViewById(R.id.contactNumber);
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select action");
        menu.add(1, 1001, 1, "Call");
        menu.add(1, 1002, 2, "SMS");
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int arrayAdapterPosition = menuInfo.position;
        ContactDetails selectedContact = contactsAdapter.getItem(arrayAdapterPosition);
        String number = selectedContact.getPhoneNumber();
        Intent callIntent;
        int id = item.getItemId();
        switch (id) {
            case 1001:
                Log.d("MainActivity", "Call option is selected");
                callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + number));

                /*if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return false;
                }*/
                try {
                    startActivity(callIntent);
                } catch(Exception e) {
                    Log.e("Exception", e.getMessage());
                }
                break;
            case 1002:
                Log.d("MainActivity", "SMS option is selected");
                callIntent = new Intent(Intent.ACTION_SENDTO);
                callIntent.addCategory(Intent.CATEGORY_DEFAULT);
                callIntent.setType("vnd.android-dir/mms-sms");
                callIntent.setData(Uri.parse("sms:" + number));
                try {
                    startActivity(callIntent);
                } catch(Exception e) {
                    Log.e("Exception", e.getMessage());
                }
                break;
        }

        return true;
    }
}
