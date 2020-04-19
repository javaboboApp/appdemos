package com.example.demoapi.mvp.presenters;

import android.util.Log;

import com.example.demoapi.mvp.contracts.PostsContract;
import com.example.demoapi.mvp.model.Post;
import com.example.demoapi.mvp.shared.BasePresenter;
import com.example.demoapi.mvp.usercases.GetPostsUserCase;
import com.example.demoapi.rx.SchedulerProvider;

import java.util.List;

import androidx.annotation.NonNull;
import rx.Subscriber;


public class PostsFragmentPresenter extends BasePresenter<PostsContract.View> implements PostsContract.Presenter {
    private PostsContract.View view;
    private GetPostsUserCase getPostsUserCase;
    private SchedulerProvider mSchedulerProvider;


    public PostsFragmentPresenter(SchedulerProvider mSchedulerProvider, GetPostsUserCase getPostsUserCase) {
        this.mSchedulerProvider = mSchedulerProvider;
        this.getPostsUserCase = getPostsUserCase;
    }



    @Override
    public void loadNextCollection() {
        rx.Observable<List<Post>> observable = getPostsUserCase.getPost();
        subscribe(observable.subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.main())
                .subscribe(new Subscriber<List<Post>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.v("Error", "subscribe->subscribe->onError->(" + throwable.getMessage() + ")");
                        view.error();
                    }

                    @Override
                    public void onNext(List<Post> transaction) {
                        Log.v("Sucess on the response", " getTransaction()");
                        handleResponse(transaction);
                    }
                }));
    }

    private void handleResponse(List<Post> posts) {
        view.showList(posts);
    }

    public void setView(@NonNull PostsContract.View view) {
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
