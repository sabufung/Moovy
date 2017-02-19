package coderschool.com.java.moovy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseActivity extends YouTubeBaseActivity {
    public static final String API_KEY = "45d3c49989b0305dbe27ba8e88a99734";
    public static final String YT_API_KEY = "AIzaSyC3VuvwvkJz2JPJ69mJqfgQzhb5sEXR4T4";

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
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
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
