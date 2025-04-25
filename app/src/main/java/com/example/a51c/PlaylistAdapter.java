package com.example.a51c;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// This adapter now uses PlaylistItem (title + url)
public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder> {

    private final List<PlaylistItem> playlist;

    public PlaylistAdapter(List<PlaylistItem> playlist) {
        this.playlist = playlist;
    }

    @NonNull
    @Override
    public PlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false); // Title + URL
        return new PlaylistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistViewHolder holder, int position) {
        PlaylistItem item = playlist.get(position);
        holder.titleView.setText(item.getTitle());
        holder.urlView.setText(item.getUrl());
    }

    @Override
    public int getItemCount() {
        return playlist.size();
    }

    public static class PlaylistViewHolder extends RecyclerView.ViewHolder {
        TextView titleView;
        TextView urlView;

        public PlaylistViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(android.R.id.text1);
            urlView = itemView.findViewById(android.R.id.text2);
        }
    }
}
