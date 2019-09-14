package com.torredben.loginactivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_login:
                Intent intent = new Intent(this,LoginActivity.class);
                startActivityForResult(intent,CC.LoginRequestCode);
                break;

            case R.id.action_call:
                Uri number = Uri.parse("tel:075526076760");
                Intent callintent = new Intent( Intent.ACTION_DIAL,number);
                startActivity(callintent);
                break;

            case R.id.action_view:
                Uri webpage = Uri.parse("http://www.baidu.com");
                Intent webintent = new Intent( Intent.ACTION_VIEW,webpage);
                startActivity(webintent);
                break;

            case R.id.action_calendar:
                Intent calendarintent = new Intent( Intent.ACTION_INSERT,
                        android.provider.CalendarContract.Events.CONTENT_URI);
                java.util.Calendar beginTime = java.util.Calendar.getInstance();
                beginTime.set(2017,6,26,10,0);
                java.util.Calendar endTime = java.util.Calendar.getInstance();
                endTime.set(2017,6,26,12,0);

                calendarintent.putExtra(android.provider.CalendarContract.EXTRA_EVENT_BEGIN_TIME,beginTime.getTimeInMillis());
                calendarintent.putExtra(android.provider.CalendarContract.EXTRA_EVENT_END_TIME,endTime.getTimeInMillis());
                calendarintent.putExtra(android.provider.CalendarContract.Events.TITLE,"Ninja class");
                calendarintent.putExtra(android.provider.CalendarContract.Events.EVENT_LOCATION,"Secret dojo");

                startActivity(calendarintent);
                break;

            case R.id.action_send:
                Intent sendintent = new Intent( Intent.ACTION_SEND);
                sendintent.setType("image/jpeg");

                java.io.File downloadPic = new java.io.File(
                        Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_DOWNLOADS),"q.jpeg");

                sendintent.putExtra( Intent.EXTRA_STREAM, Uri.fromFile(downloadPic));

                startActivity( Intent.createChooser(sendintent,"使用发送图片："));
                break;

                default: break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String name = data.getStringExtra( CC.NAME);
        String password = data.getStringExtra( CC.PASSWORD);
        String string = "用户名：" + name + "； 密码：" +password;

        TextView tvLogin = findViewById( R.id.tvLogin);
        tvLogin.setText(string);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
