package com.example.wei.first_program_assign;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class NetworkController {

    private static NetworkController controller = new NetworkController();

    public static NetworkController getInstance()
    {
        return controller;
    }

    /**
     *
     * @param context current class context
     * @param method GET or POST
     * @param requestCode to identify request
     * @param resultListener to get callback for response
     * @param stringParams can be null if method is GET
     *
     * */
    public void connect(Context context, final int requestCode, int method, HashMap<String, String> stringParams, final ResultListener resultListener)
    {
        try {
            String url = URLConstants.REQUEST_URL;

            NetworkRequest networkRequest = new NetworkRequest(context, url, method, stringParams, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    resultListener.onResult(requestCode, true, jsonObject, null);
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            resultListener.onResult(requestCode, false, null, error);
                            error.printStackTrace();
                        }
                    });

            networkRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Volley.newRequestQueue(context).add(networkRequest);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public interface ResultListener {

        void onResult(int requestCode, boolean isSuccess, JSONObject jsonObject, VolleyError volleyError);

    }

}