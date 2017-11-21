package top.code666.a200plan.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.orhanobut.logger.Logger;

/**
 * Created by code666 on 2017/11/20.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "200plan.db";
    public static final String TABLE_EX = "expenses_table";
    public static final String TABLE_EC = "economization_table";
    public static final String TABLE_AFF = "affair_table";
    public static final String TABLE_PL = "plan_table";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMNS_CONTENT = "content";

    //decimal(p,s)   p是指全部有几个数(digits)大小值，s是指小数点後有几位数
    private String CREATE_EX = "create table if not exists "+TABLE_EX+"("+
            COLUMN_ID+" integer PRIMARY KEY autoincrement,"+
            COLUMN_NAME+" varchar(100),"+
            "ex_morning_money decimal(10,2)," +
            "ex_noon_money decimal(10,2)," +
            "ex_evening_money decimal(10,2)," +
            "notes text," +
            "ex_time text);";
    private String CREATE_EC = "create table if not exists "+TABLE_EC+"(" +
            COLUMN_ID+" integer primary key autoincrement," +
            "title varchar(100)," +
            "ec_money decimal(10,2)," +
            "ec_time text);";
    private String CREATE_AF = "create table if not exists "+TABLE_AFF+"(" +
            COLUMN_ID+" integer primary key autoincrement," +
            COLUMNS_CONTENT+" text," +
            "name varchar(100)," +
            "tag varchar(100)," +
            "pb_time text);";
    private String CREATE_PL = "create table if not exists "+TABLE_PL+"(" +
            COLUMN_ID+" integer primary key autoincrement," +
            COLUMN_NAME+" varchar(100)," +
            COLUMNS_CONTENT+" text," +
            "pb_time text," +
            "pl_time text," +
            "cp_situation text);"; //cp_situation 当前完成状态 | pl_time 预计完成时间 | pb_time 发布时间

    private static final int VERSION = 1;


    public DbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Logger.d("onCreate");
        db.execSQL(CREATE_EX);
        db.execSQL(CREATE_EC);
        db.execSQL(CREATE_AF);
        db.execSQL(CREATE_PL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Logger.t("onUpgrade").e("newVersion:"+newVersion);
        Logger.e("oldVersion:"+oldVersion);
        if(VERSION>oldVersion){
            db.execSQL("drop table if exists "+TABLE_EX);
            db.execSQL("drop table if exists "+TABLE_EC);
            db.execSQL("drop table if exists "+TABLE_PL);
            db.execSQL("drop table if exists "+TABLE_AFF);
            onCreate(db);
        }
    }
}
