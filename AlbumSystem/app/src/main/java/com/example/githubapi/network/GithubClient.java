package com.example.githubapi.network;

import com.example.githubapi.models.AlbumResponseModel;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;


public interface GithubClient {
    @GET("/albums/{albumId}/photos")
    Call<List<AlbumResponseModel>> getAlbum(@Path("albumId") int albumId);

}
