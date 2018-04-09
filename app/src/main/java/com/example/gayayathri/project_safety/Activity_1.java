package com.example.gayayathri.project_safety;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.List;

public class Activity_1 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    EditText from,to;
    DataBaseHelper myDb;
    EditText editId;
    Button btnAddData,btnviewAll,btnupdate,btndelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        Intent intent=getIntent();
        from=(EditText)findViewById(R.id.from);
        to=(EditText)findViewById(R.id.to);

        myDb = new DataBaseHelper(this);

        editId = (EditText)findViewById(R.id.id);
        btnAddData = (Button)findViewById(R.id.add);
        btnviewAll = (Button)findViewById(R.id.viewall);
        btnupdate = (Button)findViewById(R.id.update);
        btndelete = (Button)findViewById(R.id.delete);



        AddData();
        viewAll();
        updateData();
        deleteData();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void search(View v) throws IOException {

        EditText e1 = (EditText) findViewById(R.id.from);
        EditText e2= (EditText) findViewById(R.id.to);


        String text = e1.getText().toString();
        String s = e2.getText().toString();

        Geocoder geocoder = new Geocoder(this);
        List<Address> addresses = geocoder.getFromLocationName(text, 1);
        Address address = addresses.get(0);
        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLng).title(text));
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

        Geocoder geocoder1 = new Geocoder(this);
        List<Address> addresses1 = geocoder1.getFromLocationName(s, 1);
        Address address1 = addresses1.get(0);
        LatLng latLng1 = new LatLng(address1.getLatitude(), address1.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLng1).title(text));
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

        mMap.addPolyline(
                new PolylineOptions().add(
                        new LatLng(latLng.latitude, latLng.longitude),
                        new LatLng(latLng1.latitude,latLng1.longitude)
                ).width(2).color(Color.BLUE).geodesic(true)
        );


    }
    public void zoom(View v)
    {
        if(v.getId()==R.id.b1)
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        else if(v.getId()==R.id.b2)
            mMap.animateCamera(CameraUpdateFactory.zoomOut());

    }
//    public void changemap(View v)
//    {
//        if (mMap.getMapType()==GoogleMap.MAP_TYPE_NORMAL)
//        {
//            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
//        }
//        else
//            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//
//    }

    public void deleteData() {
        btndelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Integer deletedRows = myDb.deleteData(editId.getText().toString());
                        if(deletedRows>0)
                            Toast.makeText(Activity_1.this,"Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Activity_1.this,"Data not Deleted", Toast.LENGTH_LONG).show();

                    }
                }

        );
    }

    public void updateData() {
        btnupdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdate = myDb.updateData(editId.getText().toString(),from.getText().toString(),to.getText().toString());
                        if(isUpdate==true)
                            Toast.makeText(Activity_1.this,"Data Updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Activity_1.this,"Data not Updated", Toast.LENGTH_LONG).show();
                    }
                }


        );
    }


    public void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = myDb.insertData(from.getText().toString(),to.getText().toString());
                        if(isInserted == true) {

                            Toast.makeText(Activity_1.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(Activity_1.this,"Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }


        );
    }

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0){
                            showMessage("Error","Nothing Found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){

                            buffer.append("Id :"+res.getString(0)+"\n");
                            buffer.append("From :"+res.getString(1)+"\n");
                            buffer.append("To :"+res.getString(2)+"\n\n");



                        }
                        showMessage("Data",buffer.toString());
                    }
                }


        );


    }


    public void showMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
// Show rationale and request permission.
        }

    }
}
