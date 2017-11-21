package top.code666.a200plan.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import top.code666.a200plan.entity.Economization;
import top.code666.a200plan.entity.Expenses;

/**
 * Created by code666 on 2017/11/20.
 */

public class DbManager {

    private DbHelper dbHelper;
    private SQLiteDatabase db;
    private static DbManager instance = null;

    public DbManager(Context context){
            dbHelper = new DbHelper(context);
            db = dbHelper.getReadableDatabase();
    }

    public static synchronized DbManager getInstance(Context context){
        if (instance == null){
            instance = new DbManager(context);
        }
        return instance;
    }

    /*
    * 插入Expenses支出表
    */
    public void insertExTable(Expenses expenses){
        ContentValues values;
        try {
            values =ExContentValues(expenses);
            db.insert(DbHelper.TABLE_EX,null,values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //支出表的Values
    private ContentValues ExContentValues(Expenses expenses) {
        ContentValues values = new ContentValues();
        try {
            values.put(DbHelper.COLUMN_NAME,expenses.getName());
            values.put("ex_morning_money",expenses.getMorning_money().doubleValue());
            values.put("ex_noon_money",expenses.getNoon_money().doubleValue());
            values.put("ex_evening_money",expenses.getEvening_money().doubleValue());
            values.put("notes",expenses.getNotes());
            values.put("ex_time",getCurrentTime());
        }catch (Exception e){
            e.printStackTrace();
        };
        return values;
    }

    //获得当前时间戳,方便插入数据库
    private String getCurrentTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
        java.util.Date curDate = new java.util.Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }
}
