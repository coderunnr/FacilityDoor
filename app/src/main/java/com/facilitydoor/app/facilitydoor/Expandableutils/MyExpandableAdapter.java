package com.facilitydoor.app.facilitydoor.Expandableutils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facilitydoor.app.facilitydoor.Models.SubcategoryModel;
import com.facilitydoor.app.facilitydoor.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shubham Chaudhary on 6/9/2016.
 */

    public class MyExpandableAdapter extends BaseExpandableListAdapter
    {

        private Activity activity;
        private ArrayList<Object> childtems;
        private LayoutInflater inflater;
        private List<SubcategoryModel> parentItems, child;
        int serviceid;
        ArrayList<String> isCheckedStatus = new ArrayList<String>();
        CheckBox check_document;
        Context context;

        // constructor
        public MyExpandableAdapter(List<SubcategoryModel> parents,Context context)
        {
            this.parentItems = parents;
            this.context=context;
            for(int i=0;i<parents.size();i++)
            {
                for(int j=0;j<parents.get(i).getServicesSubcategories().size();j++) {
                    parents.get(i).getServicesSubcategories().get(j).setQuantity("1");
                    parents.get(i).getServicesSubcategories().get(j).setFinalprice(parents.get(i).getServicesSubcategories().get(j).getPrice1());
                }
            }


        }

        public void setInflater(LayoutInflater inflater, Activity activity)
        {
            this.inflater = inflater;
            this.activity = activity;
        }

        // method getChildView is called automatically for each child view.
        //  Implement this method as per your requirement
        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, final ViewGroup parent)
        {

           // child = (ArrayList<String>) childtems.get(groupPosition);

            TextView textView = null;


            if (convertView == null) {
                convertView = inflater.inflate(R.layout.child_view, null);
            }

            // get the textView reference and set the value
            textView = (TextView) convertView.findViewById(R.id.textViewChild);

            final TextView quantity,cost;
            ImageView plus,minus;
            quantity=(TextView)convertView.findViewById(R.id.quantity);
            cost=(TextView)convertView.findViewById(R.id.child_price);
            plus=(ImageView)convertView.findViewById(R.id.img_plus);
            minus=(ImageView)convertView.findViewById(R.id.img_minus);
if(!parentItems.get(groupPosition).getServicesSubcategories().get(childPosition).getPrice1().equals("On Inspection")) {

    plus.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String value = parentItems.get(groupPosition).getServicesSubcategories().get(childPosition).getQuantity();
            int kitna = Integer.parseInt(value);
            kitna++;
            value = String.valueOf(kitna);
            parentItems.get(groupPosition).getServicesSubcategories().get(childPosition).setQuantity(value);
            quantity.setText(value);
            int howmuch=Integer.parseInt(parentItems.get(groupPosition).getServicesSubcategories().get(childPosition).getHowmuch());
            if(howmuch==1){
                cost.setText(parentItems.get(groupPosition).getServicesSubcategories().get(childPosition).getPrice1());
            }
            else
            {
                int finalprice=0;
                int howmain=kitna/howmuch;
                int howleft=kitna%howmuch;
                if(howmain==0);
                else
                {
                    finalprice=howmain*Integer.parseInt(parentItems.get(groupPosition).getServicesSubcategories().get(childPosition).getPrice5());
                }
                switch (howleft)
                {
                    case 1:
                        finalprice=finalprice+Integer.parseInt(parentItems.get(groupPosition).getServicesSubcategories().get(childPosition).getPrice1());
                        break;
                    case 2:
                        finalprice=finalprice+Integer.parseInt(parentItems.get(groupPosition).getServicesSubcategories().get(childPosition).getPrice2());
                        break;
                    case 3:
                        finalprice=finalprice+Integer.parseInt(parentItems.get(groupPosition).getServicesSubcategories().get(childPosition).getPrice3());
                        break;
                    case 4:
                        finalprice=finalprice+Integer.parseInt(parentItems.get(groupPosition).getServicesSubcategories().get(childPosition).getPrice4());
                        break;
                }
                cost.setText(String.valueOf(finalprice));
                parentItems.get(groupPosition).getServicesSubcategories().get(childPosition).setFinalprice(String.valueOf(finalprice));

            }

        }
    });
    minus.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String value = parentItems.get(groupPosition).getServicesSubcategories().get(childPosition).getQuantity();
            int kitna = Integer.parseInt(value);
            if (kitna <= 1) ;
            else {
                kitna--;
                value = String.valueOf(kitna);
                parentItems.get(groupPosition).getServicesSubcategories().get(childPosition).setQuantity(value);
                quantity.setText(value);
            }
            int howmuch=Integer.parseInt(parentItems.get(groupPosition).getServicesSubcategories().get(childPosition).getHowmuch());
            if(howmuch==1){
                cost.setText(parentItems.get(groupPosition).getServicesSubcategories().get(childPosition).getPrice1());
            }
            else
            {
                int finalprice=0;
                int howmain=kitna/howmuch;
                int howleft=kitna%howmuch;
                if(howmain==0);
                else
                {
                    finalprice=howmain*Integer.parseInt(parentItems.get(groupPosition).getServicesSubcategories().get(childPosition).getPrice5());
                }
                switch (howleft)
                {
                    case 1:
                        finalprice=finalprice+Integer.parseInt(parentItems.get(groupPosition).getServicesSubcategories().get(childPosition).getPrice1());
                        break;
                    case 2:
                        finalprice=finalprice+Integer.parseInt(parentItems.get(groupPosition).getServicesSubcategories().get(childPosition).getPrice2());
                        break;
                    case 3:
                        finalprice=finalprice+Integer.parseInt(parentItems.get(groupPosition).getServicesSubcategories().get(childPosition).getPrice3());
                        break;
                    case 4:
                        finalprice=finalprice+Integer.parseInt(parentItems.get(groupPosition).getServicesSubcategories().get(childPosition).getPrice4());
                        break;
                }
                cost.setText(String.valueOf(finalprice));
                parentItems.get(groupPosition).getServicesSubcategories().get(childPosition).setFinalprice(String.valueOf(finalprice));
            }
        }
    });
}

            else {
                plus.setVisibility(View.INVISIBLE);
    minus.setVisibility(View.INVISIBLE);
    quantity.setVisibility(View.INVISIBLE);
            }
            cost.setText(parentItems.get(groupPosition).getServicesSubcategories().get(childPosition).getFinalprice());
            textView.setText(parentItems.get(groupPosition).getServicesSubcategories().get(childPosition).getGetSubcategoryname());
            check_document = (CheckBox) convertView.findViewById(R.id.check);
            if (parentItems.get(groupPosition).getServicesSubcategories().get(childPosition).getIsChecked().equalsIgnoreCase("false")) {
                ((CheckBox) convertView.findViewById(R.id.check)).setChecked(false);
            } else if (parentItems.get(groupPosition).getServicesSubcategories().get(childPosition).getIsChecked().equalsIgnoreCase("true")) {
                ((CheckBox) convertView.findViewById(R.id.check)).setChecked(true);
            }

            check_document.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    //   System.out.println("AAAA  size" + isCheckedStatus.size());
                    if (((CheckBox) v).isChecked()) {

                        //  isCheckedStatus.add(childPosition, "true");
                        parentItems.get(groupPosition).getServicesSubcategories().get(childPosition).setIsChecked("true");


                    } else {
                        parentItems.get(groupPosition).getServicesSubcategories().get(childPosition).setIsChecked("false");

                        //  isCheckedStatus.add(childPosition, "false");
                    }

                }
                }
            );
            // set the ClickListener to handle the click event on child item

            return convertView;
        }

        // method getGroupView is called automatically for each parent item
        // Implement this method as per your requirement
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
        {

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.parent_view, null);
            }
            ImageView imageView=(ImageView)convertView.findViewById(R.id.img_plusminus);
            CheckedTextView checkedTextView = (CheckedTextView) convertView.findViewById(R.id.textViewGroupName);
            if(isExpanded){
                imageView.setImageResource(R.drawable.minus);
            }
            else {
                imageView.setImageResource(R.drawable.plus);
            }

            checkedTextView.setText(parentItems.get(groupPosition).getCategoryname());
            checkedTextView.setChecked(isExpanded);

            return convertView;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition)
        {
            return null;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition)
        {
            return 0;
        }

        @Override
        public int getChildrenCount(int groupPosition)
        {
            return parentItems.get(groupPosition).getServicesSubcategories().size();
        }

        @Override
        public Object getGroup(int groupPosition)
        {
            return null;
        }

        @Override
        public int getGroupCount()
        {
            return parentItems.size();
        }

        @Override
        public void onGroupCollapsed(int groupPosition)
        {
            super.onGroupCollapsed(groupPosition);
        }

        @Override
        public void onGroupExpanded(int groupPosition)
        {
            super.onGroupExpanded(groupPosition);
        }

        @Override
        public long getGroupId(int groupPosition)
        {
            return 0;
        }

        @Override
        public boolean hasStableIds()
        {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition)
        {
            return false;
        }

        public List<SubcategoryModel> returnresult()
        {
            return parentItems;
        }

    }




