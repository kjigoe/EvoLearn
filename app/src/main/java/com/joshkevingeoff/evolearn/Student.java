package com.joshkevingeoff.evolearn;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kevin on 4/3/15.
 */
public class Student extends User {

    private static final Class homePage = StudentAssignmentList.class;

    private double studentLevel;
    private ProblemSet[] listOfProblemSets;
    private Teacher teacher;

    public Class getHomePage()
    {
        return homePage;
    }

    public Challenge challengeClassmate() {
        return null;
    }

    public void completeAssignment() {

    }

    public void practiceProblems() {

    }

    public String viewStatistics() {
        return null;
    }


    public static Student createObject(String jsonString) {
        JSONObject fields;
        JSONObject reader;

        jsonString = jsonString.replace("[", "");
        jsonString = jsonString.replace("]", "");
        jsonString = jsonString.replace("&quot;", "\"");
        Log.d("Teacher.java", "Result: " + jsonString);

        try {
            reader = new JSONObject(jsonString);
            fields = new JSONObject(reader.getString("fields"));
            Student s = new Student();

            s.firstName = fields.getString("firstName");
            s.lastName = fields.getString("lastName");
            s.userID = fields.getString("userID");
            s.schoolName = fields.getString("schoolName");
            s.studentLevel =  fields.getDouble("studentLevel");
            int teacherID = fields.getInt("teacher");
            s.teacher = ServerInteractions.getStudentTeacher(teacherID);

            Log.d("Teacher.java", "Fields: " + reader.getString("fields"));
            return s;

        } catch (JSONException e) {
            Log.e("Teacher.java", e.getMessage());
        }
        return null;
    }

}
