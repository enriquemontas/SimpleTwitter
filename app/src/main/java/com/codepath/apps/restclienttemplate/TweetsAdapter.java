package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.databinding.ItemTweetBinding;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder>{

    Context context;
    List<Tweet> tweets;
    ItemTweetBinding binding;

    // pass in context and list of tweets

    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    // For each row inflate a layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemTweetBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder();
    }

    // Bind values based on position of the element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get data
        Tweet tweet = tweets.get(position);
        // bind data from tweet to viewholder
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Tweet> tweets){
        this.tweets.addAll(tweets);
        notifyDataSetChanged();
    }

    // Define viewholder
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivProfileImage;
        TextView tvScreenName;
        TextView tvBody;
        TextView tvName;
        TextView tvRelativeTime;
        ImageView ivMediaImage;

        public ViewHolder() {
            super(binding.getRoot());
            ivProfileImage = binding.ivProfileImage;
            tvScreenName = binding.tvScreenName;
            tvBody = binding.tvBody;
            tvName = binding.tvName;
            tvRelativeTime = binding.tvRelativeTime;
            ivMediaImage = binding.ivMediaImage;
        }

        public void bind(Tweet tweet) {
            tvBody.setText(tweet.getBody());
            tvScreenName.setText(tweet.getUser().getScreenName());
            tvName.setText(tweet.getUser().getName());
            tvRelativeTime.setText(tweet.getRelativeTimeAgo(tweet.getCreatedAt()));

            Glide.with(context)
                    .load(tweet.getUser().getProfileImageUrl())
                    .into(ivProfileImage);

            if (tweet.hasImage()){
                ivMediaImage.setVisibility(View.VISIBLE);
                Glide.with(context)
                        .load(tweet.getImageMediaUrl())
                        .centerCrop()
                        .into(ivMediaImage);
            }
            else {
                ivMediaImage.setVisibility(View.GONE);
            }
        }
    }
}
