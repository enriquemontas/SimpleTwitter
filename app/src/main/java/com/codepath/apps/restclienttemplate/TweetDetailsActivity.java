package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.databinding.ActivityTweetDetailsBinding;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class TweetDetailsActivity extends AppCompatActivity {

    Tweet tweet;

    ImageView ivProfileImage;
    TextView tvScreenName;
    TextView tvBody;
    TextView tvName;
    TextView tvRelativeTime;
    ImageView ivMediaImage;
    ActivityTweetDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTweetDetailsBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();
        setContentView(view);

        ivProfileImage = binding.ivProfileImage;
        tvScreenName = binding.tvScreenName;
        tvBody = binding.tvBody;
        tvName = binding.tvName;
        tvRelativeTime = binding.tvRelativeTime;
        ivMediaImage = binding.ivMediaImage;

        tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));

        tvScreenName.setText(tweet.getUser().getScreenName());
        tvBody.setText(tweet.getBody());
        tvName.setText(tweet.getUser().getName());
        tvRelativeTime.setText(tweet.getRelativeTimeAgo(tweet.getCreatedAt()));

        Context context = getApplicationContext();
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