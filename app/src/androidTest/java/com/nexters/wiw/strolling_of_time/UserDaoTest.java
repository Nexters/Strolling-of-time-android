package com.nexters.wiw.strolling_of_time;

import android.app.Activity;
import android.content.Context;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import android.util.Log;

import com.nexters.wiw.strolling_of_time.models.DaoMaster;
import com.nexters.wiw.strolling_of_time.models.DaoSession;
import com.nexters.wiw.strolling_of_time.models.Users;
import com.nexters.wiw.strolling_of_time.models.UsersDao;

import org.greenrobot.greendao.database.Database;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class UserDaoTest {

  Database db;
  DaoSession session;
  Context mMockContext;
  UsersDao usersDao;
  @Before
  public void setUp() {
    mMockContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mMockContext, "test_encrypted");  // 데이터베이스 암호화
    db = helper.getEncryptedWritableDb("imdeo123!!");
    session = new DaoMaster(db).newSession();
    usersDao = session.getUsersDao();
  }
  @After
  public void tearDown(){
    db.close();
    session.clear();
  }

  @Test
  public void useAppContext() {
    // Context of the app under test.
    Context appContext = InstrumentationRegistry.getTargetContext();

    assertEquals("com.nexters.wiw.strolling_of_time", appContext.getPackageName());

    Date date = new Date(2018, 1, 28);
    Users users = new Users();
    users.setName("이름");
    users.setActive(true);
    users.setBackground_image("");
    users.setCreated(date);
    users.setEmail("");
    users.setPassword("");
    users.setProfile_image("");
    long id = usersDao.insert(users);		// DB Insert

    assert id != 0;

  Log.d("hi",id + "v");
  }
}