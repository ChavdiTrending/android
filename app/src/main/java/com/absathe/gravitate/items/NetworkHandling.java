package com.absathe.gravitate.items;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by ABSathe on 11-05-2018.
 */

public class NetworkHandling {
    private Context context;
    public NetworkHandling(Context context) {
        this.context = context;
    }

    public class UpdateData extends AsyncTask<String, Void, String> {
        private String facebook = "fb";
        private String instagram = "insta";
        private String youtube = "youtube";
        public int result = 0;
        SharedPreferences pref = context.getSharedPreferences("JSONData", Context.MODE_PRIVATE);
        @Override
        protected String doInBackground(String... strings) {
            String url;
            final Long time = System.currentTimeMillis()/1000;
            /*
             * Update only if the last update was before 5 hrs
             */
            SimpleDateFormat format = new SimpleDateFormat("HH");
            result = 0;
            url = strings[0] + facebook;
            System.out.println(url);
            StringRequest stringRequestFB = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response != null) {
                                System.out.println(response);
                                pref.edit().putString("FacebookJSON", response).apply();
                                result += 1;
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            result -= 1;
                        }
                    });
            url = strings[0] + instagram;
            System.out.println(url);
            StringRequest stringRequestINSTA = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response != null) {
                                System.out.println(response);
                                pref.edit().putString("InstagramJSON", response).apply();
                                result += 1;
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            result -= 1;
                        }
                    });
            url = strings[0] + youtube;
            System.out.println(url);
            StringRequest stringRequestYT = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response != null) {
                                System.out.println(response);
                                pref.edit().putString("YouTubeJSON", response).apply();
                                result += 1;
                                pref.edit().putInt("Result", result).apply();
                                pref.edit().putString("Last Modified", DateFormat.getDateTimeInstance().format(new Date())).apply();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            result -= 1;
                        }
                    });
            RequestQueue queue = Volley.newRequestQueue(context);
            queue.add(stringRequestFB);
            queue.add(stringRequestINSTA);
            queue.add(stringRequestYT);

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            String res = "response";
            res = pref.getString("FacebookJSON", "not found");
            System.out.println("The contents of FacebookJSON are : " + res);
            res = pref.getString("InstagramJSON", "not found");
            System.out.println("The contents of InstagramJSON are: " + res);
            res = pref.getString("YouTubeJSON", "not found");
            System.out.println("The contents of YouTubeJSON are : " + res);
            res = pref.getString("Last Modified", "not found");
            System.out.println("The contents of last modified are " + res);
            System.out.println("The conntents are " +pref.getInt("result", 0));
        }
    }

    public String getFromServer(String serverURL) {
        String result = null;
        UpdateData data = new UpdateData();
        try {
            result = data.execute(serverURL).get();
        } catch(InterruptedException e) {
            e.printStackTrace();
            Log.d("NetworkHandling", "Unable to run in background thread" + e.getMessage());
        } catch(ExecutionException e) {
            Log.d("NetworkHandling", "Unable to execute in background thread" + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
