package in.blogspot.tecnopandit.retrofit_postexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    PlaceHolder placeHolder;
    TextView textView;
    Button button;
    EditText uid,title,body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit= new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create()).build();
        placeHolder=retrofit.create(PlaceHolder.class);
        textView=findViewById(R.id.result);
        uid=findViewById(R.id.uid);
        title=findViewById(R.id.title);
        body=findViewById(R.id.body);
        button=findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(uid.getText().toString().isEmpty()&&body.getText().toString().isEmpty()&&title.getText().toString().isEmpty())) {
                    createPost(Integer.parseInt(uid.getText().toString()),body.getText().toString(),title.getText().toString());
                }
                else {
                    Toast.makeText(getApplicationContext(),"Fields cannot be empty!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public  void  createPost(int uid,String body,String title){
        Post post=new Post(uid,title,body);
        Call<Post> call=placeHolder.createPost(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Response code: "+response.code(),Toast.LENGTH_SHORT).show();
                    Post temp=response.body();
                    String s="";
                    s="User Id: "+temp.getUserId()+"\nID: "+temp.getId()+"\nTitle: "+temp.getTitle()+"\nBody: "+temp.getBody();
                    textView.setText(s);

                }

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"ERROR: "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}