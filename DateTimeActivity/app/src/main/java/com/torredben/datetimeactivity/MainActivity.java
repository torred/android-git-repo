package com.torredben.datetimeactivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.widget.DatePicker;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePicker datePicker=findViewById(R.id.datePicker);
                int year=datePicker.getYear();
                int month= datePicker.getMonth();
                int day=datePicker.getDayOfMonth();
                String datetime=String.valueOf(year)+"年"+String.valueOf(month)+"月 "+String.valueOf(day)+"日";
                Toast.makeText(getApplicationContext(), datetime, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
