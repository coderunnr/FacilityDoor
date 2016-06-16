package com.facilitydoor.app.facilitydoor.DatabaseUtils;

import android.content.Context;

/**
 * Created by root on 15/6/16.
 */
public class SavedPreferences {

    public static void setName(String name,Context context)
    {
        context.getSharedPreferences("user",0).edit().putString("name",name).apply();
    }
    public static String getName(Context context)
    {
       return context.getSharedPreferences("user",0).getString("name","Yugam");
    }

    public static void setEmail(String email,Context context)
    {
        context.getSharedPreferences("user",0).edit().putString("email",email).apply();
    }
    public static String getEmail(Context context)
    {
        return context.getSharedPreferences("user",0).getString("email","Yugam@facilitydoor.com");

    }
    public static void setLine1(String line1,Context context)
    {
        context.getSharedPreferences("user",0).edit().putString("line1",line1).apply();
    }
    public static String getLine1(Context context)
    {
        return context.getSharedPreferences("user",0).getString("line1","Indirapuram");
    }
    public static void setLine2(String line2,Context context)
    {
        context.getSharedPreferences("user",0).edit().putString("line2",line2).apply();
    }
    public static String getLine2(Context context)
    {
        return context.getSharedPreferences("user",0).getString("line2","Ghaziabad");
    }
    public static void setPhone(String phone,Context context)
    {
        context.getSharedPreferences("user",0).edit().putString("phone",phone).apply();
    }
    public static String getPhone(Context context)
    {
        return context.getSharedPreferences("user",0).getString("phone","7503939992");
    }
    public static void setCityid(String cityid,Context context)
    {
        context.getSharedPreferences("user",0).edit().putString("city",cityid).apply();
    }
    public static String getCityid(Context context)
    {
        return context.getSharedPreferences("user",0).getString("city","Indirapuram");
    }
    public static void setCityname(String cityname,Context context)
    {
        context.getSharedPreferences("user",0).edit().putString("cityname",cityname).apply();
    }
    public static String getCityname(Context context)
    {
        return context.getSharedPreferences("user",0).getString("cityname","Delhi");
    }
}
