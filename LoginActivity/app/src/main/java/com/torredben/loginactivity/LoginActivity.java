package com.torredben.loginactivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void btnLoginClick(android.view.View view) {
        EditText etName=findViewById(com.torredben.loginactivity.R.id.etName);
        EditText etPassword = findViewById(com.torredben.loginactivity.R.id.etPassword);

        String strName = etName.getText().toString();
        String strPassword = etPassword.getText().toString();

        android.content.Intent intent = new android.content.Intent();
        intent.putExtra(CC.NAME, strName);
        intent.putExtra(CC.PASSWORD, strPassword);
        setResult(android.app.Activity.RESULT_OK,intent);

        finish();
    }
}
