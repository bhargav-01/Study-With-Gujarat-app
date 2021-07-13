package com.example.myapplication3;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class videosAdepter extends ArrayAdapter<Youtube_video> {

    public videosAdepter(@NonNull Context context, @NonNull List<Youtube_video> objects) {
        super(context,0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {


        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.videos_list, parent, false);
        }

        Youtube_video currentVideo = getItem(position);

        ImageView imageView=(ImageView)listItemView.findViewById(R.id.thumbnail);
        URL u= null;
        try {
            u = new URL(currentVideo.getThumbnail());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Picasso.get().load(currentVideo.getThumbnail()).into(imageView);

        TextView title = (TextView) listItemView.findViewById(R.id.city);
        title.setText(currentVideo.getTitle());

        return listItemView;
    }

}


