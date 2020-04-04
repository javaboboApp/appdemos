package com.example.loginsystem.mvp.shared;

import androidx.annotation.NonNull;

public interface BaseContract {

    interface View {
    }

    interface Presenter<T extends View> {


        public void setView(@NonNull T view);



    }
}
