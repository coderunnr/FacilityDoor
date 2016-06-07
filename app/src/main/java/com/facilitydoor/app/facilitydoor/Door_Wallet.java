package com.facilitydoor.app.facilitydoor;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Door_Wallet extends AppCompatActivity {
    TextView balance,add;
    ImageView addbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door__wallet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        balance=(TextView)findViewById(R.id.balance);

        addbtn=(ImageView)findViewById(R.id.addbtn);
        addbtn.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Intent i=new Intent(Door_Wallet.this,PaymentGateway.class);
                                          startActivity(i);

                                      }
                                  });
        add=(TextView)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Door_Wallet.this,PaymentGateway.class);
                startActivity(i);


            }
        });



    }

}
