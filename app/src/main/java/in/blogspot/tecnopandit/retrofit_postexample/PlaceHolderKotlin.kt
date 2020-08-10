package `in`.blogspot.tecnopandit.retrofit_postexample

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PlaceHolderKotlin {
    @POST("posts")
    fun createPostsKotlin(@Body post: Post) : Call<Post>
}