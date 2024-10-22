package com.example.classnotify;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Choices extends AppCompatActivity {

    // Constants for SharedPreferences
    private static final String PREFS_NAME = "AppPreferences";
    private static final String KEY_PROFILE_COMPLETED = "profileCompleted"; // Check if profile is completed
    private static final String KEY_USER_TYPE = "userType";
    private static final String USER_STUDENT = "student";
    private static final String USER_INSTRUCTOR = "instructor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get SharedPreferences
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isProfileCompleted = preferences.getBoolean(KEY_PROFILE_COMPLETED, false);
        String userType = preferences.getString(KEY_USER_TYPE, "");

        // If the profile is already completed, skip the Choices and go to the main/front page
        if (isProfileCompleted) {
            startActivity(new Intent(this, MainActivity.class)); // Replace with your main page class
            finish();
            return;
        }

        // Show the Choices screen for the first time or if profile is not completed
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_choices);

        // Apply window insets for edge-to-edge layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Method to handle selection of the "Student" profile
    public void showHome2(View view) {
        saveUserType(USER_STUDENT);
        Intent intent = new Intent(this, studentForm.class);
        startActivity(intent);
        finish(); // Close the Choices activity
    }

    // Method to handle selection of the "Instructor" profile
    public void showHome(View view) {
        saveUserType(USER_INSTRUCTOR);
        Intent intent = new Intent(this, InstructorForm.class);
        startActivity(intent);
        finish(); // Close the Choices activity
    }

    // Save the user type
    private void saveUserType(String userType) {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USER_TYPE, userType);
        editor.apply();
    }
}
