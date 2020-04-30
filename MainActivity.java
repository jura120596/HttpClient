package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {
    EditText surname, name;
    String surnameString, nameString;
    Button send;
    TextView responseView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surname = findViewById(R.id.surname);
        name = findViewById(R.id.name);
        send = findViewById(R.id.send);
        responseView = findViewById(R.id.response);
        send.setOnClickListener(this);
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
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://192.168.137.1:8081/hello");
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("surname", surnameString));
            nameValuePairs.add(new BasicNameValuePair("name", nameString));
            try {
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                String body = EntityUtils.toString(entity);
                return body;
            } catch (IOException e) {
                e.printStackTrace();

            }
            return "Произошла ошибка, проверь соединение с интернетом";
        }

        @Override
        protected void onPostExecute(String s) {
            responseView.setText(s);
        }
    }
}
