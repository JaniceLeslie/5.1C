package com.example.a51c;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentPlaylist extends Fragment {

    public FragmentPlaylist() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.playlistRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<String> urlList = PlaylistStorage.getPlaylist();
        List<PlaylistItem> samplePlaylist = new ArrayList<>();

        for (String url : urlList) {
            samplePlaylist.add(new PlaylistItem("Video", url)); // You can replace "Video" with a dynamic title if you want
        }

        PlaylistAdapter adapter = new PlaylistAdapter(samplePlaylist);
        recyclerView.setAdapter(adapter);


        return view;
    }
}
