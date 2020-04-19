package com.example.demoapi.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apidemo.R;
import com.example.demoapi.mvp.model.Post;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    private List<Post> list;
    private MainAdapterListener recyclerViewListener;

    public PostAdapter(List<Post> list, MainAdapterListener recyclerViewListener) {
        this.list = list;
        this.recyclerViewListener = recyclerViewListener;
    }

    public void setList(List<Post> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Post post = list.get(position);

        holder.bind(post, recyclerViewListener);
    }

    @Override
    public void onViewRecycled(@NonNull MyViewHolder holder) {
        super.onViewRecycled(holder);
         //TODO Use to recycled the view
    }

    public List<Post> getList() {
        return list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView description;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
        }

        public void bind(final Post post, final MainAdapterListener recyclerViewListener) {
            title.setText(post.getTitle());
            description.setText(post.getBody());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // recyclerViewListener.onClickListener(postModel);
                }
            });
        }

    }
}

