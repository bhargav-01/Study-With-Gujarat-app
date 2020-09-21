package com.example.myapplication3.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication3.Home;
import com.example.myapplication3.MainActivity;
import com.example.myapplication3.MainActivity2;
import com.example.myapplication3.MainActivity3;
import com.example.myapplication3.R;

public class HomeFragment extends Fragment {

    public  static  String p="Playlist";
    public static String type;
    private HomeViewModel homeViewModel;



    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        LinearLayout l= (LinearLayout) root.findViewById(R.id.a);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="history";
                Intent intent = new Intent(getActivity(), MainActivity3.class);
                intent.putExtra("Playlist","PLtuE4dPdOBthMVgdKpQLRAPQkUVwU_8TF");
                startActivity(intent);
            }
        });
        LinearLayout l1 = (LinearLayout) root.findViewById(R.id.b);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="current affair";
                Intent intent = new Intent(getActivity(), MainActivity3.class);
                intent.putExtra("Playlist","PLtuE4dPdOBthVaffvezW-mIKyLnfsNMsW");
                startActivity(intent);
            }
        });
        LinearLayout l2 = (LinearLayout) root.findViewById(R.id.c);
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="geography";
                Intent intent = new Intent(getActivity(), MainActivity3.class);
                intent.putExtra("Playlist","PLtuE4dPdOBtjVUXSkTNOo2vJuUTioPN0z");
                startActivity(intent);
            }
        });

        LinearLayout l3 = (LinearLayout) root.findViewById(R.id.d);
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="maths";
                Intent intent = new Intent(getActivity(), MainActivity3.class);
                intent.putExtra("Playlist","PLtuE4dPdOBtie664PAPtlMDEWgoHMV1LI");
                startActivity(intent);
            }
        });
        return root;
    }

}