package com.torredben.datasave;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.SimpleAdapter;
import android.widget.ListView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseActivity extends Activity {

    protected EditText etPersonName;
    protected EditText etPersonInfo;
    protected Button btnModify;
    protected Button btnDelete;

    protected com.torredben.datasave.Person selectPersion = new Person();

    protected ArrayList<Map<String,String>> list = new ArrayList<Map<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        etPersonName = findViewById(R.id.etName);
        etPersonInfo = findViewById(R.id.etInfo);
        btnDelete = findViewById(com.torredben.datasave.R.id.btnDelete);
        btnModify = findViewById(com.torredben.datasave.R.id.btnModify);

        btnModify.setEnabled(false);
        btnDelete.setEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        int menu_id = item.getItemId();
        switch (menu_id){
            case R.id.menu_add: {
                clickAdd();
                break;
            }

            case R.id.menu_modify: {
                clickModify();
                break;
            }

            case R.id.menu_delete:
            {
                clickDelete();
                break;
            }

            default: break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void clickModify() {
    }

    private void clickAdd() {
    }

    private void clickDelete() {

    }

    public void onAdd(View view) {
        String strName = etPersonName.getText().toString();
        String strInfo = etPersonInfo.getText().toString();

        try {
            SQLiteHelper helper = ((PersonApplication)getApplication()).getSQLitehelper();
            SQLiteDatabase database = helper.getWritableDatabase();
            String sql = "insert into persons (name,info) values ('" +
                    strName +"','" + strInfo +"')";

            database.execSQL(sql);

            Toast.makeText(getApplicationContext(), "新增成功", Toast.LENGTH_SHORT).show();
        } catch ( Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void onModify(View view) {
        String name = etPersonName.getText().toString();
        String info = etPersonInfo.getText().toString();
        int _id = selectPersion._id;

        try {
            SQLiteHelper helper = ((PersonApplication)getApplication()).getSQLitehelper();
            SQLiteDatabase database = helper.getWritableDatabase();
            android.content.ContentValues contentValues = new android.content.ContentValues();
            contentValues.put("_id",_id);
            contentValues.put("name",name);
            contentValues.put("info",info);

            if(database.update("persons",contentValues,"_id=?",new String[]{String.valueOf(_id)}) > 0) {
                listPerson();
                android.widget.Toast.makeText(getApplicationContext(), "更新成功！", android.widget.Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            android.widget.Toast.makeText(getApplicationContext(), e.getMessage(), android.widget.Toast.LENGTH_SHORT).show();
        }

        btnDelete.setEnabled(false);
        btnModify.setEnabled(false);
    }

    public void onQuery(View view) {
        listPerson();
    }

    public void onDelete(View view) {

        try {
            SQLiteHelper helper = ((PersonApplication)getApplication()).getSQLitehelper();
            SQLiteDatabase database = helper.getWritableDatabase();

            if(database.delete("persons","_id=?",new String[]{String.valueOf(selectPersion._id)}) > 0) {
                listPerson();
                android.widget.Toast.makeText(getApplicationContext(), "删除成功！", android.widget.Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            android.widget.Toast.makeText(getApplicationContext(), e.getMessage(), android.widget.Toast.LENGTH_SHORT).show();
        }

        btnDelete.setEnabled(false);
        btnModify.setEnabled(false);
    }

    protected void listPerson() {
        SQLiteHelper helper = ((PersonApplication)getApplication()).getSQLitehelper();
        SQLiteDatabase database = helper.getReadableDatabase();

        List<Person> listPerson = Person.getAll(database);
        helper.close();

        list.clear();

        for( Person person:listPerson) {
            HashMap<String,String> map = new HashMap<String, String>();
            map.put("name" , person.name);
            map.put("info" , person.info);
            list.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),
                list,
                android.R.layout.simple_list_item_2,
                new String[]{"name","info"},
                new int[]{android.R.id.text1, android.R.id.text2});

        ListView listView = findViewById(R.id.lvPerson);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectPersion._id = position+1;
                selectPersion.name = list.get(position).get("name");
                selectPersion.info = list.get(position).get("info");

                etPersonName.setText(selectPersion.name);
                etPersonInfo.setText(selectPersion.info);

                btnDelete.setEnabled(true);
                btnModify.setEnabled(true);
            }
        });
    }
}
