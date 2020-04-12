package com.example.githubapi.utils;

import android.content.Context;
import android.widget.Toast;

public class Utils {
    public static void showToast(Context context, String msg, int duration) {
        Toast.makeText(context,
                msg,
                duration).show();
    }
}