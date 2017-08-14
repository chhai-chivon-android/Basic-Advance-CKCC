package com.nuon.peter.demoapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.nuon.peter.demoapp.model.movies.Data;
import com.nuon.peter.demoapp.model.movies.MoviesItem;
import com.nuon.peter.demoapp.model.movies.ResponseMovies;
import com.nuon.peter.demoapp.model.movies.deserialize.ResponseData;
import com.nuon.peter.demoapp.model.movies.deserialize.ResponseMoviesList;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by beniten on 12/29/16.
 */

public class FragmentOne extends Fragment{

  @BindView(R.id.background) KenBurnsView image;
  @BindView(R.id.movie_recycler) RecyclerView recyclerView;

  private View view ;
  private List<String> movieback;
  private MovieAdapter adapter;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    view =  inflater.inflate(R.layout.layout_fragment_demo_one,container,false);
    ButterKnife.bind(this,view);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
    adapter = new MovieAdapter(getContext());
    recyclerView.setAdapter(adapter);
    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    Glide.with(getContext()).load(getMovieListBackground().get(new Random().nextInt(getMovieListBackground().size()-1)))
        .centerCrop()
        .crossFade()
        .diskCacheStrategy(DiskCacheStrategy.RESULT)
        .into(image);

    Call<ResponseMovies> call = MovieAPI.getMovieManager().getMovieSortByAndLimit("popularity",15);
    call.enqueue(new Callback<ResponseMovies>() {
      @Override
      public void onResponse(Call<ResponseMovies> call, Response<ResponseMovies> response) {
        adapter.appendToList(response.body().getData().getMovies());
        adapter.notifyDataSetChanged();
      }

      @Override public void onFailure(Call<ResponseMovies> call, Throwable t) {

      }
    });


    /*Call<ResponseMovies> call = MovieAPI.getMovieManager().getMovieByGenre("Family");
    call.enqueue(new Callback<ResponseMovies>() {
      @Override
      public void onResponse(Call<ResponseMovies> call, Response<ResponseMovies> response) {
        ResponseMovies data = response.body();
        Log.d("App",data.toString());
      }

      @Override public void onFailure(Call<ResponseMovies> call, Throwable t) {

      }
    });*/
    /*Call<List<ResponseGithub>> call = GithubAPI.getGithubManager().getFollowersList("JakeWharton");
    call.enqueue(new Callback<List<ResponseGithub>>() {
      @Override public void onResponse(Call<List<ResponseGithub>> call,
          Response<List<ResponseGithub>> response) {
                List<ResponseGithub> data = response.body();

      }

      @Override public void onFailure(Call<List<ResponseGithub>> call, Throwable t) {

      }
    });*/
  }

  private List<String> getMovieListBackground(){
    movieback = new ArrayList<>();
    movieback.add("http://cdn2-www.comingsoon.net/assets/uploads/gallery/beauty-and-the-beast-2017/beauty1.jpg");
    movieback.add("http://cdn1-www.comingsoon.net/assets/uploads/gallery/beauty-and-the-beast-2017/beauty2.jpg");

    return movieback;
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
  }
}
