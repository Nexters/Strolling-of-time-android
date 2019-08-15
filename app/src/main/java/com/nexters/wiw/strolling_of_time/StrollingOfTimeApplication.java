package com.nexters.wiw.strolling_of_time;

import android.app.Application;

import com.nexters.wiw.strolling_of_time.models.DaoMaster;
import com.nexters.wiw.strolling_of_time.models.DaoSession;
import org.greenrobot.greendao.database.Database;


public class StrollingOfTimeApplication extends Application {
    public static DaoSession daoSession;
    public static final boolean ENCRYPTED = true;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, Constants.ENCRYPTED ? Constants.DATABASE_NAME+"_encrypted" : Constants.DATABASE_NAME);
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("password") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }
    public DaoSession getDaoSession(){
        if (daoSession == null) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, Constants.ENCRYPTED ? Constants.DATABASE_NAME+"_encrypted" : Constants.DATABASE_NAME);
            Database db = ENCRYPTED ? helper.getEncryptedWritableDb("password") : helper.getWritableDb();
            daoSession = new DaoMaster(db).newSession();

        }
        return daoSession;
    }
}
