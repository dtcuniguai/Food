package com.example.niguai.sql_project;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by m1586 on 2018/1/8.
 */

public class randomSearh extends Fragment {
    View myView;
    private boolean showtoast=false;
     Spinner city;
     Spinner district;
    Spinner type;
    private String itemname[] = null;
    private String res_city = "";
    private String res_district = "";
    private String res_type = "";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.randomsearch,container, false);


        type = (Spinner) getActivity().findViewById(R.id.res_type);
        city = (Spinner) getActivity().findViewById(R.id.city);
        district = (Spinner)getActivity().findViewById((R.id.district));

        ArrayAdapter<String> adap_type = new ArrayAdapter<String>(
                getActivity().getBaseContext(), android.R.layout.simple_spinner_item,
                new String[]{"","其他美食","日式料理","亞洲料理","素食","烘焙、甜點、零食","小吃","速食料理","冰品、飲料、甜湯","咖啡、簡餐、茶","燒烤類",
                        "鍋類", "主題特色餐廳","異國料理","中式料理","早餐","buffet自助餐"});
        adap_type.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        type.setAdapter(adap_type);
        type.setOnItemSelectedListener( spinnerlistener);
        res_type = type.getSelectedItem().toString();

        final ArrayAdapter<String> adap_city = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item,
                new String[]{"","台北市","新北市","桃園市","台中市","台南市","高雄市","基隆市","新竹縣","新竹市","嘉義市","苗栗縣","彰化縣",
                        "南投縣","雲林縣", "嘉義縣","屏東縣","宜蘭縣","花蓮縣","台東縣","澎湖縣"});

        adap_city.setDropDownViewResource(R.layout.spinner_dropdown);
        city.setAdapter(adap_city);
        city.setOnItemSelectedListener( spinnerlistener);
        res_city = city.getSelectedItem().toString();

        Button nextPageBtn2 = (Button)myView.findViewById(R.id.advance_search);
        nextPageBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Detaill", "結果"+city.getSelectedItem().toString()+" / "+res_district+" / "+type.getSelectedItem().toString());
                String uri = "http://140.136.150.95:3000/search/district?city="+city.getSelectedItem().toString()
                        +"&zone="+res_district+"&type="+type.getSelectedItem().toString()+"&sort=0";

                Context context;
                context = getActivity().getApplicationContext();
                Intent intent = new Intent(context, search_list.class);
                Bundle bundle = new Bundle();
                bundle.putString("firstListId", uri);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        ////
        return myView;
    }


    AdapterView.OnItemSelectedListener spinnerlistener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView adapterView, View view,int position, long id) {  //當選取的spinner是kind時
            if (adapterView.getId() == R.id.city) {//判斷選取的種類是何種，設定相對應的字串陣列內容
                switch (adapterView.getSelectedItemPosition()) {
                    case 1:
                        itemname = new String[] {"","中正區","大同區","中山區","松山區","大安區","萬華區","信義區","士林區","北投區","內湖區","南港區","文山區" };
                        break;
                    case 2:
                        itemname = new String[] {"","萬里區","金山區","板橋區","汐止區","深坑區","石碇區","瑞芳區","平溪區","雙溪區","貢寮區","新店區","坪林區","烏來區","永和區","中和區","土城區","三峽區","樹林區","鶯歌區","三重區","新莊區","泰山區","林口區","蘆洲區","五股區","八里區","淡水區","三芝區","石門區"};
                        break;
                    case 3:
                        itemname = new String[] {"","中壢區","平鎮區","龍潭區","楊梅區","新屋區","觀音區","桃園區","龜山區","八德區","大溪區","復興區","大園區","蘆竹區"};
                        break;
                    case 4:
                        itemname = new String[] {"","中區","東區","南區","西區","北區","北屯區","西屯區","南屯區","太平區","大里區","霧峰區","烏日區","豐原區","后里區","石岡區","東勢區","和平區","新社區","潭子區","大雅區","神岡區","大肚區","沙鹿區","龍井區","梧棲區","清水區","大甲區","外埔區","大安區"};
                        break;
                    case 5:
                        itemname = new String[] {"","永康區","歸仁區","新化區","左鎮區","玉井區","楠西區","南化區","仁德區","關廟區","龍崎區","官田區","麻豆區","佳里區","西港區","七股區","將軍區","學甲區","北門區","新營區","後壁區","白河區","東山區","六甲區","下營區","柳營區","鹽水區","善化區","大內區","山上區","新市區","安定區"};
                        break;
                    case 6:
                        itemname = new String[] {"","新興區","前金區","苓雅區","鹽埕區","鼓山區","旗津區","前鎮區","三民區","楠梓區","小港區","左營區","仁武區","大社區","岡山區","路竹區","阿蓮區","田寮區","燕巢區","橋頭區","梓官區","彌陀區","永安區","湖內區","鳳山區","大寮區","林園區","鳥松區","大樹區","旗山區","美濃區","六龜區","內門區","杉林區","甲仙區","桃源區","那瑪夏","茂林區","茄萣區"};
                        break;
                    case 7:
                        itemname = new String[] {"","仁愛區","信義區","中正區","中山區","安樂區","暖暖區","七堵區"};
                        break;
                    case 8:
                        itemname = new String[] {"","湖口鄉","新豐鄉","新埔鎮","關西鎮","芎林鄉","寶山鄉","竹東鎮","五峰鄉","橫山鄉","尖石鄉","北埔鄉","峨眉鄉"};
                        break;
                    case 9:
                        itemname = new String[] {"","東區", "北區", "香山區"};
                        break;
                    case 10:
                        itemname = new String[] {"","東區", "西區"};
                        break;
                    case 11:
                        itemname = new String[] {"","竹南鎮","頭份鎮","三灣鄉","南庄鄉","獅潭鄉","後龍鎮","通霄鎮","苑裡鎮","苗栗市","造橋鄉","頭屋鄉","公館鄉","大湖鄉","泰安鄉","銅鑼鄉","三義鄉","西湖鄉","卓蘭鎮"};
                        break;
                    case 12:
                        itemname = new String[] {"","彰化市","芬園鄉","花壇鄉","秀水鄉","鹿港鎮","福興鄉","線西鄉","和美鎮","伸港鄉","員林鎮","社頭鄉","永靖鄉","埔心鄉","溪湖鎮","大村鄉","埔鹽鄉","田中鎮","北斗鎮","田尾鄉","埤頭鄉","溪州鄉","竹塘鄉","二林鎮","大城鄉","芳苑鄉","二水鄉"};
                        break;
                    case 13:
                        itemname = new String[] {"","南投市","中寮鄉","草屯鎮","國姓鄉","埔里鎮","仁愛鄉","名間鄉","集集鎮","水里鄉","魚池鄉","信義鄉","竹山鎮","鹿谷鄉"};
                        break;
                    case 14:
                        itemname = new String[] {"","斗南鎮","大埤鄉","虎尾鎮","土庫鎮","褒忠鄉","東勢鄉","台西鄉","崙背鄉","麥寮鄉","斗六市","林內鄉","古坑鄉","莿桐鄉","西螺鎮","二崙鄉","北港鎮","水林鄉","口湖鄉","四湖鄉","元長鄉"};
                        break;
                    case 15:
                        itemname = new String[] {"","番路鄉","梅山鄉","竹崎鄉","阿里山","中埔鄉","大埔鄉","水上鄉","鹿草鄉","太保市","朴子市","東石鄉","六腳鄉","新港鄉","民雄鄉","大林鎮","溪口鄉","義竹鄉","布袋鎮"};
                        break;
                    case 16:
                        itemname = new String[] {"","屏東市","三地門","霧台鄉","瑪家鄉","九如鄉","里港鄉","高樹鄉","鹽埔鄉","長治鄉","麟洛鄉","竹田鄉","內埔鄉","萬丹鄉","潮州鎮","泰武鄉","來義鄉","萬巒鄉","崁頂鄉","新埤鄉","南州鄉","林邊鄉","東港鎮","琉球鄉","佳冬鄉","新園鄉","枋寮鄉","枋山鄉","春日鄉","獅子鄉","車城鄉","牡丹鄉","恆春鎮","滿州鄉"};
                        break;
                    case 17:
                        itemname = new String[] {"","宜蘭市","頭城鎮","礁溪鄉","壯圍鄉","員山鄉","羅東鎮","三星鄉","大同鄉","五結鄉","冬山鄉","蘇澳鎮","南澳鄉"};
                        break;
                    case 18:
                        itemname = new String[] {"","花蓮市","新城鄉","秀林鄉","吉安鄉","壽豐鄉","鳳林鎮","光復鄉","豐濱鄉","瑞穗鄉","萬榮鄉","玉里鎮","卓溪鄉","富里鄉"};
                        break;
                    case 19:
                        itemname = new String[] {"","台東市","綠島鄉","蘭嶼鄉","延平鄉","卑南鄉","鹿野鄉","關山鎮","海端鄉","池上鄉","東河鄉","成功鎮","長濱鄉","太麻里","金峰鄉","大武鄉","達仁鄉"};
                        break;
                    case 20:
                        itemname = new String[] {"","馬公市","西嶼鄉","望安鄉","七美鄉","白沙鄉","湖西鄉"};
                        break;
                    default:
                        itemname = new String[]{""};
                        break;
                }
                //當itemanme不為空時，對spinner name進行初始化
                if (itemname != null) {
                    setnameitem();
                }
            } else if (adapterView.getId() == R.id.district) {

                showtoast=true;
            }
        }
        @Override
        public void onNothingSelected(AdapterView arg0) {
        }
    };

    private void setnameitem() {
        showtoast=false;
        ArrayAdapter<String> adap_district = new ArrayAdapter<String>(getActivity() ,R.layout.spinner_dropdown,
                itemname);
        district.setAdapter(adap_district);
        district.setOnItemSelectedListener(spinnerlistener);
    }
}
