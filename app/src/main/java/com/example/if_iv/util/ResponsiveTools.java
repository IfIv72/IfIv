package com.example.if_iv.util;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;

public class ResponsiveTools {


    public static int getDisplayWidth(View view){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) view.getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getDisplayHeight(View view){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) view.getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

}
