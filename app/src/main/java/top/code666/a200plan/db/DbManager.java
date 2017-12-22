package top.code666.a200plan.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import top.code666.a200plan.entity.Cates;
import top.code666.a200plan.entity.Expenses;
import top.code666.a200plan.entity.Plan;
import top.code666.a200plan.utils.L;
import top.code666.a200plan.utils.Tools;

/**
 * Created by code666 on 2017/11/20.
 */

public class DbManager {

    private DbHelper dbHelper;
    private SQLiteDatabase db;
    private static DbManager instance = null;

    private DbManager(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getReadableDatabase();
    }

    public static synchronized DbManager getInstance(Context context) {
        if (instance == null) {
            instance = new DbManager(context);
        }
        return instance;
    }

    //时间轴数据获取
    public ArrayList<Expenses> getLineData(int startIndex, int maxCount) {
        ArrayList<Expenses> datas = new ArrayList<>();
        String sql = "select * from " + DbHelper.TABLE_EX_IN + " order by time desc limit " + maxCount + " offset " + startIndex;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int cate = cursor.getInt(cursor.getColumnIndex("cate"));
                String note = cursor.getString(cursor.getColumnIndex("notes"));
                String time = cursor.getString(cursor.getColumnIndex("time"));
                double money = cursor.getDouble(cursor.getColumnIndex("money"));
                int type = cursor.getInt(cursor.getColumnIndex("type"));
                datas.add(new Expenses(cate, note, time, money, type));
            } while (cursor.moveToNext());
        }
        return datas;
    }

    //获取分类或者收入的分类名 与 分类总值
    public HashMap<String,Double> getCountForCate(String type){ // type == 1 支出   type == 2 收入
        HashMap<String,Double> hashMap = new HashMap<>();
        /*String sql = "select c.name,sum(e.money) from exAndIn_table as e inner join cates_table" +
                " as c on e.cate = c._id group by e.cate having e.type = 1;";*/
        String sql = "select c."+DbHelper.COLUMNS_NAME+" as cate,sum(e."+DbHelper.COLUMNS_MONEY+") as sum from "+DbHelper.TABLE_EX_IN+" as e" +
                " inner join "+DbHelper.TABLE_CATES+" as c on e."+DbHelper.COLUMNS_CATE+" = c."+DbHelper.COLUMNS_ID
                +" group by e."+DbHelper.COLUMNS_CATE+" having e."+DbHelper.COLUMNS_TYPE+" = ?";
        Cursor cursor = db.rawQuery(sql,new String[]{type});
        if (cursor.moveToFirst()){
            do {
                String name = cursor.getString(cursor.getColumnIndex("cate"));
                Double money = cursor.getDouble(cursor.getColumnIndex("sum"));
                hashMap.put(name,money);
            }while (cursor.moveToNext());
        }
        return hashMap;
    }

    //得到ex表得总数
    public int getExCount() {
        Cursor cursor = db.rawQuery("select count(*) from " + DbHelper.TABLE_EX_IN, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        return 0;
    }

    /*
    * 得到Ex表id
    */
    public int getExId(Timestamp time){
        String sql = "select "+DbHelper.COLUMNS_ID+" from "+DbHelper.TABLE_EX_IN+" where " +
                ""+DbHelper.COLUMNS_TIME+" = ?";
        Cursor cursor = db.rawQuery(sql,new String[]{time.toString()});
        if (cursor.moveToFirst()){
            return cursor.getInt(0);
        }
        return 0;
    }

    /*
    * 所有月数的支出总数
    */
    public Double ExCountForAll(int type){
        Double totalInvestMoney= 0.00;
        String sql = "";
        switch (type) {
            case 1: //支出
                sql = "select sum(" + DbHelper.COLUMNS_MONEY + ") as money from " + DbHelper.TABLE_EX_IN + " where type = 1";
                break;
            case 2: //收入
                sql = "select sum(" + DbHelper.COLUMNS_MONEY + ") as money from " + DbHelper.TABLE_EX_IN + " where type = 2";
                break;
            case 3: //所有
                sql = "select sum(" + DbHelper.COLUMNS_MONEY + ") as money from " + DbHelper.TABLE_EX_IN;
                break;
            default:
                break;
        }
        if (!"".equals(sql)) {
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                    totalInvestMoney=  cursor.getDouble(cursor.getColumnIndex("money"));
            }
        }
        return totalInvestMoney;
    }

    /*
    * 本月收入支出总数
    */
    public Double ExCountForMonth(int type) {
        BigDecimal totalInvestMoney= new BigDecimal("0");
        String sql = "";
        switch (type) {
            case 1: //支出
                sql = "select " + DbHelper.COLUMNS_MONEY + " from " + DbHelper.TABLE_EX_IN + " where " + DbHelper.COLUMNS_TIME + "" +
                        ">=datetime('now','start of month','+0 month','-0 day') " +
                        "AND " + DbHelper.COLUMNS_TIME + "<datetime('now','start of month','+1 month','0 day') AND type = 1";
                break;
            case 2: //收入
                sql = "select " + DbHelper.COLUMNS_MONEY + " from " + DbHelper.TABLE_EX_IN + " where " + DbHelper.COLUMNS_TIME + "" +
                        ">=datetime('now','start of month','+0 month','-0 day') " +
                        "AND " + DbHelper.COLUMNS_TIME + "<datetime('now','start of month','+1 month','0 day') AND type = 2";
                break;
            case 3: //所有
                sql = "select " + DbHelper.COLUMNS_MONEY + " from " + DbHelper.TABLE_EX_IN + " where " + DbHelper.COLUMNS_TIME + "" +
                        ">=datetime('now','start of month','+0 month','-0 day') " +
                        "AND " + DbHelper.COLUMNS_TIME + "<datetime('now','start of month','+1 month','0 day')";
                break;
            default:
                break;
        }
        if (!"".equals(sql)) {
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {
                    totalInvestMoney=  totalInvestMoney.add(new BigDecimal(Double.toString(cursor.getDouble
                            (cursor.getColumnIndex(DbHelper.COLUMNS_MONEY)))));
//                    count += cursor.getDouble(cursor.getColumnIndex(DbHelper.COLUMNS_MONEY));
                } while (cursor.moveToNext());
            } else {
                L.d("查询失败 也许是sql语句问题");
            }
        }
        return totalInvestMoney.doubleValue();
    }

    /*
    * 获取所有分类表总数
    */
    public int getCatesCount() {
        Cursor cursor = db.query(DbHelper.TABLE_CATES, null, null, null, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    /*
    * 获取Plan表总数
    */
    public int getPlansCount(){
        int count = 0;
        String sql = "select count(*) from "+DbHelper.TABLE_PL;
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            count = cursor.getInt(0);
        }
        return count;
    }

    /*
    * 通过imageSrc得到类别表id
    */
    public int getCateIdByimg(int imageSrc) {
        int id = 0;
        Cursor cursor = db.rawQuery("select " + DbHelper.COLUMNS_ID + " from " + DbHelper.TABLE_CATES + " where " + DbHelper.COLUMNS_IMAGE + " = " + imageSrc, null);
        if (cursor.moveToFirst()) {
            id = cursor.getInt(0);
        }
        return id;
    }

    /*
    * 通过id得到类别得img地址
    */
    public int getImgById(int id){
        Cursor cursor =db.rawQuery("select "+DbHelper.COLUMNS_IMAGE+" from "+DbHelper.TABLE_CATES+
                " where "+DbHelper.COLUMNS_ID+" = "+id,null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    /*
    * 通过id得到类别名称
    */
    public String getNameById(int id){
        Cursor cursor = db.rawQuery("select "+DbHelper.COLUMNS_NAME+" from "+DbHelper.TABLE_CATES+"" +
                " where "+DbHelper.COLUMNS_ID+" = "+id,null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    /*
    * 获取 分类表--支出或者收入 所有信息
    */
    public ArrayList<Cates> getCates(String type) {// type值只能为 ex 或者 in
        if (!type.equals("ex") && !type.equals("in")) return null;

        ArrayList<Cates> cates = new ArrayList<>();
        Cursor cursor = db.query(DbHelper.TABLE_CATES, null, DbHelper.COLUMNS_CATE + " = ?", new String[]{type}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Cates c = new Cates();
                c.setId(cursor.getInt(cursor.getColumnIndex(DbHelper.COLUMNS_ID)));
                c.setImageSrc(cursor.getInt(cursor.getColumnIndex(DbHelper.COLUMNS_IMAGE)));
                c.setName(cursor.getString(cursor.getColumnIndex(DbHelper.COLUMNS_NAME)));
                cates.add(c);
            } while (cursor.moveToNext());
        }
        return cates;
    }

    /*
    * 获取 plan表信息
    */
    public ArrayList<Plan> getAllPlans(){
        ArrayList<Plan> plans = new ArrayList<>();
        String sql = "select * from "+DbHelper.TABLE_PL;
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                String content = cursor.getString(cursor.getColumnIndex(DbHelper.COLUMNS_CONTENT));
                String pb_time = cursor.getString(cursor.getColumnIndex("pb_time"));
                String pl_time = cursor.getString(cursor.getColumnIndex("pl_time"));
                plans.add(new Plan(content,pl_time,pb_time));
            }while (cursor.moveToNext());
        }
        return plans;
    }

    /*
    * 插入支出 收入表
    */
    public boolean insertExTable(Expenses expenses) {
        ContentValues values;
        try {
            values = ExInContentValues(expenses);
            if (db.insert(DbHelper.TABLE_EX_IN, null, values) != -1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
    * 插入计划表
    */
    public boolean insertPlanTable(Plan plan){
        ContentValues values;
        try {
            values = PlanContentValues(plan);
            if (db.insert(DbHelper.TABLE_PL, null, values) != -1) {
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public int DeletePlanById(int id){
        return db.delete(DbHelper.TABLE_PL,"_id = ?",new String[]{String.valueOf(id)});
    }

    //支出收入表的Values
    private ContentValues ExInContentValues(Expenses expenses) {
        ContentValues values = new ContentValues();
        try {
            values.put(DbHelper.COLUMNS_CATE, expenses.getCate());
            values.put(DbHelper.COLUMNS_NOTES, expenses.getNotes());
            values.put(DbHelper.COLUMNS_TIME, expenses.getTimes().toString());
            values.put(DbHelper.COLUMNS_MONEY, expenses.getMoney());
            values.put(DbHelper.COLUMNS_TYPE, expenses.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return values;
    }

    //只获取content pl_time pb_time
    //另外推给后期
    private ContentValues PlanContentValues(Plan plan){
        ContentValues values = new ContentValues();
        try{
            values.put(DbHelper.COLUMNS_CONTENT,plan.getContent());
            values.put("pb_time", plan.getPb_time().toString());
            values.put("pl_time",plan.getPl_time().toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return values;
    }
}
