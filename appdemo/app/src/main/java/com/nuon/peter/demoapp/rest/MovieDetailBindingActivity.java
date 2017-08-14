package com.nuon.peter.demoapp.rest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nuon.peter.demoapp.R;
import com.nuon.peter.demoapp.SpacesItemDecoration;
import com.nuon.peter.demoapp.rest.adapter.ActorAdapter;
import com.nuon.peter.demoapp.rest.adapter.SimilarAdapter;
import com.nuon.peter.demoapp.rest.models.ResultsItem;
import com.nuon.peter.demoapp.rest.models.cast.ResponseActors;
import com.nuon.peter.demoapp.rest.models.similar.ResponseSimilar;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by manithnuon on 6/2/17.
 */

public class MovieDetailBindingActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_app) Toolbar toolbar;
    @BindView(R.id.image_background) ImageView imageView;
    @BindView(R.id.description) TextView description;
    @BindView(R.id.big_title) TextView bigTitle;
    @BindView(R.id.rating) TextView rating;
    @BindView(R.id.rating_bar) MaterialRatingBar ratingBar;
    @BindView(R.id.counting) TextView counting;
    @BindView(R.id.year_release) TextView yearRelease;
    @BindView(R.id.release_date) TextView releaseDate;
    @BindView(R.id.actor_recyclerview) RecyclerView actorRecycler;
    @BindView(R.id.related_recyclerview) RecyclerView relatedRecyclerview;

    private ResultsItem data;
    private SimilarAdapter similarAdapter;
    private ActorAdapter actorAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        overridePendingTransition(R.anim.right_in,R.anim.right_out);
        data = (ResultsItem) getIntent().getSerializableExtra("data");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_detail_movie);
        ButterKnife.bind(this);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Glide.with(this).load("http://image.tmdb.org/t/p/w650" + data.getPosterPath())
                .thumbnail(Glide.with(getBaseContext())
                .load("http://image.tmdb.org/t/p/w320" + data.getPosterPath())
                .bitmapTransform(new BlurTransformation(getBaseContext())))
                .dontAnimate()
                .into(imageView);

        description.setText(data.getOverview());
        bigTitle.setText(data.getTitle());
        rating.setText("" + data.getVoteAverage());
        counting.setText("" + data.getVoteCount());
        ratingBar.setRating((float)(data.getVoteAverage() * 0.5));
        releaseDate.setText(data.getReleaseDate());
        String year[] = data.getReleaseDate().split("-");
        yearRelease.setText(year[0]);
        similarAdapter = new SimilarAdapter(this);
        actorAdapter = new ActorAdapter(this);
        relatedRecyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        relatedRecyclerview.setHasFixedSize(true);
        relatedRecyclerview.setNestedScrollingEnabled(false);
        relatedRecyclerview.addItemDecoration(new SpacesItemDecoration(32));
        relatedRecyclerview.setAdapter(similarAdapter);
        Call<ResponseSimilar> call = MovieAPI.getAPIManager("http://139.59.125.251:3030/api/").create(MovieService.class)
                .getSimilarMovies(data.getId(),1);
        call.enqueue(new Callback<ResponseSimilar>() {
            @Override
            public void onResponse(Call<ResponseSimilar> call, Response<ResponseSimilar> response) {
                if(!response.isSuccessful()) return;
                ResponseSimilar movie = response.body();
                if (movie == null) return;
                similarAdapter.appendToList(movie.getResults());
                similarAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponseSimilar> call, Throwable t) {

            }
        });
        actorRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        actorRecycler.setHasFixedSize(true);
        actorRecycler.setNestedScrollingEnabled(false);
        actorRecycler.addItemDecoration(new SpacesItemDecoration(32));
        actorRecycler.setAdapter(actorAdapter);

        Call<ResponseActors> quest = MovieAPI.getAPIManager("http://139.59.125.251:3030/api/").create(MovieService.class)
                .getCreditsOfMovie(data.getId());
        quest.enqueue(new Callback<ResponseActors>() {
            @Override
            public void onResponse(Call<ResponseActors> call, Response<ResponseActors> response) {
                if(!response.isSuccessful()) return;
                ResponseActors actors = response.body();
                if (actors == null) return;
                actorAdapter.appendToList(actors.getCrew());
                actorAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponseActors> call, Throwable t) {

            }
        });
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.right_in,R.anim.right_out);
    }

}
