package com.example.classnotify;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WeekTable extends AppCompatActivity {

    private TableRow selectedRow = null;  // Track the selected row for editing or deleting
    private TableLayout tableLayout;      // TableLayout that holds the week table

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_table);

        // Get the TableLayout where the week table is stored
        tableLayout = findViewById(R.id.table_data);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFabMenu(view);
            }
        });
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
                    Intent intent = new Intent(WeekTable.this, addForm_dialog.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.action_edit_dialog) {
                    handleEditAction();
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

    private void handleEditAction() {
        if (selectedRow != null) {
            // Handle Edit action
            // Here you can add logic to open an edit dialog or activity
        }
    }

    private void handleDeleteAction() {
        if (selectedRow != null) {
            // Handle Delete action
            // Here you can add logic to delete the selected row
            tableLayout.removeView(selectedRow); // Example: remove the selected row from the table
            selectedRow = null; // Reset selected row
        }
    }


}