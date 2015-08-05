package com.joshkevingeoff.evolearn;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Kevin on 4/3/15.
 */
public class Assignment extends ProblemSet{

    private String assignmentName;
    private int minProblems;
    private double baseLevel;
    private Date dueDate;

    public String getAssignmentName(){return assignmentName;}

    public int getMinProblems(){return minProblems;}

    public double getBaseLevel(){return baseLevel;}

    public Date getDueDate(){return dueDate;}

    public static Assignment createObject(String jsonString)
    {
        JSONObject fields;
        JSONObject reader;

        jsonString = jsonString.replace("[","");
        jsonString = jsonString.replace("]","");
        jsonString = jsonString.replace("&quot;", "\"");
        Log.d("Teacher.java", "Result: " + jsonString);

        try
        {
            reader = new JSONObject(jsonString);
            fields = new JSONObject(reader.getString("fields"));
            Assignment a = new Assignment();

            a.assignmentName = fields.getString("assignmentName");
            a.minProblems = fields.getInt("minProblems");
            a.baseLevel = fields.getDouble("baseLevel");
            DateFormat df = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
            a.dueDate = df.parse(fields.getString("dueDate"));

            return a;

        }
        catch(JSONException e)
        {
            Log.e("Teacher.java", e.getMessage());
        }
        catch(ParseException e)
        {
            Log.e("Teacher.java", e.getMessage());
        }
        return null;
    }

}
