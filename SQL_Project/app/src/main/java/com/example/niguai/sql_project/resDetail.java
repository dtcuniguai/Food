package com.example.niguai.sql_project;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.*;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class resDetail extends AppCompatActivity {

    ////
    private String ID;
    TextView Name;
    private String res_LAT;
    private String res_LNG;
    ImageView res_Pic;
    TextView res_Cost;
    TextView res_Address;
    TextView res_Tel;
    String typeName;
    TextView res_Summary;
    String res_Score;
    String res_Taste_Score;
    String res_service_Score;
    String res_Envir_Score;
    String res_Hour;
    ////
    String storeName = "";
    String storePhone = "";
    String storeAddress = "";
    String storePicture = "";
    String storeCost = "";
    String storeInFo = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_detail);
        Intent intent = getIntent();
        storeName = intent.getStringExtra("storeName");
        storeAddress = intent.getStringExtra("stroeAddress");
        storePhone = intent.getStringExtra("storePhone");
        storePicture = intent.getStringExtra("storePicture");
        storeCost = intent.getStringExtra("storeCost");
        storeInFo = intent.getStringExtra("storeInFo");

        TextView nameLabel = (TextView)findViewById(R.id.storeName);
        nameLabel.setText(storeName);
        TextView addressLabel = (TextView)findViewById(R.id.storeAddress);
        addressLabel.setText(storeAddress);
        TextView phoneLabel = (TextView)findViewById(R.id.storePhone);
        phoneLabel.setText(storePhone);
        TextView inFoLabel = (TextView)findViewById(R.id.storeInFo);
        inFoLabel.setText(storeInFo);
        TextView costLabel = (TextView)findViewById(R.id.storeCost);
        costLabel.setText(storeCost);

        ///
        setID();
        setIntentValue();
        ///

    }

    private void setID(){
        Name = (TextView) findViewById(R.id.storeName);
        res_Cost = (TextView) findViewById(R.id.storeCost);
        res_Address = (TextView) findViewById(R.id.storeAddress);
        res_Tel = (TextView) findViewById(R.id.storePhone);
        res_Summary = (TextView) findViewById(R.id.storeInFo);
        res_Pic = (ImageView) findViewById(R.id.storeImage);
    }

    private void setIntentValue(){
        Intent intent = getIntent();
        ID = intent.getStringExtra("id");
        Name.setText(intent.getStringExtra("name"));
        res_Address.setText(intent.getStringExtra("address"));
        res_Cost.setText(intent.getStringExtra("cost"));
        res_Envir_Score=intent.getStringExtra("eScore");
        res_Hour=intent.getStringExtra("hour");
        res_LAT = intent.getStringExtra("LAT");
        res_LNG = intent.getStringExtra("LNG");
        res_Score=intent.getStringExtra("score");
        res_service_Score=intent.getStringExtra("sScore");
        res_Summary.setText(intent.getStringExtra("summary"));
        res_Taste_Score=intent.getStringExtra("tScore");
        res_Tel.setText(intent.getStringExtra("tele"));
        typeName=intent.getStringExtra("type");

        new AsyncTask<String, Void, Bitmap>()
        {
            @Override
            protected Bitmap doInBackground(String... params)
            {
                String url = params[0];
                return getBitmapFromURL(url);
            }

            @Override
            protected void onPostExecute(Bitmap result)
            {
                res_Pic.setImageBitmap (result);
                super.onPostExecute(result);
            }
        }.execute(intent.getStringExtra("pic"));
    }

    private static Bitmap getBitmapFromURL(String imageUrl)
    {
        try
        {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

}
