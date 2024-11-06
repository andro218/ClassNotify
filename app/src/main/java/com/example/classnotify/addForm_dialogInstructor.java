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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

public class addForm_dialogInstructor extends AppCompatActivity {

    private EditText subjectNameEditText, subjectCodeEditText, blockAndCourseEditText, instructorEditText, roomEditText;
    private Spinner fromTimeSpinner, toTimeSpinner;
    private CheckBox monCheckbox, tueCheckbox, wedCheckbox, thuCheckbox, friCheckbox, satCheckbox, sunCheckbox;
    private TextView[][] weekTextViews; // 2D array to hold TextViews for each day and time slot
    private int selectedColor; // Color variable to store the chosen color
    private Button pickColorButton; // Button to trigger color selection

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_form_dialog_instructor);

        // Initialize fields
        subjectNameEditText = findViewById(R.id.instructor_subject_name);
        subjectCodeEditText = findViewById(R.id.instructor_subject_code);
        blockAndCourseEditText = findViewById(R.id.instructor_block_course);
        fromTimeSpinner = findViewById(R.id.instructor_fromTimeSpinner);
        toTimeSpinner = findViewById(R.id.instructor_toTimeSpinner);
        roomEditText = findViewById(R.id.instructor_room);
        monCheckbox = findViewById(R.id.instructor_monCheckbox);
        tueCheckbox = findViewById(R.id.instructor_tueCheckbox);
        wedCheckbox = findViewById(R.id.instructor_wedCheckbox);
        thuCheckbox = findViewById(R.id.instructor_thuCheckbox);
        friCheckbox = findViewById(R.id.instructor_friCheckbox);
        satCheckbox = findViewById(R.id.instructor_satCheckbox);
        sunCheckbox = findViewById(R.id.instructor_sunCheckbox);
        pickColorButton = findViewById(R.id.instructor_colorPickerButton); // Button to pick color

        // Initialize the TextViews for the week table
        initializeWeekTextViews();

        selectedColor = Color.WHITE; // Set default color to light blue

        // Populate the Spinners with time options
        setupSpinners();

        // Add TextWatcher for input fields
        subjectNameEditText.addTextChangedListener(new SimpleTextWatcher());
        subjectCodeEditText.addTextChangedListener(new SimpleTextWatcher());

        // Set up the color picker button
        pickColorButton.setOnClickListener(v -> showColorPickerDialog());
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

        // Repeat the same for tue, wed, thu, fri, sat, sun...

        // (The rest of the initialization code remains unchanged)
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

    private void showColorPickerDialog() {
        // Inflate the custom layout for the color picker dialog
        View colorPickerView = getLayoutInflater().inflate(R.layout.dialog_color_picker_week, null);

        // Create the AlertDialog and set the inflated view
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick a Color");
        builder.setView(colorPickerView);

        // Find the color views by ID
        View color1 = colorPickerView.findViewById(R.id.color1);
        View color2 = colorPickerView.findViewById(R.id.color2);
        View color3 = colorPickerView.findViewById(R.id.color3);
        View color4 = colorPickerView.findViewById(R.id.color4);

        // Set up the AlertDialog
        final AlertDialog colorDialog = builder.create();

        // Set onClickListeners for color views
        color1.setOnClickListener(v -> {
            selectedColor = Color.parseColor("#FF885B");
            colorDialog.dismiss();  // Close the dialog
        });

        color2.setOnClickListener(v -> {
            selectedColor = Color.parseColor("#FFE5CF");
            colorDialog.dismiss();  // Close the dialog
        });

        color3.setOnClickListener(v -> {
            selectedColor = Color.parseColor("#557C56");
            colorDialog.dismiss();  // Close the dialog
        });

        color4.setOnClickListener(v -> {
            selectedColor = Color.parseColor("#33372C");
            colorDialog.dismiss();  // Close the dialog
        });

        // Add a "Cancel" button or any other action button
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());  // Dismiss the dialog if "Cancel" is clicked

        // Show the dialog
        colorDialog.show();
    }


    // Method to save class information and pass data to another activity
    public void saveClassInstructor(View view) {
        String subjectName = subjectNameEditText.getText().toString();
        String subjectCode = subjectCodeEditText.getText().toString();
        String blockAndCourse = blockAndCourseEditText.getText().toString();
        instructorEditText = findViewById(R.id.instructor);
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
        formDataInstructor form = new formDataInstructor(
                subjectName, subjectCode, blockAndCourse, instructor, room,
                fromTime, toTime, mon, tue, wed, thu, fri, sat, sun
        );

        // Prepare the intent to start WeekTable
        Intent intent = new Intent(this, WeekTableInstructor.class);
        intent.putExtra("SUBJECT_NAME", form.getSubjectName());
        intent.putExtra("SUBJECT_CODE", form.getSubjectCode());
        intent.putExtra("BLOCK_COURSE", form.getBlockCourse());
        intent.putExtra("FROM_TIME", form.getFromTimeSpinnerSelection());
        intent.putExtra("TO_TIME", form.getToTimeSpinnerSelection());
        intent.putExtra("ROOM", form.getRoom());
        intent.putExtra("WEEKDAYS", new boolean[]{mon, tue, wed, thu, fri, sat, sun});
        intent.putExtra("SELECTED_COLOR", selectedColor);

        startActivity(intent);

        // Optionally, continue with the TextView population logic if needed
        populateTextViews(new boolean[]{mon, tue, wed, thu, fri, sat, sun}, fromTime, toTime, selectedColor, subjectName, subjectCode, instructor, room);
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
                        weekTextViews[day][col].setBackgroundColor(color); // Set background color for selected slots
                    }
                }
            }
        }
    }

    // Method to convert time string to column index (you need to implement this logic based on your time format)
    private int getColumnIndex(String time) {
        String[] times = getResources().getStringArray(R.array.from_time_items);
        for (int i = 0; i < times.length; i++) {
            if (times[i].equals(time)) {
                return i;
            }
        }
        return -1; // Invalid time
    }

    private static class SimpleTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable editable) {}
    }


}
