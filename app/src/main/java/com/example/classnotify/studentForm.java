package com.example.classnotify;

import android.content.Intent;
import android.content.SharedPreferences; // Import SharedPreferences
import android.os.Bundle;
import android.view.View;
import android.widget.EditText; // Import EditText

import androidx.appcompat.app.AppCompatActivity;

public class studentForm extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs"; // Shared Preferences file name
    public static final String LAST_NAME = "lastName"; // Key for last name
    public static final String FIRST_NAME = "firstName"; // Key for first name
    public static final String MIDDLE_NAME = "middleName";
    // ... other keys for form fields
    public static final String AGE = "age";
    public static final String ADDRESS = "address"; // Key for address
    public static final String SCHOOL = "school"; // Key for school
    public static final String COURSE = "course"; // Key for course
    public static final String DEPARTMENT = "department"; // Key for department

    private EditText lastNameEditText, firstNameEditText, midNameEditText, ageEdittext, addressEdittext, schoolEdittext, courseEdittext, departmentEdittext; // ... other EditText fields

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);

        lastNameEditText   = findViewById(R.id.lastName_text);        // Initialize last name EditText
        firstNameEditText  = findViewById(R.id.firstName_text);       // Initialize first name EditText
        midNameEditText = findViewById(R.id.midName_text);         // Initialize middle name EditText
        ageEdittext        = findViewById(R.id.age_text);             // Initialize age EditText
        addressEdittext    = findViewById(R.id.address_text);         // Initialize address EditText
        schoolEdittext    = findViewById(R.id.school_text);          // Initialize school EditText
        courseEdittext     = findViewById(R.id.course_text);          // Initialize course EditText
        departmentEdittext = findViewById(R.id.department_text);      // Initialize department EditText

        loadData();

    }

    public void openMain(View view) {
        saveData(); // Save data to Shared Preferences
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(LAST_NAME, lastNameEditText.getText().toString());
        editor.putString(FIRST_NAME, firstNameEditText.getText().toString());
        // ... save other form fields
        editor.putString(MIDDLE_NAME, midNameEditText.getText().toString());
//        editor.putString(MIDDLE_NAME, middleNameEditText.getText().toString()); // Save middle name
        editor.putString(AGE, ageEdittext.getText().toString()); // Save age
        editor.putString(ADDRESS, addressEdittext.getText().toString()); // Save address
        editor.putString(SCHOOL, schoolEdittext.getText().toString()); // Save school
        editor.putString(COURSE, courseEdittext.getText().toString()); // Save course
        editor.putString(DEPARTMENT, departmentEdittext.getText().toString()); // Save department

        editor.apply(); // Apply changes asynchronously
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        String lastName = sharedPreferences.getString(LAST_NAME, "");      // Load last name
        String firstName = sharedPreferences.getString(FIRST_NAME, "");    // Load first name
        String middleName = sharedPreferences.getString(MIDDLE_NAME, "");  // Load middle name
        String age = sharedPreferences.getString(AGE, "");                 // Load age as String
        String address = sharedPreferences.getString(ADDRESS, "");         // Load address
        String school = sharedPreferences.getString(SCHOOL, "");           // Load school
        String course = sharedPreferences.getString(COURSE, "");           // Load course
        String department = sharedPreferences.getString(DEPARTMENT, "");   // Load department

        lastNameEditText.setText(lastName);
        firstNameEditText.setText(firstName);
        midNameEditText.setText(middleName);      // Set middle name
        ageEdittext.setText(age);                 // Set age as String
        addressEdittext.setText(address);         // Set address
        schoolEdittext.setText(school);           // Set school
        courseEdittext.setText(course);           // Set course
        departmentEdittext.setText(department);   // Set department
    }

}