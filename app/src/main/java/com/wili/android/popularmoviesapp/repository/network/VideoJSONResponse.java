package com.wili.android.popularmoviesapp.repository.network;

import com.google.gson.annotations.SerializedName;
import com.wili.android.popularmoviesapp.repository.model.Video;

import java.util.List;

public class VideoJSONResponse {
    @SerializedName("results")
    List<Video> videosList;

    public List<Video> getVideosList() {
        return videosList;
    }
}
