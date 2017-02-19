package coderschool.com.java.moovy.APIEndPoints;

import coderschool.com.java.moovy.Models.Movie;
import coderschool.com.java.moovy.Models.MovieDetail;
import coderschool.com.java.moovy.Models.QueryResponse;
import coderschool.com.java.moovy.Models.TrailerResponse;
import coderschool.com.java.moovy.Models.Videos;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by BuuPV on 2/15/2017.
 */

public interface MovieAPIEndPoint {
    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    String BASE_URL_IMG = "https://image.tmdb.org/t/p/";
    String POSTER_SIZE_W92 = "w92";    // 92x139 (WxH) 2:3
    String POSTER_SIZE_W154 = "w154";  // 154x231 (WxH) 2:3
    String POSTER_SIZE_W185 = "w185";  // 185x278 (WxH) 2:3
    String POSTER_SIZE_W342 = "w342";  // 342x513 (WxH) 2:3
    String POSTER_SIZE_W500 = "W500";  // 500X750 (WxH) 2:3
    String POSTER_SIZE_W780 = "w780";  // 780x1170 (WxH) 2:3

    String BACKDROP_SIZE_H342 = "h342";  // 780x1170 (WxH) 2:3

    @GET("movie/now_playing")
    Call<QueryResponse> getNowPlayingMovie(@Query("page") int page);

    @GET("movie/{movie_id}/videos")
    Call<TrailerResponse> getMovies(@Path("movie_id") int id);

    @GET("movie/{movie_id}")
    Call<MovieDetail> getDetail(@Path("movie_id") int id);

}
