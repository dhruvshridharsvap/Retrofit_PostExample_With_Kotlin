package `in`.blogspot.tecnopandit.retrofit_postexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity2_Kotlin : AppCompatActivity() {

    lateinit var placeHolderKotlin : PlaceHolderKotlin;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activity2__kotlin)
        var retrofit : Retrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create()).build()
        placeHolderKotlin=retrofit.create(PlaceHolderKotlin::class.java)
        

    }

    fun createUser(uid : Integer, title: String, body: String ) {
        var post: Post = Post(123,"Sample title","Sample body");
        var call : Call<Post> = placeHolderKotlin.createPostsKotlin(post)
        call.enqueue(object : Callback<Post>{
            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.i("Error: ",t.message)
            }

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful){
                    var temp: Post? = response.body()
                    Log.i("Data Recieved: ",temp?.body+" "+temp?.id+" "+temp?.id)
                }
            }

        })
    }
}