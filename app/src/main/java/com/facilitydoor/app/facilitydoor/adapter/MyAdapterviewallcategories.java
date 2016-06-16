package com.facilitydoor.app.facilitydoor.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facilitydoor.app.facilitydoor.Models.TrendCategories;
import com.facilitydoor.app.facilitydoor.R;
import com.facilitydoor.app.facilitydoor.ServicesExpanded;
import com.facilitydoor.app.facilitydoor.ServicesExpandedFromView;
import com.facilitydoor.app.facilitydoor.ViewAllCategory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 10/6/16.
 */
public class MyAdapterviewallcategories extends RecyclerView.Adapter<MyAdapterviewallcategories.ViewHolder> implements View.OnClickListener {
    private List<TrendCategories> categories;
    Context context;
    int clickposition;

    @Override
    public void onClick(View v) {

    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView tv;
        ImageView img;
        View v;

        public ViewHolder(View v, MyAdapterviewallcategories myAdapter) {
            super(v);
            this.v=v;
            tv=(TextView)v.findViewById(R.id.Tv_viewall);
            img=(ImageView)v.findViewById(R.id.Image_viewall);


        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapterviewallcategories(List<TrendCategories> categories, Context context,int clickposition) {
        this.categories=categories;
        this.context=context;
        this.clickposition=clickposition;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapterviewallcategories.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_viewallcategories, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v,this);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
holder.tv.setText(categories.get(clickposition).getServices().get(position).getName());
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, ServicesExpandedFromView.class);
                Bundle b = new Bundle();
                b.putString("servicename", categories.get(clickposition).getServices().get(position).getName());
                b.putString("serviceid", categories.get(clickposition).getServices().get(position).getId());
                b.putString("serviceimage", categories.get(clickposition).getServices().get(position).getImage());

                in.putExtras(b);
                context.startActivity(in);
            }
        });
        String url="http://facilitydoor.com/api/android/"+categories.get(clickposition).getServices().get(position).getImage();
        Picasso.with(context).load(url).into(holder.img);


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return categories.get(clickposition).getServices().size();

    }
}