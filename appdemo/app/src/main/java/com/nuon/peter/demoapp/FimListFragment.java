package com.nuon.peter.demoapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by manithnuon on 6/2/17.
 */

public class FimListFragment extends Fragment {

    @BindView(R.id.nowplay_recycler) RecyclerView nowplay;
    @BindView(R.id.rating_recycler) RecyclerView rating;
    @BindView(R.id.popularity_recycler) RecyclerView popularity;
    @BindView(R.id.up_comming_recycler) RecyclerView upcoming;

    private NowPlayingAdapter nowPlayingAdapter;
    private RatingAdapter ratingAdapter;
    private PopularityAdapter popularityAdapter;
    private UpCommingAdapter upCommingAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_demo_movie,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nowplay.setHasFixedSize(true);
        nowplay.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        nowplay.addItemDecoration(new SpacesItemDecoration(24));
        nowPlayingAdapter = new NowPlayingAdapter(getContext());
        nowPlayingAdapter.appendToList(getNowPlay());
        nowplay.setAdapter(nowPlayingAdapter);
        nowplay.setNestedScrollingEnabled(false);

        rating.setHasFixedSize(true);
        rating.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rating.addItemDecoration(new SpacesItemDecoration(24));
        ratingAdapter = new RatingAdapter(getContext());
        ratingAdapter.appendToList(getRating());
        rating.setAdapter(ratingAdapter);
        rating.setNestedScrollingEnabled(false);

        popularity.setHasFixedSize(true);
        popularity.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        popularity.addItemDecoration(new SpacesItemDecoration(24));
        popularityAdapter = new PopularityAdapter(getContext());
        popularityAdapter.appendToList(getPopularity());
        popularity.setAdapter(popularityAdapter);
        popularity.setNestedScrollingEnabled(false);

        upcoming.setHasFixedSize(true);
        upcoming.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        upcoming.addItemDecoration(new SpacesItemDecoration(24));
        upCommingAdapter = new UpCommingAdapter(getContext());
        upCommingAdapter.appendToList(getUpComing());
        upcoming.setAdapter(upCommingAdapter);
        upcoming.setNestedScrollingEnabled(false);
    }

    private List<MovieItem> getUpComing(){
        List<MovieItem> movies = new ArrayList<>();
        movies.add(new MovieItem("Guardians of the Galaxy Vol. 2","http://image.tmdb.org/t/p/w320/rxQynWvwl41VbJGb0FEyYtuRwS2.jpg"));
        movies.add(new MovieItem("Sleepless","http://image.tmdb.org/t/p/w320/doAzav9kfdtsoSdw1MDFvjKq3J4.jpg"));
        movies.add(new MovieItem("Split","http://image.tmdb.org/t/p/w320/ewVHnq4lUiovxBCu64qxq5bT2lu.jpg"));
        movies.add(new MovieItem("Power Rangers","http://image.tmdb.org/t/p/w320/f8Ng1Sgb3VLiSwAvrfKeQPzvlfr.jpg"));
        movies.add(new MovieItem("The Autopsy of Jane Doe","http://image.tmdb.org/t/p/w320/8K8LHSgXdUH3YHFJpcUXslPYpYr.jpg"));
        movies.add(new MovieItem("The Bye Bye Man","http://image.tmdb.org/t/p/w320/bQVqd5rWrx5GbXhJNuvKy4Viz6j.jpg"));
        movies.add(new MovieItem("Patriots Day","http://image.tmdb.org/t/p/w320/uM1dNEmcAeyDGjMcFLlkKOyBgft.jpg"));
        movies.add(new MovieItem("Going in Style","http://image.tmdb.org/t/p/w320/qvJeDDYKlfvtYayfOj5sAUXxUju.jpg"));
        movies.add(new MovieItem("The Promise","http://image.tmdb.org/t/p/w320/8LFlHYUclquOueiSbCaRdZlGRvb.jpg"));
        movies.add(new MovieItem("Aftermath","http://image.tmdb.org/t/p/w320/4VOyofBd1pexblxtDZYtYIk7NI4.jpg"));
        movies.add(new MovieItem("Alien: Covenant","http://image.tmdb.org/t/p/w320/rGOIFsq0lQ0Dm3zlxFwNYOYkbIk.jpg"));
        return movies;
    }

    private List<MovieItem> getNowPlay(){
        List<MovieItem> movies = new ArrayList<>();
        movies.add(new MovieItem("Guardians of the Galaxy Vol. 2","http://image.tmdb.org/t/p/w320/xbpSDU3p7YUGlu9Mr6Egg2Vweto.jpg"));
        movies.add(new MovieItem("Sleepless","http://image.tmdb.org/t/p/w320/gfJGlDaHuWimErCr5Ql0I8x9QSy.jpg"));
        movies.add(new MovieItem("Split","http://image.tmdb.org/t/p/w320/y4MBh0EjBlMuOzv9axM4qJlmhzz.jpg"));
        movies.add(new MovieItem("Power Rangers","http://image.tmdb.org/t/p/w320/ewVHnq4lUiovxBCu64qxq5bT2lu.jpg"));
        movies.add(new MovieItem("The Autopsy of Jane Doe","http://image.tmdb.org/t/p/w320/9THBirTgNIYaHWb3u4KX9EU7DDQ.jpg"));
        movies.add(new MovieItem("The Bye Bye Man","http://image.tmdb.org/t/p/w320/iNpz2DgTsTMPaDRZq2tnbqjL2vF.jpg"));
        movies.add(new MovieItem("Patriots Day","http://image.tmdb.org/t/p/w320/1SwAVYpuLj8KsHxllTF8Dt9dSSX.jpg"));
        movies.add(new MovieItem("Going in Style","http://image.tmdb.org/t/p/w320/rXMWOZiCt6eMX22jWuTOSdQ98bY.jpg"));
        movies.add(new MovieItem("The Promise","http://image.tmdb.org/t/p/w320/muwaDIv5cLv2Z07CZFWOsL7lYuO.jpg"));
        movies.add(new MovieItem("Aftermath","http://image.tmdb.org/t/p/w320/rxQynWvwl41VbJGb0FEyYtuRwS2.jpg"));
        movies.add(new MovieItem("Alien: Covenant","http://image.tmdb.org/t/p/w320/unPB1iyEeTBcKiLg8W083rlViFH.jpg"));
        return movies;
    }

    private List<MovieItem> getRating(){
        List<MovieItem> movies = new ArrayList<>();
        movies.add(new MovieItem("Guardians of the Galaxy Vol. 2","http://image.tmdb.org/t/p/w320/2gvbZMtV1Zsl7FedJa5ysbpBx2G.jpg"));
        movies.add(new MovieItem("Sleepless","http://image.tmdb.org/t/p/w320/9O7gLzmreU0nGkIB6K3BsJbzvNv.jpg"));
        movies.add(new MovieItem("Split","http://image.tmdb.org/t/p/w320/xq1Ugd62d23K2knRUx6xxuALTZB.jpg"));
        movies.add(new MovieItem("Power Rangers","http://image.tmdb.org/t/p/w320/rPdtLWNsZmAtoZl9PK7S2wE3qiS.jpg"));
        movies.add(new MovieItem("The Autopsy of Jane Doe","http://image.tmdb.org/t/p/w320/lIv1QinFqz4dlp5U4lQ6HaiskOZ.jpg"));
        movies.add(new MovieItem("The Bye Bye Man","http://image.tmdb.org/t/p/w320/yPisjyLweCl1tbgwgtzBCNCBle.jpg"));
        movies.add(new MovieItem("Patriots Day","http://image.tmdb.org/t/p/w320/ynXoOxmDHNQ4UAy0oU6avW71HVW.jpg"));
        movies.add(new MovieItem("Going in Style","http://image.tmdb.org/t/p/w320/bVq65huQ8vHDd1a4Z37QtuyEvpA.jpg"));
        movies.add(new MovieItem("The Promise","http://image.tmdb.org/t/p/w320/mMlPycVtj0bYZvTBpHe5BpVBk2S.jpg"));
        movies.add(new MovieItem("Aftermath","http://image.tmdb.org/t/p/w320/f7DImXDebOs148U4uPjI61iDvaK.jpg"));
        movies.add(new MovieItem("Alien: Covenant","http://image.tmdb.org/t/p/w320/adw6Lq9FiC9zjYEpOqfq03ituwp.jpg"));
        return movies;
    }

    private List<MovieItem> getPopularity(){
        List<MovieItem> movies = new ArrayList<>();
        movies.add(new MovieItem("Guardians of the Galaxy Vol. 2","http://image.tmdb.org/t/p/w320/tWqifoYuwLETmmasnGHO7xBjEtt.jpg"));
        movies.add(new MovieItem("Sleepless","http://image.tmdb.org/t/p/w320/tkt9xR1kNX5R9rCebASKck44si2.jpg"));
        movies.add(new MovieItem("Split","http://image.tmdb.org/t/p/w320/eSVtBB2PVFbQiFWC7CQi3EjIZnn.jpg"));
        movies.add(new MovieItem("Power Rangers","http://image.tmdb.org/t/p/w320/ewVHnq4lUiovxBCu64qxq5bT2lu.jpg"));
        movies.add(new MovieItem("The Autopsy of Jane Doe","http://image.tmdb.org/t/p/w320/waFr5RVKaQ9dzOt3nQuIVB1FiPu.jpg"));
        movies.add(new MovieItem("The Bye Bye Man","http://image.tmdb.org/t/p/w320/9THBirTgNIYaHWb3u4KX9EU7DDQ.jpg"));
        movies.add(new MovieItem("Patriots Day","http://image.tmdb.org/t/p/w320/wNUDAq5OUMOtxMlz64YaCp7gZma.jpg"));
        movies.add(new MovieItem("Going in Style","http://image.tmdb.org/t/p/w320/y31QB9kn3XSudA15tV7UWQ9XLuW.jpg"));
        movies.add(new MovieItem("The Promise","http://image.tmdb.org/t/p/w320/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg"));
        movies.add(new MovieItem("Aftermath","http://image.tmdb.org/t/p/w320/bXb00CkHqx7TPchTGG131sWV59y.jpg"));
        movies.add(new MovieItem("Alien: Covenant","http://image.tmdb.org/t/p/w320/iNpz2DgTsTMPaDRZq2tnbqjL2vF.jpg"));
        return movies;
    }
}
