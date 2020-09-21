package com.example.myapplication3;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class viedoesAdepter extends ArrayAdapter<Youtube_viedo> {

    private static final String TAG ="bscbc" ;

    public viedoesAdepter(@NonNull Context context, @NonNull List<Youtube_viedo> objects) {
        super(context,0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.viedoes_list, parent, false);
        }

        // Find the earthquake at the given position in the list of earthquakes
        Youtube_viedo currentEarthquake = getItem(position);

        // Find the TextView with view ID magnitude

        ImageView imageView=(ImageView)listItemView.findViewById(R.id.thumbnail);
        URL u= null;
        try {
            u = new URL(currentEarthquake.getThumbnail());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Picasso.get().load(currentEarthquake.getThumbnail()).into(imageView);
        // Display the magnitude of the current earthquake in that TextView

        TextView title = (TextView) listItemView.findViewById(R.id.city);
       title.setText(currentEarthquake.getTitle());




        return listItemView;
    }

    }


