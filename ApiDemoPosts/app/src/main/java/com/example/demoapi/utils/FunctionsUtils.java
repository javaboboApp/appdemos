package com.example.demoapi.utils;

import android.content.Context;
import android.widget.Toast;

public class FunctionsUtils {
    public static  void showToast(Context context, String message, int duration){
        Toast.makeText(context,message,duration).show();
    }
}
