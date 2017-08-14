package com.nuon.peter.demoapp.ui.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.nuon.peter.demoapp.R;
import com.nuon.peter.demoapp.ui.home.presenter.HomePresenter;
import com.nuon.peter.demoapp.ui.home.presenter.impl.HomePresenterImpl;
import com.nuon.peter.demoapp.ui.home.view.HomeView;
import com.nuon.peter.demoapp.ui.movielist.entity.ResponseMovies;
import com.nuon.peter.demoapp.ui.home.adapter.MovieAdapter;
import com.nuon.peter.demoapp.utils.views.recyclerview.SpacesItemDecoration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by beniten on 12/29/16.
 */

public class HomeFragment extends Fragment implements HomeView{

  @BindView(R.id.background) KenBurnsView image;
  @BindView(R.id.movie_recycler) RecyclerView recyclerView;

  private View view ;
  private List<String> movieback;
  private MovieAdapter adapter;
  private HomePresenter presenter;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
      view =  inflater.inflate(R.layout.layout_fragment_demo_one,container,false);
    ButterKnife.bind(this,view);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
    recyclerView.addItemDecoration(new SpacesItemDecoration(32));
    adapter = new MovieAdapter(getContext());
    recyclerView.setAdapter(adapter);
    return view;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    Glide.with(getContext()).load(getMovieListBackground().get(new Random().nextInt(getMovieListBackground().size()-1)))
        .centerCrop()
        .crossFade()
        .diskCacheStrategy(DiskCacheStrategy.RESULT)
        .into(image);

    presenter = new HomePresenterImpl(this);
    presenter.requestData(1);

  }

  private List<String> getMovieListBackground(){
    movieback = new ArrayList<>();
    movieback.add("http://cdn2-www.comingsoon.net/assets/uploads/gallery/beauty-and-the-beast-2017/beauty1.jpg");
    movieback.add("http://cdn1-www.comingsoon.net/assets/uploads/gallery/beauty-and-the-beast-2017/beauty2.jpg");
    movieback.add("http://cdn1-www.comingsoon.net/assets/uploads/gallery/beauty-and-the-beast-2017/beautybeast0003_0.jpg");
    movieback.add("http://cdn2-www.comingsoon.net/assets/uploads/gallery/beauty-and-the-beast-2017/bnatb_007.jpg");

    return movieback;
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
  }

  @Override public void responseData(ResponseMovies movies) {
      adapter.appendToList(movies.getResults());
      adapter.notifyDataSetChanged();
  }

  @Override public void showError(String msg) {

  }

  @Override public void showException(String msg) {

  }
}
