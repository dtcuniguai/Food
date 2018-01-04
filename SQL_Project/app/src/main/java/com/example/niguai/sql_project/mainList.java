package com.example.niguai.sql_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class mainList extends AppCompatActivity {

    ArrayList<restaurant> res_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
    }

    class costListView extends BaseAdapter{

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
            listItem item ;
            if(view == null){
                view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.list_item,null);
                item = new listItem();
                item.ID = (TextView) view.findViewById(R.id.image);
                item.Name = (TextView) view.findViewById(R.id.text);
                view.setTag(item);
            }else{
                item = (listItem) view.getTag();
            }
            //item.image.setText(arr1[i]);
            //item.text.setText(arr2[i]);

            return view;
        }
    }

    class listItem{
        TextView ID;
        TextView Name;
        String res_LAT;
        String res_LNG;
        TextView res_Pic;
        TextView res_Cost;
        TextView res_Address;
        TextView res_Tel;
        TextView typeName;
        String res_Summary;
        TextView res_Score;
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
