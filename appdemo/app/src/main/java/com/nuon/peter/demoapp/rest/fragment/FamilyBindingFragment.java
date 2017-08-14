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

import com.nuon.peter.demoapp.R;
import com.nuon.peter.demoapp.SpacesItemDecoration;
import com.nuon.peter.demoapp.rest.MovieAPI;
import com.nuon.peter.demoapp.rest.MovieDetailActivity;
import com.nuon.peter.demoapp.rest.MovieDetailView;
import com.nuon.peter.demoapp.rest.MovieService;
import com.nuon.peter.demoapp.rest.adapter.GenresAdapter;
import com.nuon.peter.demoapp.rest.adapter.GenresBindingAdapter;
import com.nuon.peter.demoapp.rest.adapter.GenresBindingLoadMoreAdapter;
import com.nuon.peter.demoapp.rest.adapter.OnRecycleViewScrollListener;
import com.nuon.peter.demoapp.rest.models.ResponseMovies;
import com.nuon.peter.demoapp.rest.models.ResultsItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by manithnuon on 4/29/17.
 */

public class FamilyBindingFragment extends Fragment implements MovieDetailView {
  private RecyclerView recyclerView;
  private GenresBindingLoadMoreAdapter adapter;
  private int currentpage = 1;
  private boolean isAvailable;
  private GridLayoutManager manager;
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
    manager = new GridLayoutManager(getContext(),2);
    recyclerView.setLayoutManager(manager);
    adapter = new GenresBindingLoadMoreAdapter(this);
    recyclerView.addItemDecoration(new SpacesItemDecoration(24));
    recyclerView.setAdapter(adapter);
    adapter.notifyDataSetChanged();


    Call<ResponseMovies> call = MovieAPI.getAPIManager("http://139.59.125.251:3030/api/").create(MovieService.class)
            .getMovieByGenres(10751,currentpage);
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
    isAvailable = true;
    recyclerView.addOnScrollListener(new OnRecycleViewScrollListener() {
      @Override
      public void onLoadMore() {
        if(isAvailable){
          isAvailable = false;
          adapter.setViewHasMoreDataAndFooter(true,true);
          currentpage ++;
          Call<ResponseMovies> call = MovieAPI.getAPIManager("http://139.59.125.251:3030/api/").create(MovieService.class)
                  .getMovieByGenres(10751,currentpage);
          call.enqueue(new Callback<ResponseMovies>() {
            @Override
            public void onResponse(Call<ResponseMovies> call, Response<ResponseMovies> response) {
              if(!response.isSuccessful()) return;
              ResponseMovies movie = response.body();
              if(movie == null) return;
              adapter.appendToList(movie.getResults());
              isAvailable = true;
            }

            @Override
            public void onFailure(Call<ResponseMovies> call, Throwable t) {

            }
          });
        }
      }
    });

    manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
      @Override
      public int getSpanSize(int position) {
        switch(adapter.getItemViewType(position)){
          case 0:
            return 1;
          case 1:
            return 2;
          default:
            return -1;
        }
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
