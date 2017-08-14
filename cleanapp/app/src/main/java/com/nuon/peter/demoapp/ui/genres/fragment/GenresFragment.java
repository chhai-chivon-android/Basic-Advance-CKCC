package com.nuon.peter.demoapp.ui.genres.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.nuon.peter.demoapp.R;
import com.nuon.peter.demoapp.ui.genres.adapter.GenrePagerAdapter;
import com.nuon.peter.demoapp.ui.genres.entity.genre.ResponseGenres;
import com.nuon.peter.demoapp.ui.genres.presenter.GenresPresenter;
import com.nuon.peter.demoapp.ui.genres.presenter.impl.GenresPresenterImpl;
import com.nuon.peter.demoapp.ui.genres.view.GenresView;
import com.nuon.peter.demoapp.utils.Common;
import com.nuon.peter.demoapp.utils.views.smarttab.SmartTabLayout;

/**
 * Created by manithnuon on 2/11/17.
 */

public class GenresFragment extends Fragment implements GenresView{

  @BindView(R.id.smarttab_genres_list) SmartTabLayout tab;
  @BindView(R.id.viewpager_movies_list) ViewPager pager;
  @BindView(R.id.toolbar_title_item) TextView title;

  private View view;
  private GenresPresenter presenter;
  private GenrePagerAdapter adapter;
  private GenresMoviesFragment fragment;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    view =  inflater.inflate(R.layout.layout_fragment_movie_list_genre,container,false);
    ButterKnife.bind(this,view);
    title.setText("Movies Genres");
    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    presenter = new GenresPresenterImpl(this);
    presenter.requestData(Common.STATE_GENRES);
  }

  @Override public void responseData(final ResponseGenres genres) {
    tab.setVisibility(View.VISIBLE);
    GenresMoviesFragment.mCurrentMenuCategory = genres.getGenres().get(0).getId();
    adapter = new GenrePagerAdapter(getFragmentManager(),genres.getGenres());
    pager.setOffscreenPageLimit(genres.getGenres().size());
    pager.setAdapter(adapter);
    tab.setViewPager(pager);
    tab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override public void onPageSelected(int position) {
          fragment = (GenresMoviesFragment) adapter.getRegisteredFragment(position);
          fragment.onPageSelected(position,genres.getGenres().get(position).getId());
      }

      @Override public void onPageScrollStateChanged(int state) {

      }
    });
    tab.setOnTabClickListener(new SmartTabLayout.OnTabClickListener() {
      @Override public void onTabClicked(int position) {
        fragment = (GenresMoviesFragment) adapter.getRegisteredFragment(position);
        fragment.onTabChanged(position,genres.getGenres().get(position).getId());
        pager.setCurrentItem(position);
      }
    });
  }

  @Override public void showError(int event, String msg) {

  }

  @Override public void showException(String msg) {

  }
}
