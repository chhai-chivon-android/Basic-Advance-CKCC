package com.nuon.peter.demoapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by manithnuon on 4/21/17.
 */

public class HomeFragment extends Fragment {
  private RecyclerView recyclerView;
  private ImageView imageView;
  private HomeMoviesAdapter adapter;
  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view =  inflater.inflate(R.layout.layout_fragment_home,container,false);
    recyclerView = (RecyclerView) view.findViewById(R.id.movie_recycler);
    imageView = (ImageView) view.findViewById(R.id.background);
    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    imageView.setImageResource(getMovieListBackground().get(new Random().nextInt(getMovieListBackground().size()-1)));

    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
    recyclerView.addItemDecoration(new SpacesItemDecoration(24));
    adapter = new HomeMoviesAdapter(view.getContext());
    recyclerView.setAdapter(adapter);

    adapter.appendToList(getDummy());
    adapter.notifyDataSetChanged();
  }

  private List<MovieItem> getDummyMovies(){
    List<MovieItem> movies = new ArrayList<>();
    movies.add(new MovieItem("Split",R.mipmap.bg_1));
    movies.add(new MovieItem("Guardians of the Galaxy Vol. 2",R.mipmap.bg_2));
    movies.add(new MovieItem("Power Rangers",R.mipmap.bg_3));
    movies.add(new MovieItem("The Autopsy of Jane Doe",R.mipmap.bg_4));
    movies.add(new MovieItem("Sleepless",R.mipmap.bg_5));
    movies.add(new MovieItem("Patriots Day",R.mipmap.bg_6));
    movies.add(new MovieItem("Going in Style",R.mipmap.bg_7));
    movies.add(new MovieItem("The Bye Bye Man",R.mipmap.bg_8));
    movies.add(new MovieItem("Aftermath",R.mipmap.bg_9));
    movies.add(new MovieItem("Alien: Covenant",R.mipmap.bg_10));
    movies.add(new MovieItem("The Promise",R.mipmap.bg_11));
    return movies;
  }

  private List<MovieItem> getDummyMovie(){
    List<MovieItem> movies = new ArrayList<>();
    movies.add(new MovieItem("Split","http://image.tmdb.org/t/p/w320/ewVHnq4lUiovxBCu64qxq5bT2lu.jpg"));
    movies.add(new MovieItem("Guardians of the Galaxy Vol. 2","http://image.tmdb.org/t/p/w320/rxQynWvwl41VbJGb0FEyYtuRwS2.jpg"));
    movies.add(new MovieItem("Power Rangers","http://image.tmdb.org/t/p/w320/f8Ng1Sgb3VLiSwAvrfKeQPzvlfr.jpg"));
    movies.add(new MovieItem("The Autopsy of Jane Doe","http://image.tmdb.org/t/p/w320/8K8LHSgXdUH3YHFJpcUXslPYpYr.jpg"));
    movies.add(new MovieItem("Sleepless","http://image.tmdb.org/t/p/w320/doAzav9kfdtsoSdw1MDFvjKq3J4.jpg"));
    movies.add(new MovieItem("Patriots Day","http://image.tmdb.org/t/p/w320/uM1dNEmcAeyDGjMcFLlkKOyBgft.jpg"));
    movies.add(new MovieItem("Going in Style","http://image.tmdb.org/t/p/w320/qvJeDDYKlfvtYayfOj5sAUXxUju.jpg"));
    movies.add(new MovieItem("The Bye Bye Man","http://image.tmdb.org/t/p/w320/bQVqd5rWrx5GbXhJNuvKy4Viz6j.jpg"));
    movies.add(new MovieItem("Aftermath","http://image.tmdb.org/t/p/w320/4VOyofBd1pexblxtDZYtYIk7NI4.jpg"));
    movies.add(new MovieItem("Alien: Covenant","http://image.tmdb.org/t/p/w320/rGOIFsq0lQ0Dm3zlxFwNYOYkbIk.jpg"));
    movies.add(new MovieItem("The Promise","http://image.tmdb.org/t/p/w320/8LFlHYUclquOueiSbCaRdZlGRvb.jpg"));
    return movies;
  }

  private List<MovieItem> getDummy(){
    List<MovieItem> movies = new ArrayList<>();
    movies.add(new MovieItem("Split","http://image.tmdb.org/t/p/w320/ewVHnq4lUiovxBCu64qxq5bT2lu.jpg","http://image.tmdb.org/t/p/w650/ewVHnq4lUiovxBCu64qxq5bT2lu.jpg"));
    movies.add(new MovieItem("Guardians of the Galaxy Vol. 2","http://image.tmdb.org/t/p/w320/rxQynWvwl41VbJGb0FEyYtuRwS2.jpg","http://image.tmdb.org/t/p/w650/rxQynWvwl41VbJGb0FEyYtuRwS2.jpg"));
    movies.add(new MovieItem("Power Rangers","http://image.tmdb.org/t/p/w320/f8Ng1Sgb3VLiSwAvrfKeQPzvlfr.jpg","http://image.tmdb.org/t/p/w650/f8Ng1Sgb3VLiSwAvrfKeQPzvlfr.jpg"));
    movies.add(new MovieItem("The Autopsy of Jane Doe","http://image.tmdb.org/t/p/w320/8K8LHSgXdUH3YHFJpcUXslPYpYr.jpg","http://image.tmdb.org/t/p/w650/8K8LHSgXdUH3YHFJpcUXslPYpYr.jpg"));
    movies.add(new MovieItem("Sleepless","http://image.tmdb.org/t/p/w320/doAzav9kfdtsoSdw1MDFvjKq3J4.jpg","http://image.tmdb.org/t/p/w650/doAzav9kfdtsoSdw1MDFvjKq3J4.jpg"));
    movies.add(new MovieItem("Patriots Day","http://image.tmdb.org/t/p/w320/uM1dNEmcAeyDGjMcFLlkKOyBgft.jpg","http://image.tmdb.org/t/p/w650/uM1dNEmcAeyDGjMcFLlkKOyBgft.jpg"));
    movies.add(new MovieItem("Going in Style","http://image.tmdb.org/t/p/w320/qvJeDDYKlfvtYayfOj5sAUXxUju.jpg","http://image.tmdb.org/t/p/w650/qvJeDDYKlfvtYayfOj5sAUXxUju.jpg"));
    movies.add(new MovieItem("The Bye Bye Man","http://image.tmdb.org/t/p/w320/bQVqd5rWrx5GbXhJNuvKy4Viz6j.jpg","http://image.tmdb.org/t/p/w650/bQVqd5rWrx5GbXhJNuvKy4Viz6j.jpg"));
    movies.add(new MovieItem("Aftermath","http://image.tmdb.org/t/p/w320/4VOyofBd1pexblxtDZYtYIk7NI4.jpg","http://image.tmdb.org/t/p/w650/4VOyofBd1pexblxtDZYtYIk7NI4.jpg"));
    movies.add(new MovieItem("Alien: Covenant","http://image.tmdb.org/t/p/w320/rGOIFsq0lQ0Dm3zlxFwNYOYkbIk.jpg","http://image.tmdb.org/t/p/w650/rGOIFsq0lQ0Dm3zlxFwNYOYkbIk.jpg"));
    movies.add(new MovieItem("The Promise","http://image.tmdb.org/t/p/w320/8LFlHYUclquOueiSbCaRdZlGRvb.jpg","http://image.tmdb.org/t/p/w650/8LFlHYUclquOueiSbCaRdZlGRvb.jpg"));
    return movies;
  }

  private List<Integer> getMovieListBackground(){
    List<Integer> movieback = new ArrayList<>();
    movieback.add(R.drawable.beauty_1);
    movieback.add(R.drawable.beauty_2);
    movieback.add(R.drawable.beauty_3);
    movieback.add(R.drawable.beauty_4);

    return movieback;
  }

}
