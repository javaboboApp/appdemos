package com.example.demoapi.ui.posts;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.demoapi.App;
import com.example.apidemo.R;
import com.example.demoapi.mvp.contracts.PostsContract;
import com.example.demoapi.mvp.model.Post;
import com.example.demoapi.ui.BaseFragment;
import com.example.demoapi.ui.adapters.PostAdapter;
import com.example.demoapi.ui.adapters.MainAdapterListener;
import com.example.demoapi.utils.FunctionsUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class PostFragment extends BaseFragment implements PostsContract.View {
    @Inject
    PostsContract.Presenter presenter;
    private RecyclerView recyclerView;

    private PostAdapter postAdapter;


    private static final int FRAGMENT_ID = 0;


    public interface PostCommunicator extends Communicator {

    }


    public static PostFragment newInstance() {
        PostFragment fragment = new PostFragment();

        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeDagger();

        initViews(view);

        initAdapter();
        initPresenter();
        if (savedInstanceState == null || savedInstanceState.getBoolean(TAG_IS_LOADING)) {
            loadNextCollection();
        } else {
            showList(savedInstanceState.<Post>getParcelableArrayList(TAG_LIST));
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
        postAdapter = new PostAdapter(new ArrayList<Post>(), new MainAdapterListener() {
            @Override
            public void onClickListener(Post cakeModel) {
                //mCallBack.startDetailsActivity(cakeModel);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(postAdapter);
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
    public void showList(List<Post> posts) {
        isLoading = false;
        postAdapter.setList(posts);
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
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
        if (postAdapter != null){
            savedInstanceState.putParcelableArrayList(TAG_LIST, new ArrayList<Parcelable>(postAdapter.getList()));
        }
        savedInstanceState.putBoolean(TAG_IS_LOADING, isLoading);


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("HOMEFRAGMENT", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("HOMEFRAGMENT", "onCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }


    @Override
    public int getFragmentId() {
        return FRAGMENT_ID;
    }
}
