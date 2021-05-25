package com.thecodecity.mapsdirection;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.StrictMode;
import android.provider.Settings;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class busUpdatelocation extends AppCompatActivity {
    EditText edate, edlong, edlati, edloc, edtime;
    Button btn;
    private static final int REQUEST_LOCATION = 1;

    int t1Hour, t1Minute;

    LocationManager locationManager;
    String latitude, longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_updatelocation);

        edate = findViewById(R.id.editText);
        edlong = findViewById(R.id.editText1);
        edlati = findViewById(R.id.editText2);
        edloc = findViewById(R.id.editText3);
        edtime = findViewById(R.id.editText4);

        btn = findViewById(R.id.button);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        edate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(busUpdatelocation.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayofmonth) {
                        String date = month+1 + "/" + dayofmonth + "/" + year;
                        edate.setText(date);

                    }
                }, year, month, day);
                datePickerDialog.getDatePicker();
                datePickerDialog.show();
            }
        });
        edtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(busUpdatelocation.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourofday, int minute) {

                                t1Hour = hourofday;
                                t1Minute = minute;

                                Calendar calendar = Calendar.getInstance();
                                calendar.set(0, 0, 0, t1Hour, t1Minute);
                                edtime.setText(DateFormat.format("hh:mm aa", calendar));

                            }
                        }, 12, 0, false
                );
                timePickerDialog.updateTime(t1Hour, t1Minute);
                timePickerDialog.show();
            }
        });

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            OnGPS();
        } else {

            getLocation();
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = busLogin.userdata;
                String date = edate.getText().toString();
                String longitude = edlong.getText().toString();
                String latitude = edlati.getText().toString();
                String location = edloc.getText().toString();
                String time = edtime.getText().toString();

                if (date.equals("") || longitude.equals("") || latitude.equals("") || location.equals("")
                        || time.equals("")) {
                    Toast.makeText(busUpdatelocation.this, "Please enter details.", Toast.LENGTH_SHORT).show();
                }
//                }if(username.length()<8)
//                {
//                    Toast.makeText(userRegister.this,"username must be of 8 alphabates",Toast.LENGTH_SHORT).show();
//                }
//                else if(passward.length()<8)
//                {
//                    Toast.makeText(userRegister.this,"Password must be of 8 alphabates",Toast.LENGTH_SHORT).show();
//                }
////                else if (!email.matches(emailPattern) )
////                {
////                    Toast.makeText(getApplicationContext(),"invalid email address",Toast.LENGTH_SHORT).show();
////                    // or
////                    // textView.setText("valid email");
////                }
//                else if (address.length()<10)
//                {
//                    Toast.makeText(getApplicationContext(),"Please insert proprer address",Toast.LENGTH_SHORT).show();
//                    // or
//                    // textView.setText("valid email");
//                }
                else {

                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    String url = UrlLinks.updatelocation;

                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(6);

                    nameValuePairs.add(new BasicNameValuePair("name", name));
                    nameValuePairs.add(new BasicNameValuePair("date", date));
                    nameValuePairs.add(new BasicNameValuePair("longitude", longitude));
                    nameValuePairs.add(new BasicNameValuePair("latitude", latitude));
                    nameValuePairs.add(new BasicNameValuePair("location", location));
                    nameValuePairs.add(new BasicNameValuePair("time", time));


                    JSONObject result = null;
                    try {
                        result = jSOnClassforData.forCallingServer(url, nameValuePairs);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

//        JSONArray jArray = new JSONArray(result.toString());
//
//        for(int i=0;i<jArray.length();i++) {
//            String alldata = jArray.get(i).toString();
//
//            String datasplit[] = alldata.split("_");
//            latilongidata.add(alldata);
//
//
//
//
//        }


                    JSONArray jArray = null;
                    try {
                        jArray = result.getJSONArray("jsonarrayval");
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
//
//                    System.out.println("*****JARRAY*****" + jArray.length());
//
//                    for (int i = 0; i < jArray.length(); i++) {


                    JSONObject json_data;

                    try {
                        json_data = jArray.getJSONObject(0);
                        String bookName = json_data.getString("bookName");
//                        String author = json_data.getString("author");
//                        String publisher = json_data.getString("publisher");

                        if (bookName.equals("1")) {

                            Toast.makeText(busUpdatelocation.this, "Location updated successfully", Toast.LENGTH_SHORT).show();
                            Intent io = new Intent(busUpdatelocation.this, Home.class);

                            startActivity(io);

                        } else {

                            Toast.makeText(busUpdatelocation.this, "Wrong info", Toast.LENGTH_SHORT).show();

                        }


                        //  SplittingBooktime=bookName.split(",");

//							 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//					        		 R.layout.textview, SplittingBooktime);


                        //  Toast.makeText(SelectingLcoation.this,"Doctor Available at "+ bookName, Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }

            }
        });


    }

    private void getLocation() {

        if (ActivityCompat.checkSelfPermission(busUpdatelocation.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(busUpdatelocation.this,

                Manifest.permission.ACCESS_COARSE_LOCATION) !=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
        else
        {
            Location LocationGps= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location LocationNetwork=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location LocationPassive=locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (LocationGps !=null)
            {
                double lat=LocationGps.getLatitude();
                double longi=LocationGps.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);

                edlong.setText(longitude);
                edlati.setText(latitude);
            }
            else if (LocationNetwork !=null)
            {
                double lat=LocationNetwork.getLatitude();
                double longi=LocationNetwork.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);

                edlong.setText(longitude);
                edlati.setText(latitude);
            }
            else if (LocationPassive !=null)
            {
                double lat=LocationPassive.getLatitude();
                double longi=LocationPassive.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);

                edlong.setText(longitude);
                edlati.setText(latitude);
            }
            else
            {
                Toast.makeText(this, "Can't Get Your Location", Toast.LENGTH_SHORT).show();
            }

            //Thats All Run Your App
        }

    }

    private void OnGPS() {

        final AlertDialog.Builder builder= new AlertDialog.Builder(this);

        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        final AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}