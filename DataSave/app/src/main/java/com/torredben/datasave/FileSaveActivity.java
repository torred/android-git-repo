package com.torredben.datasave;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileSaveActivity extends Activity {

    private final String FILE_NAME = "filesave.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_save);
    }

    public void onWriteFileClick(View view) {
        //java.io.File file = openFileInput(this.getDir(""),"");
        EditText editText = findViewById(R.id.etWriteText);
        String string = editText.getText().toString();
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onReadFileClick(View view) {
        String path = this.getFilesDir() + "/" + FILE_NAME;
        String vcontent = "";

        java.io.File file = new java.io.File(path);

        try {
            InputStream inputStream = new java.io.FileInputStream(file);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    vcontent += line;
                }

                inputStream.close();
            }
        } catch ( FileNotFoundException e) {
            Log.d("TestFile", "onReadFileClick: 文件不存在！ ");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        TextView textView = findViewById(R.id.tvReadText);
        textView.setText(vcontent);
    }
}
