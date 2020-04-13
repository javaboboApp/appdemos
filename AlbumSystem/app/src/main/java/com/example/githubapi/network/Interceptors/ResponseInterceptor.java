package com.example.githubapi.network.Interceptors;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.githubapi.ui.ErrorHandlingActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.ResponseBody;

public class ResponseInterceptor implements Interceptor {
    private Context context;

    public ResponseInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        okhttp3.Response response = chain.proceed(request);

            switch (response.code()) {
                case 404:
                    break;
                case 500:
                   context.startActivity(
                            new Intent(
                                    context,
                                    ErrorHandlingActivity.class
                            )
                    );
                    break;
                default:
                    break;
            }
            return response;


    }
}