package com.example.demoapi.ui;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.apidemo.R;
import com.example.demoapi.ui.comments.CommentsFragment;
import com.example.demoapi.ui.posts.PostFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements PostFragment.PostCommunicator {
    private BottomNavigationView bottomNavigationView;
    private static final String TAG_FRAGMENT_HOME = "FRAGMENT_HOME";
    private static final String TAG_FRAGMENT_COMMENT = "FRAGMENT_COMMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(listener);

        if (savedInstanceState == null) {
            initBeginTransaction(PostFragment.newInstance());
        }
    }

    private void initBeginTransaction(BaseFragment baseFragment) {
        getSupportFragmentManager()
                .beginTransaction()
                //Do not add to the back stack in order to exit of the app in home view
                // .addToBackStack("main-stack")
                .replace(R.id.fragment_container, baseFragment
                )
                .commit();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction();
            BaseFragment baseFragment = null;
            String fragmentTag = "";
            switch (item.getItemId()) {
                case R.id.frag_home:
                    baseFragment = PostFragment.newInstance();
                    fragmentTag =TAG_FRAGMENT_HOME;
                    break;
                case R.id.frag_comment:
                    baseFragment = CommentsFragment.newInstance();
                    fragmentTag = TAG_FRAGMENT_COMMENT;
                    break;
            }
            //Do not accept duplicates for usability reasons - avoid to add home fragment...
            if (getSupportFragmentManager().findFragmentByTag(fragmentTag) == null
                    && !fragmentTag.equals(TAG_FRAGMENT_HOME))
                fragmentTransaction.addToBackStack("main-stack");
            fragmentTransaction.replace(R.id.fragment_container, baseFragment, fragmentTag);
            fragmentTransaction.commit();
            return false;
        }
    };

    @Override
    public void currentFragment(int fragmentId) {
        MenuItem menuItem = bottomNavigationView.getMenu().getItem(fragmentId);
        menuItem.setChecked(true);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }
}
