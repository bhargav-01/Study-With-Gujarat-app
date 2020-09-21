package com.example.myapplication3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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

public class MainActivity3 extends AppCompatActivity {
    private static String playlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent i=getIntent();
        Bundle b=i.getExtras();
        playlist=b.getString("Playlist");



        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.viedo);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.viedo:
                        return true;
                    case R.id.pdf:
                        startActivity(new Intent(getApplicationContext(),pdf.class));
                        overridePendingTransition(0,0);
                }
                return false;
            }
        });
        QueryUtils q = new QueryUtils();
        q.execute();
    }


    public static String getPlaylist() {
        return playlist;
    }

    private void updateUi(ArrayList<Youtube_viedo> viedos) {
        // Display the earthquake title in the UI
        ListView videoslist = (ListView) findViewById(R.id.list);
        final viedoesAdepter adepter = new viedoesAdepter(this, viedos);
        videoslist.setAdapter(adepter);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interfac

        videoslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                Youtube_viedo currentEarthquake =adepter.getItem(position);

                String id=currentEarthquake.getId();

                // Create a new intent to view the earthquake URI
                Intent intent = new Intent(getBaseContext(),viedoPlayer.class);
                intent.putExtra("id",id);
                startActivity(intent);

                // Send the intent to launch a new activity

            }
        });

    }


    class QueryUtils extends AsyncTask<URL, Void, ArrayList<Youtube_viedo>> {
        private  final String Youtube_URL = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=25&playlistId="+MainActivity3.getPlaylist()+"&key=AIzaSyCGkUWYF0uCkbcxxcwc_Lua8njXVfvLz_U";
        public  final String LOG_TAG = MainActivity.class.getSimpleName();

        @Override
        protected ArrayList<Youtube_viedo> doInBackground(URL... urls) {

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
            ArrayList<Youtube_viedo> viedos = extractFeatureFromJson(jsonResponse);

            // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}
            return viedos;

        }

        @Override
        protected void onPostExecute(ArrayList<Youtube_viedo> viedos) {
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
                Log.e(LOG_TAG, "Error with creating URL", exception);
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
                    Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
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

        ArrayList<Youtube_viedo> extractFeatureFromJson(String jsonResponse) {


            // Perform HTTP request to the URL and receive a JSON response back


            // Extract relevant fields from the JSON response and create an {@link Event} object


            ArrayList<Youtube_viedo> viedos = new ArrayList<>();
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
                        viedos.add(new Youtube_viedo(title, a, id));
                    }

                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
            }
            return viedos;
        }


    }
}