package com.justice.moviebuff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
//////FULL URL---https://api.themoviedb.org/3/movie/popular?api_key=5f405b82677e13f22ed99ef6e26d27c1&language=en-US&page=1

    public static final String BASE_URL = "https://api.themoviedb.org";
    public static final String PAGE = "1";
    public static final String API_KEY = "5f405b82677e13f22ed99ef6e26d27c1";
    public static final String LANGUAGE = "en-US";
    public static final String CATEGORY = "popular";

    private LinearLayout linearLayout;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        loadDataFromDatabase();
    }

    private void initWidgets() {
        linearLayout = findViewById(R.id.linearLayout);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
    }

    private void loadDataFromDatabase() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<MoviesResults> call = apiInterface.getMovies(CATEGORY, API_KEY, LANGUAGE, PAGE);

        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<MoviesResults>() {
            @Override
            public void onResponse(Call<MoviesResults> call, Response<MoviesResults> response) {
                progressBar.setVisibility(View.GONE);
                List<Result> movieList = response.body().getResults();
                setUpRecyclerAdapter(movieList);

            }

            @Override
            public void onFailure(Call<MoviesResults> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                createAnErrorAlert(t);
            }

            private void createAnErrorAlert(Throwable throwable) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity.this);
                builder.setTitle(R.string.no_internet);
                builder.setMessage(throwable.getMessage() + "\n\nWould you like to retry loading the movies data?").setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        loadDataFromDatabase();
                    }
                });
                builder.create().show();

            }
        });

    }

    private void setUpRecyclerAdapter(List<Result> movieList) {

        MainAdapter adapter = new MainAdapter(this, movieList);
        recyclerView.setHasFixedSize(true);
        /////choose Grid span depending on the orientation of screen////////
        if (linearLayout == null) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        }

        recyclerView.setAdapter(adapter);
    }


}