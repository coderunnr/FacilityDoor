package com.facilitydoor.app.facilitydoor;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facilitydoor.app.facilitydoor.DatabaseUtils.CartDatabase;
import com.facilitydoor.app.facilitydoor.Models.DatabaseModel;
import com.facilitydoor.app.facilitydoor.adapter.MyAdapterviewAddressStore;
import com.facilitydoor.app.facilitydoor.connectionutils.connectionclasses.ConnectionBooking;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class AddressStore extends AppCompatActivity implements View.OnClickListener {

    EditText name,email,address1,address2,phone,coupon;
    View view_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_address_store);
        view_main=findViewById(R.id.address_main);
        Toolbar t=(Toolbar)findViewById(R.id.toolbar_addressstore);
        setSupportActionBar(t);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        findViewById(R.id.finalbook).setOnClickListener(this);
        name=(EditText)findViewById(R.id.register_name);
        email=(EditText)findViewById(R.id.register_email);
        address1=(EditText)findViewById(R.id.line1);
        address2=(EditText)findViewById(R.id.line2);
        phone=(EditText)findViewById(R.id.register_phone);
        coupon=(EditText)findViewById(R.id.register_coupon);

       /* for (int i=0; i<gsub_cat.size();i++) {
            sub_cat.append(gsub_cat.get(i));
            sub_cat.append("\n");
        }
        */


      /*  ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                gname);

        lv.setAdapter(arrayAdapter); */



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return false;
    }

    public void parsejson(String response) {

        Toast.makeText(AddressStore.this,"Success",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        String url="http://facilitydoor.com/api/";
        ArrayList<DatabaseModel> databaseModels=new ArrayList<>();
        HashMap<String,String> params=new HashMap<>();
        CartDatabase cartDatabase=new CartDatabase(AddressStore.this);
        try {
            cartDatabase.open();
            databaseModels=cartDatabase.getData();
            cartDatabase.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JSONArray book=new JSONArray();
        for(int i=0;i<databaseModels.size();i++)
        {
            JSONObject jsonObject=new JSONObject();
            try {
                jsonObject.put("subid",databaseModels.get(i).getSub_id());
                jsonObject.put("quantity",databaseModels.get(i).getUnits());
                jsonObject.put("price",databaseModels.get(i).getPrice());
                jsonObject.put("date",databaseModels.get(i).getDate());
                jsonObject.put("time",databaseModels.get(i).getTime());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            book.put(jsonObject);
        }
        params.put("book",book.toString());
        String name1=name.getText().toString();
        String email1=email.getText().toString();
        String line11=address1.getText().toString();
        String line21=address2.getText().toString();
        String phone=name.getText().toString();
        String coupon1=coupon.getText().toString();
       if(name1.equals("")||email1.equals("")||line11.equals("")||line21.equals("")||phone.equals(""))
       {
           Snackbar.make(view_main,"Please Fill All the Details",Snackbar.LENGTH_SHORT);
       }
        else {
           params.put("name",name1);
           params.put("email",email1);
           params.put("address",line11+","+line21);
           params.put("phone",phone);
           if(!coupon1.equals(""))
               params.put("coupon",coupon1);
           ConnectionBooking connectionBooking=new ConnectionBooking(params,url,AddressStore.this,this);
           connectionBooking.connect();
       }
        Log.v("duvbadkbvioadsv",params.toString());

        //params.put()

    }
}
