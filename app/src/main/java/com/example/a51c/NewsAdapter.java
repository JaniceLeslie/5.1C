package com.example.a51c;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private String[] newsTitles;
    private String[] imageUrls;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String title, String description, String imageUrl);
    }

    public NewsAdapter(String[] newsTitles, OnItemClickListener listener, String[] imageUrls) {
        this.newsTitles = newsTitles;
        this.listener = listener;
        this.imageUrls = imageUrls;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        String title = newsTitles[position];
        String imageUrl = imageUrls[position];

        holder.titleTextView.setText(title);
        Picasso.get().load(imageUrl).into(holder.imageView);

        holder.itemView.setOnClickListener(v -> {
            String description = "This is a full description for: " + title;
            listener.onItemClick(title, description, imageUrl);
        });
    }

    @Override
    public int getItemCount() {
        return newsTitles.length;
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ImageView imageView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.newsTitle);
            imageView = itemView.findViewById(R.id.newsImage);
        }
    }
}

