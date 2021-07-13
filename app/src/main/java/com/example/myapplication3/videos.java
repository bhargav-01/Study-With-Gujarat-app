package com.example.myapplication3;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;


public class videos extends Fragment {
    private static String playlist;
    ListView videoslist;
    public videos() {
        // Required empty public constructor
    }

    public videos(String playlist) {
        this.playlist=playlist;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_videos, container, false);
        videoslist=view.findViewById(R.id.list);
        QueryUtils q = new QueryUtils();
        q.execute();
        return view;
    }

    public static String getPlaylist() {
        return playlist;
    }

    private void updateUi(ArrayList<Youtube_video> viedos) {
        // Display the earthquake title in the UI

        final videosAdepter adepter = new videosAdepter(getContext(), viedos);
        videoslist.setAdapter(adepter);

        videoslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Youtube_video youtube_video =adepter.getItem(position);
                String id=youtube_video.getId();
                Intent intent = new Intent(getContext(),videoPlayer.class);
                intent.putExtra("id",id);
                startActivity(intent);

            }
        });

    }


    class QueryUtils extends AsyncTask<URL, Void, ArrayList<Youtube_video>> {
        private  final String Youtube_URL = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=25&playlistId="+videos.getPlaylist()+"&key=AIzaSyCGkUWYF0uCkbcxxcwc_Lua8njXVfvLz_U";
//        public  final String LOG_TAG = MainActivity.class.getSimpleName();

        @Override
        protected ArrayList<Youtube_video> doInBackground(URL... urls) {

            // Create URL object
            URL url = createUrl(Youtube_URL);

            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                // TODO Handle the IOException
            }

            // Extract relevant fields from the JSON response and create an {@link Event} object
            ArrayList<Youtube_video> viedos = extractFeatureFromJson(jsonResponse);

            // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}
            return viedos;

        }

        @Override
        protected void onPostExecute(ArrayList<Youtube_video> viedos) {
            if (viedos.isEmpty() == false) {

                updateUi(viedos);
            }

            updateUi(viedos);

        }

        private URL createUrl(String stringUrl) {
            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException exception) {
//                Log.e(LOG_TAG, "Error with creating URL", exception);
                return null;
            }
            return url;
        }

        /**
         * Make an HTTP request to the given URL and return a String as the response.
         */
        private  String makeHttpRequest(URL url) throws IOException {


            String jsonResponse = "";
            if (url == null) {
                return jsonResponse;
            }
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;


            try {
                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.connect();
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = urlConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);
                } else {
//                    Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
                }

            } catch (IOException e) {
                // TODO: Handle the exception
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    // function must handle java.io.IOException here
                    inputStream.close();
                }
            }
            return jsonResponse;
        }

        private  String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }

        ArrayList<Youtube_video> extractFeatureFromJson(String jsonResponse) {

            ArrayList<Youtube_video> viedos = new ArrayList<>();
            if (jsonResponse.equals("")) {
                return null;

            }
            try {


                JSONObject baseJsonResponse = new JSONObject(jsonResponse);
                JSONArray itemArray = baseJsonResponse.getJSONArray("items");

                // If there are results in the features array
                for (int i = 0; i < itemArray.length(); i++) {
                    // Extract out the first feature (which is an earthquake)
                    JSONObject firstFeature = itemArray.getJSONObject(i);
                    JSONObject snippet = firstFeature.getJSONObject("snippet");

                    // Extract out the title, time, and tsunami values
                    String title = snippet.getString("title");
                    if(!title.equals("Private video")) {

                        JSONObject thumbnails = snippet.getJSONObject("thumbnails");
                        JSONObject b = thumbnails.getJSONObject("standard");
                        String a = b.getString("url");

                        String id = snippet.getJSONObject("resourceId").getString("videoId");
                        System.out.println(id);
                        // Create a new {@link Event} object
                        viedos.add(new Youtube_video(title, a, id));
                    }

                }
            } catch (JSONException e) {
            }
            return viedos;
        }


    }
}