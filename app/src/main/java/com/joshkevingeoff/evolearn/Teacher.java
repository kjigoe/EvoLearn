package com.joshkevingeoff.evolearn;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;

/**
 * Created by Kevin on 4/3/15.
 */
public class Teacher extends User {

    private static final Class homePage = MyClassroom.class;

    private Student[] listOfStudents;
    private Assignment[] listOfAssignments;
    private int numberOfStudents = 0;

    public Class getHomePage()
    {
        return homePage;
    }

    public void createAssignment() {

    }
    public float[] viewProblemData() {
        return null;
    }

    public float[] viewStudentGrowthData() {
        return null;
    }

    public float viewAverageStudentLevel() {
        return 1;
    }

    public void deleteAssignment() {

    }

    public void createStudent() {

    }

    public void deleteStudent() {

    }

    public static Teacher createObject(String jsonString)
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
            Teacher t = new Teacher();

            t.firstName = fields.getString("firstName");
            t.lastName = fields.getString("lastName");
            t.userID = fields.getString("userID");
            t.schoolName = fields.getString("schoolName");
            t.numberOfStudents = fields.getInt("numberOfStudents");

            Log.d("Teacher.java", "Fields: "+reader.getString("fields"));
            return t;

        }
        catch(JSONException e)
        {
            Log.e("Teacher.java", e.getMessage());
        }
        return null;
    }


}
