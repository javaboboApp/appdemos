package com.example.githubapi.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.githubapi.R;
import com.example.githubapi.adapters.GithubAdapter;
import com.example.githubapi.models.AlbumResponseModel;
import com.example.githubapi.mvp.contracts.MainActivityContract;
import com.example.githubapi.mvp.presenters.MainActivityPresenter;
import com.example.githubapi.mvp.usecases.GetAlbum;
import com.example.githubapi.utils.Utils;
import com.example.githubapi.viewmodel.AlbumViewModel;


import java.util.List;

/**
 * The Album program implements an application that
 * simply displays a list of albums, has been create for testing propose.
 *
 * @author Luis ernesto alcantara
 * @version 1.0
 * @since 2020-04-12
 */
public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView statusMessage;
    private GithubAdapter githubAdapter;
    private AlbumViewModel viewModel;
    private Button tryAgain;
    private final String TAG_STATUS_MSG = "STATUS_MSG";

    private final String TAG_PROGRESSBAR_VISIBLE = "PROGRESSBAR_VISIBLE";
    private final String TAG_RECYCLERVIEW_VISIBLE = "RECYCLERVIEW_VISIBLE";
    private final String TAG_STATUS_MESSAGE_VISIBLE = "STATUS_MESSAGE_VISIBLE";
    private final String TAG_TRY_AGAIN_VISIBLE = "TRY_AGAIN_VISIBLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initScrollListener();
        initListerners();
        initViewModel();
        initPresenter();
        initAdapter();

        if (savedInstanceState == null) {
            getNextAlbumRequestToGitHub();
        } else {
            recoveryVisibilityInfo(savedInstanceState);
        }
    }

    /**
     * This method is used to init the  the listeners
     */
    private void initListerners() {
        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNextAlbumRequestToGitHub();
            }
        });
    }

    /**
     * This method is used to init the viewmodel.
     * Android allow us to keep all the object when we rotate the screen using viewmodels
     */

    private void initViewModel() {
        viewModel = new ViewModelProvider(MainActivity.this).get(AlbumViewModel.class);
    }

    /**
     * This method is used to recovery the visibility of some elements and another objects
     * I decided to uses this method in order to save somes views's properties
     *
     * @param savedInstanceState Bundle with all the objects that has been saved
     */

    private void recoveryVisibilityInfo(Bundle savedInstanceState) {
        statusMessage.setText(savedInstanceState.getString(TAG_STATUS_MSG));
        progressBar.setVisibility(savedInstanceState.getInt(TAG_PROGRESSBAR_VISIBLE));
        recyclerView.setVisibility(savedInstanceState.getInt(TAG_RECYCLERVIEW_VISIBLE));
        statusMessage.setVisibility(savedInstanceState.getInt(TAG_STATUS_MESSAGE_VISIBLE));
        tryAgain.setVisibility(savedInstanceState.getInt(TAG_TRY_AGAIN_VISIBLE));
    }

    /**
     * This method is init the adapter.
     * The viewModel has been used to get the list
     */

    private void initAdapter() {
        githubAdapter = new GithubAdapter(viewModel.getAlbumResponseModels());
        recyclerView.setAdapter(githubAdapter);
    }

    /**
     * We need to initialized the scroll listener in order to detect the last
     * element of the view and get the next album
     */

    private void initScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //Avoid double requests.
                if (!viewModel.isLoading()) {
                    //Check if we are at the end of the list
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == viewModel.getAlbumResponseModels().size() - 1) {
                        viewModel.setLoading(true); //Active the lock
                        //Send the request (next page)
                        getNextAlbumRequestToGitHub();

                    }
                }
            }
        });


    }

    /**
     * This method is used init the presenter.
     * Only is necessary to create the presenter if it is not in the view model.
     */
    private void initPresenter() {
        if (viewModel.getPresenter() == null) {
            viewModel.setPresenter(new MainActivityPresenter(new GetAlbum()));
        }
        viewModel.getPresenter().setView(MainActivity.this);
    }

    /**
     * Method that call to the presenter in order to get the next album.
     */
    private void getNextAlbumRequestToGitHub() {
        progressBar.setVisibility(View.VISIBLE);
        statusMessage.setVisibility(View.GONE);
        tryAgain.setVisibility(View.GONE);
        viewModel.getPresenter().getAlbum(viewModel.getCurrentPage());
    }

    /**
     * Method that initialized the views.
     */
    private void initViews() {
        recyclerView = findViewById(R.id.githublist);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        progressBar = findViewById(R.id.progress_bar);
        statusMessage = findViewById(R.id.status_msg);
        tryAgain = findViewById(R.id.retry);
    }

    /**
     * If there is an error when a request has been executed the presenter call this method
     */
    @Override
    public void stateError() {
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        statusMessage.setVisibility(View.VISIBLE);
        tryAgain.setVisibility(View.VISIBLE);

        statusMessage.setText(getString(R.string.error_in_the_request_back));

        Utils.showToast(MainActivity.this, getString(R.string.error_in_the_request), Toast.LENGTH_LONG);
        //Reset loading property
        viewModel.setLoading(false);

    }

    /**
     * If the list is empty when a request has been executed the presenter call this method
     */
    @Override
    public void stateEmpty() {
        progressBar.setVisibility(View.GONE);
        statusMessage.setVisibility(View.VISIBLE);
        tryAgain.setVisibility(View.GONE);

        statusMessage.setText(getString(R.string.empty_list));
        recyclerView.setVisibility(View.GONE);
    }

    /**
     * If there was not errors when a request has been executed the presenter call this method
     *
     * @param githubResponse Is albumList that came from the server
     */
    @Override
    public void stateSucess(List<AlbumResponseModel> githubResponse) {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        tryAgain.setVisibility(View.GONE);

        int insertIndex = viewModel.getAlbumResponseModels().size();
        viewModel.addAll(githubResponse);
        githubAdapter.notifyItemRangeInserted(insertIndex, githubResponse.size());
        viewModel.setLoading(false);
        viewModel.incrementToNextPage();
    }


    /**
     * Method that is call before the activity is destroyed
     *
     * @param bundle Bundle where we are want to save information
     */

    @Override
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(TAG_RECYCLERVIEW_VISIBLE, recyclerView.getVisibility());
        bundle.putInt(TAG_STATUS_MESSAGE_VISIBLE, statusMessage.getVisibility());
        bundle.putInt(TAG_PROGRESSBAR_VISIBLE, progressBar.getVisibility());
        bundle.putInt(TAG_TRY_AGAIN_VISIBLE, tryAgain.getVisibility());
        bundle.putString(TAG_STATUS_MSG, statusMessage.getText().toString());

    }

    @Override
    protected void onDestroy() {
        viewModel.getPresenter().detachView();
        super.onDestroy();
    }
}
