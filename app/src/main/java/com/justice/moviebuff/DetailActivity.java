package com.justice.moviebuff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flaviofaria.kenburnsview.KenBurnsView;

import static com.justice.moviebuff.MainAdapter.IMAGE_PATH;

public class DetailActivity extends AppCompatActivity {

    public static Result movie;

    ////widgets/////
    private ImageView posterImageView;
    private ImageView backdropImageView;
    private TextView titleTxtView;
    private TextView rateTxtView;
    private TextView dateTxtView;
    private TextView overviewTxtView;
    private KenBurnsView videoView;
    private TextView reviewTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initWidgets();
        setDefaultValues();
    }

    private void setDefaultValues() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.mipmap.place_holder);
        Glide.with(this).applyDefaultRequestOptions(requestOptions).load(IMAGE_PATH + movie.getPosterPath()).into(posterImageView);
        Glide.with(this).applyDefaultRequestOptions(requestOptions).load(IMAGE_PATH + movie.getBackdropPath()).into(backdropImageView);
        Glide.with(this).applyDefaultRequestOptions(requestOptions).load(IMAGE_PATH + movie.getPosterPath()).into(videoView);
        titleTxtView.setText(movie.getTitle());
        rateTxtView.setText(""+movie.getPopularity());
        dateTxtView.setText(movie.getReleaseDate());
        overviewTxtView.setText(movie.getOverview());
        reviewTxtView.setText(movie.getOverview());
    }

    private void initWidgets() {
        posterImageView = findViewById(R.id.posterImageView);
        backdropImageView = findViewById(R.id.backDropImageView);
        titleTxtView = findViewById(R.id.titleTxtView);
        rateTxtView = findViewById(R.id.ratingTxtView);
        dateTxtView = findViewById(R.id.dateTxtView);
        overviewTxtView = findViewById(R.id.overViewTxtView);
        videoView = findViewById(R.id.videoView);
        reviewTxtView = findViewById(R.id.reviewsTxtView);

    }
}