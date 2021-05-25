package com.thecodecity.mapsdirection;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.StrictMode;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class addBusinfo extends AppCompatActivity {
    EditText edno,edplateno,edup,eddown;
    Spinner source,destination,stop1,stop2;
    Button btn;
    int t1Hour,t1Minute,t2Hour,t2Minute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_businfo);

        edno = findViewById(R.id.editText);
        edplateno = findViewById(R.id.editText1);
        edup = findViewById(R.id.uptime);
        eddown = findViewById(R.id.downtime);

        source = findViewById(R.id.spin);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.source, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        source.setAdapter(adapter);

        destination = findViewById(R.id.spin1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.destination, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        destination.setAdapter(adapter1);

        stop1 = findViewById(R.id.spin2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.stop, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stop1.setAdapter(adapter2);

        stop2 = findViewById(R.id.spin3);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.stop, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stop2.setAdapter(adapter3);

        edup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(addBusinfo.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourofday, int minute) {

                                t1Hour = hourofday;
                                t1Minute = minute;

                                Calendar calendar = Calendar.getInstance();
                                calendar.set(0,0,0,t1Hour,t1Minute);
                                edup.setText(DateFormat.format("hh:mm aa",calendar));

                            }
                        },12,0,false
                );
                timePickerDialog.updateTime(t1Hour,t1Minute);
                timePickerDialog.show();
            }
        });
        eddown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(addBusinfo.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourofday, int minute) {

                                t2Hour = hourofday;
                                t2Minute = minute;

                                Calendar calendar = Calendar.getInstance();
                                calendar.set(0,0,0,t2Hour,t2Minute);
                                eddown.setText(DateFormat.format("hh:mm aa",calendar));

                            }
                        },12,0,false
                );
                timePickerDialog.updateTime(t2Hour,t2Minute);
                timePickerDialog.show();
            }
        });

        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String busno = edno.getText().toString();
                String busplate = edplateno.getText().toString();
                String sourc = source.getSelectedItem().toString();
                String destinatio = destination.getSelectedItem().toString();
                String s1 = stop1.getSelectedItem().toString();
                String s2 = stop2.getSelectedItem().toString();
                String s3 = s1+" --> "+s2;
                String uptime = edup.getText().toString();
                String downtime = eddown.getText().toString();

                if(busno.equals("")||busplate.equals("")||sourc.equals("Select source")||destinatio.equals("Select destination")||s3.equals("")||uptime.equals("")||downtime.equals("")) {
                    Toast.makeText(addBusinfo.this, "Please enter details.", Toast.LENGTH_SHORT).show();
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
                    String url = UrlLinks.addbusinformation;

                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(8);

                    nameValuePairs.add(new BasicNameValuePair("busno", busno));
                    nameValuePairs.add(new BasicNameValuePair("busplate", busplate));
                    nameValuePairs.add(new BasicNameValuePair("sourc", sourc));
                    nameValuePairs.add(new BasicNameValuePair("destinatio", destinatio));
                    nameValuePairs.add(new BasicNameValuePair("stop", s3));
                    nameValuePairs.add(new BasicNameValuePair("uptime", uptime));
                    nameValuePairs.add(new BasicNameValuePair("downtime", downtime));


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

                            Toast.makeText(addBusinfo.this, "Bus added successfully", Toast.LENGTH_SHORT).show();
                            Intent io = new Intent(addBusinfo.this, Home.class);

                            startActivity(io);
                            finish();

                        } else {

                            Toast.makeText(addBusinfo.this, "Wrong information", Toast.LENGTH_SHORT).show();

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
}