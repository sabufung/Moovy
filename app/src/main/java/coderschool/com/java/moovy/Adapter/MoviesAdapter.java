package coderschool.com.java.moovy.Adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import coderschool.com.java.moovy.APIEndPoints.MovieAPIEndPoint;
import coderschool.com.java.moovy.BaseActivity;
import coderschool.com.java.moovy.Models.Movie;
import coderschool.com.java.moovy.R;

/**
 * Created by BuuPV on 2/17/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private Movie dataSet;
    List<Movie> movies;
    private Context context;

    // Provide a suitable constructor (depends on the kind of dataset)
    public MoviesAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        context = parent.getContext();
        Configuration configuration = context.getResources()
                .getConfiguration();
        View view ;
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            view = inflater.inflate(R.layout.movie, parent, false);
        } else {
            view = inflater.inflate(R.layout.movie_landscape, parent, false);
        }

        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    // Get the data model based on position
        dataSet = movies.get(position);

        // Set item views based on your views and data model
        Configuration configuration = context.getResources()
                .getConfiguration();
        String imageUrl = MovieAPIEndPoint.BASE_URL_IMG + MovieAPIEndPoint.POSTER_SIZE_W154 ;
        boolean landscape = false;
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            imageUrl += dataSet.getPosterPath();
        } else {
            imageUrl += dataSet.getBackdropPath();
            landscape = true;
        }

        loadImage(holder.ivPoster, imageUrl,landscape);
        holder.tvTitle.setText(dataSet.getTitle());
        holder.tvOverview.setText(dataSet.getOverview());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    //
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPoster;
        TextView tvTitle;
        TextView tvOverview;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPoster = (ImageView) itemView.findViewById(R.id.ivPoster);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvOverview = (TextView) itemView.findViewById(R.id.tvOverview);
        }
    }

    private void loadImage(ImageView imageView, String path,boolean landscape) {
        // TODO: Insert your code
        int placeholder;
        if (landscape){
            placeholder = R.drawable.movie_placeholder_backdrop;
        } else {
            placeholder = R.drawable.movie_placeholder;
        }
        Picasso.with(context)
                .load(path)
                .placeholder(placeholder)
                .into(imageView);
    }
}
