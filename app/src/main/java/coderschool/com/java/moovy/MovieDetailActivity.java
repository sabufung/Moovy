package coderschool.com.java.moovy;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import coderschool.com.java.moovy.APIEndPoints.MovieAPIEndPoint;
import coderschool.com.java.moovy.Models.Genre;
import coderschool.com.java.moovy.Models.MovieDetail;
import coderschool.com.java.moovy.Models.TrailerResponse;
import coderschool.com.java.moovy.databinding.ActivityMovieDetailBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends BaseActivity {

    YouTubePlayerView yvTrailer;
    TextView tvTitle;
    TextView tvDuration;
    TextView tvGenre;
    TextView tvOverview;
    TextView tvReleaseDate;
    RatingBar rbAverageVote;
    ImageView ivPoster;
    ActivityMovieDetailBinding binding;


    MovieAPIEndPoint movieAPI = retrofit.create(MovieAPIEndPoint.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        yvTrailer = binding.yvTrailer;
        tvTitle = binding.tvTitle;
        tvDuration = binding.tvDuration;
        tvGenre = binding.tvGenre;
        tvOverview = binding.tvOverview;
        tvReleaseDate = binding.tvReleaseDate;
        rbAverageVote = binding.rbAverageVote;
        ivPoster = binding.ivPoster;

        Intent i = getIntent();
        int movieId = i.getIntExtra("ID", 0);
        Log.d("error", movieId + " cc ");
        fetchDetail(movieId);
        fetchTrailer(movieId);
    }


    public void fetchTrailer(int id) {
        movieAPI.getMovies(id).enqueue(new Callback<TrailerResponse>() {
            @Override
            public void onResponse(Call<TrailerResponse> call, final Response<TrailerResponse> response) {
                yvTrailer.initialize(YT_API_KEY, new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                        youTubePlayer.cueVideo(response.body().getResults().get(0).getKey());
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                        Toast.makeText(MovieDetailActivity.this, "Error Initializing", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<TrailerResponse> call, Throwable t) {
                Toast.makeText(MovieDetailActivity.this, "Error occurs", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void fetchDetail(int id) {
        movieAPI.getDetail(id).enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                if (response.isSuccessful()) {
                    bindDetailData(response.body());
                } else {
                    Toast.makeText(MovieDetailActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {

            }
        });
    }

    private void bindDetailData(MovieDetail detail) {
//        tvTitle.setText(detail.getTitle());
//        String imageUrl = MovieAPIEndPoint.BASE_URL_IMG + MovieAPIEndPoint.POSTER_SIZE_W154;
//        Picasso.with(getBaseContext())
//                .load(imageUrl + detail.getPosterPath())
//                .into(ivPoster);
//        tvOverview.setText(detail.getOverview());
//        String genreText = "";
//        for (int i = 0; i < detail.getGenres().size(); i++) {
//            if (i == 0) {
//                genreText += detail.getGenres().get(i).getName();
//            }else {
//                genreText += " | " + detail.getGenres().get(i).getName();
//
//            }
//        }
//        Date releaseDate = detail.getReleaseDate();
//        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
//        String newFormat = formatter.format(releaseDate);
//        tvReleaseDate.setText(newFormat);
        float rating = (float) (detail.getVoteAverage() / 2);
        rbAverageVote.setRating(rating);
        rbAverageVote.setVisibility(View.VISIBLE);
//        tvGenre.setText(genreText);
//        tvDuration.setText(detail.getRuntime() + " minutes");
        binding.setDetail(detail);
    }
}
