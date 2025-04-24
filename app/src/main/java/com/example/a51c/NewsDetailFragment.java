package com.example.a51c;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class NewsDetailFragment extends Fragment {

    public NewsDetailFragment() {
        // Required empty public constructor
    }

    public static NewsDetailFragment newInstance(String title, String description, String imageUrl) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("description", description);
        args.putString("imageUrl", imageUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); // enables back button
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_detail, container, false);

        TextView titleText = view.findViewById(R.id.newsTitle);
        TextView descText = view.findViewById(R.id.newsDescription);
        ImageView imageView = view.findViewById(R.id.newsDetailImage);

        if (getArguments() != null) {
            String title = getArguments().getString("title");
            String description = getArguments().getString("description");
            String imageUrl = getArguments().getString("imageUrl");

            titleText.setText(title);
            descText.setText(description);
            Picasso.get().load(imageUrl).into(imageView);

            RecyclerView relatedRecyclerView = view.findViewById(R.id.relatedRecyclerView);
            relatedRecyclerView.setLayoutManager(
                    new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

            String[] relatedTitles = {
                    "📢 Election coming soon", "🛫 Flight prices drop", "💼 Tips to boost productivity"
            };

            String[] relatedImages = {
                    "https://picsum.photos/300/200?random=10",
                    "https://picsum.photos/300/200?random=11",
                    "https://picsum.photos/300/200?random=12"
            };

            NewsAdapter relatedAdapter = new NewsAdapter(
                    relatedTitles,
                    (t, d, i) -> {}, // No action on click for now
                    relatedImages
            );

            relatedRecyclerView.setAdapter(relatedAdapter);
        }

        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Details");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }
}
