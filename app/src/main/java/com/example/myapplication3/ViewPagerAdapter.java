package com.example.myapplication3;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    String playlist="";
    String topic="";
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setPlaylist(String playlist,String topic) {
        this.playlist = playlist;
        this.topic=topic;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
            fragment = new videos(playlist);
        else if (position == 1)
            fragment = new pdfs(topic);

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
            title = "Video";
        else if (position == 1)
            title = "Pdf";

        return title;
    }
}
