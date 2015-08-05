package com.joshkevingeoff.evolearn;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.jar.Attributes;


/**
 * Created by josh on 4/3/15.
 */
public class ServerInteractions {
    private final static String TAG = "ServerInteractions.java";

    // List of urls
    private final static String baseURL = "http://ec2-54-88-150-106.compute-1.amazonaws.com/mobile/";
    private final static String loginURL = baseURL + "login/";
    private final static String teacherAssignmentsURL = baseURL + "assignments/";
    private final static String studentTeacherURL = baseURL + "studentteacher/";

    public static User checkLoginDetails(String username, String password) {
        try {
            ArrayList<NameValuePair> post = new ArrayList<NameValuePair>();
            post.add(new BasicNameValuePair("user", username));
            post.add(new BasicNameValuePair("pass", password));
            CustomHttpClient c = new CustomHttpClient(loginURL);
            c.execute(post);
            String result = c.get();
            return User.createObject(result);
        } catch (InterruptedException e) {
            Log.e(TAG, e.getMessage());
        } catch (ExecutionException e) {
            Log.e(TAG, e.getMessage());
        }

        return null;
    }

    public static Teacher getStudentTeacher(int teacherID)
    {
        try {
            ArrayList<NameValuePair> post = new ArrayList<NameValuePair>();
            post.add(new BasicNameValuePair("teacherid", Integer.toString(teacherID)));
            // TODO Need to change django code eventually
            CustomHttpClient c = new CustomHttpClient(studentTeacherURL);
            c.execute(post);
            String result = c.get();
            return Teacher.createObject(result);
        } catch (InterruptedException e) {
            Log.e(TAG, e.getMessage());
        } catch (ExecutionException e) {
            Log.e(TAG, e.getMessage());
        }

        return null;
    }

    public static ArrayList<Assignment> getAssignmentList(String teacherName)
    {
        ArrayList<Assignment> assignments = new ArrayList<Assignment>();
        try {
            ArrayList<NameValuePair> post = new ArrayList<NameValuePair>();
            post.add(new BasicNameValuePair("teacherid", teacherName));
            // TODO Need to change django code eventually
            CustomHttpClient c = new CustomHttpClient(teacherAssignmentsURL);
            c.execute(post);
            String result = c.get();
            Log.d("SI.java", result);
            result = result.replace("&quot;", "\"");
            String[] tempAssignments = result.split("\\]\\[");

            for(int i=0; i<tempAssignments.length; i++)
            {
                assignments.add(Assignment.createObject(tempAssignments[i]));
                Log.d("SI.java","tempAssignments: "+tempAssignments[i]);

            }
        } catch (InterruptedException e) {
            Log.e(TAG, e.getMessage());
        } catch (ExecutionException e) {
            Log.e(TAG, e.getMessage());
        }

        return assignments;
    }
}
