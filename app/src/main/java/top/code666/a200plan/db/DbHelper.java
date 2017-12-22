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
    static final String TABLE_EX_IN = "exAndIn_table";//收入支出表
    //    public static final String TABLE_EC = "economization_table";
//    public static final String TABLE_AFF = "affair_table";//事件表
//    public static final String TABLE_IN = "income_table";//收入表
    static final String TABLE_PL = "plan_table";//计划表
    static final String TABLE_PL_CP = "plan_cp_table";//计划完成情况表
    static final String TABLE_CATES = "cates_table";//类别表
    static final String TABLE_RE = "repeat_table";

    static final String COLUMNS_ID = "_id";
    static final String COLUMNS_NAME = "name";
    static final String COLUMNS_CONTENT = "content";
    static final String COLUMNS_CATE = "cate";
    static final String COLUMNS_MONEY = "money";
    static final String COLUMNS_NOTES = "notes";
    static final String COLUMNS_TIME = "time";
    static final String COLUMNS_IMAGE = "imageSrc";
    static final String COLUMNS_TYPE = "type";

    private static final int VERSION = 2;


    public DbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Logger.d("onCreate");
        String CREATE_EX_IN = "create table if not exists " + TABLE_EX_IN + "(" +
                COLUMNS_ID + " integer PRIMARY KEY autoincrement," +
                COLUMNS_CATE + " integer," +
                COLUMNS_MONEY + " money," +
                COLUMNS_NOTES + " text," +
                COLUMNS_TIME + " datetime," +
                COLUMNS_TYPE + " integer);";
        db.execSQL(CREATE_EX_IN); //收入支出
        String CREATE_PL = "create table if not exists " + TABLE_PL + "(" +
                COLUMNS_ID + " integer primary key autoincrement," +
                COLUMNS_NAME + " varchar(100)," +
                COLUMNS_CONTENT + " text," +
                "pb_time datetime," +
                "pl_time datetime," +
                "remind_time datetime" +
                "cp_situation text);";
        db.execSQL(CREATE_PL); // 计划
        String CREATE_CA = "create table if not exists " + TABLE_CATES + "(" +
                COLUMNS_ID + " integer primary key autoincrement," +
                COLUMNS_IMAGE + " text," +
                COLUMNS_NAME + " varchar(100)," +
                COLUMNS_CATE + " varchar(20));";
        db.execSQL(CREATE_CA);//categories类别
        String CREATE_PL_CP = "create table if not exists " + TABLE_PL_CP + "(" +
                COLUMNS_ID + " integer primary key autoincrement," +
                COLUMNS_CONTENT + " text," +
                COLUMNS_TIME + " datetime);";
        db.execSQL(CREATE_PL_CP);
        String CREATE_RE = "create table if not exists " + TABLE_RE + "(" +
                COLUMNS_ID + " integer primary key autoincrement," +
                "repeat_time datetime," +
                "plan_id integer," +
                "status enum(0,1) default 0);";
        db.execSQL(CREATE_RE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Logger.t("onUpgrade").e("newVersion:"+newVersion);
        Logger.e("oldVersion:"+oldVersion);
        if(VERSION>oldVersion){
            db.execSQL("drop table if exists "+TABLE_EX_IN);
            db.execSQL("drop table if exists "+TABLE_PL);
//            db.execSQL("drop table if exists "+TABLE_AFF);
//            db.execSQL("drop table if exists "+TABLE_IN);
            db.execSQL("drop table if exists "+TABLE_CATES);
            db.execSQL("drop table if exists "+TABLE_PL_CP);
            db.execSQL("drop table if exists "+TABLE_RE);
            onCreate(db);
        }
    }
}
