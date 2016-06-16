package com.facilitydoor.app.facilitydoor;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.facilitydoor.app.facilitydoor.Expandableutils.MyExpandableAdapter;
import com.facilitydoor.app.facilitydoor.Models.ServiceCategoryExpand;
import com.facilitydoor.app.facilitydoor.Models.ServiceExpand;
import com.facilitydoor.app.facilitydoor.Models.ServicesSubcategory;
import com.facilitydoor.app.facilitydoor.Models.SubcategoryModel;
import com.facilitydoor.app.facilitydoor.adapter.MyAdapterServicesExpanded;
import com.facilitydoor.app.facilitydoor.connectionutils.connectionclasses.ConnectionServicesExpanded;
import com.facilitydoor.app.facilitydoor.connectionutils.connectionclasses.ConnectionSubcategory;
import com.facilitydoor.app.facilitydoor.connectionutils.connectionclasses.CustomVolleyRequestQueue;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ServicesExpandedFromView extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener,ExpandableListView.OnChildClickListener{
    ArrayList<ServiceExpand> services;
    List<SubcategoryModel> subcategoryModels;
    String serviccename,serviceid,serviceimage;
    MyExpandableAdapter adapter;
  //  Spinner spinner;
    String categoryid;
    ProgressDialog progressDialog;
    int clickposition;
    ExpandableListView expandableList;
    int j=0;
    private ArrayList<String> parentItems = new ArrayList<String>();
    private ArrayList<Object> childItems = new ArrayList<Object>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_main2);
        FloatingActionButton floatingActionButton=(FloatingActionButton)findViewById(R.id.fab_cart);
        floatingActionButton.setOnClickListener(this);
        Bundle b=getIntent().getExtras();
        serviccename=b.getString("servicename","none");
        serviceid=b.getString("serviceid","none");
        serviceimage=b.getString("serviceimage","none");
       // String url="http://facilitydoor.com/api/service_category.php";
       // findViewById(R.id.b_servicesexpand_next).setOnClickListener(this);
        //ConnectionServicesExpanded connservicesExpanded=new ConnectionServicesExpanded(url,ServicesExpandedFromView.this,this);
        //connservicesExpanded.connect();
        progressDialog=new ProgressDialog(ServicesExpandedFromView.this,ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Loading");
        ImageView image=(ImageView)findViewById(R.id.logo);
        String image_url="http://facilitydoor.com/api/android/"+serviceimage;
        Picasso.with(ServicesExpandedFromView.this).load(image_url).into(image);
/*
ImageLoader imageLoader;
        NetworkImageView mNetworkImageView=(NetworkImageView)findViewById(R.id.logo);
        imageLoader= CustomVolleyRequestQueue.getInstance(ServicesExpandedFromView.this).getImageLoader();
        String url1="http://facilitydoor.com/api/android/"+serviceimage;
        Log.v("Image",serviceimage);
        imageLoader.get(url1, ImageLoader.getImageListener(mNetworkImageView,
                R.mipmap.ic_launcher, android.R.drawable
                        .ic_dialog_alert));
        mNetworkImageView.setImageUrl(url, imageLoader);*/
        expandableList =(ExpandableListView)findViewById(R.id.expandable_list);
        expandableList.setDividerHeight(2);
        expandableList.setGroupIndicator(null);
        expandableList.setClickable(true);

        // Set the Items of Parent
      //  setGroupParents();
        // Set The Child Data
        //setChildData();

        // Create the Adapter

        expandableList.setOnChildClickListener(this);
        progressDialog.show();
        String url="http://facilitydoor.com/api/service_subcategory.php";
        ConnectionSubcategory connectionSubcategory=new ConnectionSubcategory(url,ServicesExpandedFromView.this,this);
        connectionSubcategory.connect();
     //  spinner=(Spinner)findViewById(R.id.spinner_servicesexpand);
       // spinner.setOnItemSelectedListener(this);
    }
/*
    public void parsejson(String response) {
        try {
            Log.v("  Services Expanded  ", response);
            services = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {

                List<ServiceCategoryExpand> serviceCategory;
                serviceCategory = new ArrayList<>();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String service_id = jsonObject.getString("service_id");
                String service_name = jsonObject.getString("service_name");
                String header_id = jsonObject.getString("header_id");
                ServiceExpand serviceExpand = new ServiceExpand();
                serviceExpand.setService__id(service_id);
                serviceExpand.setService_name(service_name);
                //JSONArray jsonArray1 = new JSONArray("services_category");
                if(jsonObject.has("services_category")) {
                    JSONArray jsonArray1 = jsonObject.getJSONArray("services_category");
                    for (int j = 0; j < jsonArray1.length(); j++) {
                        JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
                        String category_id = jsonObject1.getString("category_id");
                        String category_name = jsonObject1.getString("category_name");
                        ServiceCategoryExpand serviceCategoryExpand = new ServiceCategoryExpand();
                        serviceCategoryExpand.setCategory_id(category_id);
                        serviceCategoryExpand.setCategory_name(category_name);
                        serviceCategory.add(serviceCategoryExpand);

                    }
                }
                serviceExpand.setServiceCategoryExpands(serviceCategory);
                services.add(serviceExpand);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int i=0;
        for(i=0;i<services.size();i++)
        {
            if(services.get(i).getService__id().equals(serviceid))
                break;
        }
List<ServiceCategoryExpand> serviceCategoryExpands=services.get(i).getServiceCategoryExpands();
        ArrayList<String> servicename=new ArrayList<>();
        j=i;
        for(i=0;i<serviceCategoryExpands.size();i++)
        {
            servicename.add(serviceCategoryExpands.get(i).getCategory_name());
        }
    //    ArrayAdapter<String> adapter=new MyAdapterServicesExpanded(ServicesExpandedFromView.this,R.layout.spinner_item,servicename);
      //  spinner.setAdapter(adapter);

    }*/

    public void parsejson(String response) {
        if (!response.equals("error")) {
        try {



                Log.v("---subcategory api---", response);
                subcategoryModels = new ArrayList<>();

                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    List<ServicesSubcategory> servicesSubcategories = new ArrayList<>();
                    String serviceid = jsonObject.getString("service_id");
                    String categoryid = jsonObject.getString("category_id");
                    String category_name = jsonObject.getString("category_name");
                    SubcategoryModel subcategoryModel = new SubcategoryModel();
                    subcategoryModel.setCategoryId(categoryid);
                    subcategoryModel.setServiceId(serviceid);
                    subcategoryModel.setCategoryname(category_name);
                    if (jsonObject.has("services_subcategory")) {
                        JSONArray jsonArray1 = jsonObject.getJSONArray("services_subcategory");

                        for (int j = 0; j < jsonArray1.length(); j++) {
                            JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
                            String subcategoryid = jsonObject1.getString("subcategory_id");
                            String subcategoryname = jsonObject1.getString("subcategory_name");
                            int howmuch = 1;
                            String price1 = "", price2 = "", price3 = "", price4 = "", price5 = "";
                            if (jsonObject1.has("Price(1 Unit)")) {
                                price1 = jsonObject1.getString("Price(1 Unit)");
                            } else {
                                Log.v("Exception Causer", subcategoryname);
                            }
                            if (jsonObject1.has("Price(2 Unit)")) {
                                price2 = jsonObject1.getString("Price(2 Unit)");
                                howmuch++;
                                if (jsonObject1.has("Price(3 Unit)")) {
                                    price3 = jsonObject1.getString("Price(3 Unit)");
                                    howmuch++;
                                    if (jsonObject1.has("Price(4 Unit)")) {
                                        price4 = jsonObject1.getString("Price(4 Unit)");
                                        howmuch++;
                                        if (jsonObject1.has("Price(5 Unit)")) {
                                            price5 = jsonObject1.getString("Price(5 Unit)");
                                            howmuch++;

                                        }
                                    }
                                }


                            }
                            //  String price2 = jsonObject1.getString("Price(2 Unit)");
                            //String price3 = jsonObject1.getString("Price(3 Unit)");
                            // String price4=jsonObject1.getString("price(4 Unit)");
                            //String price5 = jsonObject1.getString("Price(5 Unit)");

                            ServicesSubcategory servicesSubcategory = new ServicesSubcategory();
                            servicesSubcategory.setGetSubcategoryname(subcategoryname);
                            servicesSubcategory.setSubcategoryid(subcategoryid);
                            servicesSubcategory.setPrice1(price1);
                            servicesSubcategory.setIsChecked("false");
                            servicesSubcategory.setPrice2(price2);
                            servicesSubcategory.setPrice3(price3);
                            servicesSubcategory.setPrice4(price4);
                            servicesSubcategory.setPrice5(price5);
                            servicesSubcategory.setHowmuch(String.valueOf(howmuch));

                            servicesSubcategories.add(servicesSubcategory);

                        }

                    }
                    subcategoryModel.setServicesSubcategories(servicesSubcategories);

                    subcategoryModels.add(subcategoryModel);


                }

            }catch(JSONException e){
                e.printStackTrace();
            }


     /*   int i=0;
        for(i=0;i<subcategoryModels.size();i++)
        {
            if(subcategoryModels.get(i).getServiceId().equals(serviceid)&&subcategoryModels.get(i).getCategoryId().equals(categoryid))
                break;
        }
        clickposition=i;
        ArrayList<String> arrayList=new ArrayList<>();
        for(int j=0;j<subcategoryModels.get(clickposition).getServicesSubcategories().size();j++)
        {
            arrayList.add(subcategoryModels.get(i).getServicesSubcategories().get(j).getGetSubcategoryname());
        }


*/
            List<SubcategoryModel> sub = new ArrayList<>();
            for (int j = 0; j < subcategoryModels.size(); j++) {

                if (subcategoryModels.get(j).getServiceId().equals(serviceid))
                    sub.add(subcategoryModels.get(j));
            }
            adapter = new MyExpandableAdapter(sub, ServicesExpandedFromView.this);
            adapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), this);
            //  adapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), this);

            // Set the Adapter to expandableList
            expandableList.setAdapter(adapter);
        }
        progressDialog.hide();
       // ArrayAdapter adapter=new MyAdapterServicesExpanded(ServicesExpandedFromView.this,R.layout.spinner_item,arrayList);
       // spinner.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        ArrayList<String> sucategory_id=new ArrayList<>();
        ArrayList<String> sub_name=new ArrayList<>();
        ArrayList<String> category=new ArrayList<>();
        //ArrayList<String> category_id=new ArrayList<>();
        ArrayList<String> price=new ArrayList<>();
        ArrayList<String> quantity=new ArrayList<>();
        List<SubcategoryModel> result=adapter.returnresult();
        for(int i=0;i<result.size();i++)
        {
            for(int j=0;j<result.get(i).getServicesSubcategories().size();j++)
            {
                if(result.get(i).getServicesSubcategories().get(j).getIsChecked().equalsIgnoreCase("true"))
                {
                    quantity.add(result.get(i).getServicesSubcategories().get(j).getQuantity());
                    sucategory_id.add(result.get(i).getServicesSubcategories().get(j).getSubcategoryid());
                    category.add(result.get(i).getCategoryId());
                    price.add(result.get(i).getServicesSubcategories().get(j).getFinalprice());
                    sub_name.add(result.get(i).getServicesSubcategories().get(j).getGetSubcategoryname());

                    Log.v ("step 1-------","step1   completed");
                }
            }
        }

        Intent in=new Intent(ServicesExpandedFromView.this,Picker.class);
     //   in.putExtra("catid",categoryid);
       // in.putExtra("serviceid",serviceid);
        in.putExtra("subcategory_id",sucategory_id);
        in.putExtra("category",category);
        in.putExtra("price",price);
        in.putExtra("subcategoryname",sub_name);
        in.putExtra("quantity",quantity);

        startActivity(in);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
     categoryid=services.get(j).getServiceCategoryExpands().get(position).getCategory_id();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        categoryid=services.get(j).getServiceCategoryExpands().get(0).getCategory_id();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return false;
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        return false;
    }
}

