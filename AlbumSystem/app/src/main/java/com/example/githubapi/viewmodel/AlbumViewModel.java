package com.example.githubapi.viewmodel;

import com.example.githubapi.models.AlbumResponseModel;
import com.example.githubapi.mvp.contracts.MainActivityContract;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.ViewModel;

public class AlbumViewModel extends ViewModel {
    private ArrayList<AlbumResponseModel> albumResponseModels;
    private MainActivityContract.Presenter presenter;

    //By default is 1
    private int currentPage = 1;
    //By default is false
    private boolean isLoading;

    public MainActivityContract.Presenter getPresenter() {
        return presenter;
    }

    public void setPresenter(MainActivityContract.Presenter presenter) {
        this.presenter = presenter;
    }



    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int incrementToNextPage() {
        currentPage++;
        return currentPage;
    }

    public AlbumViewModel() {
        this.albumResponseModels = new ArrayList<>();
    }

    public ArrayList<AlbumResponseModel> getAlbumResponseModels() {
        return albumResponseModels;
    }

    public void setAlbumResponseModels(ArrayList<AlbumResponseModel> albumResponseModels) {
        this.albumResponseModels = albumResponseModels;
    }

    public void addAll(List<AlbumResponseModel> githubResponse) {
          this.albumResponseModels.addAll(githubResponse);
    }
}
