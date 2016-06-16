package com.facilitydoor.app.facilitydoor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.facilitydoor.app.facilitydoor.Models.TrendCategories;
import com.facilitydoor.app.facilitydoor.Models.TrendServices;
import com.facilitydoor.app.facilitydoor.connectionutils.connectionclasses.Connection;
import com.facilitydoor.app.facilitydoor.connectionutils.connectionclasses.ConnectionCarousel;
import com.facilitydoor.app.facilitydoor.connectionutils.connectionclasses.ConnectionTrend;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,ImageListener {
    List<TrendCategories> categories;
    List<TrendServices> services;
    CarouselView carouselView;
    ProgressDialog pd;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<String> url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pd=new ProgressDialog(MainActivity.this,ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Getting Your Things Ready....");
        pd.show();
        String trendresponse=getIntent().getStringExtra("trend");
        String carouselresponse=getIntent().getStringExtra("carousel");

        //Checks the trend servics
        Connection connection=new Connection(getString(R.string.check),MainActivity.this);
        connection.connect();
        //Calls the trend service
        //ConnectionTrend connectionTrend = new ConnectionTrend(url, MainActivityExpandable.this, this);
        carouselView = (CarouselView) findViewById(R.id.carouselView);
      //  ConnectionCarousel connectionCarousel=new ConnectionCarousel("http://facilitydoor.com/api/carousel.php",MainActivityExpandable.this,this);
        //connectionCarousel.connect();

       // carouselView.setImageListener(imageListener);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycleview_mainactivity);


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
       // connectionTrend.connect();
        parsejson(trendresponse);
        parsejsoncarousel(carouselresponse);
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,BookServices.class);
                startActivity(intent);
            }
        });
        //mAdapter = new MyAdapterMain(str, MainActivityExpandable.this);
        // mRecyclerView.setAdapter(mAdapter);
      //  mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.logsign) {
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            // Handle the login signup
        } else if (id == R.id.Doorwallet) {
            Intent i = new Intent(MainActivity.this, Door_Wallet.class);
            startActivity(i);

        }  else if (id == R.id.terms_of_use) {
            Intent i = new Intent(MainActivity.this, ViewAllCategory.class);
            startActivity(i);


        } else if (id == R.id.privacy_policy) {
            Intent i = new Intent(MainActivity.this, PrivacyPolicy.class);
            startActivity(i);


        } else if (id == R.id.About_us) {
            Intent i = new Intent(MainActivity.this, AboutUs.class);
            startActivity(i);


        } else if (id == R.id.Contact_us) {
            Intent i = new Intent(MainActivity.this, ContactUs.class);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void parsejson(String response) {
        try {
            Log.v("checked", response);
            categories=new ArrayList<>();

            JSONArray jsonArray = new JSONArray(response);
            for(int i=0;i<jsonArray.length();i++) {
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                List<TrendServices> services=new ArrayList<>();

                String id=jsonObject.getString("id");
                String heading=jsonObject.getString("heading");
                TrendCategories trendCategories=new TrendCategories();
                trendCategories.setId(id);
                trendCategories.setHeading(heading);
                JSONArray jsonArray1=jsonObject.getJSONArray("services");
                for(int j=0;j<jsonArray1.length();j++) {
                    TrendServices trendServices=new TrendServices();
                    JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
                    String id1=jsonObject1.getString("service_id");
                    String name=jsonObject1.getString("service_name");
                    String image=jsonObject1.getString("aimage");
                    trendServices.setId(id1);
                    trendServices.setName(name);
                    trendServices.setImage(image);
                    services.add(trendServices);
                                }
                trendCategories.setServices(services);
                categories.add(trendCategories);
            };
        }catch (JSONException e){e.printStackTrace();}
pd.hide();
        mAdapter=new MyAdapterMain(categories,MainActivity.this);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void setImageForPosition(int position, ImageView imageView) {
        Picasso.with(MainActivity.this).load("http://facilitydoor.com/api/carousel/"+url.get(position)).into(imageView);
    }

    public void parsejsoncarousel(String response) {
        try {
            JSONArray jsonArray=new JSONArray(response);
            url=new ArrayList<>();
            url.add(jsonArray.getJSONObject(0).getString("image"));
            url.add(jsonArray.getJSONObject(1).getString("image"));
            url.add(jsonArray.getJSONObject(2).getString("image"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        carouselView.setImageListener(this);
        carouselView.setPageCount(3);

    }
}
