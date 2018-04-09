package com.example.gayayathri.project_safety;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.ActionMode;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class navact extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(navact.this,Settings.class);
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
        switch (id)
        {
            case R.id.feedback:
                sendFeedback();
                return true;
            case R.id.about:
                openabout();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

//        return super.onOptionsItemSelected(item);
    }

    public void openabout(){
        Intent i = new Intent(navact.this,about.class);
        startActivity(i);
    }
    public void sendFeedback(){
        Intent i = new Intent(navact.this,feedback.class);
        startActivity(i);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id==R.id.m1)
        {

            Intent intent = new Intent(navact.this, Activity_1.class);
            startActivity(intent);
        }


        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void openroutes(View v)

    {
        int backgroundColor = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
// Color backgroundColor = ... (Don't do this. The color is just an int.)

        v.setBackgroundColor(backgroundColor);
        Intent intent = new Intent(navact.this, Activity_1.class);
        startActivity(intent);
    }
    public void selectdefaultroute2(View v){
        int backgroundColor = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
// Color backgroundColor = ... (Don't do this. The color is just an int.)

        v.setBackgroundColor(backgroundColor);

    }
    public void selectdefaultroute3(View v){
        int backgroundColor = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
// Color backgroundColor = ... (Don't do this. The color is just an int.)

        v.setBackgroundColor(backgroundColor);

    }
    public void selectdefaultroute4(View v){
        int backgroundColor = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
// Color backgroundColor = ... (Don't do this. The color is just an int.)

        v.setBackgroundColor(backgroundColor);

    }
    public void selectdefaultroute1(View v){
        int backgroundColor = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
// Color backgroundColor = ... (Don't do this. The color is just an int.)

        v.setBackgroundColor(backgroundColor);

    }
    public void start(View v){
        int backgroundColor = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
// Color backgroundColor = ... (Don't do this. The color is just an int.)

        v.setBackgroundColor(backgroundColor);

    }





}

