package com.nuon.peter.demoapp.rest.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nuon.peter.demoapp.GenreMovieAdapter;
import com.nuon.peter.demoapp.MovieItem;
import com.nuon.peter.demoapp.R;
import com.nuon.peter.demoapp.SpacesItemDecoration;
import com.nuon.peter.demoapp.rest.MovieAPI;
import com.nuon.peter.demoapp.rest.MovieDetailActivity;
import com.nuon.peter.demoapp.rest.MovieDetailView;
import com.nuon.peter.demoapp.rest.MovieService;
import com.nuon.peter.demoapp.rest.adapter.GenresAdapter;
import com.nuon.peter.demoapp.rest.models.ResponseMovies;
import com.nuon.peter.demoapp.rest.models.ResultsItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by manithnuon on 4/29/17.
 */

public class ActionFragment extends Fragment implements MovieDetailView {
  private RecyclerView recyclerView;
  private GenresAdapter adapter;
  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.layout_fragment_movie_rest,container,false);
    recyclerView = (RecyclerView) view.findViewById(R.id.recycler_movie);
    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
    adapter = new GenresAdapter(view.getContext(),this);
    recyclerView.addItemDecoration(new SpacesItemDecoration(24));
    recyclerView.setAdapter(adapter);
    adapter.notifyDataSetChanged();

    Call<ResponseMovies> call = MovieAPI.getAPIManager("http://139.59.125.251:3030/api/").create(MovieService.class)
            .getMovieByGenres(28,1);
    call.enqueue(new Callback<ResponseMovies>() {
      @Override
      public void onResponse(Call<ResponseMovies> call, Response<ResponseMovies> response) {
        if(!response.isSuccessful()) return;
        ResponseMovies movie = response.body();
        if(movie == null) return;
        adapter.appendToList(movie.getResults());
        adapter.notifyDataSetChanged();
      }

      @Override
      public void onFailure(Call<ResponseMovies> call, Throwable t) {

      }
    });

  }


  @Override
  public void navigateToDetail(ResultsItem movies) {
    Intent intent = new Intent(getContext(),MovieDetailActivity.class);
    intent.putExtra("data",movies);
    startActivity(intent);
  }
}
