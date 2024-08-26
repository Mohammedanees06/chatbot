package com.example.chatsage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Request;
import okio.Timeout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    //adapter class will be called in the main activity
    private RecyclerView chatsrv;
    private EditText usermsgedt;
    private FloatingActionButton sendmsgfab;
    private final String BOT_KEY="bot"; //similar to adapter class key
    private final String USER_KEY="user";
    private ArrayList<ChatModal> chatModalArrayList;
    private ChatAdapter chatAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chatsrv=findViewById(R.id.chats);
        usermsgedt=findViewById(R.id.editmsg);
        sendmsgfab=findViewById(R.id.fabsend);
        chatModalArrayList=new ArrayList<>();
        chatAdapter=new ChatAdapter(chatModalArrayList,this);
        //set this adapter to recyclerview
        LinearLayoutManager manager= new LinearLayoutManager(this);
        chatsrv.setLayoutManager(manager);
        chatsrv.setAdapter(chatAdapter);
        sendmsgfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usermsgedt.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this,"enter ur msg" + "",Toast.LENGTH_SHORT).show();
                    return;
                }
                getResponse(usermsgedt.getText().toString());
                usermsgedt.setText("");
            }

        });
    }
    private void getResponse(String message) {
        chatModalArrayList.add(new ChatModal(message,USER_KEY));  //adding user msg with key
        chatAdapter.notifyDataSetChanged();                       //notify the adapter the data inside our arraylist has been changed
        String url = "http://api.brainshop.ai/get?bid=182459&key=iTOCPGlqlTZCl4cZ&uid=[uid]&msg="+message; //add our msg which we will be creating in user input
        String BASE_URL="http://api.brainshop.ai/";
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI=retrofit.create(RetrofitAPI.class);
        Call<MsgModal> call = retrofitAPI.getMessage(url);
        call.enqueue(new Callback<MsgModal>() {
            @Override
            public void onResponse(Call<MsgModal> call, Response<MsgModal> response) {
                if(response.isSuccessful())
                {
                    MsgModal modal= response.body();
                    chatModalArrayList.add(new ChatModal(modal.getCnt(),BOT_KEY));
                    chatAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<MsgModal> call, Throwable t) {
                chatModalArrayList.add(new ChatModal("Please enter your question",BOT_KEY));
                chatAdapter.notifyDataSetChanged();
            }
        });


    }
}

