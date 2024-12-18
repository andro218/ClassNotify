package com.example.classnotify;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WeekTableInstructor extends AppCompatActivity {

    private TableRow selectedRow = null;  // Track the selected row for editing or deleting
    private TableLayout tableLayout;      // TableLayout that holds the week table
    private boolean isEditMode = false;   // Flag to manage if the user is in edit mode

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_table);

        // Retrieve the data passed from addForm_dialog
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("SUBJECT_NAME")) {
            String subjectName = intent.getStringExtra("SUBJECT_NAME");
            String subjectCode = intent.getStringExtra("SUBJECT_CODE");
            String blockCourse = intent.getStringExtra("BLOCK_COURSE");
            String instructor = intent.getStringExtra("INSTRUCTOR");
            String fromTime = intent.getStringExtra("FROM_TIME");
            String toTime = intent.getStringExtra("TO_TIME");
            String room = intent.getStringExtra("ROOM");
            boolean[] weekdays = intent.getBooleanArrayExtra("WEEKDAYS");
            int selectedColor = intent.getIntExtra("SELECTED_COLOR", Color.BLACK);

            // Format the class information text
            String classInfo = subjectName + " (" + subjectCode + ")\n" +
                    "Instructor: " + instructor + "\n" +
                    "Room: " + room;

            // Update the TextViews for the corresponding weekdays
            updateTextViews(weekdays, classInfo, selectedColor, fromTime, toTime);
        }

        // Set up the TableLayout and row selection
        tableLayout = findViewById(R.id.table_data); // Ensure tableLayout is properly initialized
        setupTableRows(); // Set up click listeners for table rows

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFabMenu(view);
            }
        });
    }

    private void setupTableRows() {
        // Loop through all rows in the TableLayout and set an OnClickListener
        for (int i = 0; i < tableLayout.getChildCount(); i++) {
            TableRow row = (TableRow) tableLayout.getChildAt(i);
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isEditMode) { // Only allow row selection if in edit mode
                        // Select the clicked row
                        selectedRow = (TableRow) v;
                        // You could also change the background color of the selected row for visual feedback
                        v.setBackgroundColor(Color.LTGRAY);
                        Toast.makeText(WeekTableInstructor.this, "Row selected for editing", Toast.LENGTH_SHORT).show();
                    } else {
                        // Optionally, notify the user they need to enable edit mode first
                        Toast.makeText(WeekTableInstructor.this, "Please enable edit mode to select a row", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void updateTextViews(boolean[] weekdays, String classInfo, int selectedColor, String fromTime, String toTime) {
        int fromColumn = getColumnIndex(fromTime); // Get the index for the "From" time
        int toColumn = getColumnIndex(toTime); // Get the index for the "To" time

        // Check if the indices are valid
        if (fromColumn == -1 || toColumn == -1 || fromColumn >= toColumn) {
            Toast.makeText(this, "Invalid time selected", Toast.LENGTH_SHORT).show();
            return; // Exit if time is invalid
        }

        // Loop through each day and update TextViews based on selected time columns
        for (int day = 0; day < weekdays.length; day++) {
            if (weekdays[day]) { // If the checkbox for the day is checked
                for (int col = fromColumn; col < toColumn; col++) {
                    int resId = getResources().getIdentifier(getDayString(day) + "Col" + (col + 1), "id", getPackageName());
                    TextView textView = findViewById(resId);
                    if (textView != null) {
                        textView.setText(classInfo);
                        textView.setBackgroundColor(selectedColor); // Set background color
                    }
                }
            }
        }
    }

    private String getDayString(int day) {
        switch (day) {
            case 0: return "mon";
            case 1: return "tue";
            case 2: return "wed";
            case 3: return "thu";
            case 4: return "fri";
            case 5: return "sat";
            case 6: return "sun";
            default: return "";
        }
    }

    private int getColumnIndex(String time) {
        String[] timeSlots = getResources().getStringArray(R.array.from_time_items); // Ensure this array has the correct time slots

        // Loop through the time slots to find the index
        for (int i = 0; i < timeSlots.length; i++) {
            if (timeSlots[i].equals(time)) {
                return i; // Return the column index corresponding to the time (0-based index for table columns)
            }
        }
        return -1; // Return -1 if time not found
    }

    private void showFabMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.fab_popup_menu, popupMenu.getMenu());

        // Set up menu item click listeners
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_add_dialog) {
                    // Navigate to addForm_dialog
                    Intent intent = new Intent(WeekTableInstructor.this, addForm_dialogInstructor.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.action_edit_dialog) {
                    // Enable edit mode
                    isEditMode = true; // Allow row selection for editing
                    Toast.makeText(WeekTableInstructor.this, "Edit mode enabled", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.action_delete_dialog) {
                    handleDeleteAction();
                    return true;
                } else {
                    return false; // Return false for unhandled menu items
                }
            }
        });

        popupMenu.show(); // Show the menu
    }

    private void handleDeleteAction() {
        if (selectedRow != null) {
            // Confirm deletion
            new AlertDialog.Builder(this)
                    .setTitle("Confirm Deletion")
                    .setMessage("Are you sure you want to delete this entry?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        // Remove the selected row
                        tableLayout.removeView(selectedRow);
                        selectedRow = null; // Reset the selected row after deletion
                        Toast.makeText(WeekTableInstructor.this, "Entry deleted", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null) // Dismiss the dialog
                    .show();
        } else {
            // Notify the user to select a row first
            Toast.makeText(this, "Please select a row to delete", Toast.LENGTH_SHORT).show();
        }
    }
}
