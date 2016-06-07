package com.facilitydoor.app.facilitydoor;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mRecyclerView = (RecyclerView)findViewById(R.id.recycleview_mainactivity);


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<String> str=new ArrayList<>();
        str.add("Puja");
        str.add("Plumber");
        str.add("Courier");
        str.add("Puja");
        str.add("Plumber");
        str.add("Courier");
        str.add("Puja");
        str.add("Plumber");
        str.add("Courier");
        str.add("Puja");
        str.add("Plumber");
        str.add("Courier");
        mAdapter = new MyAdapterMain(str,MainActivity.this);
        // mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setAdapter(mAdapter);
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
           Intent i=new Intent(MainActivity.this,Log_Sign.class);
            startActivity(i);
            // Handle the login signup
        } else if (id == R.id.Doorwallet) {
            Intent i=new Intent(MainActivity.this,Door_Wallet.class);
            startActivity(i);

        } else if (id == R.id.ratecard) {



        } else if (id == R.id.terms_of_use) {
            Intent i=new Intent(MainActivity.this,Door_Wallet.class);
            startActivity(i);


        } else if (id == R.id.privacy_policy) {
            Intent i=new Intent(MainActivity.this,PrivacyPolicy.class);
            startActivity(i);


        } else if (id ==R.id.About_us){
            Intent i=new Intent(MainActivity.this,AboutUs.class);
            startActivity(i);


        } else if (id ==R.id.Contact_us) {
            Intent i=new Intent(MainActivity.this,ContactUs.class);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
