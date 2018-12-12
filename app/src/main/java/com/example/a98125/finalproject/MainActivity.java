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
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;




public class MainActivity extends AppCompatActivity {
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

        mRequestQueue1 = Volley.newRequestQueue(this);



        Button btn1 = (Button) findViewById(R.id.enter1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TextView textView = (TextView) findViewById(R.id.outputDisplay);
                EditText editText = (EditText) findViewById(R.id.enterNumber);

                sendRequestAndPrintReponse1(textView, editText);

            }
        });

    }

    private void sendRequestAndPrintReponse1(final TextView textView, final EditText editText) {
        String value;
        if (editText == null || editText.length() == 0) {
            value = "random";
        } else {
            value = editText.getText().toString();
        }
        //int findValue = Integer.parseInt(value);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                "http://numbersapi.com/" + value + "/trivia?json",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "Received response.");
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