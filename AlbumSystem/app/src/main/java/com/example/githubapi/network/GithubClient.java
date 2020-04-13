package com.example.githubapi.network;

import com.example.githubapi.models.AlbumResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface GithubClient {
    @GET("/albums/{albumId}/photos")
    Call<List<AlbumResponseModel>> getAlbum(@Path("albumId") int albumId);

}
