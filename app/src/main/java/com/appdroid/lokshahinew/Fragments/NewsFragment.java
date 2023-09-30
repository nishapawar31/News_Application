package com.appdroid.lokshahinew.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.appdroid.lokshahinew.Activitys.ShowingVillagesData;
import com.appdroid.lokshahinew.Adepter.NewsAdapter;
import com.appdroid.lokshahinew.DetailedNews;
import com.appdroid.lokshahinew.Holder.NewsHolder;
import com.appdroid.lokshahinew.R;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class NewsFragment extends Fragment {
    RecyclerView recyclerView, tajyaRecyclerView, jalgaonRecyclerView, jalgaonCityRecyclerView,nokriRecyclerView,havamanRecyclerView ;
    CardView card1, card2, card3, card4, card5, card6;
    List<NewsHolder> newsHolderList, tajyaList, jalgaonList, cityList, nokriList, havamanList;
    public static Boolean isScrolling = true;
    public static  int previoustotal;
    public static NewsAdapter adepter,tajyaAdapter, jalgaonAdapter, cityAdapter, nokriAdapter, havamanAdapter;
    LinearLayoutManager manager,tajyaManager, jalgaonManager, cityManager, nokriManager, havamanManager;
    RelativeLayout titleTXT, tajyaText, jalgaonText, cityText, nokriText, havamanText;
    private String resultOfIP ="";
    String link, flag;

    public NewsFragment() {

    }
    public NewsFragment(String action, String flag) {
        this.link = action;
        this.flag = flag;
    }

    View view;
    Intent intent;
    NewsHolder breaking, tajya, jalgaon, city, nokri, havaman;
    RelativeLayout firstBreaking, firstTajya, firstJalgaon, firstCity, firstNokri, firstHavaman;
    TextView   breakingHeadline, tajyaHeadline, jalgaonHeadline, cityHeadline, nokriHeadline, havamanHeadline;
    TextView breakingViewMore, tajyaViewMore, visheshViewMore, cityViewMore, nokriViewMore, havamanViewMore;
    ImageView postImg, postImg1, postImg2, postImg3, postImg4, postImg5, postImg6;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_news, container, false);

        breakingViewMore = view.findViewById(R.id.breakingViewMore);
        tajyaViewMore = view.findViewById(R.id.tajyaViewMore);
        visheshViewMore = view.findViewById(R.id.visheshViewMore);
        cityViewMore = view.findViewById(R.id.cityViewMore);
        nokriViewMore = view.findViewById(R.id.nokriViewMore);
        havamanViewMore = view.findViewById(R.id.havamanViewMore);

        card1 = view.findViewById(R.id.card1);
        card2 = view.findViewById(R.id.card2);
        card3 = view.findViewById(R.id.card3);
        card4 = view.findViewById(R.id.card4);
        card5 = view.findViewById(R.id.card5);
        card6 = view.findViewById(R.id.card6);


        breaking = new NewsHolder();
        tajya = new NewsHolder();
        jalgaon = new NewsHolder();
        city = new NewsHolder();
        nokri = new NewsHolder();
        havaman = new NewsHolder();

        firstBreaking = view.findViewById(R.id.firstBreaking);
        firstTajya = view.findViewById(R.id.firstTajya);
        firstJalgaon = view.findViewById(R.id.firstJalgaon);
        firstCity = view.findViewById(R.id.firstJalgaonCity);
        firstNokri = view.findViewById(R.id.firstNokri);
        firstHavaman = view.findViewById(R.id.firstHavaman);

        breakingHeadline = view.findViewById(R.id.breakingHeadLine);
        tajyaHeadline = view.findViewById(R.id.tajyaHeadLine);
        jalgaonHeadline = view.findViewById(R.id.jalgaonHeadLine);
        cityHeadline = view.findViewById(R.id.jalgaonCityHeadLine);
        nokriHeadline = view.findViewById(R.id.nokriHeadLine);
        havamanHeadline = view.findViewById(R.id.havamanHeadLine);

        postImg = view.findViewById(R.id.postImg);
        postImg1 = view.findViewById(R.id.postImg1);
        postImg2 = view.findViewById(R.id.postImg2);
        postImg3 = view.findViewById(R.id.postImg3);
        postImg4 = view.findViewById(R.id.postImg4);
        postImg5 = view.findViewById(R.id.postImg5);
        postImg6 = view.findViewById(R.id.postImg6);

        isScrolling = true;
        previoustotal = 0;
        titleTXT = view.findViewById(R.id.title);
        tajyaText = view.findViewById(R.id.tajyaBatmya);
        jalgaonText = view.findViewById(R.id.jalgaonLiveVishesh);
        cityText = view.findViewById(R.id.jalgaonCityLiveVishesh);
        nokriText = view.findViewById(R.id.nokri);
        havamanText = view.findViewById(R.id.havaman);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        tajyaRecyclerView = view.findViewById(R.id.tajyaRecyclerView);
        tajyaRecyclerView.hasFixedSize();
        tajyaManager = new LinearLayoutManager(getContext());
        tajyaRecyclerView.setLayoutManager(tajyaManager);

        jalgaonRecyclerView = view.findViewById(R.id.jalgaonRecyclerView);
        jalgaonRecyclerView.hasFixedSize();
        jalgaonManager = new LinearLayoutManager(getContext());
        jalgaonRecyclerView.setLayoutManager(jalgaonManager);

        jalgaonCityRecyclerView = view.findViewById(R.id.jalgaonCityRecyclerView);
        jalgaonCityRecyclerView.hasFixedSize();
        cityManager = new LinearLayoutManager(getContext());
        jalgaonCityRecyclerView.setLayoutManager(cityManager);

        nokriRecyclerView = view.findViewById(R.id.nokriRecyclerView);
        nokriRecyclerView.hasFixedSize();
        nokriManager = new LinearLayoutManager(getContext());
        nokriRecyclerView.setLayoutManager(nokriManager);

        havamanRecyclerView = view.findViewById(R.id.havamanRecyclerView);
        havamanRecyclerView.hasFixedSize();
        havamanManager = new LinearLayoutManager(getContext());
        havamanRecyclerView.setLayoutManager(havamanManager);

        ViewCompat.setNestedScrollingEnabled(tajyaRecyclerView, false);
        ViewCompat.setNestedScrollingEnabled(jalgaonRecyclerView, false);
        ViewCompat.setNestedScrollingEnabled(jalgaonCityRecyclerView, false);
        ViewCompat.setNestedScrollingEnabled(nokriRecyclerView, false);
        ViewCompat.setNestedScrollingEnabled(havamanRecyclerView, false);


        newsHolderList = new ArrayList<>();
        tajyaList = new ArrayList<>();
        jalgaonList = new ArrayList<>();
        cityList = new ArrayList<>();
        nokriList = new ArrayList<>();
        havamanList = new ArrayList<>();

        adepter = new NewsAdapter(newsHolderList,getContext());
        recyclerView.setAdapter(adepter);

        tajyaAdapter = new NewsAdapter(tajyaList, getContext());
        tajyaRecyclerView.setAdapter(tajyaAdapter);

        jalgaonAdapter = new NewsAdapter(jalgaonList,getContext());
        jalgaonRecyclerView.setAdapter(jalgaonAdapter);

        cityAdapter = new NewsAdapter(cityList,getContext());
        jalgaonCityRecyclerView.setAdapter(cityAdapter);

        nokriAdapter = new NewsAdapter(nokriList,getContext());
        nokriRecyclerView.setAdapter(nokriAdapter);

        havamanAdapter = new NewsAdapter(havamanList,getContext());
        havamanRecyclerView.setAdapter(havamanAdapter);

        firstBreaking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailedNews.class);
                intent.setAction("insideApp");
                intent.putExtra("all",breaking);
                startActivity(intent);
            }
        });


        firstTajya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailedNews.class);
                intent.setAction("insideApp");
                intent.putExtra("all", tajya);
                startActivity(intent);
            }
        });

        firstJalgaon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailedNews.class);
                intent.setAction("insideApp");
                intent.putExtra("all",jalgaon);
                startActivity(intent);
            }
        });

        firstCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailedNews.class);
                intent.setAction("insideApp");
                intent.putExtra("all",city);
                startActivity(intent);
            }
        });

        firstNokri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailedNews.class);
                intent.setAction("insideApp");
                intent.putExtra("all",nokri);
                startActivity(intent);
            }
        });

        firstHavaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailedNews.class);
                intent.setAction("insideApp");
                intent.putExtra("all",havaman);
                startActivity(intent);
            }
        });

        titleTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent =new Intent(getContext(), ShowingVillagesData.class);
                intent.putExtra("link",link);
                startActivity(intent);
            }
        });

        tajyaText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent =new Intent(getContext(), ShowingVillagesData.class);
                intent.putExtra("link",getString(R.string.brecking_cat));
                startActivity(intent);
            }
        });

        jalgaonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent =new Intent(getContext(), ShowingVillagesData.class);
                intent.putExtra("link","https://lokshahilive.com/wp-json/wp/v2/posts?categories=442");
                startActivity(intent);
            }
        });

        cityText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent =new Intent(getContext(), ShowingVillagesData.class);
                intent.putExtra("link",getString(R.string.jalgoanShahar));
                startActivity(intent);
            }
        });

        nokriText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent =new Intent(getContext(), ShowingVillagesData.class);
                intent.putExtra("link",getString(R.string.nokri));
                startActivity(intent);
            }
        });

        havamanText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent =new Intent(getContext(), ShowingVillagesData.class);
                intent.putExtra("link",getString(R.string.havaman));
                startActivity(intent);
            }
        });


        breakingViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), ShowingVillagesData.class);
                intent.putExtra("link", link);
                startActivity(intent);
            }
        });

        tajyaViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent =new Intent(getContext(), ShowingVillagesData.class);
                intent.putExtra("link",getString(R.string.brecking_cat));
                startActivity(intent);
            }
        });

        visheshViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent =new Intent(getContext(), ShowingVillagesData.class);
                intent.putExtra("link",getString(R.string.vishesh));
                startActivity(intent);
            }
        });

        cityViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent =new Intent(getContext(), ShowingVillagesData.class);
                intent.putExtra("link",getString(R.string.jalgoanShahar));
                startActivity(intent);
            }
        });

        nokriViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent =new Intent(getContext(), ShowingVillagesData.class);
                intent.putExtra("link",getString(R.string.nokri));
                startActivity(intent);
            }
        });

        havamanViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent =new Intent(getContext(), ShowingVillagesData.class);
                intent.putExtra("link",getString(R.string.havaman));
                startActivity(intent);
            }
        });

        extractTajyaPost(getString(R.string.fistTenPostForAllCategories));
        extractTajyaPost(getString(R.string.fistTenPostForAllCategories));
        extractJalgaonPost("https://lokshahilive.com/wp-json/wp/v2/posts?categories=442");
        extractCityPost(getString(R.string.jalgoanShahar));
        extractNokriPost(getString(R.string.nokri));
        extractHavamanPost(getString(R.string.havaman));

   return view;
}


    public void extractTajyaPost(String URL){

        RequestQueue queue = Volley.newRequestQueue(getContext());

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.d("TAG", "onResponse: "+response.length());
                if (response.length() == 0){

                }
                for (int i=0; i < 5; i++){
                    try {
                        NewsHolder p = new NewsHolder();
                        {

                            JSONObject jsonObjectdata = response.getJSONObject(i);

                            p.setDate(jsonObjectdata.getString("date"));

                            p.setId(jsonObjectdata.getLong("id"));

                            JSONObject titleObject = jsonObjectdata.getJSONObject("title");
                            p.setTitle(titleObject.getString("rendered"));

                            JSONObject shareLinkObj = jsonObjectdata.getJSONObject("guid");
                            p.setLink(shareLinkObj.getString("rendered"));

                            JSONObject contentObject = jsonObjectdata.getJSONObject("content");
                            p.setContent(contentObject.getString("rendered"));

                            JSONObject excerptObject = jsonObjectdata.getJSONObject("excerpt");
                            p.setExcerpt(excerptObject.getString("rendered"));
                            p.setFeature_image(jsonObjectdata.getString("jetpack_featured_media_url"));

                            Log.d("SSSSSSSSAAAAA", "onResponse: "+p.getId());
                            tajyaAdapter.notifyDataSetChanged();
                        }

                        if (i==0){
                            tajya = p;
                            tajyaHeadline.setText(tajya.getTitle());
                            Glide.with(getContext()).load(tajya.getFeature_image()).into(postImg1);
                            firstTajya.setVisibility(View.VISIBLE);
                        }else{
                            tajyaList.add(p);
                        }
                        tajyaHeadline.setVisibility(View.VISIBLE);
                        breakingViewMore.setVisibility(View.VISIBLE);
                        card1.setVisibility(View.VISIBLE);

                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        queue.add(request);
    }
    private void extractHavamanPost(String URL) {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.d("TAG", "onResponse: "+response.length());
                if (response.length() == 0){
                }
                for (int i=0; i < 5; i++){
                    try {
                        NewsHolder p = new NewsHolder();
                        {

                            JSONObject jsonObjectdata = response.getJSONObject(i);
                            //Log.d("date", "onResponse: " + jsonObjectdata.getString("date"));

                            p.setDate(jsonObjectdata.getString("date"));

                            p.setId(jsonObjectdata.getLong("id"));

                            JSONObject titleObject = jsonObjectdata.getJSONObject("title");
                            p.setTitle(titleObject.getString("rendered"));

                            JSONObject shareLinkObj = jsonObjectdata.getJSONObject("guid");
                            p.setLink(shareLinkObj.getString("rendered"));

                            JSONObject contentObject = jsonObjectdata.getJSONObject("content");
                            p.setContent(contentObject.getString("rendered"));

                            JSONObject excerptObject = jsonObjectdata.getJSONObject("excerpt");
                            p.setExcerpt(excerptObject.getString("rendered"));

                            p.setFeature_image(jsonObjectdata.getString("jetpack_featured_media_url"));

                            Log.d("SSSSSSSSAAAAA", "onResponse: "+p.getId());
                            havamanAdapter.notifyDataSetChanged();
                        }

                        if (i==0){
                            havaman = p;
                            havamanHeadline.setText(havaman.getTitle());
                            Glide.with(getContext()).load(havaman.getFeature_image()).into(postImg5);
                            firstHavaman.setVisibility(View.VISIBLE);
                        }else{
                            havamanList.add(p);
                        }

                        havamanText.setVisibility(View.VISIBLE);
                        havamanViewMore.setVisibility(View.VISIBLE);
                        card6.setVisibility(View.VISIBLE);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        queue.add(request);
    }

    private void extractNokriPost(String URL) {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.d("TAG", "onResponse: "+response.length());
                if (response.length() == 0){

                }
                for (int i=0; i < 5; i++){
                    try {
                        NewsHolder p = new NewsHolder();
                        {
                            JSONObject jsonObjectdata = response.getJSONObject(i);
                            //Log.d("date", "onResponse: " + jsonObjectdata.getString("date"));

                            p.setDate(jsonObjectdata.getString("date"));

                            p.setId(jsonObjectdata.getLong("id"));

                            JSONObject titleObject = jsonObjectdata.getJSONObject("title");
                            p.setTitle(titleObject.getString("rendered"));

                            JSONObject shareLinkObj = jsonObjectdata.getJSONObject("guid");
                            p.setLink(shareLinkObj.getString("rendered"));

                            JSONObject contentObject = jsonObjectdata.getJSONObject("content");
                            p.setContent(contentObject.getString("rendered"));

                            JSONObject excerptObject = jsonObjectdata.getJSONObject("excerpt");
                            p.setExcerpt(excerptObject.getString("rendered"));
                            p.setFeature_image(jsonObjectdata.getString("jetpack_featured_media_url"));

                            Log.d("SSSSSSSSAAAAA", "onResponse: "+p.getId());
                            nokriAdapter.notifyDataSetChanged();
                        }

                        if (i==0){
                            nokri = p;
                            nokriHeadline.setText(nokri.getTitle());
                            Glide.with(getContext()).load(nokri.getFeature_image()).into(postImg4);
                            firstNokri.setVisibility(View.VISIBLE);
                        }else{
                            nokriList.add(p);
                        }
                        nokriText.setVisibility(View.VISIBLE);
                        nokriViewMore.setVisibility(View.VISIBLE);
                        card5.setVisibility(View.VISIBLE);

                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        queue.add(request);
    }

    private void extractCityPost(String URL) {

        RequestQueue queue = Volley.newRequestQueue(getContext());

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.d("TAG", "onResponse: "+response.length());
                if (response.length() == 0){
                }
                for (int i=0; i < 5; i++){
                    try {
                        NewsHolder p = new NewsHolder();
                        {

                            JSONObject jsonObjectdata = response.getJSONObject(i);

                            p.setDate(jsonObjectdata.getString("date"));

                            p.setId(jsonObjectdata.getLong("id"));

                            JSONObject titleObject = jsonObjectdata.getJSONObject("title");
                            p.setTitle(titleObject.getString("rendered"));

                            JSONObject shareLinkObj = jsonObjectdata.getJSONObject("guid");
                            p.setLink(shareLinkObj.getString("rendered"));

                            JSONObject contentObject = jsonObjectdata.getJSONObject("content");
                            p.setContent(contentObject.getString("rendered"));

                            JSONObject excerptObject = jsonObjectdata.getJSONObject("excerpt");
                            p.setExcerpt(excerptObject.getString("rendered"));

                            p.setFeature_image(jsonObjectdata.getString("jetpack_featured_media_url"));

                            Log.d("SSSSSSSSAAAAA", "onResponse: "+p.getId());
                            cityAdapter.notifyDataSetChanged();
                        }

                        if (i==0){
                            city = p;
                            cityHeadline.setText(city.getTitle());
                            Glide.with(getContext()).load(city.getFeature_image()).into(postImg3);
                            firstCity.setVisibility(View.VISIBLE);
                        }else{
                            cityList.add(p);
                        }
                        cityText.setVisibility(View.VISIBLE);
                        cityViewMore.setVisibility(View.VISIBLE);
                        card4.setVisibility(View.VISIBLE);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //  Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);
    }

    private void extractJalgaonPost(String URL) {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.d("TAG", "onResponse: "+response.length());
                if (response.length() == 0){
                }
                for (int i=0; i < 5; i++){
                    try {
                        NewsHolder p = new NewsHolder();
                        {

                            JSONObject jsonObjectdata = response.getJSONObject(i);
                            p.setDate(jsonObjectdata.getString("date"));

                            p.setId(jsonObjectdata.getLong("id"));

                            JSONObject titleObject = jsonObjectdata.getJSONObject("title");
                            p.setTitle(titleObject.getString("rendered"));

                            JSONObject shareLinkObj = jsonObjectdata.getJSONObject("guid");
                            p.setLink(shareLinkObj.getString("rendered"));

                            JSONObject contentObject = jsonObjectdata.getJSONObject("content");
                            p.setContent(contentObject.getString("rendered"));

                            JSONObject excerptObject = jsonObjectdata.getJSONObject("excerpt");
                            p.setExcerpt(excerptObject.getString("rendered"));

                            p.setFeature_image(jsonObjectdata.getString("jetpack_featured_media_url"));

                            Log.d("SSSSSSSSAAAAA", "onResponse: "+p.getId());
                            jalgaonAdapter.notifyDataSetChanged();
                        }

                        if (i==0){
                            jalgaon = p;
                            jalgaonHeadline.setText(jalgaon.getTitle());
                            Glide.with(getContext()).load(jalgaon.getFeature_image()).into(postImg2);
                            firstJalgaon.setVisibility(View.VISIBLE);
                        }else{
                            jalgaonList.add(p);
                        }
                        jalgaonText.setVisibility(View.VISIBLE);
                        visheshViewMore.setVisibility(View.VISIBLE);
                        card3.setVisibility(View.VISIBLE);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //  Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }
}