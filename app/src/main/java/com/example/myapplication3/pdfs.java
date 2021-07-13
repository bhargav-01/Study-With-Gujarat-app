package com.example.myapplication3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class pdfs extends Fragment {
    ListView  listView;
    String topic;
    DatabaseReference databaseReference;
    List<pdflist> p;
    PdfAdapter adepter;
    public pdfs() {
        // Required empty public constructor
    }

    public pdfs(String topic) {
        this.topic=topic;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_pdfs, container, false);
        listView=(ListView)view.findViewById(R.id.list1);
        p=new ArrayList<>();
        viewallfile();
        adepter = new PdfAdapter(getContext(), p);
        listView.setAdapter(adepter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pdflist pk=adepter.getItem(position);
                Intent webintent=new Intent(Intent.ACTION_VIEW, Uri.parse(pk.getUrl()));
                startActivity(webintent);
            }
        });
        return  view;
    }

    private void viewallfile() {

        databaseReference= FirebaseDatabase.getInstance().getReference().child(topic);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()==true)
                {
                    for(DataSnapshot postsnapshot:dataSnapshot.getChildren()) {

                        pdflist pdf = new pdflist(postsnapshot.child("title").getValue().toString(), postsnapshot.child("url").getValue().toString());
                        p.add(pdf);
                    }

                }else {

                }

                String s[]=new String[p.size()];
                for(int i=0;i<s.length;i++)
                {
                    s[i]=p.get(i).getTitle();
                }

                listView.setAdapter(adepter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }


}