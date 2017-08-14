package com.nuon.peter.demoapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manithnuon on 6/3/17.
 */

public class Dummy {
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
}
