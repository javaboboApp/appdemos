package com.example.demoapi.mvp.presenters;

import android.util.Log;

import com.example.demoapi.mvp.contracts.CommentsContract;
import com.example.demoapi.mvp.model.Comment;
import com.example.demoapi.mvp.shared.BasePresenter;
import com.example.demoapi.mvp.usercases.GetCommentsUserCase;
import com.example.demoapi.rx.SchedulerProvider;

import java.util.List;

import androidx.annotation.NonNull;
import rx.Subscriber;


public class CommentsFragmentPresenter extends BasePresenter<CommentsContract.View> implements CommentsContract.Presenter {
    private CommentsContract.View view;
    private GetCommentsUserCase getCommentsUserCase;
    private SchedulerProvider mSchedulerProvider;


    public CommentsFragmentPresenter(SchedulerProvider mSchedulerProvider,
                                     GetCommentsUserCase getCommentsUserCase) {
        this.mSchedulerProvider = mSchedulerProvider;
        this.getCommentsUserCase = getCommentsUserCase;
    }



    @Override
    public void loadNextCollection() {
        rx.Observable<List<Comment>> observable = getCommentsUserCase.getComments();

        subscribe(observable.subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.main())
                .subscribe(new Subscriber<List<Comment>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.v("Error", "subscribe->subscribe->onError->(" + throwable.getMessage() + ")");
                        view.error();
                    }

                    @Override
                    public void onNext(List<Comment> comments) {
                        Log.v("Sucess on the response", " List<Comment> comments");
                        handleResponse(comments);
                    }
                }));
    }

    private void handleResponse(List<Comment> comments) {
        view.showList(comments);
    }

    public void setView(@NonNull CommentsContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        //Remove the subscribers
        onViewDetached();
        //detach from the view
        view = null;
    }


}
