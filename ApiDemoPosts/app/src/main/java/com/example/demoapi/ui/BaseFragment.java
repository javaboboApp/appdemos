package com.example.demoapi.ui;

import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseFragment extends Fragment {
    private boolean isVisible;
    private Communicator mCallBack;
    protected Button retry;
    protected ProgressBar progressBar;
    protected TextView statusMsg;
    protected RecyclerView recyclerView;
    protected  boolean isLoading;
    protected static final String TAG_LIST = "TAG_LIST";
    protected static final String TAG_IS_LOADING = "IS_LOADING";


    public BaseFragment() {
        // Required empty public constructor
    }

    public abstract int getFragmentId();


    public interface Communicator {
        void currentFragment(int fragment);
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i("HOMEFRAGMENT", "onAttach");
        try {
            mCallBack = (Communicator) context;
        } catch (ClassCastException c) {
            throw new ClassCastException(context.toString() + " Must implement the comunicator interface");
        }
    }


    @Override
    public void onResume() {
        Log.i("HOMEFRAGMENT", "onResume");
        //This method can be called several times, the fragment is visible to the user see {@link #onResume()}
        if (!isVisible) {
            mCallBack.currentFragment(getFragmentId());
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i("HOMEFRAGMENT", "onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        //The fragment is visible to the user see {@link #onStop()}
        Log.i("HOMEFRAGMENT", "onStop");
        isVisible = false;
        super.onStop();
    }
}
