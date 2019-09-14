package com.torredben.datasave;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ExternalStorageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);
    }

    public void onCheckESS(android.view.View view) {
        TextView tvCheckESS = findViewById(com.torredben.datasave.R.id.tvCheckESS);

        if(isExternalStorageWritable()){
            tvCheckESS.setText("外部存储可读写！");
        } else if(isExternalStorageReadable()) {
            tvCheckESS.setText("外部存储可读！");
        }
        else {
            tvCheckESS.setText("外部存储不可用！");
        }
    }

    protected boolean isExternalStorageWritable() {
        String state = android.os.Environment.getExternalStorageState();

        if (android.os.Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }

        return false;
    }

    protected boolean isExternalStorageReadable() {
        String state = android.os.Environment.getExternalStorageState();

        if (android.os.Environment.MEDIA_MOUNTED.equals(state) ||
                android.os.Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}
