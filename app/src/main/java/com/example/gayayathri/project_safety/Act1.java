package com.example.gayayathri.project_safety;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Act1 extends AppCompatActivity {
    Button b1,b2,b3;
    private ActionMode mActionMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i = new Intent(Act1.this,Settings.class);
                startActivity(i);
            }
        });
        b1=(Button) findViewById(R.id.b1);
        b2=(Button) findViewById(R.id.b2);
//        setUpSharedPreferences();
        TextView textView = (TextView) findViewById(R.id.tv);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Act1.this, Activity_1.class);
                startActivity(intent);

            }

        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        // add long click listener to text view
        textView.setOnLongClickListener(new View.OnLongClickListener() {
            // Called when the user long-clicks on someView
            public boolean onLongClick(View view) {
                if (mActionMode != null) {
                    return false;
                }

                // Start the CAB (Context Action Bar) using the ActionMode.Callback defined above
                mActionMode = Act1.this.startActionMode(mActionModeCallback);
                view.setSelected(true);
                return true;
            }
        });

    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        // Called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.contextmenu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {

            return false;

        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.yes:
                    Toast.makeText(getApplicationContext(), "Thanks for liking!", Toast.LENGTH_SHORT).show();
                    actionMode.finish(); // Action picked, so close the CAB
                    return true;
                case R.id.no:
                    Toast.makeText(getApplicationContext(), "We'll try to improve", Toast.LENGTH_SHORT).show();
                    actionMode.finish(); // Action picked, so close the CAB
                    return true;

                default:
                    return false;
            }

        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            mActionMode = null;
        }
    };




    }


