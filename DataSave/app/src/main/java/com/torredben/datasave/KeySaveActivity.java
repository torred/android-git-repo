package com.torredben.datasave;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.content.Context;

public class KeySaveActivity extends Activity {

    protected EditText mEditText;
    protected TextView mTextView;

    private final String KEY_NAME="MYKEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_save);

        mEditText = findViewById(com.torredben.datasave.R.id.writeText);
        mTextView = findViewById(com.torredben.datasave.R.id.readText);
    }

    public void onWriteClick(View view) {
        SharedPreferences vsp = getSharedPreferences(KEY_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = vsp.edit();
        editor.putString(KEY_NAME,mEditText.getText().toString());
        editor.commit();
    }

    public void onReadClick(View view) {
        SharedPreferences vsp = getSharedPreferences(KEY_NAME, android.content.Context.MODE_PRIVATE);
        String string = vsp.getString(KEY_NAME,"none");
        mTextView.setText(string);
    }
}
