package com.facilitydoor.app.facilitydoor.adapter;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facilitydoor.app.facilitydoor.R;

import java.util.ArrayList;

/**
 * Created by root on 8/6/16.
 */
public class MyAdapter extends ArrayAdapter<String> {
    Context context;
    ArrayList<String> objects;

    public MyAdapter(Context ctx, int txtViewResourceId, ArrayList<String> objects) {
        super(ctx, txtViewResourceId, objects);
        context=ctx;
        this.objects=objects;
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

