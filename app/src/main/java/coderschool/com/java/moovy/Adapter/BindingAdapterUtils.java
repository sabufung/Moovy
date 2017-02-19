package coderschool.com.java.moovy.Adapter;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import coderschool.com.java.moovy.APIEndPoints.MovieAPIEndPoint;

/**
 * Created by BuuPV on 2/19/2017.
 */

public class BindingAdapterUtils {
    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String url) {
        String imageUrl = MovieAPIEndPoint.BASE_URL_IMG + MovieAPIEndPoint.POSTER_SIZE_W154;
        Picasso.with(view.getContext()).load(imageUrl+ url).into(view);
    }
}
