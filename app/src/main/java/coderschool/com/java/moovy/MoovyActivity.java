package coderschool.com.java.moovy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import coderschool.com.java.moovy.APIEndPoints.MovieAPIEndPoint;
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

public class MoovyActivity extends AppCompatActivity {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();


    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                                @Override
                                public okhttp3.Response intercept(Chain chain) throws IOException {
                                    Request original = chain.request();
                                    HttpUrl originalHttpUrl = original.url();

                                    HttpUrl url = originalHttpUrl.newBuilder()
                                            .addQueryParameter("api_key", "45d3c49989b0305dbe27ba8e88a99734")
                                            .build();

                                    // Request customization: add request headers
                                    Request request = chain.request().newBuilder().url(url).build();

                                    return chain.proceed(request);
                                }
                            }
            )
            .build();
    // Trailing slash is needed
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();

    MovieAPIEndPoint movieAPI = retrofit.create(MovieAPIEndPoint.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moovy);
        Call<QueryResponse> listNowPlayingMovie = movieAPI.getNowPlayingMovie();
        listNowPlayingMovie.enqueue(new Callback<QueryResponse>() {
            @Override
            public void onResponse(Call<QueryResponse> call, Response<QueryResponse> response) {
                Log.d("Xong", response.body().toString());
            }

            @Override
            public void onFailure(Call<QueryResponse> call, Throwable t) {
                Log.e("Xong", t.toString());

            }
        });
    }
}
