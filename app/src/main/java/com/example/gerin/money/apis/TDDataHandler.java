package com.example.gerin.money.apis;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class TDDataHandler implements DataHandler {

    private static final String API_KEY = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDQlAiLCJ0ZWFtX2lkIjoiNGEwNjRlNDQtNmJjOS0zNjUxLWE2NDAtNzlmYWQ2MDMxZjU3IiwiZXhwIjo5MjIzMzcyMDM2ODU0Nzc1LCJhcHBfaWQiOiI4YzcxZThkMC1lNjNiLTQzNjUtYjlkOS1mMmEzMDgxN2ZhNGUifQ.cX0JoweUD4f9eaGfSoMEdTy86KY8igzkyOVicLlQS6k";

    private String customerID;
    private RequestQueue rQueue;

    public TDDataHandler(String id, Context ctx){
        customerID = id;
        // Instantiate the cache
        Cache cache = new DiskBasedCache(ctx.getCacheDir(), 1024 * 1024); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        rQueue = new RequestQueue(cache, network);
        rQueue.start();
    }

    @Override
    public void getCustomerName(final TextView textView) {
        StringRequest request = new StringRequest(Request.Method.GET, "https://api.td-davinci.com/api/customers/" + customerID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);
                    JSONObject result = json.getJSONObject("result");
                    String name = result.getString("givenName") + " " + result.getString("surname");
                    Log.i("TDDataHandler", name);
                    textView.setText(name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TDDataHandler", "Failed to get customer data");
                textView.setText("First Last");
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", API_KEY);

                return params;
            }
        };

        rQueue.add(request);
    }
}

