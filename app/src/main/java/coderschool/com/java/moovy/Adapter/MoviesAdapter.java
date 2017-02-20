package coderschool.com.java.moovy.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.squareup.picasso.Picasso;

import java.util.List;

import coderschool.com.java.moovy.APIEndPoints.MovieAPIEndPoint;
import coderschool.com.java.moovy.Adapter.ViewHolder.MovieViewHolder;
import coderschool.com.java.moovy.Adapter.ViewHolder.PopularMovieViewHolder;
import coderschool.com.java.moovy.BaseActivity;
import coderschool.com.java.moovy.Models.Movie;
import coderschool.com.java.moovy.MoovyActivity;
import coderschool.com.java.moovy.MovieDetailActivity;
import coderschool.com.java.moovy.R;
import coderschool.com.java.moovy.YoutubePlayerActivity;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

import static coderschool.com.java.moovy.BaseActivity.YT_API_KEY;

/**
 * Created by BuuPV on 2/17/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Movie dataSet;
    List<Movie> movies;
    Context context;
    Configuration configuration;

    private static final int POPULAR = 2, NORMAL = 0, NORMAL_LAND = 1;

    // Provide a suitable constructor (depends on the kind of dataset)
    public MoviesAdapter(List<Movie> movies) {
        this.movies = movies;

    }


//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        // create a new view
//        context = parent.getContext();
//        Configuration configuration = context.getResources()
//                .getConfiguration();
//        View view = getInflatedLayoutForType(getItemViewType(viewType))
//
//        ViewHolder vh = new ViewHolder(view);
//        return vh;
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        context = parent.getContext();
        configuration = context.getResources().getConfiguration();

        LayoutInflater inflater = LayoutInflater.from(context);

        switch (viewType) {
            case NORMAL:
                View v1;
                if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    v1 = inflater.inflate(R.layout.movie_landscape, parent, false);
                } else {
                    v1 = inflater.inflate(R.layout.movie, parent, false);

                }
                viewHolder = new MovieViewHolder(v1);
                break;
            case POPULAR:
                View v2 = inflater.inflate(R.layout.movie_popular, parent, false);
                viewHolder = new PopularMovieViewHolder(v2);
                break;
            default:
                View v = inflater.inflate(R.layout.movie, parent, false);
                viewHolder = new MovieViewHolder(v);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case NORMAL:

                MovieViewHolder vh1 = (MovieViewHolder) holder;
                configureViewHolder1(vh1, position);
                break;
            case POPULAR:
                PopularMovieViewHolder vh2 = (PopularMovieViewHolder) holder;
                configureViewHolder2(vh2, position);
                break;
            default:
                MovieViewHolder vh = (MovieViewHolder) holder;
                configureViewHolder1(vh, position);
                break;
        }
    }

    private void configureViewHolder1(MovieViewHolder vh1, int position) {
        final Movie movie = (Movie) movies.get(position);
        if (movie != null) {
            vh1.getTvTitle().setText(movie.getTitle());
            float rating = (float) (movie.getVoteAverage() / 2);
            vh1.getRbAverageVote().setRating(rating);
            vh1.getTvOverview().setText(movie.getOverview());
            if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                loadImage(vh1.getIvPoster(), movie.getBackdropPath(), true);
            } else {
                loadImage(vh1.getIvPoster(), movie.getPosterPath(), false);
            }
            vh1.getIvPoster().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, MovieDetailActivity.class);
                    i.putExtra("ID", movie.getId());
                    context.startActivity(i);
                }
            });
        }
    }

    private void configureViewHolder2(PopularMovieViewHolder vh2, int position) {
        final Movie movie = (Movie) movies.get(position);
        if (movie != null) {
            vh2.getTvTitle().setText(movie.getTitle());
            vh2.getIvPoster().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, YoutubePlayerActivity.class);
                    i.putExtra("ID", movie.getId());
                    context.startActivity(i);
                }
            });
            loadImage(vh2.getIvPoster(), movie.getBackdropPath(), true);
        }
    }

    @Override
    public int getItemViewType(int position) {
        double average = movies.get(position).getVoteAverage();
        if (average >= 7) {
            return POPULAR;
        } else {
//
            return NORMAL;
        }
    }


//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//    // Get the data model based on position
//        dataSet = movies.get(position);
//
//        // Set item views based on your views and data model
//        configuration = context.getResources()
//                .getConfiguration();
//        String imageUrl = MovieAPIEndPoint.BASE_URL_IMG + MovieAPIEndPoint.POSTER_SIZE_W154 ;
//        boolean landscape = false;
//        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            imageUrl += dataSet.getPosterPath();
//        } else {
//            imageUrl += dataSet.getBackdropPath();
//            landscape = true;
//        }
//
//        loadImage(holder.ivPoster, imageUrl,landscape);
//        holder.tvTitle.setText(dataSet.getTitle());
//
//        holder.ivPoster.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, MovieDetailActivity.class);
//
//                ((MoovyActivity) context).startActivityForResult(intent, 0);
//            }
//        });
//    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    private void loadImage(ImageView imageView, String path, boolean landscape) {
        // TODO: Insert your code
        int placeholder;
        String imageUrl = MovieAPIEndPoint.BASE_URL_IMG;
        if (landscape) {
            imageUrl += MovieAPIEndPoint.POSTER_SIZE_W342;
            placeholder = R.drawable.movie_placeholder_backdrop;
        } else {
            imageUrl += MovieAPIEndPoint.POSTER_SIZE_W154;
            placeholder = R.drawable.movie_placeholder;
        }
        Picasso.with(context)
                .load(imageUrl + path)
                .placeholder(placeholder)
                .transform(new RoundedCornersTransformation(2, 2))
                .into(imageView);
    }

    // Clean all elements of the recycler
    public void clear() {
        movies.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<Movie> list) {
        movies.addAll(list);
        notifyDataSetChanged();
    }

    private View getInflatedLayoutForType(double voteAverage) {
        if (voteAverage >= 7) {
            return LayoutInflater.from(context).inflate(R.layout.movie_popular, null);
        } else {
            return LayoutInflater.from(context).inflate(R.layout.movie, null);
        }
    }
}
