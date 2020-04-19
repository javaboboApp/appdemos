package com.example.demoapi.ui.comments;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.demoapi.App;
import com.example.apidemo.R;
import com.example.demoapi.mvp.contracts.CommentsContract;
import com.example.demoapi.mvp.model.Comment;
import com.example.demoapi.ui.BaseFragment;
import com.example.demoapi.ui.adapters.CommentAdapter;
import com.example.demoapi.utils.FunctionsUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;


public class CommentsFragment extends BaseFragment implements CommentsContract.View {

    private static final int FRAGMENT_ID = 1;
    private CommentAdapter commentAdapter;


    @Inject
    CommentsContract.Presenter presenter;


    public interface CommentsFragmentCommunicator extends Communicator {

    }

    @Override
    public int getFragmentId() {
        return FRAGMENT_ID;
    }


    public static CommentsFragment newInstance() {
        CommentsFragment fragment = new CommentsFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comments, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeDagger();

        initViews(view);

        initAdapter();
        initPresenter();
        if (savedInstanceState == null|| savedInstanceState.getBoolean(TAG_IS_LOADING)) {
            loadNextCollection();
        } else {
            showList(savedInstanceState.<Comment>getParcelableArrayList(TAG_LIST));
        }
    }

    private void initializeDagger() {
        App app = (App) getActivity().getApplication();
        app.getMainComponent().inject(this);
    }

    private void loadNextCollection() {
        isLoading = true;
        progressBar.setVisibility(View.VISIBLE);
        retry.setVisibility(View.GONE);
        presenter.loadNextCollection();
    }

    private void initAdapter() {
        commentAdapter = new CommentAdapter(new ArrayList<>(), cakeModel -> {
             //TODO DO SOMETHING
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(commentAdapter);
    }


    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        progressBar = view.findViewById(R.id.progress_bar);
        statusMsg = view.findViewById(R.id.status_msg);
        retry = view.findViewById(R.id.retry);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                loadNextCollection();
            }
        });
    }

    private void initPresenter() {
        presenter.setView(this);
    }


    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void empty() {
        isLoading = false;
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        statusMsg.setVisibility(View.VISIBLE);
        retry.setVisibility(View.GONE);
        statusMsg.setText(getString(R.string.empty));
    }

    @Override
    public void error() {
        isLoading = false;
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        statusMsg.setVisibility(View.VISIBLE);
        retry.setVisibility(View.VISIBLE);
        statusMsg.setText(getString(R.string.error));

        FunctionsUtils.showToast(getContext(), getString(R.string.error), Toast.LENGTH_LONG);
    }



    @Override
    public void onStop() {
        //The fragment can be destroy at any time
        if (presenter != null)
            //Be sure that we avoid memory leak.
            presenter.detachView();
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        //The fragment can be destroy at any time
        if (commentAdapter != null)
            savedInstanceState.putParcelableArrayList(TAG_LIST, new ArrayList<Parcelable>(commentAdapter.getList()));
        savedInstanceState.putBoolean(TAG_IS_LOADING, isLoading);

    }

    @Override
    public void showList(List<Comment> comments) {
        isLoading = false;
        commentAdapter.setList(comments);
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }


}
