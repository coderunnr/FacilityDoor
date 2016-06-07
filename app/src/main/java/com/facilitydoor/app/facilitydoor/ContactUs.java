package com.facilitydoor.app.facilitydoor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ContactUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        Toast.makeText(getApplicationContext(),"this is to contact us.....",Toast.LENGTH_LONG).show();
    }
}
