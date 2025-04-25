package com.example.a51c;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NewsList extends Fragment {
    RecyclerView topStoriesRecyclerView, newsRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);

        topStoriesRecyclerView = view.findViewById(R.id.topStoriesRecyclerView);
        newsRecyclerView = view.findViewById(R.id.newsRecyclerView);

        topStoriesRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        newsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));


        String[] topTitles = {
                "🔥 Breaking: Market hits record",
                "🚀 NASA discovers new moon",
                "🎬 Top 10 movies of 2025"
        };

        String[] topImages = {
                "https://picsum.photos/300/200?random=1",
                "https://picsum.photos/300/200?random=2",
                "https://picsum.photos/300/200?random=3"
        };

        String[] newsTitles = {
                "🌦️ Weather update: Heavy rain ahead",
                "🏀 Sports: Final scores are in",
                "📱 Tech: New phone released",
                "🎵 Music: Top charts this week"
        };

        String[] newsImages = {
                "https://picsum.photos/300/200?random=4",
                "https://picsum.photos/300/200?random=5",
                "https://picsum.photos/300/200?random=6",
                "https://picsum.photos/300/200?random=7"
        };

        NewsAdapter.OnItemClickListener listener = (title, description, imageUrl) -> {
            Fragment detailFragment = NewsDetailFragment.newInstance(title, description,imageUrl);
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, detailFragment)
                    .addToBackStack(null)
                    .commit();
        };

        NewsAdapter topAdapter = new NewsAdapter(topTitles,listener,topImages);
        NewsAdapter newsAdapter = new NewsAdapter(newsTitles, listener,newsImages);

        topStoriesRecyclerView.setAdapter(topAdapter);
        newsRecyclerView.setAdapter(newsAdapter);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("NEWS");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }


}
