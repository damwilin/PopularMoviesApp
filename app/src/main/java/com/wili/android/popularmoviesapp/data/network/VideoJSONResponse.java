package com.wili.android.popularmoviesapp.data.network;

import com.google.gson.annotations.SerializedName;
import com.wili.android.popularmoviesapp.data.model.Video;

import java.util.List;

public class VideoJSONResponse {
    @SerializedName("results")
    List<Video> videosList;

    public List<Video> getVideosList() {
        return videosList;
    }
}
