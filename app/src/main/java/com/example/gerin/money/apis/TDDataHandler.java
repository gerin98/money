package com.example.gerin.money.apis;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.example.gerin.money.objects.AccountObject;
import com.example.gerin.money.objects.CustomerObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class TDDataHandler implements DataHandler {

    private static final String API_KEY = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDQlAiLCJ0ZWFtX2lkIjoiNGEwNjRlNDQtNmJjOS0zNjUxLWE2NDAtNzlmYWQ2MDMxZjU3IiwiZXhwIjo5MjIzMzcyMDM2ODU0Nzc1LCJhcHBfaWQiOiI4YzcxZThkMC1lNjNiLTQzNjUtYjlkOS1mMmEzMDgxN2ZhNGUifQ.cX0JoweUD4f9eaGfSoMEdTy86KY8igzkyOVicLlQS6k";

    private RequestQueue rQueue;

    public TDDataHandler(Context ctx) {
        // Instantiate the cache
        Cache cache = new DiskBasedCache(ctx.getCacheDir(), 1024 * 1024); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        rQueue = new RequestQueue(cache, network);
        rQueue.start();
    }

    @Override
    public void getCustomerAccounts(String id, CustomerObject customerObject) {
        StringRequest request = new StringRequest(Request.Method.GET, "https://api.td-davinci.com/api/customers/" + id + "/accounts", response -> {
            try {
                JSONObject json = new JSONObject(response);
                JSONArray array = json.getJSONObject("result").getJSONArray("bankAccounts");
                for (int i = 0; i < array.length(); i++) {
                    customerObject.accountIDs.add(array.getJSONObject(i).getString("id"));
                    Log.i("TDDataHandler", array.getJSONObject(i).getString("id"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Log.e("TDDataHandler", "Failed to get customer data");
            error.printStackTrace();
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", API_KEY);

                return params;
            }
        };

        rQueue.add(request);
    }

    @Override
    public void getAccountData(String id, AccountObject accountObject) {
        StringRequest request = new StringRequest(Request.Method.GET, "https://api.td-davinci.com/api/accounts/" + id, response -> {
            try {
                Log.i("SDFSDFS", response);
                JSONObject json = new JSONObject(response);
                JSONObject result = json.getJSONObject("result");
                JSONObject account = result.getJSONObject("bankAccount");
                accountObject.balance.set(account.getDouble("balance"));
                accountObject.currency.set(account.getString("currency"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Log.e("TDDataHandler", "Failed to get customer data");
            error.printStackTrace();
        }) {
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

