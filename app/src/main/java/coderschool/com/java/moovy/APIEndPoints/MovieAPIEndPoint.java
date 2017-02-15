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

    @GET("movie/now_playing")
    Call<QueryResponse> getNowPlayingMovie();
//
//    @GET("group/{id}/users")
//    Call<List<User>> groupList(@Path("id") int groupId, @Query("sort") String sort);
//
//    @POST("users/new")
//    Call<User> createUser(@Body User user);
}
