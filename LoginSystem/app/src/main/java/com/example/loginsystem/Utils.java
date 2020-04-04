package com.example.loginsystem;

import android.content.Context;
import android.widget.Toast;

import com.example.loginsystem.ui.RegisterActivity;

public class Utils {
    public static String getResourceString(Context c, int id){
        return c.getResources().getString(id);
    }

    public static void showToast(Context context, int id, int duration){
            Toast.makeText(context,
                    getResourceString(context, id) ,
                    duration).show();

    }
}
