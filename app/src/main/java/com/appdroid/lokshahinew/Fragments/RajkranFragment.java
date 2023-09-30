package com.appdroid.lokshahinew.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.appdroid.lokshahinew.Adepter.NewsAdapter;
import com.appdroid.lokshahinew.Holder.NewsHolder;
import com.appdroid.lokshahinew.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class RajkranFragment extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager manager_jalgaon_shahar;
    List<NewsHolder> newsHolderList;
    public static NewsAdapter adapter;
    public static Boolean isScrolling=true;
    RelativeLayout progress_layout;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_rajkran, container, false);
        isScrolling=true;
        recyclerView=view.findViewById(R.id.recyclerView_rajkaran);
        recyclerView.hasFixedSize();
        manager_jalgaon_shahar=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager_jalgaon_shahar);

        newsHolderList=new ArrayList<>();

        extractPost("https://lokshahilive.com/wp-json/wp/v2/posts?categories=10");
        adapter=new NewsAdapter(newsHolderList,getContext());
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void extractPost(String URL) {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("TAG", "onResponse:" + response.length());
                for (int i = 0; i < response.length(); i++) {
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

                            Log.d("Hello", "onResponse: "+p.getId());
                            adapter.notifyDataSetChanged();
                        }
                        newsHolderList.add(p);
                    } catch (JSONException e) {
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
    private void fetchData(int totalItems) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                extractPost("https://lokshahilive.com/wp-json/wp/v2/posts?categories=10"+"offset="+totalItems);
            }
        },1000);
    }
}