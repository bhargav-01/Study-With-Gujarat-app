package com.example.myapplication3;

public class pdflist {
private  String url;
private  String title;

   public pdflist(String title, String url)
   {
       this.url=url;
       this.title=title;
   }


public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

}
