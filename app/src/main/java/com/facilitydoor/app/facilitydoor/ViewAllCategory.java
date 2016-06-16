package com.facilitydoor.app.facilitydoor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.facilitydoor.app.facilitydoor.Models.TrendCategories;
import com.facilitydoor.app.facilitydoor.Models.TrendServices;
import com.facilitydoor.app.facilitydoor.adapter.MyAdapterviewallcategories;
import com.facilitydoor.app.facilitydoor.connectionutils.connectionclasses.ConnectionHomeMaintenance;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewAllCategory extends AppCompatActivity {
    ArrayList<TrendCategories> categories;
        int clickposition;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    TextView tv;
    Toolbar t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_maintenance);
        String url = "http://facilitydoor.com/api/services.php";
        HashMap<String,String> params=new HashMap<>();
        params.put("city_id", "9");
        ConnectionHomeMaintenance connHomeConnection = new ConnectionHomeMaintenance(url, ViewAllCategory.this, this,params);
        connHomeConnection.connect();
        t=(Toolbar)findViewById(R.id.toolbar_viewall);
        setSupportActionBar(t);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_home_maintenance);
        mRecyclerView.setHasFixedSize(true);
        clickposition=getIntent().getIntExtra("clickposition",1);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(ViewAllCategory.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
      //  connHomeConnection.connect();


    }

    public void parsejson(String response) {

        try {
            Log.v("    ---view all------ ", response);
            categories = new ArrayList<>();

            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                List<TrendServices> services = new ArrayList<>();
                String id = jsonObject.getString("id");
                String heading = jsonObject.getString("heading");
                TrendCategories trendCategories = new TrendCategories();
                trendCategories.setId(id);
                trendCategories.setHeading(heading);
                JSONArray jsonArray1 = jsonObject.getJSONArray("services");
                for (int j = 0; j < jsonArray1.length(); j++) {
                    TrendServices trendServices = new TrendServices();
                    JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
                    String id1 = jsonObject1.getString("service_id");
                    String name = jsonObject1.getString("service_name");
                    String image=jsonObject1.getString("aimage");
                    trendServices.setId(id1);
                    trendServices.setName(name);
                    trendServices.setImage(image);
                    services.add(trendServices);
                }
                trendCategories.setServices(services);
                categories.add(trendCategories);
            }
            ;
        } catch (JSONException e) {
            e.printStackTrace();
        }


        t.setTitle(categories.get(clickposition).getHeading());
        mAdapter = new MyAdapterviewallcategories(categories,ViewAllCategory.this,clickposition);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return false;
    }
}