package com.example.niguai.sql_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Createmember extends AppCompatActivity {

    TextView account;
    TextView password;
    TextView name;
    Button create;

    private ExecutorService service;
    private OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createmember);
        account = (TextView) findViewById(R.id.account);
        password = (TextView) findViewById(R.id.account);
        name = (TextView) findViewById(R.id.name);
        create = (Button) findViewById(R.id.createAction);

        client = new OkHttpClient();
        service = Executors.newSingleThreadExecutor();

        create.setOnClickListener(createAction);


    }

    private View.OnClickListener createAction = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(account.getText().toString().equals("")){
                //TODO  通知帳號不得為空
            }else if(password.getText().toString().equals("")){
                //TODO  通知密碼不得為空
            }else if(name.getText().toString().equals("")){
                //TODO  通知姓名不得為空
            }else {
                handleRequestInBackground(account.getText().toString(), password.getText().toString(), name.getText().toString());
            }
        }
    };

    private void handleRequestInBackground(final String account, final String pass,final String name){
        service.submit(new Runnable() {
            @Override
            public void run() {
                HttpUrl.Builder builder = HttpUrl.parse("http://140.136.150.95:3000/user/user/register?account="+account+"&password="+pass+"&userType=U&name="+name+"&gender=B&career=老師&month=12&day=1&year=1995&phone=0928008062&userPic=").newBuilder();

                Request request = new Request.Builder()
                        .url(builder.toString())
                        .build();
                try {
                    final Response response = client.newCall(request).execute();
                    final String str = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("Login Msg",str);
                            if(str.equals("success")){
                                //TODO 跳出登入成功通知
                                Intent intent = new Intent();
                                intent.setClass(Createmember.this, mainList.class);
                                startActivity(intent);
                                Createmember.this.finish();
                            }else{
                                //TODO 通知創建失敗
                            }
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }

}
