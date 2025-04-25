package com.example.a51c;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    public HomeFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText editTextUrl = view.findViewById(R.id.editTextYoutubeUrl);
        Button playBtn = view.findViewById(R.id.buttonPlay);
        Button addBtn = view.findViewById(R.id.buttonAddToPlaylist);
        Button myPlaylistBtn = view.findViewById(R.id.buttonMyPlaylist);
        Button buttonSeeNews = view.findViewById(R.id.buttonSeeNews);


        playBtn.setOnClickListener(v -> {
            String url = editTextUrl.getText().toString();
            if (!url.isEmpty()) {

                Toast.makeText(requireContext(), "Playing: " + url, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Please enter a URL", Toast.LENGTH_SHORT).show();
            }
        });

        buttonSeeNews.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new NewsList())
                    .addToBackStack(null)
                    .commit();
        });

        addBtn.setOnClickListener(v -> {
            String url = editTextUrl.getText().toString().trim();
            if (!url.isEmpty()) {
                PlaylistStorage.add(url);
                Toast.makeText(requireContext(), "Added to playlist!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Please enter a URL", Toast.LENGTH_SHORT).show();
            }
        });

        myPlaylistBtn.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new FragmentPlaylist())  // Make sure this matches your fragment class name!
                    .addToBackStack(null)
                    .commit();
        });



        playBtn.setOnClickListener(v -> {
            String url = editTextUrl.getText().toString();
            if (!url.isEmpty()) {
                Fragment videoFragment = FragmentVideoPlayer.newInstance(url);
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, videoFragment)
                        .addToBackStack(null)
                        .commit();
            } else {
                Toast.makeText(requireContext(), "Please enter a URL", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
