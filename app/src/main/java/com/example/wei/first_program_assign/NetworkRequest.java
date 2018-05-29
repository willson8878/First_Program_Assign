package com.example.wei.first_program_assign;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class NetworkRequest extends Request<JSONObject> {

    private Listener<JSONObject> listener;
    private Map<String, String> params;
    Context context;
    String url;

    public NetworkRequest(Context context, String url, int method, Map<String, String> params, Listener<JSONObject> responseListener, ErrorListener errorListener) {
        super(method, url, errorListener);
        try {
            this.listener = responseListener;
            this.params = params;
            this.context = context;
            this.url = url;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Map<String, String> getParams()
            throws AuthFailureError {

        if (params == null)
            return new HashMap<>();

        return params;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));

            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            je.printStackTrace();
            return Response.error(new ParseError(je));
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.error(new ParseError(ex));
        }
    }

    @Override
    protected void deliverResponse(JSONObject jsonObject) {
        try {
            listener.onResponse(jsonObject);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ErrorListener getErrorListener() {

        return super.getErrorListener();
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        volleyError.printStackTrace();
        return super.parseNetworkError(volleyError);
    }

    @Override
    public void deliverError(VolleyError volleyError) {

        try {
            volleyError.printStackTrace();
            super.deliverError(volleyError);
        } catch (Exception ex) {
            ex.printStackTrace();
            super.deliverError(volleyError);
        }
    }

}
