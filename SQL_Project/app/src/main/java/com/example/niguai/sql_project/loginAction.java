package com.example.niguai.sql_project;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import android.view.*;
import android.content.*;
import java.io.IOException;
import java.util.List;

import okhttp3.*;
import java.util.concurrent.*;

import com.google.gson.Gson;
import com.google.gson.reflect.*;
import com.google.gson.annotations.*;


public class loginAction extends AppCompatActivity {

    //////////////temp Static value Zone////////////////
    private static String userName = "Guest";
    ////////////////////////////////////////////////////
    Button loginAction;
    Button createAccount;
    EditText account;
    EditText password;
    private ExecutorService service;
    private OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_action);

        //page's item init
        account = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.password);
        loginAction = (Button) findViewById(R.id.login);
        createAccount = (Button) findViewById(R.id.create);

        loginAction.setOnClickListener(actionLogin);
        createAccount.setOnClickListener(actionCreate);

        client = new OkHttpClient();
        service = Executors.newSingleThreadExecutor();
    }

    //action for login
    private View.OnClickListener actionLogin = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO
            handleRequestInBackground(account.getText().toString(),password.getText().toString());
        }
    };

    //create new account
    private View.OnClickListener actionCreate = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(loginAction.this, Createmember.class);
            startActivity(intent);
            loginAction.this.finish();
        }
    };

    private void handleRequestInBackground(final String account, final String pass){
        service.submit(new Runnable() {
            @Override
            public void run() {
                HttpUrl.Builder builder = HttpUrl.parse("http://140.136.150.95:3000/user/login?account="+account+"&password="+pass).newBuilder();

                Request request = new Request.Builder()
                        .url(builder.toString())
                        .build();
                try {
                    final Response response = client.newCall(request).execute();
                    String resStr = response.body().string();
                    final List<JsonData> jsonData = new Gson().fromJson(resStr, new TypeToken<List<JsonData>>(){}.getType());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(jsonData.size()>0){
                                Intent intent = new Intent();
                                intent.setClass(loginAction.this, mainList.class);
                                startActivity(intent);
                                loginAction.this.finish();
                            }else{
                                Log.e("Error Msg","~~~~Login Error with"+account+"  AND  "+pass);
                            }
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    private class JsonData{
        @SerializedName("user_ID")
        private String user_ID;
        @SerializedName("user_Account")
        private String user_Account;
        @SerializedName("user_Password")
        private String user_Password;

        public String getUser_ID() {
            return user_ID;
        }

        public void setUser_ID(String user_ID) {
            this.user_ID = user_ID;
        }

        public String getUser_Account() {
            return user_Account;
        }

        public void setUser_Account(String user_Account) {
            this.user_Account = user_Account;
        }

        public String getUser_Password() {
            return user_Password;
        }

        public void setUser_Password(String user_Password) {
            this.user_Password = user_Password;
        }

    }

}
