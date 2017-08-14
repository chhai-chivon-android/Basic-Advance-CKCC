package com.nuon.peter.demoapp.ui.genres.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.nuon.peter.demoapp.R;
import com.nuon.peter.demoapp.ui.genres.adapter.GenresMoviesAdapter;
import com.nuon.peter.demoapp.ui.genres.entity.movie.ResponseMovies;
import com.nuon.peter.demoapp.ui.genres.entity.movie.ResultsItem;
import com.nuon.peter.demoapp.ui.genres.listener.OnPageSelectListener;
import com.nuon.peter.demoapp.ui.genres.listener.OnRecycleViewScrollListener;
import com.nuon.peter.demoapp.ui.genres.listener.TabChangedPageListener;
import com.nuon.peter.demoapp.ui.genres.presenter.GenresMoviesPresenter;
import com.nuon.peter.demoapp.ui.genres.presenter.impl.GenresMoviesPresenterImpl;
import com.nuon.peter.demoapp.ui.genres.view.GenresMoviesView;
import com.nuon.peter.demoapp.ui.moviedetail.activity.MovieDetailActivity;
import com.nuon.peter.demoapp.utils.Common;
import com.nuon.peter.demoapp.utils.views.recyclerview.SpacesItemDecoration;

/**
 * Created by manithnuon on 2/11/17.
 */

public class GenresMoviesFragment extends Fragment implements OnPageSelectListener,TabChangedPageListener,GenresMoviesView {

  @BindView(R.id.genres_recycler)
  RecyclerView genres;
  public static int mCurrentMenuCategory;
  private GenresMoviesAdapter adapter;
  private View view;
  private GenresMoviesPresenter presenter;
  private boolean isFirstResume = true;
  private boolean isFirstVisible = true;
  private boolean isFirstInvisible = true;
  private boolean isPrepared;
  private int currentpage = 1;
  private boolean isAvailable;
  private GridLayoutManager manager;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    view =  inflater.inflate(R.layout.layout_fragment_genres_movies,container,false);
    ButterKnife.bind(this,view);

    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    genres.setHasFixedSize(true);
    manager = new GridLayoutManager(getContext(),2);
    genres.setLayoutManager(manager);
    genres.addItemDecoration(new SpacesItemDecoration(32));
    adapter = new GenresMoviesAdapter(getContext(),this);
    genres.setAdapter(adapter);
    isAvailable = true;
    genres.addOnScrollListener(new OnRecycleViewScrollListener() {
      @Override
      public void onLoadMore() {
        if(isAvailable){
          isAvailable = false;
          currentpage ++;
          adapter.setViewHasMoreDataAndFooter(true,true);
          presenter.requestData(Common.STATE_GENRES_MOVIES_MORE,mCurrentMenuCategory,currentpage);
        }
      }
    });

    /*manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
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
    });*/
  }

  @Override public void onPageSelected(int position, int cate_id) {
    mCurrentMenuCategory = cate_id;
  }

  @Override public void onTabChanged(int position, int cate_id) {
    mCurrentMenuCategory = cate_id;
  }

  @Override public void responseGenres(ResponseMovies movies) {
      adapter.appendToList(movies.getResults());
      adapter.notifyDataSetChanged();
  }

  @Override public void responseGenresMore(ResponseMovies movies) {
      adapter.appendToListNotify(movies.getResults());
      isAvailable = true;
  }

  @Override public void navigateDetail(ResultsItem movie) {
   MovieDetailActivity.startActivity(getContext(),movie);
  }

  @Override public void showError(int event, String msg) {

  }

  @Override public void showException(String msg) {

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

  private void onFirstUserVisible(){
    presenter = new GenresMoviesPresenterImpl(this);
    presenter.requestData(Common.STATE_GENRES_MOVIES,mCurrentMenuCategory,currentpage);
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

}
