package com.facilitydoor.app.facilitydoor.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facilitydoor.app.facilitydoor.Models.ServiceCategoryExpand;
import com.facilitydoor.app.facilitydoor.Models.ServiceExpand;
import com.facilitydoor.app.facilitydoor.Models.TrendCategories;
import com.facilitydoor.app.facilitydoor.R;
import com.facilitydoor.app.facilitydoor.ServicesExpanded;
import com.facilitydoor.app.facilitydoor.ServicesExpandedFromView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 10/6/16.
 */
public class MyAdapterServicesExpanded extends ArrayAdapter<String> {
    Context context;
    //List<ServiceCategoryExpand> categories;
    ArrayList<String> objects;
   // String serviceid;

    public MyAdapterServicesExpanded(Context ctx, int txtViewResourceId,ArrayList<String> objects) {
        super(ctx, txtViewResourceId, objects);
        context=ctx;
        this.objects=objects;
       // this.categories=categories;
       // this.serviceid=serviceid;
    }

    @Override
    public View getDropDownView(int position, View cnvtView, ViewGroup prnt) {
        return getCustomView(position, cnvtView, prnt);
    }
    @Override
    public View getView(int pos, View cnvtView, ViewGroup prnt) {
        return getCustomView(pos, cnvtView, prnt);
    }
    public View getCustomView(int position, View convertView,
                              ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view= inflater.inflate(R.layout.spinner_item, parent,
                false);
        TextView tv=(TextView)view.findViewById(R.id.tv_spinner_item);
        tv.setText(objects.get(position));



        return view;
    }
}

