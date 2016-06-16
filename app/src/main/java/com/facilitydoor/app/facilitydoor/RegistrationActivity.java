package com.facilitydoor.app.facilitydoor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facilitydoor.app.facilitydoor.connectionutils.connectionclasses.Connection;
import com.facilitydoor.app.facilitydoor.connectionutils.connectionclasses.ConnectionRegister;

import java.util.HashMap;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name,email,password,phone;
    Button button_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        name=(EditText)findViewById(R.id.register_name);
        email=(EditText)findViewById(R.id.register_email);
        password=(EditText)findViewById(R.id.register_password);
        phone=(EditText)findViewById(R.id.register_phone);
        button_signup=(Button)findViewById(R.id.register);

        button_signup.setOnClickListener(this);

//        getSupportActionBar().setHomeButtonEnabled(true);
   //     getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public void onClick(View v) {
        String url="http://facilitydoor.com/api/regapp.php";
        String sname,semail,spassword,sphone;
        sname=name.getText().toString();
        semail=email.getText().toString();
        spassword=password.getText().toString();
        sphone=phone.getText().toString();
        HashMap<String,String> params=new HashMap<>();
        params.put("sname", sname);
        params.put("semailid", semail);
        params.put("spassword", spassword);
        params.put("snumber",sphone);
        ConnectionRegister connectionRegister=new ConnectionRegister(params,url,RegistrationActivity.this,this);
        connectionRegister.connect();

    }

    public void parsejson(String response) {
        Log.v("Working: Yo!",response);
    }
}

