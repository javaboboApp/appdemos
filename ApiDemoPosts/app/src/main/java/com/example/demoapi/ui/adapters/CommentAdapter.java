package com.example.demoapi.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apidemo.R;
import com.example.demoapi.mvp.model.Comment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {
    private List<Comment> list;
    private MainAdapterListener recyclerViewListener;

    public CommentAdapter(List<Comment> list, MainAdapterListener recyclerViewListener) {
        this.list = list;
        this.recyclerViewListener = recyclerViewListener;
    }

    public void setList(List<Comment> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Comment comment = list.get(position);
        holder.bind(comment, recyclerViewListener);
    }

    @Override
    public void onViewRecycled(@NonNull MyViewHolder holder) {
        super.onViewRecycled(holder);
         //TODO Use to recycled the view
    }

    public List<Comment> getList() {
        return list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView description;
        private TextView email;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            email = itemView.findViewById(R.id.email);
        }

        public void bind(final Comment comment, final MainAdapterListener recyclerViewListener) {
            title.setText(comment.getName());
            description.setText(comment.getBody());
            email.setText(comment.getEmail());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // recyclerViewListener.onClickListener(postModel);
                }
            });
        }

    }
}

