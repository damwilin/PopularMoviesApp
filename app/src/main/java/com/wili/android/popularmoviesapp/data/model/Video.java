package com.wili.android.popularmoviesapp.data.model;

import com.google.gson.annotations.SerializedName;

import static com.wili.android.popularmoviesapp.data.network.ApiParam.YT_BASE_URL;
import static com.wili.android.popularmoviesapp.data.network.ApiParam.YT_IMAGE_DEFAULT_QUALITY;
import static com.wili.android.popularmoviesapp.data.network.ApiParam.YT_IMAGE_URL;

public class Video {
    @SerializedName("key")
    private String key;
    @SerializedName("site")
    private String site;

    public String getVideoPath() {
        return YT_BASE_URL.concat(key);
    }

    public String getVideoImagePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(YT_IMAGE_URL)
                .append(key)
                .append(YT_IMAGE_DEFAULT_QUALITY);
        return sb.toString();
    }
}
