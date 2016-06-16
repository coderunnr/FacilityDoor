package com.facilitydoor.app.facilitydoor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.toolbox.NetworkImageView;

import com.facilitydoor.app.facilitydoor.Models.TrendCategories;
import com.facilitydoor.app.facilitydoor.connectionutils.connectionclasses.CustomVolleyRequestQueue;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by my hp on 3/13/2016.
 */
public class MyAdapterMain extends RecyclerView.Adapter<MyAdapterMain.ViewHolder> implements View.OnClickListener {
    private List<TrendCategories> categories;
Context context;

    @Override
    public void onClick(View v) {

    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView,textView1,textView2,textView3,textView4;
        Button viewall;
        ImageView n1,n2,n3;
        LinearLayout ll1,ll2,ll3;

        public ViewHolder(View v, MyAdapterMain myAdapter) {
            super(v);
            mTextView=(TextView)v.findViewById(R.id.text_recycleitem_mainservices);
            textView1=(TextView)v.findViewById(R.id.tv_mainservices1);
            textView2=(TextView)v.findViewById(R.id.tv_mainservices2);
            textView3=(TextView)v.findViewById(R.id.tv_mainservices3);
           // textView4=(TextView)v.findViewById(R.id.tv_mainservices4);
            n1=(ImageView)v.findViewById(R.id.n1);
            n2=(ImageView)v.findViewById(R.id.n2);
            n3=(ImageView)v.findViewById(R.id.n3);
            viewall=(Button)v.findViewById(R.id.viewall);
            ll1=(LinearLayout)v.findViewById(R.id.ll1);
            ll2=(LinearLayout)v.findViewById(R.id.ll2);
            ll3=(LinearLayout)v.findViewById(R.id.ll3);


        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapterMain(List<TrendCategories> categories, Context context) {
        this.categories=categories;
this.context=context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapterMain.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleitem_mainservices, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v,this);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
       holder.mTextView.setText(categories.get(position).getHeading());
        holder.textView1.setText(categories.get(position).getServices().get(0).getName());
        holder.textView2.setText(categories.get(position).getServices().get(1).getName());
        holder.textView3.setText(categories.get(position).getServices().get(2).getName());
       // holder.textView4.setText(categories.get(position).getServices().get(3).getName());
        holder.viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(context,ViewAllCategory.class);
                in.putExtra("clickposition",position);
                context.startActivity(in);


            }
        });
        holder.ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ServicesExpandedFromView.class);
                intent.putExtra("serviceid",categories.get(position).getServices().get(0).getId());
                intent.putExtra("servicename",categories.get(position).getServices().get(0).getName());
                intent.putExtra("serviceimage",categories.get(position).getServices().get(0).getImage());
                context.startActivity(intent);
            }
        });
        holder.ll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ServicesExpandedFromView.class);
                intent.putExtra("serviceid",categories.get(position).getServices().get(1).getId());
                intent.putExtra("servicename",categories.get(position).getServices().get(1).getName());
                intent.putExtra("serviceimage",categories.get(position).getServices().get(1).getImage());
                context.startActivity(intent);
            }
        });
        holder.ll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ServicesExpandedFromView.class);
                intent.putExtra("serviceid",categories.get(position).getServices().get(2).getId());
                intent.putExtra("servicename",categories.get(position).getServices().get(2).getName());
                intent.putExtra("serviceimage",categories.get(position).getServices().get(2).getImage());
                context.startActivity(intent);
            }
        });




      //  ImageLoader imageLoader1,imageLoader2,imageLoader3;

        //imageLoader1= CustomVolleyRequestQueue.getInstance(context).getImageLoader();
        //imageLoader2= CustomVolleyRequestQueue.getInstance(context).getImageLoader();
        //imageLoader3= CustomVolleyRequestQueue.getInstance(context).getImageLoader();
        String url1="http://facilitydoor.com/api/android/"+categories.get(position).getServices().get(0).getImage();
        String url2="http://facilitydoor.com/api/android/"+categories.get(position).getServices().get(1).getImage();
        String url3="http://facilitydoor.com/api/android/"+categories.get(position).getServices().get(2).getImage();
   /*    // Log.v("Image", serviceimage);
        imageLoader1.get(url1, ImageLoader.getImageListener(holder.n1,
                R.mipmap.ic_launcher, android.R.drawable
                        .ic_dialog_alert));
        imageLoader2.get(url2, ImageLoader.getImageListener(holder.n2,
                R.mipmap.ic_launcher, android.R.drawable
                        .ic_dialog_alert));
        imageLoader3.get(url3, ImageLoader.getImageListener(holder.n3,
                R.mipmap.ic_launcher, android.R.drawable
                        .ic_dialog_alert));
        holder.n1.setImageUrl(url1, imageLoader1);
        holder.n2.setImageUrl(url2, imageLoader2);
        holder.n3.setImageUrl(url3, imageLoader3);
*/
        Picasso.with(context).load(url1).into(holder.n1);
        Picasso.with(context).load(url2).into(holder.n2);
        Picasso.with(context).load(url3).into(holder.n3);
      //  String image_url = Constants.BASE_URL + "static/" + question.getImage();

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return categories.size();

    }
}