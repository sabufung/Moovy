package coderschool.com.java.moovy.Adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import coderschool.com.java.moovy.R;

/**
 * Created by BuuPV on 2/18/2017.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder {
    ImageView ivPoster;
    TextView tvTitle;
    RatingBar rbAverageVote;
    TextView tvOverview;

    public MovieViewHolder(View itemView) {
        super(itemView);
        ivPoster = (ImageView) itemView.findViewById(R.id.ivPoster);
        tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        rbAverageVote = (RatingBar) itemView.findViewById(R.id.rbAverageVote);
        tvOverview = (TextView) itemView.findViewById(R.id.tvOverview);
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(TextView title) {
        this.tvTitle = title;
    }

    public ImageView getIvPoster() {
        return ivPoster;
    }

    public void setIvPoster(ImageView poster) {
        this.ivPoster = poster;
    }

    public RatingBar getRbAverageVote() {
        return rbAverageVote;
    }

    public void setRbAverageVote(RatingBar rbAverageVote) {
        this.rbAverageVote = rbAverageVote;
    }

    public TextView getTvOverview() {
        return tvOverview;
    }

    public void setTvOverview(TextView tvOverview) {
        this.tvOverview = tvOverview;
    }
}
