package com.example.classnotify;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

    private EditText subjectNameEditText;
    private EditText subjectCodeEditText;
    private EditText blockAndCourseEditText;
    private EditText instructorEditText;
    private Spinner fromTimeSpinner;
    private Spinner toTimeSpinner;
    private EditText roomEditText;
    private CheckBox monCheckbox, tueCheckbox, wedCheckbox, thuCheckbox, friCheckbox, satCheckbox, sunCheckbox;
    private TextView monCol1, tueCol1, wedCol1, thuCol1, friCol1, satCol1, sunCol1;
    private int selectedColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_form_dialog);

        // Initialize the form fields
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
        monCol1 = findViewById(R.id.monCol1);
        tueCol1 = findViewById(R.id.tueCol1);
        wedCol1 = findViewById(R.id.wedCol1);
        thuCol1 = findViewById(R.id.thuCol1);
        friCol1 = findViewById(R.id.friCol1);
        satCol1 = findViewById(R.id.satCol1);
        sunCol1 = findViewById(R.id.sunCol1);

        // Initialize the selected color to a default value
        selectedColor = Color.BLACK;

        // Populate the Spinners
        setupSpinners();

        // Add TextWatcher for subject name and code
        subjectNameEditText.addTextChangedListener(new SimpleTextWatcher());
        subjectCodeEditText.addTextChangedListener(new SimpleTextWatcher());

        // Set up the color picker button
        findViewById(R.id.colorPickerButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker(); // Call the method to open the color picker
            }
        });
    }

    private void setupSpinners() {
        // Load the string arrays from resources
        String[] fromTimeOptions = getResources().getStringArray(R.array.from_time_items);
        String[] toTimeOptions = getResources().getStringArray(R.array.to_time_items);

        // Create an ArrayAdapter for the "From" time spinner
        ArrayAdapter<String> fromAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, fromTimeOptions);
        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromTimeSpinner.setAdapter(fromAdapter); // Set the adapter to the "From" spinner

        // Create an ArrayAdapter for the "To" time spinner
        ArrayAdapter<String> toAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, toTimeOptions);
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toTimeSpinner.setAdapter(toAdapter); // Set the adapter to the "To" spinner
    }

    public void saveClass(View view) {
        // Retrieve the input values from the form
        String subjectName = subjectNameEditText.getText().toString();
        String subjectCode = subjectCodeEditText.getText ().toString();
        String blockAndCourse = blockAndCourseEditText.getText().toString();
        String instructor = instructorEditText.getText().toString();
        String fromTime = fromTimeSpinner.getSelectedItem().toString();
        String toTime = toTimeSpinner.getSelectedItem().toString();
        String room = roomEditText.getText().toString();

        // Validate inputs
        if (subjectName.isEmpty() || subjectCode.isEmpty()) {
            Toast.makeText(this, "Subject Name and Code cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a boolean array to store the weekdays
        boolean[] weekdays = new boolean[7];
        weekdays[0] = monCheckbox.isChecked();
        weekdays[1] = tueCheckbox.isChecked();
        weekdays[2] = wedCheckbox.isChecked();
        weekdays[3] = thuCheckbox.isChecked();
        weekdays[4] = friCheckbox.isChecked();
        weekdays[5] = satCheckbox.isChecked();
        weekdays[6] = sunCheckbox.isChecked();

        // Display the selected color (you can use it as needed)
        String colorHex = String.format("#%06X", (0xFFFFFF & selectedColor));
        Toast.makeText(this, "Class saved successfully! Color: " + colorHex, Toast.LENGTH_SHORT).show();

        // Create an Intent to start the WeekTable activity
        Intent intent = new Intent(this, WeekTable.class);
        intent.putExtra("SUBJECT_NAME", subjectName);
        intent.putExtra("SUBJECT_CODE", subjectCode);
        intent.putExtra("BLOCK_COURSE", blockAndCourse);
        intent.putExtra("INSTRUCTOR", instructor);
        intent.putExtra("FROM_TIME", fromTime);
        intent.putExtra("TO_TIME", toTime);
        intent.putExtra("ROOM", room);
        intent.putExtra("WEEKDAYS", weekdays);
        intent.putExtra("SELECTED_COLOR", selectedColor);

        // Start the WeekTable activity
        startActivity(intent);
    }

    private void updateTextViews() {
        String inputText = subjectNameEditText.getText().toString() + " \n" + subjectCodeEditText.getText().toString();

        // Reset all TextViews
        monCol1.setText("");
        tueCol1.setText("");
        wedCol1.setText("");
        thuCol1.setText("");
        friCol1.setText("");
        satCol1.setText("");
        sunCol1.setText("");

        // Update the TextViews based on CheckBox states
        if (monCheckbox.isChecked()) monCol1.setText(inputText);
        if (tueCheckbox.isChecked()) tueCol1.setText(inputText);
        if (wedCheckbox.isChecked()) wedCol1.setText(inputText);
        if (thuCheckbox.isChecked()) thuCol1.setText(inputText);
        if (friCheckbox.isChecked()) friCol1.setText(inputText);
        if (satCheckbox.isChecked()) satCol1.setText(inputText);
        if (sunCheckbox.isChecked()) sunCol1.setText(inputText);
    }

    private void openColorPicker() {
        new ColorPickerDialog
                .Builder(this)
                .setTitle("Pick a Color")
                .setDefaultColor(selectedColor)
                .setColorListener(new ColorListener() {
                    @Override
                    public void onColorSelected(int color, String hexColor) {
                        selectedColor = color;
                        // Update the background color of the TextViews based on CheckBox states
                        if (monCheckbox.isChecked()) monCol1.setBackgroundColor(selectedColor);
                        if (tueCheckbox.isChecked()) tueCol1.setBackgroundColor(selectedColor);
                        if (wedCheckbox.isChecked()) wedCol1.setBackgroundColor(selectedColor);
                        if (thuCheckbox.isChecked()) thuCol1.setBackgroundColor(selectedColor);
                        if (friCheckbox.isChecked()) friCol1.setBackgroundColor(selectedColor);
                        if (satCheckbox.isChecked()) satCol1.setBackgroundColor(selectedColor);
                        if (sunCheckbox.isChecked()) sunCol1.setBackgroundColor(selectedColor);
                    }
                })
                .show();
    }

    private class SimpleTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            // Enable/Disable the save button based on input validity
            // You can add more conditions as needed
            boolean isValid = !subjectNameEditText.getText().toString().isEmpty() && !subjectCodeEditText.getText().toString().isEmpty();
            findViewById(R.id.save_button).setEnabled(isValid);
        }
    }
}