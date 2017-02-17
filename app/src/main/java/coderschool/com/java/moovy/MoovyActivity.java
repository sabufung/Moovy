package coderschool.com.java.moovy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import coderschool.com.java.moovy.APIEndPoints.MovieAPIEndPoint;
import coderschool.com.java.moovy.Adapter.MoviesAdapter;
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
    RecyclerView.Adapter mAdapter;
    LinearLayoutManager mLayoutManager;

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
        mLayoutManager = new LinearLayoutManager(this);
        rlvMovie.setLayoutManager(mLayoutManager);


        // specify an adapter (see also next example)

        fetchNowPlaying();

    }

    public void fetchNowPlaying() {
        Call<QueryResponse> listNowPlayingMovie = movieAPI.getNowPlayingMovie();
        listNowPlayingMovie.enqueue(new Callback<QueryResponse>() {
            @Override
            public void onResponse(Call<QueryResponse> call, Response<QueryResponse> response) {
                Log.d("result", response.body().getResults().toString());
                mAdapter = new MoviesAdapter(response.body().getResults());
                rlvMovie.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<QueryResponse> call, Throwable t) {
                Log.e("Xong", t.toString());

            }
        });
    }
}
