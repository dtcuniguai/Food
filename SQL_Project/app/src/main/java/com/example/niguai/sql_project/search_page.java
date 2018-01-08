package com.example.niguai.sql_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class search_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        Button next_text_search = (Button)findViewById(R.id.next_text_page);
        next_text_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(search_page.this , Text_search.class);
                startActivity(intent);
            }
        });

        Button next_advanced_search = (Button)findViewById(R.id.next_advanced_page);
        next_advanced_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(search_page.this , Advanced_search.class);
                startActivity(intent);
            }
        });
    }
}
