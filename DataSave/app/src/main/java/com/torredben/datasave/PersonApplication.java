package com.torredben.datasave;

public class PersonApplication extends android.app.Application {
    private SQLiteHelper dbhelper;

    public SQLiteHelper getSQLitehelper() {
        if(dbhelper == null ) {
            dbhelper = new SQLiteHelper(this);
        }
        return dbhelper;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getSQLitehelper().create();
    }
}
