package com.appdroid.lokshahinew;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.appdroid.lokshahinew.Adepter.NewsAdapter;
import com.appdroid.lokshahinew.Holder.NewsHolder;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class  DetailedNews extends AppCompatActivity {

    ImageView postImg,backBTN;
    NewsHolder newsHolder;

    RecyclerView recyclerView;
    LinearLayoutManager manager;

    NewsAdapter adapter;
    TextView headLine,detaildpart2,detaildpart3,detaildpart4,detaildpart5,detaildpart6,detaildpart7,detaildpart8,detaildpart9,detaildpart10,detaildpart11,detaildpart12;
    List<NewsHolder> newsList;
    public static TextView detaildpart1;
    ImageView whatsapp,telegram,share;

    Boolean isScrolling = true;
    int previoustotal=0;
    int page =1;
    int currentItems, totalItems,scrollOutItems;
    NestedScrollView nestedScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_news);


        newsList = new ArrayList<>();
        extractPost(getString(R.string.brecking_cat));

        adapter = new NewsAdapter(newsList, this);


        backBTN = findViewById(R.id.backBTN);
        whatsapp = findViewById(R.id.whatsapp);
        telegram = findViewById(R.id.telegram);
        share = findViewById(R.id.share);
        detaildpart1 = findViewById(R.id.detaildpart1);
        detaildpart2 = findViewById(R.id.detaildpart2);
        detaildpart3 = findViewById(R.id.detaildpart3);
        detaildpart4 = findViewById(R.id.detaildpart4);
        detaildpart5 = findViewById(R.id.detaildpart5);

        detaildpart6 = findViewById(R.id.detaildpart6);
        detaildpart7 = findViewById(R.id.detaildpart7);
        detaildpart8 = findViewById(R.id.detaildpart8);
        detaildpart9 = findViewById(R.id.detaildpart9);
        detaildpart10 = findViewById(R.id.detaildpart10);
        detaildpart11 = findViewById(R.id.detaildpart11);
        detaildpart12 = findViewById(R.id.detaildpart12);

        postImg = findViewById(R.id.postImg);
        headLine = findViewById(R.id.headLine);


        Intent intent = getIntent();
        String action =intent.getAction();

        if(action.equals("insideApp")){
            newsHolder = (NewsHolder) intent.getSerializableExtra("all");
            headLine.setText(Html.fromHtml("<b>"+newsHolder.getTitle()+"<b>"));
            detaildpart1.setText(Html.fromHtml(newsHolder.getContent()));

            Glide.with(DetailedNews.this).load(newsHolder.getFeature_image()).into(postImg);
        }


        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ganerateShareLinkForWhatsApp(newsHolder, "w");
            }
        });

        telegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ganerateShareLinkForWhatsApp(newsHolder, "t");
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ganerateShareLinkForWhatsApp(newsHolder, "all");
            }
        });

        recyclerView = findViewById(R.id.newsRecylerView);
        recyclerView.hasFixedSize();
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        newsList = new ArrayList<>();

        extractPost(getString(R.string.brecking_cat));

        adapter = new NewsAdapter(newsList,this);
        recyclerView.setAdapter(adapter);


//        nestedScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
//            @Override
//            public void onScrollChanged() {
//                View view = (View) nestedScrollView.getChildAt(nestedScrollView.getChildCount() - 1);
//                int diff = (view.getBottom() - (nestedScrollView.getHeight() + nestedScrollView.getScrollY()));
//                if (diff == 0) {
//                    //code to fetch more data for endless scrolling
//                    currentItems = manager.getChildCount();
//                    totalItems = manager.getItemCount();
//                    scrollOutItems = manager.findFirstVisibleItemPosition();
//
//                    Log.d("appdroid "+isScrolling, "currentitek "+currentItems+" total Item "+totalItems+" scrollout "+scrollOutItems+" previase "+previoustotal);
//                    if (isScrolling) {
//                        if (totalItems > previoustotal) {
//
//                            previoustotal = totalItems;
//                            page++;
//
//                            isScrolling = false;
//
//                        }
//
//                    }
//
//                    if (!isScrolling && (currentItems+scrollOutItems) == totalItems){
//
//                        Log.d("currentItem", "onScrolled: "+currentItems);
//
//                        Log.d("scrollOutItems", "onScrolled: "+scrollOutItems);
//                        Log.d("totalItems", "onScrolled: "+totalItems);
//
//                        isScrolling = true;
//                        fetchData(totalItems);
//
//                    }
//                }
//            }
//        });


        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void ganerateShareLinkForWhatsApp(NewsHolder newsHolder, String w) {
        String shareNews = "*"+headLine.getText().toString()+"*";
        Bitmap bitmap =((BitmapDrawable) postImg.getDrawable()).getBitmap();
        File file = new File(saveImage(bitmap,DetailedNews.this));

        if (w.equals("all")){
            Intent intent = new Intent(Intent.ACTION_SEND);
//            intent.setType("text/plain");
            intent.setType("image/*");
            Uri photoURI = FileProvider.getUriForFile(DetailedNews.this,getPackageName()+".fileprovider",file);
            intent .putExtra(Intent.EXTRA_STREAM,photoURI);
            intent.putExtra(Intent.EXTRA_SUBJECT, "SS");
            intent.putExtra(Intent.EXTRA_TEXT, shareNews);
            startActivity(Intent.createChooser(intent, "Share"));
            startActivity(intent);
        }else if (w.equals("w")){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setType("image/*");
            Uri photoURI = FileProvider.getUriForFile(DetailedNews.this,getPackageName()+".fileprovider",file);
            sendIntent.putExtra(Intent.EXTRA_STREAM,photoURI);
            sendIntent.putExtra(Intent.EXTRA_TEXT, shareNews);
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);

        }else if (w.equals("t")){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://telegram.me/share/url?url="+shareNews));
            startActivity(intent);
        }

    }

    public static String getAppFolder(Context activity){
        return activity.getExternalFilesDir(null).getPath()+"/";
    }

    public static String saveImage(Bitmap paramBitmap, Context context) {
        File directory = new File(getAppFolder(context)+"LokshahiNew/");
        if(!directory.exists()){
            directory.mkdirs();
        }
        File file = new File(directory,System.currentTimeMillis()+".png");
        if(file.exists()){
            file.delete();
        }
        try{
            OutputStream outputStream = new FileOutputStream(file);
            paramBitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
            outputStream.close();
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.DATA,file.getAbsolutePath());
            context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);

            return file.getAbsolutePath();
        }catch (Exception e){
            return "";
        }

    }

    public void extractPost(String URL){

        RequestQueue queue = Volley.newRequestQueue(DetailedNews.this);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.d("TAssssssG", "onResponse: "+response.length());
                if (response.length() == 0){
                }

                for (int i=0; i< response.length(); i++){
                    try {

                        NewsHolder p = new NewsHolder();
                        JSONObject jsonObjectdata = response.getJSONObject(i);
                        //Log.d("date", "onResponse: " + jsonObjectdata.getString("date"));

                        p.setDate(jsonObjectdata.getString("date"));

                        JSONObject titleObject = jsonObjectdata.getJSONObject("title");
                        p.setTitle(titleObject.getString("rendered"));


                        JSONObject shareLinkObj = jsonObjectdata.getJSONObject("guid");
                        p.setLink(shareLinkObj.getString("rendered"));

                        JSONObject contentObject = jsonObjectdata.getJSONObject("content");
                        p.setContent(contentObject.getString("rendered"));

                        JSONObject excerptObject = jsonObjectdata.getJSONObject("excerpt");
                        p.setExcerpt(excerptObject.getString("rendered"));

                        JSONObject shortNewsObject = jsonObjectdata.getJSONObject("excerpt");
                        p.setShortNews(shortNewsObject.getString("rendered"));

                        p.setFeature_image(jsonObjectdata.getString("jetpack_featured_media_url"));
                        newsList.add(p);
                        adapter.notifyDataSetChanged();

                    }catch (JSONException e){
                        e.printStackTrace();
                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error.getLocalizedMessage());
                //Toast.makeText(DetailedNews.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);
    }

    private void fetchData(int totalItems) {

        Log.d(TAG, "fetchData: call"+totalItems);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                extractPost(getString(R.string.brecking_cat)+"&offset="+totalItems);
            }
        },1000);
    }
}