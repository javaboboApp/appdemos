package com.example.githubapi.adapters;

import android.graphics.Color;
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


public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder> {
    private List<AlbumResponseModel> list;
    private RecyclerViewListener recyclerViewListener;

    public AlbumAdapter(List<AlbumResponseModel> list, RecyclerViewListener recyclerViewListener) {
        this.list = list;
        this.recyclerViewListener = recyclerViewListener;
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
        if((position+1) % 3 ==0){
            holder.title.setTextColor(Color.RED);
        }
        AlbumResponseModel albumResponseModel = list.get(position);
        Picasso.get().load(albumResponseModel.getThumbnailUrl())
                .into(holder.imageView);
        holder.bind(albumResponseModel, recyclerViewListener);
    }

    @Override
    public void onViewRecycled(@NonNull MyViewHolder holder) {
        super.onViewRecycled(holder);
        holder.title.setTextColor(Color.BLACK);
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

        public void bind(final AlbumResponseModel albumResponseModel, final RecyclerViewListener recyclerViewListener) {
            title.setText(albumResponseModel.getTitle());
            albumId.setText("Id: " + albumResponseModel.getId() + " AlbumId: " + albumResponseModel.getAlbumId());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewListener.onItemClicked(albumResponseModel);
                }
            });
        }

    }
}

