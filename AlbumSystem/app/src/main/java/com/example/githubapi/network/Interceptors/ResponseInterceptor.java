package com.example.githubapi.network.Interceptors;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.ResponseBody;

public class ResponseInterceptor implements Interceptor {

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        okhttp3.Response response = chain.proceed(request);
        if(response.code() == 200) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("code",200);
                jsonObject.put("status","OK");
                jsonObject.put("message","Successful");

                MediaType contentType = response.body().contentType();
                ResponseBody body = ResponseBody.create(contentType, jsonObject.toString());
                return response.newBuilder().body(body).build();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if(response.code() == 403) {

        }
        return response;
    }
}