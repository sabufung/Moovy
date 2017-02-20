package coderschool.com.java.moovy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import coderschool.com.java.moovy.APIEndPoints.MovieAPIEndPoint;
import coderschool.com.java.moovy.Models.TrailerResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class YoutubePlayerActivity extends BaseActivity {

    YouTubePlayerView yvTrailer;
    int movieId;
    MovieAPIEndPoint movieAPI = retrofit.create(MovieAPIEndPoint.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_player);
        yvTrailer = (YouTubePlayerView) findViewById(R.id.yvTrailer);
        Intent i = getIntent();
        movieId = i.getIntExtra("ID",0);
        fetchTrailer(movieId);


    }

    private void fetchTrailer(int id) {
        movieAPI.getMovies(id).enqueue(new Callback<TrailerResponse>() {
            @Override
            public void onResponse(Call<TrailerResponse> call, final Response<TrailerResponse> response) {
                yvTrailer.initialize("YOUR API KEY",
                        new YouTubePlayer.OnInitializedListener() {
                            @Override
                            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                                YouTubePlayer youTubePlayer, boolean b) {

                                // do any work here to cue video, play video, etc.
                                youTubePlayer.loadVideo(response.body().getResults().get(0).getKey());
                            }
                            @Override
                            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                                YouTubeInitializationResult youTubeInitializationResult) {
                                Toast.makeText(YoutubePlayerActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        });
            }

            @Override
            public void onFailure(Call<TrailerResponse> call, Throwable t) {
                Toast.makeText(YoutubePlayerActivity.this, "asd", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
