package com.justice.moviebuff;

import android.content.Context;
import android.content.Intent;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    public static final String IMAGE_PATH = "https://image.tmdb.org/t/p/w185";

    private Context context;
    private List<Result> movieList;

    public MainAdapter(Context context, List<Result> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RequestOptions requestOptions=new RequestOptions();
        requestOptions.placeholder(R.mipmap.place_holder);
        Glide.with(context).applyDefaultRequestOptions(requestOptions).load(IMAGE_PATH + movieList.get(position).getPosterPath()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);

            //////Set click listener for an item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DetailActivity.movie=movieList.get(getAdapterPosition());
                    context.startActivity(new Intent(context,DetailActivity.class));
                }
            });
        }
    }
}
