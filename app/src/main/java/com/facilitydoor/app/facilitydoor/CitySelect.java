package com.facilitydoor.app.facilitydoor;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import com.facilitydoor.app.facilitydoor.Models.CityModel;
import com.facilitydoor.app.facilitydoor.adapter.MyAdapter;
import com.facilitydoor.app.facilitydoor.connectionutils.connectionclasses.Connection;
import com.facilitydoor.app.facilitydoor.connectionutils.connectionclasses.ConnectionCityavail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 8/6/16.
 */
public class CitySelect extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ProgressDialog pd;
    ArrayList<String> categories;
    ArrayList<CityModel> cityModels;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cityselect);
        spinner=(Spinner)findViewById(R.id.spinner_cityselect);
        // Spinner click listener
        pd=new ProgressDialog(CitySelect.this,ProgressDialog.STYLE_SPINNER);
        pd.show();
        Toolbar t=(Toolbar)findViewById(R.id.toolbar_viewall);
        setSupportActionBar(t);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        spinner.setOnItemSelectedListener(this);
        String url="http://facilitydoor.com/api/availcity.php";
        ConnectionCityavail connectionCityavail=new ConnectionCityavail(url,CitySelect.this,this);
        connectionCityavail.connect();
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.v("Selected",position+"");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void parsejson(String response) {
        try {
            JSONArray jsonArray=new JSONArray(response);
            categories=new ArrayList<>();
            cityModels=new ArrayList<>();
            for(int i=0;i<jsonArray.length();i++)
            {
                CityModel cityModel=new CityModel();
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                String cityname=jsonObject.getString("name");
                String id=jsonObject.getString("id");
                cityModel.setCityname(cityname);
                cityModel.setId(id);
                categories.add(cityname);
                cityModels.add(cityModel);



            }

            ArrayAdapter<String> dataAdapter=new MyAdapter(this,R.layout.spinner_item,categories);
            spinner.setAdapter(dataAdapter);
            pd.hide();

        } catch (JSONException e) {
            e.printStackTrace();
            pd.hide();
        }
        Log.v("Done",response);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return false;
    }
}
