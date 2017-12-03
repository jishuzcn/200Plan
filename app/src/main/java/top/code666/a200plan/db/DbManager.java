package top.code666.a200plan.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import top.code666.a200plan.entity.Cates;
import top.code666.a200plan.entity.Economization;
import top.code666.a200plan.entity.Expenses;

/**
 * Created by code666 on 2017/11/20.
 */

public class DbManager {

    private DbHelper dbHelper;
    private SQLiteDatabase db;
    private static DbManager instance = null;

    private DbManager(Context context){
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
    * 获取所有分类表总数
    */
    public int  getCatesCount(){
        Cursor cursor = db.query(DbHelper.TABLE_CATES,null,null,null,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    /*
    * 获取分类表所有信息
    */
    public ArrayList<Cates> getAllCates(){
        ArrayList<Cates> cates = new ArrayList<>();
        Cursor cursor = db.query(DbHelper.TABLE_CATES,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                Cates c = new Cates();
                c.setId(cursor.getInt(cursor.getColumnIndex(DbHelper.COLUMNS_ID)));
                c.setImageSrc(cursor.getInt(cursor.getColumnIndex(DbHelper.COLUMNS_IMAGE)));
                c.setName(cursor.getString(cursor.getColumnIndex(DbHelper.COLUMNS_NAME)));
                cates.add(c);
            }while (cursor.moveToNext());
        }
        return cates;
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
        /*try {
            values.put(DbHelper.COLUMNS_NAME,ExpensesActivity.getName());
            values.put("ex_morning_money",ExpensesActivity.getMorning_money().doubleValue());
            values.put("ex_noon_money",ExpensesActivity.getNoon_money().doubleValue());
            values.put("ex_evening_money",ExpensesActivity.getEvening_money().doubleValue());
            values.put("notes",ExpensesActivity.getNotes());
            values.put("ex_time",getCurrentTime());
        }catch (Exception e){
            e.printStackTrace();
        };*/
        return values;
    }

    //获得当前时间戳,方便插入数据库
    private String getCurrentTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
        java.util.Date curDate = new java.util.Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }
}
