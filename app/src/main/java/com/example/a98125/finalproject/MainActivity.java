package com.example.a98125.finalproject;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;


import org.json.JSONObject;




public class MainActivity extends AppCompatActivity {

    //    private static final String TAG = MainActivity.class.getName();
    private static final String TAG = "Final Project";
    private static final String REQUESTTAG = "string request first";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView mEnterNumber;
    private Button btnSendRequest1;
    private RequestQueue mRequestQueue1;
    private StringRequest stringRequest1;
    private String url = "http://numbersapi.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        mRequestQueue1 = Volley.newRequestQueue(this);
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
                Log.d(TAG, "onDateSet: date:" + month + "/" + dayOfMonth);
                String date = month + "/" + dayOfMonth;
                mDisplayDate.setText(date);
            }
        };
        */

        Button btn1 = (Button) findViewById(R.id.enter1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TextView textView = (TextView) findViewById(R.id.outputDisplay);
                EditText editText = (EditText) findViewById(R.id.enterNumber);
                //TextView suffix = findViewById(R.id.enterNumber);
                //url = "http://numbersapi.com/" + suffix;
                //textView.setText(url);
                sendRequestAndPrintReponse1(textView, editText);

            }
        });

    }

    /*
     btnSendRequest1 = (Button) findViewById(R.id.enter1);
     btnSendRequest1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             //send response and print the response using volley library
             sendRequestAndPrintReponse1();
         }
     });
}
*/
    private void sendRequestAndPrintReponse1(final TextView textView, final EditText editText) {
        String value = editText.getText().toString();
        int findValue = Integer.parseInt(value);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                "http://numbersapi.com/" + findValue + "/trivia?json",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "Received response.");
                        //textView.setText("Got response, " + response.toString());
                        try {
                            String string = response.getString("text");
                            textView.setText(string);
                        } catch (Exception e) {

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "error.");
                textView.setText("Got error, ");
            }
        });
        mRequestQueue1.add(request);

    }
}