package in.blogspot.tecnopandit.retrofit_postexample;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PlaceHolder {
    @POST("posts")//posts is the end point or relative url
    Call<Post> createPost(@Body Post post);//this createpost method will take post object as body and will return Call<post> object
}
