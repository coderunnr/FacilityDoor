package com.facilitydoor.app.facilitydoor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Log_Sign extends AppCompatActivity implements View.OnClickListener {
    TextView logo, userName, password, registerHere,forgotPassword;
    Button bLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log__sign);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        logo=(TextView)findViewById(R.id.facilitydoor);
        userName=(TextView)findViewById(R.id.user_name);
        password=(TextView)findViewById(R.id.password);
        forgotPassword=(TextView)findViewById(R.id.forgot_password);
        forgotPassword.setOnClickListener(this);
        bLogin=(Button)findViewById(R.id.login_button);
        bLogin.setOnClickListener(this);
        registerHere=(TextView)findViewById(R.id.register_here);
        registerHere.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.forgot_password:
                Intent intent=new Intent(this,ResetPassword.class);
                startActivity(intent);
                break;
            case R.id.login_button:
                break;
            case R.id.register_here:
                Intent i=new Intent(this,Registration.class);
                startActivity(i);
                break;
        }

    }
}
