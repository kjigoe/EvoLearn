package com.joshkevingeoff.evolearn;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class ProblemSetActivity extends ActionBarActivity {

    TextView num1;
    TextView num2;
    TextView operation;
    ListView answersView;
    ProblemSet ps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_set);

        num1 = (TextView) findViewById(R.id.number1);
        num2 = (TextView) findViewById(R.id.number2);
        operation = (TextView) findViewById(R.id.operation);

        Problem p = ProblemGenerator.computeProblem();
        num1.setText(p.getNumberA()+"");
        num2.setText(p.getNumberB()+"");
        operation.setText(p.getOp().toString());
        int[] answers = p.getPossibleAnswers();

        answersView = (ListView) findViewById(R.id.answers);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.simple_row);
        char answerLetter = 'A';
        for(int i=0; i<answers.length; i++)
        {
            adapter.add(answerLetter+") "+answers[i]);
            answerLetter++;
        }
        answersView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_problem_set, menu);
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
