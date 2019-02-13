package com.example.moviematerialdesignmaster.Retrofit2;

import com.example.moviematerialdesignmaster.Common.Common;
import com.example.moviematerialdesignmaster.Models.Genres;
import com.example.moviematerialdesignmaster.Models.Response;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IRestClient {
    // Gets a list of popular movies.
    @GET(Common.POPULAR_MOVIES_URL)
    Observable<Response> getPopularMovies(@Query(Common.PAGE) int page);

    // Gets a list of the top rated movies
    @GET(Common.TOP_RATED_MOVIES_URL)
    Observable<Response> getTopRatedMovies(@Query(Common.PAGE) int page);

    //Gets a list of genres
    @GET(Common.GENRES_URL)
    Observable<Genres> getGenres();
}
