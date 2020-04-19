package com.example.demoapi.mvp.contracts;

import com.example.demoapi.mvp.model.Comment;
import com.example.demoapi.mvp.shared.BaseContract;

import java.util.List;

public interface CommentsContract {
    interface  View extends BaseContract.View {
        void showList(List<Comment> comments);
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
