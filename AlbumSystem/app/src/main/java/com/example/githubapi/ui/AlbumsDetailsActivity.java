package com.example.githubapi.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.githubapi.R;
import com.example.githubapi.models.AlbumResponseModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class AlbumsDetailsActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView title;
    private TextView ids_info;
    private TextView errorMsg;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums_details);
        setupViews();
        getSupportActionBar().setHomeButtonEnabled(true);
        AlbumResponseModel albumResponseModel = getIntent().getParcelableExtra(MainActivity.TAG_ALBUM);
        progressBar.setVisibility(View.VISIBLE);
        Picasso.get().load(albumResponseModel.getUrl()).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                progressBar.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);
                errorMsg.setVisibility(View.VISIBLE);

            }
        });
        title.setText(albumResponseModel.getTitle());
        ids_info.setText("Album{" + albumResponseModel.getAlbumId() + "}, ItemId: " + albumResponseModel.getId());

    }

    private void setupViews() {
        imageView = findViewById(R.id.image);
        title = findViewById(R.id.title);
        ids_info = findViewById(R.id.album_details_ids);
        progressBar = findViewById(R.id.loading);
        errorMsg = findViewById(R.id.msg_error);
    }
}
