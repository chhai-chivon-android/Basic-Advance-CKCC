package com.nuon.peter.demoapp.rest;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.nuon.peter.demoapp.R;
import com.nuon.peter.demoapp.SpacesItemDecoration;
import com.nuon.peter.demoapp.databinding.LayoutMovieBinding;
import com.nuon.peter.demoapp.rest.adapter.NowPlayingBindingAdapter;
import com.nuon.peter.demoapp.rest.adapter.PopularityBindingAdapter;
import com.nuon.peter.demoapp.rest.adapter.RatingBindingAdapter;
import com.nuon.peter.demoapp.rest.adapter.UpCommingBindingAdapter;
import com.nuon.peter.demoapp.rest.models.DataMovie;
import com.nuon.peter.demoapp.rest.models.ResponseMovies;
import com.nuon.peter.demoapp.rest.models.ResultsItem;
import java.util.Random;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by manithnuon on 6/2/17.
 */

public class FilmRestBindFragment extends Fragment implements MovieDetailView{

    @BindView(R.id.nowplay_recycler) RecyclerView nowplay;
    @BindView(R.id.rating_recycler) RecyclerView rating;
    @BindView(R.id.popularity_recycler) RecyclerView popularity;
    @BindView(R.id.up_comming_recycler) RecyclerView upcoming;

    private NowPlayingBindingAdapter nowPlayingAdapter;
    private PopularityBindingAdapter popularityAdapter;
    private RatingBindingAdapter ratingAdapter;
    private UpCommingBindingAdapter upCommingAdapter;
    public LayoutMovieBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_movie,container,false);
        binding = DataBindingUtil.bind(view);
                ButterKnife.bind(this,binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nowplay.setHasFixedSize(true);
        nowplay.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        nowplay.addItemDecoration(new SpacesItemDecoration(24));
        nowPlayingAdapter = new NowPlayingBindingAdapter(this);
        nowplay.setAdapter(nowPlayingAdapter);
        nowplay.setNestedScrollingEnabled(false);

        Call<ResponseMovies> nowCall = getRetrofitManager().getNowPlayingMovie(1);
        nowCall.enqueue(new Callback<ResponseMovies>() {
            @Override
            public void onResponse(Call<ResponseMovies> call, Response<ResponseMovies> response) {
                if(!response.isSuccessful()) return;
                ResponseMovies movie = response.body();
                if(movie == null) return;
                nowPlayingAdapter.appendToList(movie.getResults());
                nowPlayingAdapter.notifyDataSetChanged();
                int random = new Random().nextInt(movie.getResults().size()-1);
                binding.setData(new DataMovie(movie.getResults().get(random).getPosterPath(),movie.getResults().get(random).getTitle()));
            }

            @Override
            public void onFailure(Call<ResponseMovies> call, Throwable t) {

            }
        });

        rating.setHasFixedSize(true);
        rating.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rating.addItemDecoration(new SpacesItemDecoration(24));
        ratingAdapter = new RatingBindingAdapter(this);
        rating.setAdapter(ratingAdapter);
        rating.setNestedScrollingEnabled(false);

        Call<ResponseMovies> rateCall = getRetrofitManager().getTopRateMovie(1);
        rateCall.enqueue(new Callback<ResponseMovies>() {
            @Override
            public void onResponse(Call<ResponseMovies> call, Response<ResponseMovies> response) {
                if(!response.isSuccessful()) return;
                ResponseMovies movie = response.body();
                if(movie == null) return;
                ratingAdapter.appendToList(movie.getResults());
                ratingAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseMovies> call, Throwable t) {

            }
        });


        popularity.setHasFixedSize(true);
        popularity.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        popularity.addItemDecoration(new SpacesItemDecoration(24));
        popularityAdapter = new PopularityBindingAdapter(this);
        popularity.setAdapter(popularityAdapter);
        popularity.setNestedScrollingEnabled(false);

        Call<ResponseMovies> popularCall = getRetrofitManager().getPopularityMovie(1);
        popularCall.enqueue(new Callback<ResponseMovies>() {
            @Override
            public void onResponse(Call<ResponseMovies> call, Response<ResponseMovies> response) {
                if(!response.isSuccessful()) return;
                ResponseMovies movie = response.body();
                if(movie == null) return;
                popularityAdapter.appendToList(movie.getResults());
                popularityAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseMovies> call, Throwable t) {

            }
        });

        upcoming.setHasFixedSize(true);
        upcoming.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        upcoming.addItemDecoration(new SpacesItemDecoration(24));
        upCommingAdapter = new UpCommingBindingAdapter(this);
        upcoming.setAdapter(upCommingAdapter);
        upcoming.setNestedScrollingEnabled(false);

        Call<ResponseMovies> upCall = getRetrofitManager().getUpComingMovie(1);
        upCall.enqueue(new Callback<ResponseMovies>() {
            @Override
            public void onResponse(Call<ResponseMovies> call, Response<ResponseMovies> response) {
                if(!response.isSuccessful()) return;
                ResponseMovies movie = response.body();
                if(movie == null) return;
                upCommingAdapter.appendToList(movie.getResults());
                upCommingAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseMovies> call, Throwable t) {

            }
        });

    }

    private MovieService getRetrofitManager(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://139.59.125.251:3030/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(MovieService.class);
    }

    @Override
    public void navigateToDetail(ResultsItem movies) {
        Intent intent = new Intent(getContext(),MovieDetailActivity.class);
        intent.putExtra("data",movies);
        startActivity(intent);
    }
}
