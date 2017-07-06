package com.journaldev.expandablelistview;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by praveendewangan on 01/07/17.
 */


public class Singleton_VolleyClass {


    private RequestQueue requestQueue;
    private static Singleton_VolleyClass mysingleton_volleyclass;
    private static Context mcontext;


    private Singleton_VolleyClass(Context context) {

        mcontext = context;
        requestQueue = getRequestQueue();


    }

    public static Singleton_VolleyClass getInstance(Context context) {
        if (mysingleton_volleyclass == null)

        {
            mysingleton_volleyclass = new Singleton_VolleyClass(context);
        }

        return mysingleton_volleyclass;
    }

    public synchronized RequestQueue getRequestQueue() {

        if (requestQueue == null) {

            requestQueue = Volley.newRequestQueue(mcontext.getApplicationContext());

        }

        return requestQueue;
    }

    public <T> Request addrequest(JsonObjectRequest req) {

        return requestQueue.add(req);
    }



}