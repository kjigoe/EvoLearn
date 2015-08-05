package com.joshkevingeoff.evolearn;
/*
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.TextView; */

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kevin on 4/3/15.
 */
public abstract class User {
    protected String firstName;
    protected String lastName;
    protected String userID;
    protected String loginPassword;
    protected String schoolName;
    protected boolean needToUpdateServer;
    private boolean isTeacher;

    private final static String TAG = "User.java";
    private final static String CREATESTUDENTFLAG = "mobile.student";
    private final static String CREATETEACHERFLAG = "mobile.teacher";

    public String getUserID()
    {
        return userID;
    }

    public String getFirstName(){return firstName;}

    public String getLastName(){return lastName;}

    public String getSchoolName(){return schoolName;}

    public abstract Class getHomePage();

    public void login() {

    }

    public static User createObject(String jsonString)
    {
        JSONObject fields;
        JSONObject reader;
        User u;

        jsonString = jsonString.replace("[","");
        jsonString = jsonString.replace("]","");
        jsonString = jsonString.replace("&quot;", "\"");
        Log.d(TAG, "Result: " + jsonString);

        try
        {
            reader = new JSONObject(jsonString);
            fields = new JSONObject(reader.getString("fields"));

            if(reader.getString("model").equals(CREATESTUDENTFLAG))
            {
                return Student.createObject(jsonString);

            }
            else //if (reader.getString("model").equals(CREATETEACHERFLAG))
            {
                return Teacher.createObject(jsonString);
            }
        }
        catch(JSONException e)
        {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }


}
