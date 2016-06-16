package com.facilitydoor.app.facilitydoor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.facilitydoor.app.facilitydoor.DatabaseUtils.CartDatabase;
import com.facilitydoor.app.facilitydoor.Models.TimeModel;
import com.facilitydoor.app.facilitydoor.adapter.MyAdapter;
import com.facilitydoor.app.facilitydoor.connectionutils.connectionclasses.ConnectionTime;
import com.facilitydoor.app.facilitydoor.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Picker extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener{
    ProgressDialog pd;
    ArrayList<String> sub_id,name,paisa,date,time;
    ArrayList<String> categories;
    ArrayList<TimeModel> cityModels;
    Spinner spinner;
    private TextView tvDisplayDate;

    ArrayList<String> subategory_id=new ArrayList<>();
    ArrayList<String> sub_name=new ArrayList<>();
    ArrayList<String> price=new ArrayList<>();
    ArrayList<String> quantity= new ArrayList<>();

    private Button btnChangeDate;
    Picker picker;
    private int myear,cyear;
    private int mmonth,cmonth;
    private int mday,cday;
    String timeslot;

    static final int DATE_DIALOG_ID = 999;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dateandtime);
        Toolbar t=(Toolbar)findViewById(R.id.toolbar_dateand);
        setSupportActionBar(t);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
     //   sub_id=getIntent().getStringExtra("sub_id");
     //   name=getIntent().getStringExtra("name");
     //   paisa=getIntent().getStringExtra("paisa");
        subategory_id=getIntent().getStringArrayListExtra("subcategory_id");
        sub_name=getIntent().getStringArrayListExtra("subcategoryname");
        price=getIntent().getStringArrayListExtra("price");
        quantity=getIntent().getStringArrayListExtra("quantity");
        Log.v ("step 2-------","step2   completed");

        setCurrentDateOnView();
        addListenerOnButton();
        picker=this;
        spinner=(Spinner)findViewById(R.id.spinner);
        findViewById(R.id.next).setOnClickListener(this);
        // Spinner click listener
        pd=new ProgressDialog(Picker.this,ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Loading");
        pd.show();


        spinner.setOnItemSelectedListener(this);
        String url="http://facilitydoor.com/api/timeslot.php";
        int newmonth=mmonth+1;

        String newmnth;
        if(String.valueOf(newmonth).length()==1 ){
            newmnth="0"+newmonth;
        }
        else
        {
            newmnth=String.valueOf(newmonth);
        }
        ConnectionTime connectionTime=new ConnectionTime(url,Picker.this,this,newmnth+"-"+mday+"-"+myear);
        connectionTime.connect();


    }

    // display current date
    public void setCurrentDateOnView() {

        tvDisplayDate = (TextView) findViewById(R.id.tvDate);

        final Calendar c = Calendar.getInstance();
        myear = c.get(Calendar.YEAR);
        mmonth = c.get(Calendar.MONTH);
        mday = c.get(Calendar.DAY_OF_MONTH);
        cyear=myear;
        cmonth=mmonth;
        cday=mday;
        // set current date into textview
        tvDisplayDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(mmonth + 1).append("-").append(mday).append("-")
                .append(myear).append(" "));
    }

    public void addListenerOnButton() {

        btnChangeDate = (Button) findViewById(R.id.btnChangeDate);

        btnChangeDate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {



                showDialog(DATE_DIALOG_ID);


            }

        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date Picker as current date
                DatePickerDialog _date =   new DatePickerDialog(this, datePickerListener, myear,mmonth,
                        mday){
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                    {
                        if (year < cyear)
                            view.updateDate(myear, mmonth, mday);

                        if (monthOfYear < cmonth && year == myear)
                            view.updateDate(myear, mmonth, mday);

                        if (dayOfMonth < cday && year == cyear && monthOfYear == cmonth)
                            view.updateDate(myear, mmonth, mday);

                        String url="http://facilitydoor.com/api/timeslot.php";
                        int newmonth=mmonth+1;
                        String newmnth;
                        if(String.valueOf(newmonth).length()==1 ){
                            newmnth="0"+newmonth;
                        }
                        else
                        {
                            newmnth=String.valueOf(newmonth);
                        }
                        pd.show();
                        ConnectionTime connectionTime=new ConnectionTime(url,Picker.this,picker,newmnth+"-"+mday+"-"+myear);
                        connectionTime.connect();

                    }
                };
                return _date;
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            myear = selectedYear;
            mmonth = selectedMonth;
            mday = selectedDay;

            // set selected date into textview
            tvDisplayDate.setText(new StringBuilder().append(mmonth + 1)
                    .append("-").append(mday).append("-").append(myear)
                    .append(" "));

        }
    };





    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.v("Selected", position + "");
        timeslot=cityModels.get(position).getTimeslot();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        timeslot=cityModels.get(0).getTimeslot();

    }

    public void parsejson(String response) {
        Log.v("Gandhi  Chutiya",response);
        try {
            JSONArray jsonArray=new JSONArray(response);
            categories=new ArrayList<>();
            cityModels=new ArrayList<>();
            for(int i=0;i<jsonArray.length();i++)
            {
                TimeModel timeModel=new TimeModel();
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                String timeslot=jsonObject.getString("timeslot");
                String id=jsonObject.getString("id");
                timeModel.setId(id);
                timeModel.setTimeslot(timeslot);
                categories.add(timeslot);
                cityModels.add(timeModel);




            }


            ArrayAdapter<String> dataAdapter=new MyAdapter(this,R.layout.spinner_item,categories);
            spinner.setAdapter(dataAdapter);
            pd.hide();

        } catch (JSONException e) {
            e.printStackTrace();
            pd.hide();
        }
        Log.v("Done",response);
    }

    @Override
    public void onClick(View v) {
        CartDatabase cartDatabase=new CartDatabase(Picker.this);
        try {
            cartDatabase.open();
            int newmonth=mmonth+1;
            for(int i=0;i<subategory_id.size();i++)
            {
                cartDatabase.createentry(subategory_id.get(i), sub_name.get(i), quantity.get(i), price.get(i), mmonth + "-" + mday + "-" + myear, timeslot);

            }
            cartDatabase.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Intent i=new Intent(Picker.this,BookServices.class);
        i.putStringArrayListExtra("sub_id",subategory_id);
        i.putStringArrayListExtra("name",sub_name);
        i.putStringArrayListExtra("price",price);
        i.putExtra("date",mmonth+"-"+mday+"-"+myear);
        i.putExtra("time", timeslot);
        startActivity(i);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return false;
    }
}