package com.joshkevingeoff.evolearn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by josh on 4/10/15.
 */
public class AssignmentListAdapter extends ArrayAdapter<String>
{
    private final Context context;
    private final ArrayList<Assignment> values;

    public AssignmentListAdapter(Context context, ArrayList<Assignment> values) {
        super(context, R.layout.assignment_row);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.assignment_row, parent, false);
        TextView assignmentNameView = (TextView) rowView.findViewById(R.id.assignmentName);
        TextView assignmentDueDateView = (TextView) rowView.findViewById(R.id.assignmentDueDate);
        TextView assignmentProgressView = (TextView) rowView.findViewById(R.id.assignmentProgress);

        Assignment current = values.get(position);
        assignmentNameView.setText(current.getAssignmentName());
        assignmentProgressView.setText(String.format("%d / %d", 0, current.getMinProblems()));
        String formattedDate = new SimpleDateFormat("mm/dd").format(current.getDueDate());
        assignmentDueDateView.setText(formattedDate);

        return rowView;
    }
}