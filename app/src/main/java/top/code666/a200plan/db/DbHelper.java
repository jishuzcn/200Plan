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
    public static final String TABLE_EX_IN = "exAndIn_table";//收入支出表
//    public static final String TABLE_EC = "economization_table";
//    public static final String TABLE_AFF = "affair_table";//事件表
//    public static final String TABLE_IN = "income_table";//收入表
    public static final String TABLE_PL = "plan_table";//计划表
    public static final String TABLE_PL_CP = "plan_cp_table";//计划完成情况表
    public static final String TABLE_CATES = "cates_table";//类别表

    public static final String COLUMNS_ID = "_id";
    public static final String COLUMNS_NAME = "name";
    public static final String COLUMNS_CONTENT = "content";
    public static final String COLUMNS_CATE = "cate";
    public static final String COLUMNS_MONEY = "money";
    public static final String COLUMNS_NOTES = "notes";
    public static final String COLUMNS_TIME = "time";
    public static final String COLUMNS_IMAGE = "imageSrc";
    public static final String COLUMNS_TYPE = "type";

    //decimal(p,s)   p是指全部有几个数(digits)大小值，s是指小数点後有几位数
    private String CREATE_EX_IN = "create table if not exists "+TABLE_EX_IN+"("+
            COLUMNS_ID+" integer PRIMARY KEY autoincrement,"+
            COLUMNS_CATE+" integer,"+
            COLUMNS_MONEY+" money," +
            COLUMNS_NOTES+" text," +
            COLUMNS_TIME+" datetime," +
            COLUMNS_TYPE+" integer);"; //type 1代表支出  2收入
    /*private String CREATE_IN = "create table if not exists "+TABLE_IN+"("+
            COLUMNS_ID+" integer PRIMARY KEY autoincrement,"+
            COLUMNS_CATE+" integer,"+
            COLUMNS_MONEY+" money," +
            COLUMNS_NOTES+" text," +
            COLUMNS_TIME+" datetime);";*/
    /*private String CREATE_EC = "create table if not exists "+TABLE_EC+"(" +
            COLUMN_ID+" integer primary key autoincrement," +
            "title varchar(100)," +
            "ec_money decimal(10,2)," +
            "ec_time DATETIME);";*/
    /*private String CREATE_AF = "create table if not exists "+TABLE_AFF+"(" +
            COLUMNS_ID+" integer primary key autoincrement," +
            COLUMNS_CONTENT+" text," +
            COLUMNS_NAME+" varchar(100)," +
            "tag varchar(100)," +
            "pb_time DATETIME);";*/
    private String CREATE_PL = "create table if not exists "+TABLE_PL+"(" +
            COLUMNS_ID+" integer primary key autoincrement," +
            COLUMNS_NAME+" varchar(100)," +
            COLUMNS_CONTENT+" text," +
            "pb_time datetime," +
            "pl_time datetime," +
            "cp_situation text);"; //cp_situation 当前完成状态 | pl_time 预计完成时间 | pb_time 发布时间
    private String CREATE_PL_CP = "create table if not exists "+TABLE_PL_CP+"("+
            COLUMNS_ID+" integer primary key autoincrement," +
            COLUMNS_CONTENT+" text," +
            COLUMNS_TIME+" datetime);";
    private String CREATE_CA = "create table if not exists "+TABLE_CATES+"(" +
            COLUMNS_ID+" integer primary key autoincrement," +
            COLUMNS_IMAGE+" text,"+
            COLUMNS_NAME+" varchar(100)," +
            COLUMNS_CATE+" varchar(20));";

    private static final int VERSION = 2;


    public DbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Logger.d("onCreate");
        db.execSQL(CREATE_EX_IN);
        db.execSQL(CREATE_PL);
//        db.execSQL(CREATE_AF);
//        db.execSQL(CREATE_IN);
        db.execSQL(CREATE_CA);
        db.execSQL(CREATE_PL_CP);
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
            onCreate(db);
        }
    }
}
