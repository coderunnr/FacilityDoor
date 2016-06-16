package com.facilitydoor.app.facilitydoor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.facilitydoor.app.facilitydoor.Models.ServiceCategoryExpand;
import com.facilitydoor.app.facilitydoor.Models.ServiceExpand;
import com.facilitydoor.app.facilitydoor.Models.TrendServices;
import com.facilitydoor.app.facilitydoor.connectionutils.connectionclasses.ConnectionServicesExpanded;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ServicesExpanded extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_expanded);
      /*  String url="http://facilitydoor/api/service_category.php";
        ConnectionServicesExpanded connservicesExpanded=new ConnectionServicesExpanded(url,ServicesExpanded.this,this);
        connservicesExpanded.connect();*/
    }


}
