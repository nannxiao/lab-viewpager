package edu.uw.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements MovieListFragment.OnMovieSelectedListener, SearchFragment.OnSearchListener {

    private static final String TAG = "MainActivity";
    public static final String MOVIE_LIST_FRAGMENT_TAG = "MoviesListFragment";
    public static final String MOVIE_DETAIL_FRAGMENT_TAG = "DetailFragment";

    private SearchFragment searchFragment;
    private MovieListFragment listFragment;
    private DetailFragment detailFragment;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchFragment = SearchFragment.newInstance();
        viewPager = (ViewPager)findViewById(R.id.pager);
        pagerAdapter = new MoviePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
    }



    @Override
    public void onSearchSubmitted(String searchTerm) {
        listFragment = MovieListFragment.newInstance(searchTerm);
        pagerAdapter.notifyDataSetChanged();
        viewPager.setCurrentItem(1); //hard-code the shift
    }

    @Override
    public void onMovieSelected(Movie movie) {
        detailFragment = DetailFragment.newInstance(movie);
        pagerAdapter.notifyDataSetChanged();
        viewPager.setCurrentItem(2);

    }



    private class MoviePagerAdapter extends FragmentStatePagerAdapter {

        public MoviePagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position){
            if(position == 0){
                return searchFragment;
            }
            if(position == 1){
                return listFragment;
            }
            if(position == 2){
                return detailFragment;
            }
            return null;

        }

        @Override
        public int getCount() {
            if(listFragment == null){
                return 1;
            } else if (detailFragment == null) {
                return 2;
            } else {
                return 3;
            }
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }




    }
}
