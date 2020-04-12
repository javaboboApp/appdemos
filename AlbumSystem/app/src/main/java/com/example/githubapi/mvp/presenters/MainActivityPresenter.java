package com.example.githubapi.mvp.presenters;

import com.example.githubapi.models.AlbumResponseModel;
import com.example.githubapi.mvp.contracts.MainActivityContract;
import com.example.githubapi.mvp.usecases.GetAlbum;

import java.util.List;

import androidx.annotation.NonNull;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private MainActivityContract.View view;
    private GetAlbum getAlbum;

    /*
    * The main activity Presenter retrieves the data from the Model,
    * applies the UI logic and manages the state of the View,
    * decides what to display and reacts to user input notifications
    * from the View.
    *
    * TODO We could improve the code using dagger2 to inject dependences
    *      and Rxjava that can deal with complex requests using the pattern observer.
    * */

    public MainActivityPresenter( GetAlbum getAlbum) {
        this.getAlbum = getAlbum;
    }

    @Override
    public void setView(@NonNull MainActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void getAlbum(int albumId) {
        //calling the use case
        getAlbum.getAlbum(albumId, new Callback<List<AlbumResponseModel>>() {
            @Override
            public void onResponse(Response<List<AlbumResponseModel>> response, Retrofit retrofit) {
                List<AlbumResponseModel> responseModels = response.body();
                if (responseModels.isEmpty()) {
                    view.stateEmpty();
                } else
                    view.stateSucess(responseModels);
            }

            @Override
            public void onFailure(Throwable t) {

                view.stateError();
            }

        });
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
