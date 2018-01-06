package com.example.niguai.sql_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.*;
import android.util.Log;

public class resDetail extends AppCompatActivity {

    String str = "123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_detail);
        Intent intent = getIntent();
        str = intent.getStringExtra("value");
        Log.e("PassValue is",str);
    }
}
