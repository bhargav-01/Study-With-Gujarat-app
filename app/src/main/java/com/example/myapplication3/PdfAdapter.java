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

public class PdfAdapter extends ArrayAdapter<pdflist> {

    private static final String TAG ="bscbc" ;

    public PdfAdapter(@NonNull Context context, @NonNull List<pdflist> objects) {
        super(context,0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {


        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.pdfs_list, parent, false);
        }

        pdflist currentPdf = getItem(position);
        TextView title = (TextView) listItemView.findViewById(R.id.title);
        title.setText(currentPdf.getTitle());

        return listItemView;
    }

}


