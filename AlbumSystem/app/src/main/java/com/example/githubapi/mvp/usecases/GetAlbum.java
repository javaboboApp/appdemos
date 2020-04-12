package com.example.githubapi.mvp.usecases;

import com.example.githubapi.models.AlbumResponseModel;
import com.example.githubapi.network.GithubClient;
import com.example.githubapi.network.RetrofitInstance;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;

public class GetAlbum {

    public void getAlbum(int albumId, Callback<List<AlbumResponseModel>> callback){
        //Send the request to the server
        GithubClient githubClient = RetrofitInstance.getRetrofitInstance().
                create(GithubClient.class);

        Call<List<AlbumResponseModel>> xx =
                githubClient.getAlbum(albumId);

        xx.enqueue(callback);
    }
}
