package com.example.myapplication3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myapplication3.ui.home.HomeFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class pdf extends AppCompatActivity {
ListView  listView;
DatabaseReference databaseReference;
List<pdflist> p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i=getIntent();
        viewallfile();
        setContentView(R.layout.activity_pdf);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.pdf);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.pdf:
                        return true;
                    case R.id.viedo:
                        startActivity(new Intent(getApplicationContext(),MainActivity3.class));
                        overridePendingTransition(0,0);
                }
                return false;
            }
        });
        listView=(ListView)findViewById(R.id.list1);
        p=new ArrayList<>();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pdflist pk=p.get(position);
                Intent webintent=new Intent(Intent.ACTION_VIEW, Uri.parse(pk.getUrl()));
                startActivity(webintent);
            }
        });



    }

    private void viewallfile() {

        databaseReference= FirebaseDatabase.getInstance().getReference().child(HomeFragment.type);
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
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "This is a message displayed in a Toast",
                            Toast.LENGTH_SHORT);

                    toast.show();

                }

                String s[]=new String[p.size()];
                for(int i=0;i<s.length;i++)
                {
                    s[i]=p.get(i).getTitle();
                }

                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,s)
                {
                    @Override
                    public View getView(int position, View convertView,ViewGroup parent) {
                        View view=super.getView(position,convertView,parent);
                        TextView textView=(TextView)view.findViewById(android.R.id.text1);
                        textView.setTextSize(30);
                        return view;
                    }
                };
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}