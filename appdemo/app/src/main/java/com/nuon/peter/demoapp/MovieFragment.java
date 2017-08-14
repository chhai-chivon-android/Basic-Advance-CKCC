package com.nuon.peter.demoapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by manithnuon on 4/21/17.
 */

public class MovieFragment extends Fragment implements OnPageSelectListener{



  private RecyclerView recyclerView;
  private GenreMovieAdapter adapter;
  public static int mCurrentMenuCategory;
  private boolean isFirstResume = true;
  private boolean isFirstVisible = true;
  private boolean isFirstInvisible = true;
  private boolean isPrepared;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.layout_fragment_movie,container,false);
    recyclerView = (RecyclerView) view.findViewById(R.id.recycler_movie);
    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
    adapter = new GenreMovieAdapter(view.getContext());
    recyclerView.setAdapter(adapter);

  }

  private void onFirstUserVisible(){
    adapter.appendToList(getMovieById(mCurrentMenuCategory));
    adapter.notifyDataSetChanged();
  }

  private void onUserVisible(){}

  private void onFirstUserInvisible(){}

  private void onUserInvisible(){}

  @Override
  public void onResume() {
    super.onResume();
    if (isFirstResume) {
      isFirstResume = false;
      return;
    }
    if (getUserVisibleHint()) {
      onUserVisible();
    }
  }

  @Override
  public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);
    if (isVisibleToUser) {
      if (isFirstVisible) {
        isFirstVisible = false;
        initPrepare();
      } else {
        onUserVisible();
      }
    } else {
      if (isFirstInvisible) {
        isFirstInvisible = false;
        onFirstUserInvisible();
      } else {
        onUserInvisible();
      }
    }
  }

  private List<MovieItem> getMovieById(int id){
    switch (id){
      case 1: return getFamilyMovies();
      case 2: return getComedyMovies();
      case 3: return getActionMovies();
      case 4: return getHorrorMovies();
      case 5: return getAdventureMovies();
      default: return getFamilyMovies();
    }
  }

  private List<MovieItem> getFamilyMovies(){
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

  private List<MovieItem> getComedyMovies(){
    List<MovieItem> movies = new ArrayList<>();
    movies.add(new MovieItem("Split",R.mipmap.bg_1));
    movies.add(new MovieItem("Guardians of the Galaxy Vol. 2",R.mipmap.bg_2));
    movies.add(new MovieItem("Patriots Day",R.mipmap.bg_6));
    movies.add(new MovieItem("Power Rangers",R.mipmap.bg_3));
    movies.add(new MovieItem("The Autopsy of Jane Doe",R.mipmap.bg_4));
    movies.add(new MovieItem("The Promise",R.mipmap.bg_11));
    movies.add(new MovieItem("Going in Style",R.mipmap.bg_7));
    movies.add(new MovieItem("Alien: Covenant",R.mipmap.bg_10));
    movies.add(new MovieItem("The Bye Bye Man",R.mipmap.bg_8));
    movies.add(new MovieItem("Sleepless",R.mipmap.bg_5));
    movies.add(new MovieItem("Aftermath",R.mipmap.bg_9));
    return movies;
  }

  private List<MovieItem> getActionMovies(){
    List<MovieItem> movies = new ArrayList<>();
    movies.add(new MovieItem("Sleepless",R.mipmap.bg_5));
    movies.add(new MovieItem("Split",R.mipmap.bg_1));
    movies.add(new MovieItem("Power Rangers",R.mipmap.bg_3));
    movies.add(new MovieItem("The Autopsy of Jane Doe",R.mipmap.bg_4));
    movies.add(new MovieItem("Guardians of the Galaxy Vol. 2",R.mipmap.bg_2));
    movies.add(new MovieItem("Alien: Covenant",R.mipmap.bg_10));
    movies.add(new MovieItem("Going in Style",R.mipmap.bg_7));
    movies.add(new MovieItem("Patriots Day",R.mipmap.bg_6));
    movies.add(new MovieItem("The Promise",R.mipmap.bg_11));
    movies.add(new MovieItem("The Bye Bye Man",R.mipmap.bg_8));
    movies.add(new MovieItem("Aftermath",R.mipmap.bg_9));
    return movies;
  }

  private List<MovieItem> getHorrorMovies(){
    List<MovieItem> movies = new ArrayList<>();
    movies.add(new MovieItem("Split",R.mipmap.bg_1));
    movies.add(new MovieItem("Guardians of the Galaxy Vol. 2",R.mipmap.bg_2));
    movies.add(new MovieItem("Power Rangers",R.mipmap.bg_3));
    movies.add(new MovieItem("The Autopsy of Jane Doe",R.mipmap.bg_4));
    movies.add(new MovieItem("The Promise",R.mipmap.bg_11));
    movies.add(new MovieItem("Patriots Day",R.mipmap.bg_6));
    movies.add(new MovieItem("Going in Style",R.mipmap.bg_7));
    movies.add(new MovieItem("The Bye Bye Man",R.mipmap.bg_8));
    movies.add(new MovieItem("Sleepless",R.mipmap.bg_5));
    movies.add(new MovieItem("Aftermath",R.mipmap.bg_9));
    movies.add(new MovieItem("Alien: Covenant",R.mipmap.bg_10));
    return movies;
  }

  private List<MovieItem> getAdventureMovies(){
    List<MovieItem> movies = new ArrayList<>();
    movies.add(new MovieItem("Guardians of the Galaxy Vol. 2",R.mipmap.bg_2));
    movies.add(new MovieItem("Power Rangers",R.mipmap.bg_3));
    movies.add(new MovieItem("Patriots Day",R.mipmap.bg_6));
    movies.add(new MovieItem("The Autopsy of Jane Doe",R.mipmap.bg_4));
    movies.add(new MovieItem("Sleepless",R.mipmap.bg_5));
    movies.add(new MovieItem("Alien: Covenant",R.mipmap.bg_10));
    movies.add(new MovieItem("The Promise",R.mipmap.bg_11));
    movies.add(new MovieItem("Going in Style",R.mipmap.bg_7));
    movies.add(new MovieItem("Split",R.mipmap.bg_1));
    movies.add(new MovieItem("The Bye Bye Man",R.mipmap.bg_8));
    movies.add(new MovieItem("Aftermath",R.mipmap.bg_9));
    return movies;
  }

  @Override public void onPageSelected(int position, int cate_id) {
    mCurrentMenuCategory = cate_id;
  }

  private synchronized void initPrepare() {
    if (isPrepared) {
      onFirstUserVisible();
    } else {
      isPrepared = true;
    }
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    initPrepare();
  }

}
