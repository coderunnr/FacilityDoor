package com.facilitydoor.app.facilitydoor.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facilitydoor.app.facilitydoor.AddressStore;
import com.facilitydoor.app.facilitydoor.BookServices;
import com.facilitydoor.app.facilitydoor.DatabaseUtils.CartDatabase;
import com.facilitydoor.app.facilitydoor.Models.DatabaseModel;
import com.facilitydoor.app.facilitydoor.Models.TrendCategories;
import com.facilitydoor.app.facilitydoor.R;
import com.facilitydoor.app.facilitydoor.ServicesExpandedFromView;
import com.squareup.picasso.Picasso;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 10/6/16.
 */
public class MyAdapterviewAddressStore extends RecyclerView.Adapter<MyAdapterviewAddressStore.ViewHolder> implements View.OnClickListener {
    private List<TrendCategories> categories;
    Context context;
    ArrayList<DatabaseModel> databaseModels;
    BookServices addressStore;

    @Override
    public void onClick(View v) {

    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView tv_name,price,date,time,quantity;
        ImageView delete;
        View v;


        public ViewHolder(View v, MyAdapterviewAddressStore myAdapter) {
            super(v);
            this.v=v;
            tv_name=(TextView)v.findViewById(R.id.tv_subname);
            price=(TextView)v.findViewById(R.id.tv_price);
            date=(TextView)v.findViewById(R.id.tv_date_address);
            time=(TextView)v.findViewById(R.id.tv_time_address);
            delete=(ImageView)v.findViewById(R.id.img_cart_delete);
            quantity=(TextView)v.findViewById(R.id.tv_quantityaddress);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapterviewAddressStore(ArrayList<DatabaseModel> databaseModels, Context context,BookServices addressStore) {

        this.context=context;
        this.databaseModels=databaseModels;
        this.addressStore=addressStore;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapterviewAddressStore.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_adressstore, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v,this);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.tv_name.setText(databaseModels.get(position).getSub_name());
        holder.price.setText(databaseModels.get(position).getPrice());
        holder.date.setText(databaseModels.get(position).getDate());
        holder.time.setText(databaseModels.get(position).getTime());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartDatabase cartDatabase=new CartDatabase(context);
                try {
                    cartDatabase.open();
                    int l=Integer.parseInt(databaseModels.get(position).getRow_id());
                    cartDatabase.deleteEntry(l);
                    cartDatabase.close();
                    addressStore.updateviewofadapter();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        holder.quantity.setText(databaseModels.get(position).getUnits());


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return databaseModels.size();

    }
}