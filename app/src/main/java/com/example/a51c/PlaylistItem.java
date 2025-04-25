package com.example.a51c;

public class PlaylistItem {
    private String title;
    private String url;

    public PlaylistItem(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
