package com.example.classnotify;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.classnotify.databinding.ActivityWeekTableBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import android.content.SharedPreferences;

public class InstructorActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private LinearLayout nameListLayout; // To hold the names dynamically
    private static final String SHARED_PREFS = "schedule_prefs";
    private static final String ENTRY_KEY_PREFIX = "entry_";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);

        Toolbar toolbar = findViewById(R.id.toolbarInstructor);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layoutInstructor);
        NavigationView navigationView = findViewById(R.id.nav_viewInstructor);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Initialize the LinearLayout to display names
        nameListLayout = findViewById(R.id.nameListLayoutInstructor);

        // Initialize FloatingActionButton and set its onClickListener
        FloatingActionButton addButton = findViewById(R.id.addButtonInstructor);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNameInputDialog();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_about_app) {
            Intent intent = new Intent(InstructorActivity.this, aboutTheApp.class);
            startActivity(intent);
        } else if (id == R.id.nav_developer) {
            Intent intent = new Intent(InstructorActivity.this, developer.class);
            startActivity(intent);
        } else if (id == R.id.nav_personal_info) {
            // Handle Personal info click
            Intent intent = new Intent(InstructorActivity.this, studentForm.class);
            startActivity(intent);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Method to show a dialog for name input
    private void showNameInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_name_input_instructor, null);
        builder.setView(dialogView);

        EditText nameInput = dialogView.findViewById(R.id.nameInputInstructor);
        Button saveButton = dialogView.findViewById(R.id.saveButtonInstructor);
        ImageView colorPickerImageView = dialogView.findViewById(R.id.colorPickerImageViewInstructor);
        RelativeLayout dialogLayout = dialogView.findViewById(R.id.dialogLayoutInstructor); // Updated ID
        AlertDialog dialog = builder.create();

        // Create a new RelativeLayout for the entry
        RelativeLayout entryLayout = (RelativeLayout) getLayoutInflater().inflate(R.layout.item_schedule_entry_instructor, null);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InstructorActivity.this, addForm_dialogInstructor.class); // Updated to WeekTable
                startActivity(intent);

                String name = nameInput.getText().toString();
                if (!name.isEmpty()) {
                    TextView nameTextView = entryLayout.findViewById(R.id.nameTextViewInstructor);
                    nameTextView.setText(name);

                    // Create LayoutParams with margins and apply dp to px conversion
                    int marginInDp = (int) (16 * getResources().getDisplayMetrics().density); // Example 16dp
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    params.setMargins(0, marginInDp, 0, 0); // Set top margin

                    // Apply LayoutParams to entryLayout
                    entryLayout.setLayoutParams(params);

                    // Add the entry to the layout
                    nameListLayout.addView(entryLayout);

                    // Set OnClickListener for the burger icon to show options
                    ImageView burgerIcon = entryLayout.findViewById(R.id.burgerIconInstructor);
                    burgerIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showOptionsMenu(entryLayout); // Show options menu on burger icon click
                        }
                    });
                }
                dialog.dismiss();
            }
        });

        // Set OnClickListener for the color picker ImageView
        colorPickerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showColorPickerDialog(dialogLayout, entryLayout); // Pass both layouts
            }
        });

        dialog.show();
    }

    // New method to update the background color of the dialog and entry
    private void updateDialogAndEntryBackgroundColor(RelativeLayout dialogLayout, RelativeLayout entryLayout, String color) {
        dialogLayout.setBackgroundColor(Color.parseColor(color));
        entryLayout.setBackgroundColor(Color.parseColor(color)); // Update the entry layout as well
    }

    // Method to show the color picker dialog
    private void showColorPickerDialog(final RelativeLayout dialogLayout, final RelativeLayout entryLayout) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick a Color");

        // Inflate the color picker dialog layout
        View colorPickerView = getLayoutInflater().inflate(R.layout.dialog_color_picker, null);
        builder.setView(colorPickerView);

        // Define color buttons in the custom dialog view
        View color1 = colorPickerView.findViewById(R.id.color1); // Button for #FF885B
        View color2 = colorPickerView.findViewById(R.id.color2); // Button for #FFE5CF
        View color3 = colorPickerView.findViewById(R.id.color3); // Button for #557C56
        View color4 = colorPickerView.findViewById(R.id.color4); // Button for #33372C

        // Create the dialog
        AlertDialog colorDialog = builder.create();

        // Set onClickListeners for each color
        color1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDialogAndEntryBackgroundColor(dialogLayout, entryLayout, "#FF885B");
                colorDialog.dismiss();
            }
        });

        color2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDialogAndEntryBackgroundColor(dialogLayout, entryLayout, "#FFE5CF");
                colorDialog.dismiss();
            }
        });

        color3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDialogAndEntryBackgroundColor(dialogLayout, entryLayout, "#557C56");
                colorDialog.dismiss();
            }
        });

        color4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDialogAndEntryBackgroundColor(dialogLayout, entryLayout, "#33372C");
                colorDialog.dismiss();
            }
        });

        // Show the color picker dialog
        colorDialog.show();
    }

    // Method to show the options menu when the burger icon is clicked
    private void showOptionsMenu(final RelativeLayout entryLayout) {
        // Create an AlertDialog for options (edit or delete)
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Options");

        // Add options (Edit and Delete)
        String[] options = {"Edit", "Delete"};
        builder.setItems(options, (dialog, which) -> {
            if (which == 0) {
                // Edit the entry
                showEditDialog(entryLayout);
            } else if (which == 1) {
                // Remove the entry
                nameListLayout.removeView(entryLayout);
            }
        });

        builder.show();
    }

    // Method to show edit dialog for updating the entry
    private void showEditDialog(final RelativeLayout entryLayout) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_name_input_instructor, null);
        builder.setView(dialogView);

        EditText nameInput = dialogView.findViewById(R.id.nameInputInstructor);
        Button saveButton = dialogView.findViewById(R.id.saveButtonInstructor);
        ImageView colorPickerImageView = dialogView.findViewById(R.id.colorPickerImageViewInstructor);
        RelativeLayout dialogLayout = dialogView.findViewById(R.id.dialogLayoutInstructor);

        // Pre-fill the EditText with the existing name
        TextView nameTextView = entryLayout.findViewById(R.id.nameTextViewInstructor);
        String currentName = nameTextView.getText().toString();
        nameInput.setText(currentName);

        AlertDialog dialog = builder.create();

        // Save button click listener
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = nameInput.getText().toString();
                if (!newName.isEmpty()) {
                    // Update the entry's TextView with the new name
                    nameTextView.setText(newName);
                }
                dialog.dismiss();
            }
        });

        // Set OnClickListener for the color picker ImageView
        colorPickerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showColorPickerDialog(dialogLayout, entryLayout); // Pass both layouts
            }
        });

        dialog.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveScheduleEntries();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadScheduleEntries();
    }

    // Method to save schedule entries to SharedPreferences
    private void saveScheduleEntries() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        int entryCount = nameListLayout.getChildCount();
        editor.putInt("entry_count_instructor", entryCount);

        for (int i = 0; i < entryCount; i++) {
            RelativeLayout entryLayout = (RelativeLayout) nameListLayout.getChildAt(i);
            TextView nameTextView = entryLayout.findViewById(R.id.nameTextViewInstructor);
            String name = nameTextView.getText().toString();
            String entryKey = ENTRY_KEY_PREFIX + i;

            // Save the name
            editor.putString(entryKey + "_name_instructor", name);

            // Save the background color
            Drawable background = entryLayout.getBackground();
            if (background instanceof ColorDrawable) {
                int color = ((ColorDrawable) background).getColor();
                editor.putInt(entryKey + "_color_instructor", color);
            }
        }

        editor.apply();
    }

    // Method to load schedule entries from SharedPreferences
    private void loadScheduleEntries() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        int entryCount = sharedPreferences.getInt("entry_count_instructor", 0);

        // Clear existing entries to avoid duplication
        nameListLayout.removeAllViews(); // Clear all existing views

        for (int i = 0; i < entryCount; i++) {
            String entryKey = ENTRY_KEY_PREFIX + i;

            // Load the name and color
            String name = sharedPreferences.getString(entryKey + "_name_instructor", "");
            int color = sharedPreferences.getInt(entryKey + "_color_instructor", Color.WHITE); // Default to white if not saved

            // Create a new RelativeLayout for each entry
            RelativeLayout entryLayout = (RelativeLayout) getLayoutInflater().inflate(R.layout.item_schedule_entry_instructor, null);
            TextView nameTextView = entryLayout.findViewById(R.id.nameTextViewInstructor);
            nameTextView.setText(name);

            // Set the background color
            entryLayout.setBackgroundColor(color);

            // Add the entry to the layout
            nameListLayout.addView(entryLayout);

            // Set OnClickListener for the entryLayout to navigate to WeekTableActivity when clicked
            entryLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Start WeekTable activity when an entry is clicked
                    Intent intent = new Intent(InstructorActivity.this, WeekTableInstructor.class);
                    startActivity(intent);
                }
            });

            ImageView burgerIcon = entryLayout.findViewById(R.id.burgerIconInstructor);
            burgerIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showOptionsMenu(entryLayout); // Show options menu on burger icon click
                }
            });
        }
    }

}
