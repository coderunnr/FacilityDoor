package com.facilitydoor.app.facilitydoor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facilitydoor.app.facilitydoor.connectionutils.connectionclasses.ConnectionLogin;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    TextView logo, registerHere,forgotPassword;
    EditText  userName, password;
    Button bLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log__sign);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // logo=(TextView)findViewById(R.id.facilitydoor);
        userName=(EditText)findViewById(R.id.user_name);
        password=(EditText)findViewById(R.id.password);
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
                String url="http://facilitydoor.com/api/loginapp.php";
                HashMap<String,String> params=new HashMap<>();
                params.put("emailid",userName.getText().toString());
                params.put("password",password.getText().toString());
                ConnectionLogin connection=new ConnectionLogin(params,url,LoginActivity.this,this);
                connection.connect();
                break;
            case R.id.register_here:
                Intent i=new Intent(this,RegistrationActivity.class);
                startActivity(i);
                break;
        }

    }

    public void parsejson(String response) {

        Log.v("Login Working",response);
    }
}
