package com.example.niguai.sql_project;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by m1586 on 2018/1/8.
 */

public class textSearh extends Fragment {
    View myView;
    //private SharedPreferences settings;
    private  String data = "DATA";
    private String json = "JSON";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.textsearch,container, false);
        Button text_output  =(Button) myView.findViewById(R.id.bt_text_search);
        text_output.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               //settings = getSharedPreferences(data, 0);
                EditText ed1=(EditText) myView.findViewById(R.id.edt_text_search);
                //settings.edit().putString(json, ed1.getText().toString()).commit();
                Log.d("TEXT",ed1.getText().toString());
               // Intent intent = new  Intent();
                String uri = "http://140.136.150.95:3000/search/text?search="+ed1.getText().toString();
                Log.d("Detail",uri);
                //intent.putExtra("value",uri);
                //intent.setClass(textSearh.this, search_list.class);
                //startActivity(intent);*/
                Context context;
                context = getActivity().getApplicationContext();
                Intent intent = new Intent(context, search_list.class);
                Bundle bundle = new Bundle();
                bundle.putString("firstListId", uri);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        return myView;
    }




}
