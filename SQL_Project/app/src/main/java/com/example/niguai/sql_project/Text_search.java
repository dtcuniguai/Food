package com.example.niguai.sql_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Text_search extends AppCompatActivity {
    private SharedPreferences settings;
    private  String data = "DATA";
    private String json = "JSON";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_search);

        Button text_output  =(Button) findViewById(R.id.bt_text_search);
        text_output.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                settings = getSharedPreferences(data,0);
                EditText ed1=(EditText)findViewById(R.id.edt_text_search);
                settings.edit().putString(json, ed1.getText().toString()).commit();
                Log.d("TEXT",ed1.getText().toString());
                Intent intent = new  Intent();
                String uri = "http://140.136.150.95:3000/search/text?search="+ed1.getText().toString();
                Log.d("Detail",uri);
                intent.putExtra("value",uri);
                intent.setClass(Text_search.this, search_list.class);
                startActivity(intent);
            }
        });
    }
}