package com.example.githubapi.mvp.usecases;

import android.content.Context;

import com.example.githubapi.models.AlbumResponseModel;
import com.example.githubapi.network.GithubClient;
import com.example.githubapi.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class GetAlbum {

    public void getAlbum(Context context, int albumId, Callback<List<AlbumResponseModel>> callback){
        //Send the request to the server
        GithubClient githubClient = RetrofitInstance.getRetrofitInstance(context).
                create(GithubClient.class);

        Call<List<AlbumResponseModel>> xx =
                githubClient.getAlbum(albumId);

        xx.enqueue(callback);
    }
}
