package com.example.moviematerialdesignmaster.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.moviematerialdesignmaster.Adapter.MovieAdapter;
import com.example.moviematerialdesignmaster.Common.Common;
import com.example.moviematerialdesignmaster.MainActivity;
import com.example.moviematerialdesignmaster.Models.Banner;
import com.example.moviematerialdesignmaster.Models.Movie;
import com.example.moviematerialdesignmaster.Models.Response;
import com.example.moviematerialdesignmaster.R;
import com.example.moviematerialdesignmaster.Retrofit2.IRestClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    private SliderLayout sliderMovieReview;
    private DatabaseReference movie;
    private FirebaseDatabase database;
    private HashMap<String, String> imageList;
    private CompositeDisposable disposable = new CompositeDisposable();
    private IRestClient mServer;
    private RecyclerView recyclerMoviePopular;
    private LinearLayoutManager linearLayoutManager;

    public MovieFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        MappingAndConnect(view);
        getDataBannerFirebase();
        getDataForMovies();
        return view;
    }

    private void getDataForMovies() {
        disposable.add(mServer.getPopularMovies(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Response>() {
                    @Override
                    public void accept(Response response) throws Exception {
                        listData(response.getResults());
                    }
                }));
    }

    private void listData(List<Movie> results) {
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerMoviePopular.setLayoutManager(linearLayoutManager);
        MovieAdapter adapter = new MovieAdapter(results, getContext());
        recyclerMoviePopular.setAdapter(adapter);

    }


    private void getDataBannerFirebase() {

        imageList = new HashMap<>();
        movie.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot value : dataSnapshot.getChildren()) {
                    Banner banner = value.getValue(Banner.class);
                    imageList.put(banner.getName(), banner.getImage());
                }

                for (String name : imageList.keySet()) {
                    TextSliderView textSliderView = new TextSliderView(getContext());
                    textSliderView.description(name)
                            .image(imageList.get(name))
                            .setScaleType(BaseSliderView.ScaleType.Fit);

                    sliderMovieReview.addSlider(textSliderView);
                    movie.removeEventListener(this);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        sliderMovieReview.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderMovieReview.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderMovieReview.setCustomAnimation(new DescriptionAnimation());
        sliderMovieReview.setDuration(4000);
    }

    private void MappingAndConnect(View view) {
        //Firebase
        database = FirebaseDatabase.getInstance();
        movie = database.getReference("Movies");
        sliderMovieReview = (SliderLayout) view.findViewById(R.id.sliderMovieReview);
        recyclerMoviePopular = (RecyclerView) view.findViewById(R.id.recyclerPopularMovie);
        mServer = Common.getAPI();
    }

}
