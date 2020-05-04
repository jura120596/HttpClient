package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.services.UserService;


import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {
    EditText surname, name;
    String surnameString, nameString;
    Button send;
    TextView responseView;
    Retrofit retrofit;
    UserService userService;
    User userFromServer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surname = findViewById(R.id.surname);
        name = findViewById(R.id.name);
        send = findViewById(R.id.send);
        responseView = findViewById(R.id.response);
        send.setOnClickListener(this);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.137.1:8081")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userService = retrofit.create(UserService.class);
    }

    @Override
    public void onClick(View v) {
        HttpTask task = new HttpTask();
        surnameString = surname.getText().toString();
        nameString = name.getText().toString();
        task.execute();
    }

    private class HttpTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            Call<User> response = userService.greetingUser(nameString, surnameString);
            try {
                Response<User> body = response.execute();
                userFromServer = body.body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            responseView.setText(userFromServer.school + " - " + userFromServer.firstname);
        }
    }
}
