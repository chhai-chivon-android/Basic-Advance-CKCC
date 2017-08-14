package com.nuon.peter.demoapp.rest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nuon.peter.demoapp.R;
import com.nuon.peter.demoapp.SpacesItemDecoration;
import com.nuon.peter.demoapp.rest.models.ResponseMovies;
import com.nuon.peter.demoapp.rest.models.ResultsItem;

import java.util.ArrayList;
import java.util.List;
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

public class HomeRestBindingFragment extends Fragment implements MovieDetailView {

    @BindView(R.id.movie_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.background)
    ImageView imageView;

    private HomeRestMovieBindingAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_home,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView.setImageResource(getMovieListBackground().get(new Random().nextInt(getMovieListBackground().size()-1)));

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.addItemDecoration(new SpacesItemDecoration(24));
        adapter = new HomeRestMovieBindingAdapter(this);
        recyclerView.setAdapter(adapter);
        Call<ResponseMovies> call = getRetrofitManager().getNowPlayingMovie(1);
        call.enqueue(new Callback<ResponseMovies>() {
            @Override
            public void onResponse(Call<ResponseMovies> call, Response<ResponseMovies> response) {
                    if(!response.isSuccessful()) return;
                    ResponseMovies data = response.body();
                    if(data == null) return;
                    adapter.appendToList(data.getResults());
                    adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseMovies> call, Throwable t) {

            }
        });



    }




    private MovieService getRetrofitManager(){
        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("https://api.themoviedb.org/3/")
                .baseUrl("http://139.59.125.251:3030/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(MovieService.class);
    }

    private List<Integer> getMovieListBackground(){
        List<Integer> movieback = new ArrayList<>();
        movieback.add(R.drawable.beauty_1);
        movieback.add(R.drawable.beauty_2);
        movieback.add(R.drawable.beauty_3);
        movieback.add(R.drawable.beauty_4);

        return movieback;
    }

    @Override
    public void navigateToDetail(ResultsItem movies) {
        Intent intent = new Intent(getContext(),MovieDetailActivity.class);
        intent.putExtra("data",movies);
        startActivity(intent);
    }
}
