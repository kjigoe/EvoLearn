package com.joshkevingeoff.evolearn;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;


public class ViewStudentAccount extends ActionBarActivity {

    TextView userName;
    TextView schoolName;
    TextView studentLevel;
    TextView teacherName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_account);

        userName = (TextView) findViewById(R.id.textView8);
        userName.setText("Getter for user name");

        schoolName = (TextView) findViewById(R.id.textView14);
        schoolName.setText("Getter method for school name");

        studentLevel = (TextView) findViewById(R.id.textView15);
        studentLevel.setText("Getter method for student level");

        teacherName = (TextView) findViewById(R.id.textView16);
        teacherName.setText("getTeacherObject.getfirstName");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_student_account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
