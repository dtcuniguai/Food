package com.example.niguai.sql_project;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class hotMainList extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    ArrayList<restaurant> res_list = new ArrayList<>();
    private ExecutorService service;
    private OkHttpClient client;
    ListView hotMainList;
    costListView adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_main_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ///
        hotMainList = (ListView)findViewById(R.id.hotList);

        adapter = new costListView();
        hotMainList.setAdapter(adapter);

        client = new OkHttpClient();
        service = Executors.newSingleThreadExecutor();
        handleRequestInBackground();

        hotMainList.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.setClass(hotMainList.this, resDetail.class);
                intent.putExtra("id",res_list.get(i).getID());
                intent.putExtra("name",res_list.get(i).getName());
                intent.putExtra("address",res_list.get(i).getRes_Address());
                intent.putExtra("cost",res_list.get(i).getRes_Cost());
                intent.putExtra("eScore",res_list.get(i).getRes_Envir_Score());
                intent.putExtra("hour",res_list.get(i).getRes_Hour());
                intent.putExtra("LAT",res_list.get(i).getRes_LAT());
                intent.putExtra("LNG",res_list.get(i).getRes_LNG());
                intent.putExtra("score",res_list.get(i).getRes_Score());
                intent.putExtra("sScore",res_list.get(i).getRes_service_Score());
                intent.putExtra("summary",res_list.get(i).getRes_Summary());
                intent.putExtra("tScore",res_list.get(i).getRes_Taste_Score());
                intent.putExtra("pic",res_list.get(i).getRes_Pic());
                intent.putExtra("tele",res_list.get(i).getRes_Tel());
                intent.putExtra("type",res_list.get(i).getTypeName());

                startActivity(intent);
                hotMainList.this.finish();
            }
        });
        ///
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hot_main_list, menu);
        return true;
    }

    //@Override
   /* public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();
        if (id == R.id.nav_pickBlock) {

        } else if (id == R.id.nav_random) {
            fragmentManager.beginTransaction().replace(R.id.content_Fragment, new randomSearh()).commit();
            hotMainList.setVisibility(View.INVISIBLE);
        } else if (id == R.id.nav_text) {
            fragmentManager.beginTransaction().replace(R.id.content_Fragment, new textSearh()).commit();
            hotMainList.setVisibility(View.INVISIBLE);
        }else if (id == R.id.nav_hot){
            fragmentManager.beginTransaction().replace(R.id.content_Fragment, new spacePage()).commit();
            hotMainList.setVisibility(View.VISIBLE);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void handleRequestInBackground(){
        service.submit(new Runnable() {
            @Override
            public void run() {
                HttpUrl.Builder builder = HttpUrl.parse("http://140.136.150.95:3000/search/hot").newBuilder();

                Request request = new Request.Builder()
                        .url(builder.toString())
                        .build();
                try {
                    final Response response = client.newCall(request).execute();
                    final String resStr = response.body().string();
                    Log.e("Object",resStr);
                    final List<hotMainList.restaurant> jsonData = new Gson().fromJson(resStr, new TypeToken<List<hotMainList.restaurant>>(){}.getType());
                    Log.e("JSON DATA IS :",jsonData.toString());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int i=0;
                            for(hotMainList.restaurant json : jsonData) {
                                res_list.add(json);
                            }
                            adapter.notifyDataSetChanged();

                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
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

    class costListView extends BaseAdapter {

        @Override
        public int getCount() {
            return res_list.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            final hotMainList.listItem item ;
            if(view == null){
                view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.list_item,null);
                item = new hotMainList.listItem();
                item.Name = (TextView) view.findViewById(R.id.Name);
                item.res_Pic = (TextView) view.findViewById(R.id.res_Pic);
                item.res_Address = (TextView) view.findViewById(R.id.res_Address);
                item.res_Tel = (TextView) view.findViewById(R.id.res_Tel);
                item.res_Img = (ImageView) view.findViewById(R.id.resImage);
                view.setTag(item);
            }else{
                item = (hotMainList.listItem) view.getTag();
            }
            item.Name.setText(res_list.get(i).getName());
            item.res_Pic.setText(res_list.get(i).getRes_Pic());
            item.res_Address.setText(res_list.get(i).getRes_Address());
            item.res_Tel.setText(res_list.get(i).getRes_Tel());
            item.ID = res_list.get(i).getID();
            item.res_LAT = res_list.get(i).res_LAT;
            item.res_LNG = res_list.get(i).res_LNG;
            item.res_Cost = res_list.get(i).getRes_Cost();
            item.typeName = res_list.get(i).getTypeName();
            item.res_Summary = res_list.get(i).getRes_Summary();
            item.res_Score = res_list.get(i).getRes_Score();
            item.res_service_Score = res_list.get(i).getRes_service_Score();
            item.res_Taste_Score = res_list.get(i).getRes_Taste_Score();
            item.res_service_Score = res_list.get(i).getRes_service_Score();
            item.res_Envir_Score = res_list.get(i).getRes_Envir_Score();
            item.res_Hour = res_list.get(i).getRes_Hour();
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
                    item.res_Img.setImageBitmap (result);
                    super.onPostExecute(result);
                }
            }.execute(res_list.get(i).getRes_Pic());
            return view;
        }
    }

    class listItem{
        ImageView res_Img;
        TextView Name;
        TextView res_Pic;
        TextView res_Address;
        TextView res_Tel;
        String ID;
        String res_LAT;
        String res_LNG;
        String res_Cost;
        String typeName;
        String res_Summary;
        String res_Score;
        String res_Taste_Score;
        String res_service_Score;
        String res_Envir_Score;
        String res_Hour;
    }

    class restaurant{
        @SerializedName("ID")
        private String ID;
        @SerializedName("Name")
        private String Name;
        @SerializedName("res_LAT")
        private String res_LAT;
        @SerializedName("res_LNG")
        private String res_LNG;
        @SerializedName("res_Pic")
        private String res_Pic;
        @SerializedName("res_Cost")
        private String res_Cost;
        @SerializedName("res_Address")
        private String res_Address;
        @SerializedName("res_Tel")
        private String res_Tel;
        @SerializedName("typeName")
        private String typeName;
        @SerializedName("res_Summary")
        private String res_Summary;
        @SerializedName("res_Score")
        private String res_Score;
        @SerializedName("res_Taste_Score")
        private String res_Taste_Score;
        @SerializedName("res_service_Score")
        private String res_service_Score;
        @SerializedName("res_Envir_Score")
        private String res_Envir_Score;
        @SerializedName("res_Hour")
        private String res_Hour;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getRes_LAT() {
            return res_LAT;
        }

        public void setRes_LAT(String res_LAT) {
            this.res_LAT = res_LAT;
        }

        public String getRes_LNG() { return res_LNG; }

        public void setRes_LNG(String res_LNG) { this.res_LNG = res_LNG; }

        public String getRes_Pic() { return res_Pic; }

        public void setRes_Pic(String res_Pic) { this.res_Pic = res_Pic; }

        public String getRes_Cost() { return res_Cost; }

        public void setRes_Cost(String res_Cost) { this.res_Cost = res_Cost; }

        public String getRes_Address() { return res_Address; }

        public void setRes_Address(String res_Address) { this.res_Address = res_Address; }

        public String getRes_Tel() { return res_Tel; }

        public void setRes_Tel(String res_Tel) { this.res_Tel = res_Tel; }

        public String getTypeName() { return typeName; }

        public void setTypeName(String typeName) { this.typeName = typeName; }

        public String getRes_Summary() { return res_Summary; }

        public void setRes_Summary(String res_Summary) { this.res_Summary = res_Summary; }

        public String getRes_Score() { return res_Score; }

        public void setRes_Score(String res_Score) { this.res_Score = res_Score; }

        public String getRes_Taste_Score() { return res_Taste_Score; }

        public void setRes_Taste_Score(String res_Taste_Score) { this.res_Taste_Score = res_Taste_Score; }

        public String getRes_service_Score() { return res_service_Score; }

        public void setRes_service_Score(String res_service_Score) { this.res_service_Score = res_service_Score; }

        public String getRes_Envir_Score() { return res_Envir_Score; }

        public void setRes_Envir_Score(String res_Envir_Score) { this.res_Envir_Score = res_Envir_Score; }

        public String getRes_Hour() { return res_Hour; }

        public void setRes_Hour(String res_Hour) { this.res_Hour = res_Hour; }
    }
}
