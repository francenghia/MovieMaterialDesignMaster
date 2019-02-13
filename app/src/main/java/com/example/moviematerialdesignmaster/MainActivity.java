package com.example.moviematerialdesignmaster;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.moviematerialdesignmaster.Fragment.FindFragment;
import com.example.moviematerialdesignmaster.Fragment.FrankingFragment;
import com.example.moviematerialdesignmaster.Fragment.MoreFragment;
import com.example.moviematerialdesignmaster.Fragment.MovieFragment;
import com.example.moviematerialdesignmaster.Helpers.BottomNavigationBehavior;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_main);
        loadFragment(new MovieFragment());
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_movie:
                    fragment = new MovieFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_ranking:
                    fragment = new FrankingFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_find:
                    fragment = new FindFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_more:
                    fragment = new MoreFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
