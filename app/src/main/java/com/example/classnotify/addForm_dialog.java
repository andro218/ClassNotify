package com.example.classnotify;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class addForm_dialog extends AppCompatActivity {

    private EditText subjectName, subjectCode, blockCourse, instructor, fromTime, toTime, room;
    private Button saveButton;
    private RadioButton monButton, tueButton, wedButton, thuButton, friButton, satButton, sunButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_form_dialog); // Ensure this layout exists

        // Initialize EditText fields
        subjectName = findViewById(R.id.subject_name);
        subjectCode = findViewById(R.id.subject_code);
        blockCourse = findViewById(R.id.block_course);
        instructor = findViewById(R.id.instructor);
        fromTime = findViewById(R.id.fromTime);
        toTime = findViewById(R.id.toTime);
        room = findViewById(R.id.room);

        // Initialize RadioButtons
        monButton = findViewById(R.id.monButton);
        tueButton = findViewById(R.id.tueButton);
        wedButton = findViewById(R.id.wedButton);
        thuButton = findViewById(R.id.thuButton);
        friButton = findViewById(R.id.friButton);
        satButton = findViewById(R.id.satButton);
        sunButton = findViewById(R.id.sunButton);

        // Initialize Save Button
        saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        // Set up Time Picker for "From" and "To" EditText fields
        setupTimePickers();
    }

    private void setupTimePickers() {
        fromTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker(fromTime);
            }
        });

        toTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker(toTime);
            }
        });
    }

    private void showTimePicker(final EditText editText) {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, selectedHour, selectedMinute) -> {
            String time = String.format("%02d:%02d", selectedHour, selectedMinute);
            editText.setText(time);
        }, hour, minute, true); // Use 24-hour format
        timePickerDialog.show();
    }

    private void saveData() {
        // Retrieve data from EditText fields
        String subject = subjectName.getText().toString();
        String code = subjectCode.getText().toString();
        String block = blockCourse.getText().toString();
        String instructorName = instructor.getText().toString();
        String from = fromTime.getText().toString();
        String to = toTime.getText().toString();
        String roomNumber = room.getText().toString();

        // Validate inputs (basic example)
        if (subject.isEmpty() || code.isEmpty() || block.isEmpty() || instructorName.isEmpty()) {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Determine which day is selected using if-else
        String selectedDay = getSelectedDay();

        // Show the selected day
        Toast.makeText(this, "Selected Day: " + selectedDay, Toast.LENGTH_SHORT).show();

        // Here you can handle the data, e.g., save to a database or send to another activity
        // For now, let's just show a confirmation message
        Toast.makeText(this, "Data saved successfully!", Toast.LENGTH_SHORT).show();

        // Clear the fields after saving
        clearFields();
    }

    private String getSelectedDay() {
        if (monButton.isChecked()) {
            return "Monday";
        } else if (tueButton.isChecked()) {
            return "Tuesday";
        } else if (wedButton.isChecked()) {
            return "Wednesday";
        } else if (thuButton.isChecked()) {
            return "Thursday";
        } else if (friButton.isChecked()) {
            return "Friday";
        } else if (satButton.isChecked()) {
            return "Saturday";
        } else if (sunButton.isChecked()) {
            return "Sunday";
        } else {
            return "No day selected"; // Fallback if no day is selected
        }
    }

    private void clearFields() {
       /* subjectName.setText("");
        subjectCode.setText("");
        blockCourse.setText("");
        instructor.setText("");
        fromTime.setText("");
        toTime.setText("");
        room.setText("");

        // Clear radio button selection
        monButton.setChecked(false);
        tueButton.setChecked(false);
        wedButton.setChecked(false);
        thuButton.setChecked(false);
        friButton.setChecked(false);
        satButton.setChecked(false);
        sunButton.setChecked(false);*/
    }
}