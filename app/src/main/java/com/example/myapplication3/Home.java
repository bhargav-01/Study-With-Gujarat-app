package com.example.myapplication3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        LinearLayout l= (LinearLayout) findViewById(R.id.a);
         ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setTitle("Study With Gujarat");
        actionBar.show();
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, MainActivity2.class);
                intent.putExtra("Playlist","PLtuE4dPdOBthMVgdKpQLRAPQkUVwU_8TF");
                intent.putExtra("topic","history");
                startActivity(intent);
            }
        });
        LinearLayout l1 = (LinearLayout) findViewById(R.id.b);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, MainActivity2.class);
                intent.putExtra("Playlist","PLtuE4dPdOBthVaffvezW-mIKyLnfsNMsW");
                intent.putExtra("topic","currentAffair");
                startActivity(intent);
            }
        });
        LinearLayout l2 = (LinearLayout) findViewById(R.id.c);
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, MainActivity2.class);
                intent.putExtra("Playlist","PLtuE4dPdOBtjVUXSkTNOo2vJuUTioPN0z");
                intent.putExtra("topic","geometry");
                startActivity(intent);
            }
        });

        LinearLayout l3 = (LinearLayout) findViewById(R.id.d);
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, MainActivity2.class);
                intent.putExtra("Playlist","PLtuE4dPdOBtie664PAPtlMDEWgoHMV1LI");
                intent.putExtra("topic","maths");
                startActivity(intent);
            }
        });
    }
}