package com.snowcascades.app;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
 
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class SplashScreen extends Activity {
	private ArrayList<String> data;
 
 
    /*
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
 
        new Handler().postDelayed(new Runnable() {
  
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
//                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                Intent i = new Intent(SplashScreen.this, resortListActivity.class);
                startActivity(i);
 
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
    */
    String now_playing, earned;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
 
        /**
         * Showing splashscreen while making network calls to download necessary
         * data before launching the app Will use AsyncTask to make http call
         */
        new PrefetchData().execute();
 
    }
 
    /**
     * Async Task to make http call
     */
    private class PrefetchData extends AsyncTask<Void, Void, Void> {
 
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // before making http calls         
 
        }
 
        @Override
        protected Void doInBackground(Void... arg0) {
            /*
             * Will make http call here This call will download required data
             * before launching the app 
             * example: 
             * 1. Downloading and storing in SQLite 
             * 2. Downloading images 
             * 3. Fetching and parsing the xml / json 
             * 4. Sending device information to server 
             * 5. etc.,
             */
            JsonParser jsonParser = new JsonParser();
            String json = jsonParser
                    .getJSONFromUrl("http://snowcascades.com/cascade/data.json");
 
            Log.e("Response: ", "> " + json);
            data = new ArrayList<String>();
 
            if (json != null) {
                try {
                    JSONArray jArr = new JSONObject(json)
                    .getJSONArray("resorts");
                	/*
                    JSONObject jObj = new JSONObject(json)
                            .getJSONObject("game_stat");
                    now_playing = jObj.getString("now_playing");
                    earned = jObj.getString("earned");
                    */
 
                    for (int i = 0; i < jArr.length(); i++) {
                        JSONObject o = jArr.getJSONObject(i);
                    	Log.e("JSON", "> " + o.getString("name"));
                    	data.add(o.getString("name"));
                    }
 
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
 
            }
 
            return null;
        }
 
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // After completing http call
            // will close this activity and lauch main activity
//          Intent i = new Intent(SplashScreen.this, MainActivity.class);
            Intent i = new Intent(SplashScreen.this, resortListActivity.class);
            i.putExtra("now_playing", now_playing);
            i.putExtra("earned", earned);
            i.putStringArrayListExtra("resorts",data);
            startActivity(i);
 
            // close this activity
            finish();
        }
 
    } 
}