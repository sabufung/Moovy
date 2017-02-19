package coderschool.com.java.moovy.Adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import coderschool.com.java.moovy.R;

/**
 * Created by BuuPV on 2/18/2017.
 */

public class PopularMovieViewHolder extends RecyclerView.ViewHolder {
    TextView tvTitle;
    ImageView ivPoster;

    public PopularMovieViewHolder(View itemView) {
        super(itemView);
        tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        ivPoster = (ImageView) itemView.findViewById(R.id.ivPoster);
    }

    public ImageView getIvPoster() {
        return ivPoster;
    }

    public void setIvPoster(ImageView ivPoster) {
        this.ivPoster = ivPoster;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(TextView title) {
        this.tvTitle = title;
    }


}
