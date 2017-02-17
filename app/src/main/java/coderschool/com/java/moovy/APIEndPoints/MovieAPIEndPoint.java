package coderschool.com.java.moovy.APIEndPoints;

import coderschool.com.java.moovy.Models.QueryResponse;
import retrofit2.Call;
import retrofit2.http.GET;

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

    @GET("movie/now_playing")
    Call<QueryResponse> getNowPlayingMovie();
//
//    @GET("group/{id}/users")
//    Call<List<User>> groupList(@Path("id") int groupId, @Query("sort") String sort);
//
//    @POST("users/new")
//    Call<User> createUser(@Body User user);
}
