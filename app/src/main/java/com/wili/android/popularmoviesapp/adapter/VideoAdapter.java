package com.wili.android.popularmoviesapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.wili.android.popularmoviesapp.R;
import com.wili.android.popularmoviesapp.repository.model.Video;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    private List<Video> videoList;
    private final VideoAdapterOnClickHandler onClickHandler;

    public interface VideoAdapterOnClickHandler {
        void onClick(String videoPath);
    }

    public VideoAdapter(List<Video> videoList, VideoAdapterOnClickHandler onClickHandler) {
        this.videoList = videoList;
        this.onClickHandler = onClickHandler;
    }


    public class VideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.video_image)
        ImageView videoImage;

        public VideoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            String videoPath = videoList.get(adapterPosition).getVideoPath();
            onClickHandler.onClick(videoPath);
        }
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_list_item, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        String videoImagePath = videoList.get(position).getVideoImagePath();
        Picasso.get()
                .load(videoImagePath)
                .placeholder(R.drawable.poster_placeholder)
                .error(R.drawable.ic_error_black_24dp)
                .into(holder.videoImage);
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

}
