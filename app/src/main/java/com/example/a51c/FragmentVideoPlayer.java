package com.example.a51c;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentVideoPlayer extends Fragment {

    private static final String ARG_URL = "video_url";

    public static FragmentVideoPlayer newInstance(String videoUrl) {
        FragmentVideoPlayer fragment = new FragmentVideoPlayer();
        Bundle args = new Bundle();
        args.putString(ARG_URL, videoUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_player, container, false);

        WebView webView = view.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true); // enable JS
        webView.setWebViewClient(new WebViewClient());

        if (getArguments() != null) {
            String videoUrl = getArguments().getString(ARG_URL);

            // Embed version
            if (videoUrl.contains("watch?v=")) {
                videoUrl = videoUrl.replace("watch?v=", "embed/");
            }

            webView.loadUrl(videoUrl);
        }

        return view;
    }
}
