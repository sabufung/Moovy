package coderschool.com.java.moovy;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import coderschool.com.java.moovy.APIEndPoints.MovieAPIEndPoint;
import coderschool.com.java.moovy.Adapter.MoviesAdapter;
import coderschool.com.java.moovy.Models.Movie;
import coderschool.com.java.moovy.Models.QueryResponse;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MoovyActivity extends BaseActivity {

    @BindView(R.id.rlvMovie)
    RecyclerView rlvMovie;

    @BindView(R.id.swrMovie)
    SwipeRefreshLayout swipeContainer;

    MoviesAdapter mAdapter;
    LinearLayoutManager mLayoutManager;
    List<Movie> movieList = new ArrayList<>();
    int currentPage=1;
    boolean isLoadMore;

    MovieAPIEndPoint movieAPI = retrofit.create(MovieAPIEndPoint.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moovy);
        ButterKnife.bind(this);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        rlvMovie.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        rlvMovie.setLayoutManager(mLayoutManager);


        mAdapter = new MoviesAdapter(movieList);
        rlvMovie.setAdapter(mAdapter);

        rlvMovie.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isLoadMore)
                    return;
                if (mLayoutManager.findLastCompletelyVisibleItemPosition() == movieList.size() - 4) {
                    fetchNowPlaying(++currentPage);
                    isLoadMore = true;
                }
            }
        });
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                fetchNowPlaying(currentPage);
            }
        });



        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        fetchNowPlaying(currentPage);

    }

    public void fetchNowPlaying(int page) {
        Call<QueryResponse> listNowPlayingMovie = movieAPI.getNowPlayingMovie(page);
        listNowPlayingMovie.enqueue(new Callback<QueryResponse>() {
            @Override
            public void onResponse(Call<QueryResponse> call, Response<QueryResponse> response) {
                swipeContainer.setRefreshing(false);
                isLoadMore = false;
                if (response.isSuccessful()) {
                    if (currentPage == 1) {
                        mAdapter.clear();
                    }
                    mAdapter.addAll(response.body().getResults());
                } else {
                    Toast.makeText(MoovyActivity.this, "TimeOut", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<QueryResponse> call, Throwable t) {
                swipeContainer.setRefreshing(false);
                isLoadMore = false;
                Toast.makeText(MoovyActivity.this, "TimeOut", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
