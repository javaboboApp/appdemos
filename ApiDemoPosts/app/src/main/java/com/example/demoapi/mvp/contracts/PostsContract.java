package com.example.demoapi.mvp.contracts;

import com.example.demoapi.mvp.model.Post;
import com.example.demoapi.mvp.shared.BaseContract;

import java.util.List;

public interface PostsContract {
    interface  View extends BaseContract.View {
        void showList(List<Post> posts);
        void hideProgressBar();
        void showProgressBar();
        void empty();
        void error();

    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadNextCollection();
        void detachView();
    }
}
