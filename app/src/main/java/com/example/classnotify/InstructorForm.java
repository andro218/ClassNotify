package com.example.classnotify;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log; // Import Log for debugging
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class InstructorForm extends AppCompatActivity {

    public static final String INSTRUCTOR_SHARED_PREFS = "instructorPrefs"; // Shared Preferences file name
    public static final String INSTRUCTOR_LAST_NAME = "INSTRUCTOR_lastName"; // Key for last name
    public static final String INSTRUCTOR_FIRST_NAME = "INSTRUCTOR_firstName"; // Key for first name
    public static final String INSTRUCTOR_MIDDLE_NAME = "INSTRUCTOR_middleName"; // Key for middle name
    public static final String INSTRUCTOR_AGE = "INSTRUCTOR_age"; // Key for age
    public static final String INSTRUCTOR_ADDRESS = "INSTRUCTOR_address"; // Key for address
    public static final String INSTRUCTOR_SCHOOL = "INSTRUCTOR_school"; // Key for school
    public static final String INSTRUCTOR_DEPARTMENT = "INSTRUCTOR_department"; // Key for department

    private EditText lastNameEditText, firstNameEditText, midNameEditText, ageEdittext, addressEdittext, schoolEdittext, departmentEdittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_instructor_form);

        // Initialize EditText fields
        lastNameEditText = findViewById(R.id.lastName_text_instructor);
        firstNameEditText = findViewById(R.id.firstName_text_instructor);
        midNameEditText = findViewById(R.id.midName_text_instructor);
        ageEdittext = findViewById(R.id.age_text_instructor);
        addressEdittext = findViewById(R.id.address_text_instructor);
        schoolEdittext = findViewById(R.id.school_text_instructor);
        departmentEdittext = findViewById(R.id.department_text_instructor);

        loadData(); // Load data when the activity is created
    }

    public void openMainInstructor(View view) {
        saveData(); // Save data to Shared Preferences before navigating
        Intent intent = new Intent(this, InstructorActivity.class);
        startActivity(intent);
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(INSTRUCTOR_SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(INSTRUCTOR_LAST_NAME, lastNameEditText.getText().toString());
        editor.putString(INSTRUCTOR_FIRST_NAME, firstNameEditText.getText().toString());
        editor.putString(INSTRUCTOR_MIDDLE_NAME, midNameEditText.getText().toString());
        editor.putString(INSTRUCTOR_AGE, ageEdittext.getText().toString());
        editor.putString(INSTRUCTOR_ADDRESS, addressEdittext.getText().toString());
        editor.putString(INSTRUCTOR_SCHOOL, schoolEdittext.getText().toString());
        editor.putString(INSTRUCTOR_DEPARTMENT, departmentEdittext.getText().toString());

        editor.apply(); // Apply changes asynchronously
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(INSTRUCTOR_SHARED_PREFS, MODE_PRIVATE);

        String lastName = sharedPreferences.getString(INSTRUCTOR_LAST_NAME, "");
        String firstName = sharedPreferences.getString(INSTRUCTOR_FIRST_NAME, "");
        String middleName = sharedPreferences.getString(INSTRUCTOR_MIDDLE_NAME, "");
        String age = sharedPreferences.getString(INSTRUCTOR_AGE, "");
        String address = sharedPreferences.getString(INSTRUCTOR_ADDRESS, "");
        String school = sharedPreferences.getString(INSTRUCTOR_SCHOOL, "");
        String department = sharedPreferences.getString(INSTRUCTOR_DEPARTMENT, "");

        Log.d("InstructorForm", "Loading data...");
        if (lastNameEditText != null) {
            lastNameEditText.setText(lastName);
        } else {
            Log.e("InstructorForm", "lastNameEditText is null");
        }
        if (firstNameEditText != null) {
            firstNameEditText.setText(firstName);
        } else {
            Log.e("InstructorForm", "firstNameEditText is null");
        }
        if (midNameEditText != null) {
            midNameEditText.setText(middleName);
        } else {
            Log.e("InstructorForm", "midNameEditText is null");
        }
        if (ageEdittext != null) {
            ageEdittext.setText(age);
        } else {
            Log.e("InstructorForm", "ageEdittext is null");
        }
        if (addressEdittext != null) {
            addressEdittext.setText(address);
        } else {
            Log.e("InstructorForm", "addressEdittext is null");
        }
    }
}
