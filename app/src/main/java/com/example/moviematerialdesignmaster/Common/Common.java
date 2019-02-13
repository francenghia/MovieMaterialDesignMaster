package com.example.moviematerialdesignmaster.Common;

import com.example.moviematerialdesignmaster.Retrofit2.IRestClient;
import com.example.moviematerialdesignmaster.Retrofit2.RestClient;

public class Common {
    public static final String BASE_URL = "https://api.themoviedb.org";
    public static final String API_KEY = "a7733aa6ad9413b5225d28feffa45ed1";
    public static final String GENRES_URL = "/3/genre/movie/list?api_key=" + API_KEY;
    public static final String POPULAR_MOVIES_URL = "3/movie/popular?api_key=" + API_KEY;
    public static final String TOP_RATED_MOVIES_URL = "/3/movie/top_rated?api_key=" + API_KEY;
    public static final String MOVIE_ID = "movie_id";
    public static final String BACKDROP_URL = "https://image.tmdb.org/t/p/w300";
    public static final String PAGE = "page";

    public static IRestClient getAPI() {
        return RestClient.getClient(BASE_URL).create(IRestClient.class);
    }
}
