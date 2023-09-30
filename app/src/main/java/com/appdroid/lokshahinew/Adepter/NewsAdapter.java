package com.appdroid.lokshahinew.Adepter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.appdroid.lokshahinew.DetailedNews;
import com.appdroid.lokshahinew.Holder.NewsHolder;
import com.appdroid.lokshahinew.R;
import com.appdroid.lokshahinew.Utils.AppUtils;
import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    List<NewsHolder> newslist;
    Context context;
    Date today;


    public NewsAdapter(List<NewsHolder> list, Context context) {
        this.newslist = list;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.horizontal_news_item, parent, false);
        today = new Date();
        return new NewsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        Log.d("Hello", "onBindViewHolder: " + newslist.size());
        NewsHolder newsHolder = newslist.get(position);

        if(newsHolder != null) {
            Log.d(TAG, "onBindViewHolder: "+newsHolder.getTitle());
            {
                Glide.with(context).load(newsHolder.getFeature_image()).into(holder.postImg);
                holder.headLine.setText(Html.fromHtml("<b>" + newsHolder.getTitle() + "</b>"));
                //holder.body.setText(Html.fromHtml("<p style=\"font-size: 20px;text-align:justify;\">"+newsHolder.getContent()+"</p>"));
                String oldDate = newsHolder.getDate();
                long postDate = AppUtils.getFormattedDate(oldDate).getTime();
                long todatTime = today.getTime();

                long diff = todatTime - postDate;

                int numOfday = (int) (diff / (1000 * 60 * 60 * 24));
                int min = (int) (diff / (1000 * 60));
                int hours = (int) (diff / (1000 * 60 * 60));
                int sec = (int) (diff / (1000));

                String s = DateFormat.getDateTimeInstance().format(AppUtils.getFormattedDate(oldDate));

                if (min > 60) {
                    if (hours > 24) {
                        if (numOfday > 2) {
                            holder.date.setText(Html.fromHtml(s));
                        } else {
                            holder.date.setText(numOfday + " दिवसांपूर्वी");
                        }
                    } else {
                        holder.date.setText(hours + " तासांपूर्वी");
                    }

                } else if (min == 0) {
                    holder.date.setText("काही सेकंदांपूर्वी");
                } else if (min < 60) {
                    if (min < 10) {
                        holder.date.setText(min + " मिनिटांपूर्वी");
                    } else {
                        holder.date.setText(min + " मिनिटांपूर्वी");
                    }

                }
                holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, DetailedNews.class);
                        intent.setAction("insideApp");
                        intent.putExtra("all",newsHolder);
                        context.startActivity(intent);
                    }
                });
            }
        }
    }

    @Override
        public int getItemCount () {
            return newslist.size();
        }
    @Override
        public long getItemId ( int position){
            return position;
        }

    @Override
        public int getItemViewType ( int position){
            return position;
        }

    public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView postImg;
            RelativeLayout relativeLayout;
            TextView headLine, date;
            String resultOfIP = "";

            public ViewHolder(@NonNull View view) {
                super(view);
                postImg = view.findViewById(R.id.postImg);
                relativeLayout = view.findViewById(R.id.relativeLayout);
                date = view.findViewById(R.id.date);
                headLine = view.findViewById(R.id.headLine);



            }
        }

}




