package com.example.githubapi.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.githubapi.R;
import com.example.githubapi.models.AlbumResponseModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.MyViewHolder> {
    private List<AlbumResponseModel> list;

    public GithubAdapter(List<AlbumResponseModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.github_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AlbumResponseModel albumResponseModel = list.get(position);
        Picasso.get().load(albumResponseModel.getThumbnailUrl())
                .into(holder.imageView);
        holder.title.setText(albumResponseModel.getTitle() + "");
        holder.albumId.setText("Id: "+ albumResponseModel.getId() + " AlbumId: " + albumResponseModel.getAlbumId());
    }

    public List<AlbumResponseModel> getList() {
        return list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView albumId;
        private ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.imageView);
            albumId = itemView.findViewById(R.id.album_id);
        }

    }
}

