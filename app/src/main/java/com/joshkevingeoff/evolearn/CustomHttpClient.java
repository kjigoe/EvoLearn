package com.joshkevingeoff.evolearn;

/**
 * Created by josh on 4/3/15.
 * Code from: http://wowjava.wordpress.com/2011/01/16/login-application-for-android/
 */
import android.os.AsyncTask;
import android.provider.SyncStateContract;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class CustomHttpClient extends AsyncTask<ArrayList<NameValuePair>, String, String> {
    /** The time it takes for our client to timeout */
    public static final int HTTP_TIMEOUT = 30 * 1000; // milliseconds

    /** Single instance of our HttpClient */
    private static HttpClient mHttpClient;
    private static String url;
    private String csrftoken;

    public CustomHttpClient(String urlToSubmit)
    {
        url = urlToSubmit;
    }

    /**
     * Get our single instance of our HttpClient object.
     *
     * @return an HttpClient object with connection parameters set
     */
    private HttpClient getHttpClient() {
        if (mHttpClient == null) {
            mHttpClient = new DefaultHttpClient();
            final HttpParams params = mHttpClient.getParams();
            HttpConnectionParams.setConnectionTimeout(params, HTTP_TIMEOUT);
            HttpConnectionParams.setSoTimeout(params, HTTP_TIMEOUT);
            ConnManagerParams.setTimeout(params, HTTP_TIMEOUT);

        }
        return mHttpClient;
    }

    /**
     * Performs an HTTP Post request to the specified url with the
     * specified parameters.
     *
     * @return The result of the request
     * @throws Exception
     */
    protected String doInBackground(ArrayList<NameValuePair> ... paramLists)  {
        BufferedReader in = null;
        String result = "";
        try {
            HttpClient client = getHttpClient();
            HttpPost request = new HttpPost(url);

//            DefaultHttpClient cookieMonster = new DefaultHttpClient();
//            csrftoken = "";
//
//
//            client.execute(new HttpGet(url));
//            CookieStore cookieStore1 = cookieMonster.getCookieStore();
//            List<Cookie> cookies = cookieStore1.getCookies();
//            Log.d("CustomHttpClient", "CSize: "+cookies.size());
//
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("csrftoken")) {
//                    csrftoken = cookie.getValue();
//                    Log.d("CustomHttpClient", "Domain: "+cookie.getDomain());
//                }
//                Log.d("CustomHttpClient", "Name: "+cookie.getName());
//            }
//
//            request.setHeader("Referer", url);
//            request.setHeader("csrftoken", "Up6JKclfSPUyLV9rmrycd7r4cRS75b6y");
//
//            final BasicCookieStore cookieStore =  new BasicCookieStore();
//
//            BasicClientCookie csrf_cookie = new BasicClientCookie("csrftoken", "Up6JKclfSPUyLV9rmrycd7r4cRS75b6y");
//            csrf_cookie.setDomain("ec2-54-88-150-106.compute-1.amazonaws.com");
//            cookieStore.addCookie(csrf_cookie);

//// Create local HTTP context - to store cookies
//            HttpContext localContext = new BasicHttpContext();
//// Bind custom cookie store to the local context
//            localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);

            Log.d("CustomHttpClient", "URL: "+url);
            Log.d("CustomHttpClient", "ParamSize: "+paramLists[0].size());
            ArrayList<NameValuePair> postParameters = paramLists[0];
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
            request.setEntity(formEntity);
            HttpResponse response = client.execute(request);

            //Log.d("CustomHttpClient", "Response: "+response.getEntity().getContent().toString());
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line);
                //Log.d("CustomHttpClient", "A");
            }

            result = sb.toString();
            Log.d("CustomHttpClient", "ResultA: "+result);
            in.close();
            return result;
        }
        catch(IOException e) {
            Log.e("HttpRequestClient", e.getMessage());
            return "";
        }
        /*finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Log.d("CustomHttpClient", "ResultB: "+result);
            return result;
        }*/
    }

/*    *//**
     * Performs an HTTP GET request to the specified url.
     *
     * @param url The web address to post the request to
     * @return The result of the request
     * @throws Exception
     *//*
    public static String executeHttpGet(String url) throws Exception {
        BufferedReader in = null;
        try {
            HttpClient client = getHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(url));
            HttpResponse response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();

            String result = sb.toString();
            return result;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/
}