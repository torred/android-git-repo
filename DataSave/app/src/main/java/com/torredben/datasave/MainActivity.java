package com.torredben.datasave;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sample01Click(View view) {
        Intent intent = new Intent();
        intent.setClass(this,KeySaveActivity.class);
        startActivity(intent);
    }

    public void sample02Click(View view) {
        Intent intent = new Intent();
        intent.setClass(this, FileSaveActivity.class);
        startActivity(intent);
    }

    public void sample03Click(android.view.View view) {
        Intent intent = new android.content.Intent();
        intent.setClass(this,ExternalStorageActivity.class);
        startActivity(intent);
    }

    public void sample04Click(android.view.View view) {
        Intent intent = new Intent();
        intent.setClass(this,DatabaseActivity.class);
        startActivity(intent);
    }
}
