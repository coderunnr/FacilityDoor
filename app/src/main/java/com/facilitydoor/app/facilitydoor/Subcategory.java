package com.facilitydoor.app.facilitydoor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.facilitydoor.app.facilitydoor.Models.ServicesSubcategory;
import com.facilitydoor.app.facilitydoor.Models.SubcategoryModel;
import com.facilitydoor.app.facilitydoor.adapter.MyAdapterServicesExpanded;
import com.facilitydoor.app.facilitydoor.connectionutils.connectionclasses.ConnectionSubcategory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Subcategory extends AppCompatActivity{
   /* List<SubcategoryModel> subcategoryModels;
  //  List<ServicesSubcategory> servicesSubcategories;
    Spinner spinner;
    TextView priceshow;
    Button picker;
    String serviceid;
    String categoryid;
    int clickposition;
    String sub_id,name,paisa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);
        Toolbar t=(Toolbar)findViewById(R.id.toolbar_subcatg);
        setSupportActionBar(t);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        serviceid=getIntent().getStringExtra("serviceid");
        categoryid=getIntent().getStringExtra("catid");
        String url="http://facilitydoor.com/api/service_subcategory.php";
        ConnectionSubcategory connectionSubcategory=new ConnectionSubcategory(url,Subcategory.this,this);
        connectionSubcategory.connect();
        picker=(Button)findViewById(R.id.picker);
        picker.setOnClickListener(this);
        spinner=(Spinner)findViewById(R.id.spinner_subcategory);
        spinner.setOnItemSelectedListener(this);
    }

    public void parsejson(String response) {
        try {



            Log.v("---subcategory api---",response);
            subcategoryModels =new ArrayList<>();

            JSONArray jsonArray=new JSONArray(response);
            for(int i=0;i<jsonArray.length();i++){
            JSONObject jsonObject=jsonArray.getJSONObject(i);
                List<ServicesSubcategory> servicesSubcategories=new ArrayList<>();
                String serviceid=jsonObject.getString("service_id");
                String categoryid=jsonObject.getString("category_id");
                SubcategoryModel subcategoryModel=new SubcategoryModel();
                subcategoryModel.setCategoryId(categoryid);
                subcategoryModel.setServiceId(serviceid);
                if(jsonObject.has("services_subcategory")) {
                    JSONArray jsonArray1 = jsonObject.getJSONArray("services_subcategory");

                    for (int j = 0; j < jsonArray1.length(); j++) {
                        JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
                        String subcategoryid = jsonObject1.getString("subcategory_id");
                        String subcategoryname = jsonObject1.getString("subcategory_name");
                        String price1 = jsonObject1.getString("Price(1 Unit)");
                      //  String price2 = jsonObject1.getString("Price(2 Unit)");
                        //String price3 = jsonObject1.getString("Price(3 Unit)");
                        // String price4=jsonObject1.getString("price(4 Unit)");
                        //String price5 = jsonObject1.getString("Price(5 Unit)");
                        //String price6 = jsonObject1.getString("Price(6 Unit)");
                        ServicesSubcategory servicesSubcategory = new ServicesSubcategory();
                        servicesSubcategory.setGetSubcategoryname(subcategoryname);
                        servicesSubcategory.setSubcategoryid(subcategoryid);
                        servicesSubcategory.setPrice1(price1);
                        //servicesSubcategory.setPrice2(price2);
                       // servicesSubcategory.setPrice3(price3);
                        //  servicesSubcategory.setPrice4(price4);
                        //servicesSubcategory.setPrice5(price5);
                      //  servicesSubcategory.setPrice6(price6);
                        servicesSubcategories.add(servicesSubcategory);

                    }

                }
                subcategoryModel.setServicesSubcategories(servicesSubcategories);

                subcategoryModels.add(subcategoryModel);


            }

        }catch (JSONException e){e.printStackTrace();}


        int i;
        for(i=0;i<subcategoryModels.size();i++)
        {
            if(subcategoryModels.get(i).getServiceId().equals(serviceid)&&subcategoryModels.get(i).getCategoryId().equals(categoryid))
                break;
        }
        clickposition=i;
        ArrayList<String> arrayList=new ArrayList<>();
        for(int j=0;j<subcategoryModels.get(i).getServicesSubcategories().size();j++)
        {
                arrayList.add(subcategoryModels.get(i).getServicesSubcategories().get(j).getGetSubcategoryname());
        }
        ArrayAdapter adapter=new MyAdapterServicesExpanded(Subcategory.this,R.layout.spinner_item,arrayList);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
sub_id=subcategoryModels.get(clickposition).getServicesSubcategories().get(position).getSubcategoryid();

        name=subcategoryModels.get(clickposition).getServicesSubcategories().get(position).getGetSubcategoryname();
        paisa=subcategoryModels.get(clickposition).getServicesSubcategories().get(position).getPrice1();
        priceshow=(TextView)findViewById(R.id.priceshow);
        priceshow.setText(paisa);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        sub_id=subcategoryModels.get(clickposition).getServicesSubcategories().get(0).getSubcategoryid();
        name=subcategoryModels.get(clickposition).getServicesSubcategories().get(0).getGetSubcategoryname();
        paisa=subcategoryModels.get(clickposition).getServicesSubcategories().get(0).getPrice1();



    }

    @Override
    public void onClick(View v) {
        Log.v("sub_id",sub_id);
        Intent i=new Intent(Subcategory.this,Picker.class);
        i.putExtra("sub_id",sub_id);
        i.putExtra("name",name);
        i.putExtra("paisa",paisa);
        startActivity(i);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return false;
    }*/
}
