package com.example.classnotify;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.colorpicker.ColorPickerDialog;
import com.github.dhaval2404.colorpicker.listener.ColorListener;

public class addForm_dialog extends AppCompatActivity {

    private EditText subjectNameEditText, subjectCodeEditText, blockAndCourseEditText, instructorEditText, roomEditText;
    private Spinner fromTimeSpinner, toTimeSpinner;
    private CheckBox monCheckbox, tueCheckbox, wedCheckbox, thuCheckbox, friCheckbox, satCheckbox, sunCheckbox;
    private TextView[][] weekTextViews; // 2D array to hold TextViews for each day and time slot
    private int selectedColor; // Color variable to store the chosen color

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_form_dialog);

        // Initialize fields
        subjectNameEditText = findViewById(R.id.subject_name);
        subjectCodeEditText = findViewById(R.id.subject_code);
        blockAndCourseEditText = findViewById(R.id.block_course);
        instructorEditText = findViewById(R.id.instructor);
        fromTimeSpinner = findViewById(R.id.fromTimeSpinner);
        toTimeSpinner = findViewById(R.id.toTimeSpinner);
        roomEditText = findViewById(R.id.room);
        monCheckbox = findViewById(R.id.monCheckbox);
        tueCheckbox = findViewById(R.id.tueCheckbox);
        wedCheckbox = findViewById(R.id.wedCheckbox);
        thuCheckbox = findViewById(R.id.thuCheckbox);
        friCheckbox = findViewById(R.id.friCheckbox);
        satCheckbox = findViewById(R.id.satCheckbox);
        sunCheckbox = findViewById(R.id.sunCheckbox);

        // Initialize the TextViews for the week table
        initializeWeekTextViews();

        selectedColor = Color.WHITE; // Set default color to light blue

        // Populate the Spinners with time options
        setupSpinners();

        // Setup Color Picker Button
        findViewById(R.id.colorPickerButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker(); // Open color picker when the button is clicked
            }
        });

        // Add TextWatcher for input fields
        subjectNameEditText.addTextChangedListener(new SimpleTextWatcher());
        subjectCodeEditText.addTextChangedListener(new SimpleTextWatcher());
    }

    private void initializeWeekTextViews() {
        // Initialize the 2D array to hold TextViews for 7 days and 18 time slots each
        weekTextViews = new TextView[7][18];

        // Directly assign TextViews to the array
        weekTextViews[0] = new TextView[]{
                findViewById(R.id.monCol1), findViewById(R.id.monCol2), findViewById(R.id.monCol3),
                findViewById(R.id.monCol4), findViewById(R.id.monCol5), findViewById(R.id.monCol6),
                findViewById(R.id.monCol7), findViewById(R.id.monCol8), findViewById(R.id.monCol9),
                findViewById(R.id.monCol10), findViewById(R.id.monCol11), findViewById(R.id.monCol12),
                findViewById(R.id.monCol13), findViewById(R.id.monCol14), findViewById(R.id.monCol15),
                findViewById(R.id.monCol16), findViewById(R.id.monCol17), findViewById(R.id.monCol18)
        };

        weekTextViews[1] = new TextView[]{
                findViewById(R.id.tueCol1), findViewById(R.id.tueCol2), findViewById(R.id.tueCol3),
                findViewById(R.id.tueCol4), findViewById(R.id.tueCol5), findViewById(R.id.tueCol6),
                findViewById(R.id.tueCol7), findViewById(R.id.tueCol8), findViewById(R.id.tueCol9),
                findViewById(R.id.tueCol10), findViewById(R.id.tueCol11), findViewById(R.id.tueCol12),
                findViewById(R.id.tueCol13), findViewById(R.id.tueCol14), findViewById(R.id.tueCol15),
                findViewById(R.id.tueCol16), findViewById(R.id.tueCol17), findViewById(R.id.tueCol18)
        };

        weekTextViews[2] = new TextView[]{
                findViewById(R.id.wedCol1), findViewById(R.id.wedCol2), findViewById(R.id.wedCol3),
                findViewById(R.id.wedCol4), findViewById(R.id.wedCol5), findViewById(R.id.wedCol6),
                findViewById(R.id.wedCol7), findViewById(R.id.wedCol8), findViewById(R.id.wedCol9),
                findViewById(R.id.wedCol10), findViewById(R.id.wedCol11), findViewById(R.id.wedCol12),
                findViewById(R.id.wedCol13), findViewById(R.id.wedCol14), findViewById(R.id.wedCol15),
                findViewById(R.id.wedCol16), findViewById(R.id.wedCol17), findViewById(R.id.wedCol18)
        };

        weekTextViews[3] = new TextView[]{
                findViewById(R.id.thuCol1), findViewById(R.id.thuCol2), findViewById(R.id.thuCol3),
                findViewById(R.id.thuCol4), findViewById(R.id.thuCol5), findViewById(R.id.thuCol6),
                findViewById(R.id.thuCol7), findViewById(R.id.thuCol8), findViewById(R.id.thuCol9),
                findViewById(R.id.thuCol10), findViewById(R.id.thuCol11), findViewById(R.id.thuCol12),
                findViewById(R.id.thuCol13), findViewById(R.id.thuCol14), findViewById(R.id.thuCol15),
                findViewById(R.id.thuCol16), findViewById(R.id.thuCol17), findViewById(R.id.thuCol18)
        };

        weekTextViews[4] = new TextView[]{
                findViewById(R.id.friCol1), findViewById(R.id.friCol2), findViewById(R.id.friCol3),
                findViewById(R.id.friCol4), findViewById(R.id.friCol5), findViewById(R.id.friCol6),
                findViewById(R.id.friCol7), findViewById(R.id.friCol8), findViewById(R.id.friCol9),
                findViewById(R.id.friCol10), findViewById(R.id.friCol11), findViewById(R.id.friCol12),
                findViewById(R.id.friCol13), findViewById(R.id.friCol14), findViewById(R.id.friCol15),
                findViewById(R.id.friCol16), findViewById(R.id.friCol17), findViewById(R.id.friCol18)
        };

        weekTextViews[5] = new TextView[]{
                findViewById(R.id.satCol1), findViewById(R.id.satCol2), findViewById(R.id.satCol3),
                findViewById(R.id.satCol4), findViewById(R.id.satCol5), findViewById(R.id.satCol6),
                findViewById(R.id.satCol7), findViewById(R.id.satCol8), findViewById(R.id.satCol9),
                findViewById(R.id.satCol10), findViewById(R.id.satCol11), findViewById(R.id.satCol12),
                findViewById(R.id.satCol13), findViewById(R.id.satCol14), findViewById(R.id.satCol15),
                findViewById(R.id.satCol16), findViewById(R.id.satCol17), findViewById(R.id.satCol18)
        };

        weekTextViews[6] = new TextView[]{
                findViewById(R.id.sunCol1), findViewById(R.id.sunCol2), findViewById(R.id.sunCol3),
                findViewById(R.id.sunCol4), findViewById(R.id.sunCol5), findViewById(R.id.sunCol6),
                findViewById(R.id.sunCol7), findViewById(R.id.sunCol8), findViewById(R.id.sunCol9),
                findViewById(R.id.sunCol10), findViewById(R.id.sunCol11), findViewById(R.id.sunCol12),
                findViewById(R.id.sunCol13), findViewById(R.id.sunCol14), findViewById(R.id.sunCol15),
                findViewById(R.id.sunCol16), findViewById(R.id.sunCol17), findViewById(R.id.sunCol18)
        };
    }


    private void setupSpinners() {
        // Load the string arrays for time options
        String[] fromTimeOptions = getResources().getStringArray(R.array.from_time_items);
        String[] toTimeOptions = getResources().getStringArray(R.array.to_time_items);

        // Set adapters for spinners
        ArrayAdapter<String> fromAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, fromTimeOptions);
        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromTimeSpinner.setAdapter(fromAdapter);

        ArrayAdapter<String> toAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, toTimeOptions);
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toTimeSpinner.setAdapter(toAdapter);
    }

    // Method to save class information and pass data to another activity
    public void saveClass(View view) {
        String subjectName = subjectNameEditText.getText().toString();
        String subjectCode = subjectCodeEditText.getText().toString();
        String blockAndCourse = blockAndCourseEditText.getText().toString();
        String instructor = instructorEditText.getText().toString();
        String fromTime = fromTimeSpinner.getSelectedItem().toString();
        String toTime = toTimeSpinner.getSelectedItem().toString();
        String room = roomEditText.getText().toString();

        if (subjectName.isEmpty() || subjectCode.isEmpty()) {
            Toast.makeText(this, "Subject Name and Code cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean mon = monCheckbox.isChecked();
        boolean tue = tueCheckbox.isChecked();
        boolean wed = wedCheckbox.isChecked();
        boolean thu = thuCheckbox.isChecked();
        boolean fri = friCheckbox.isChecked();
        boolean sat = satCheckbox.isChecked();
        boolean sun = sunCheckbox.isChecked();

        // Create a new formData object and pass the input values
        formData form = new formData(
                subjectName, subjectCode, blockAndCourse, instructor, room,
                fromTime, toTime, mon, tue, wed, thu, fri, sat, sun
        );

        // Prepare the intent to start WeekTable
        Intent intent = new Intent(this, WeekTable.class);
        intent.putExtra("SUBJECT_NAME", form.getSubjectName());
        intent.putExtra("SUBJECT_CODE", form.getSubjectCode());
        intent.putExtra("BLOCK_COURSE", form.getBlockCourse());
        intent.putExtra("INSTRUCTOR", form.getInstructor());
        intent.putExtra("FROM_TIME", form.getFromTimeSpinnerSelection());
        intent.putExtra("TO_TIME", form.getToTimeSpinnerSelection());
        intent.putExtra("ROOM", form.getRoom());
        intent.putExtra("WEEKDAYS", new boolean[]{mon, tue, wed, thu, fri, sat, sun});
        intent.putExtra("SELECTED_COLOR", selectedColor);

        startActivity(intent);

        // Optionally, continue with the TextView population logic if needed
        populateTextViews(new boolean[]{mon, tue, wed, thu, fri, sat, sun}, fromTime, toTime, selectedColor, subjectName, subjectCode, instructor, room);
    }

    private void openColorPicker() {
        new ColorPickerDialog
                .Builder(this)
                .setTitle("Pick a Color")
                .setDefaultColor(selectedColor)
                .setColorListener(new ColorListener() {
                    @Override
                    public void onColorSelected(int color, String hexColor) {
                        selectedColor = color; // Save the selected color
                        applyColorToCheckedDays(); // Apply color to checked days
                    }
                })
                .show();
    }

    private void applyColorToCheckedDays() {
        if (monCheckbox.isChecked()) {
            for (TextView textView : weekTextViews[0]) {
                textView.setBackgroundColor(selectedColor);
            }
        }
        if (tueCheckbox.isChecked()) {
            for (TextView textView : weekTextViews[1]) {
                textView.setBackgroundColor(selectedColor);
            }
        }
        if (wedCheckbox.isChecked()) {
            for (TextView textView : weekTextViews[2]) {
                textView.setBackgroundColor(selectedColor);
            }
        }
        if (thuCheckbox.isChecked()) {
            for (TextView textView : weekTextViews[3]) {
                textView.setBackgroundColor(selectedColor);
            }
        }
        if (friCheckbox.isChecked()) {
            for (TextView textView : weekTextViews[4]) {
                textView.setBackgroundColor(selectedColor);
            }
        }
        if (satCheckbox.isChecked()) {
            for (TextView textView : weekTextViews[5]) {
                textView.setBackgroundColor(selectedColor);
            }
        }
        if (sunCheckbox.isChecked()) {
            for (TextView textView : weekTextViews[6]) {
                textView.setBackgroundColor(selectedColor);
            }
        }
    }

    // Method to populate TextViews based on selected times and days
    private void populateTextViews(boolean[] weekdays, String fromTime, String toTime, int color, String subjectName, String subjectCode, String instructor, String room) {
        int fromColumn = getColumnIndex(fromTime); // Get the index for the "From" time
        int toColumn = getColumnIndex(toTime); // Get the index for the "To" time

        // Check if the indices are valid
        if (fromColumn == -1 || toColumn == -1 || fromColumn >= toColumn) {
            Toast.makeText(this, "Invalid time selected", Toast.LENGTH_SHORT).show();
            return; // Exit if time is invalid
        }

        // Populate the TextViews for the selected days
        for (int day = 0; day < weekdays.length; day++) {
            if (weekdays[day]) { // If the checkbox for the day is checked
                for (int col = fromColumn; col < toColumn; col++) {
                    if (weekTextViews[day][col] != null) { // Check for null
                        weekTextViews[day][col].setText(String.format("%s\n%s\n%s\n%s", subjectName, subjectCode, instructor, room));
                        weekTextViews[day][col].setBackgroundColor(color);
                    } else {
                        Log.e("addForm_dialog", "TextView for day " + day + " column " + col + " is null");
                    }
                }
            }
        }
    }



    // Get the row index based on the selected time
    private int getColumnIndex(String time) {
        String[] timeSlots = getResources().getStringArray(R.array.from_time_items); // Adjust if necessary

        // Loop through the time slots to find the index
        for (int i = 0; i < timeSlots.length; i++) {
            if (timeSlots[i].equals(time)) {
                return i; // Return the column index corresponding to the time (0-based index for table columns)
            }
        }
        return -1; // Return -1 if time not found
    }


    private class SimpleTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void afterTextChanged(Editable editable) {
            // Any additional actions after text changes can go here
        }
    }
}
