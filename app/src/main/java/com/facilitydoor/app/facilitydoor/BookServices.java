package com.facilitydoor.app.facilitydoor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.facilitydoor.app.facilitydoor.DatabaseUtils.CartDatabase;
import com.facilitydoor.app.facilitydoor.Models.DatabaseModel;
import com.facilitydoor.app.facilitydoor.adapter.MyAdapterviewAddressStore;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by root on 15/6/16.
 */
public class BookServices extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<DatabaseModel> databaseModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinatorforcart);
        Toolbar t=(Toolbar)findViewById(R.id.toolbar_addressstore2);
        setSupportActionBar(t);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        databaseModels=new ArrayList<>();

        CartDatabase cartDatabase=new CartDatabase(BookServices.this);
        try {
            cartDatabase.open();
            databaseModels=cartDatabase.getData();
            cartDatabase.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        recyclerView=(RecyclerView)findViewById(R.id.recycle_adressstore);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(BookServices.this);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new MyAdapterviewAddressStore(databaseModels,BookServices.this,this);
        recyclerView.setAdapter(adapter);
        findViewById(R.id.fab_next_cart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookServices.this, AddressStore.class);
                startActivity(intent);
            }
        });

    }

    public void updateviewofadapter() {
        databaseModels=new ArrayList<>();

        CartDatabase cartDatabase=new CartDatabase(BookServices.this);
        try {
            cartDatabase.open();
            databaseModels=cartDatabase.getData();
            cartDatabase.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapter=new MyAdapterviewAddressStore(databaseModels,BookServices.this,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return false;
    }
}
