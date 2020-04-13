package com.example.githubapi.mvp.contracts;


import android.content.Context;

import com.example.githubapi.models.AlbumResponseModel;
import com.example.githubapi.mvp.shared.BaseContract;

import java.util.List;

public interface MainActivityContract {
    interface View extends BaseContract.View {


        void stateError();

        void stateEmpty();

        void stateSucess(List<AlbumResponseModel> albumResponseModels);



    }

    interface Presenter extends BaseContract.Presenter<MainActivityContract.View> {
         void getAlbum(Context context, int albumId);

         void detachView();

    }
}
