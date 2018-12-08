package com.example.a98125.finalproject;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView mEnterNumber;
    private Button btnSendRequest1;
    private RequestQueue mRequestQueue1;
    private StringRequest stringRequest1;
    private String url = "http://www.mocky.io/v2/5c0b2e8a2f0000550013eba8";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDisplayDate = (TextView) findViewById(R.id.tvDate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG,"onDateSet: date:" + month + "/" + dayOfMonth);
                String date = month + "/" + dayOfMonth;
                mDisplayDate.setText(date);
            }
        };
        /**
        Button btn1 = (Button) findViewById(R.id.enter1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //API???
                //here we got access to the textview
                TextView textView = (TextView) findViewById(R.id.outputDisplay);
                //???
                textView.setText("We haven't finished this app yet!");
            }
        });
        */
         btnSendRequest1 = (Button) findViewById(R.id.enter1);
         btnSendRequest1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //send response and print the response using volley library
                 sendRequestAndPrintReponse1();
             }
         });
    }
    private void sendRequestAndPrintReponse1() {
        mRequestQueue1 = Volley.newRequestQueue(this);
        //enter1 ??? enterNumber???
        /*TextView suffix = findViewById(R.id.enterNumber);
        if (suffix == null || suffix.length() == 0) {
            return ...???
        }
        url = "http://numbersapi.com/" + suffix;
        */
        stringRequest1 = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "Response : " + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "Error : " + error.toString());
            }
        });
        mRequestQueue1.add(stringRequest1);
    }

}
