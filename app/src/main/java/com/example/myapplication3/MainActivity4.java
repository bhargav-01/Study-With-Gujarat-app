package com.example.myapplication3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity4 extends AppCompatActivity {
private TextView textView;
private PDFView pdfView;
private FirebaseDatabase database=FirebaseDatabase.getInstance();


@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        pdfView=(PDFView)findViewById(R.id.pdfview);
        textView=(TextView)findViewById(R.id.text);
    Intent i=getIntent();


    DatabaseReference databaseReference=database.getReference("history/pdf");
    databaseReference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            String ur=dataSnapshot.getValue(String.class);
            Retrivepdf retrivepdf=new Retrivepdf();
            retrivepdf.execute(ur);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });


    }
    class Retrivepdf extends AsyncTask<String,Void, InputStream> {

        @Override
        protected InputStream doInBackground(String... strings) {
           InputStream inputStream=null;
           try {
               URL url=new URL(strings[0]);
               HttpsURLConnection urlConnection=(HttpsURLConnection)url.openConnection();
               if(urlConnection.getResponseCode()==200)
               {
                   inputStream=new BufferedInputStream(urlConnection.getInputStream());

               }



           } catch (MalformedURLException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           }
           return  inputStream;
        }


        protected void onPostExecute(InputStream inputStream) {
          pdfView.fromStream(inputStream).load();
        }

    }

}