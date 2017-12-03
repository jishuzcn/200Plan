package top.code666.a200plan.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import top.code666.a200plan.R;

/**
 * Created by code666 on 2017/12/3.
 */

public class DbInit {
    private Context mContext;

    public DbInit(Context context){
        mContext = context;
    }

    public void init(){
        DbHelper dbHelper = new DbHelper(mContext);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.execSQL("insert into "+DbHelper.TABLE_CATES+" VALUES(1,"+R.mipmap.cook+",'餐饮')");
        db.execSQL("insert into "+DbHelper.TABLE_CATES+" VALUES(2,"+R.mipmap.doctor+",'医院')");
        db.execSQL("insert into "+DbHelper.TABLE_CATES+" VALUES(3,"+R.mipmap.edu+",'教育')");
        db.execSQL("insert into "+DbHelper.TABLE_CATES+" VALUES(4,"+R.mipmap.happy+",'娱乐')");
        db.execSQL("insert into "+DbHelper.TABLE_CATES+" VALUES(5,"+R.mipmap.home+",'家用')");
        db.execSQL("insert into "+DbHelper.TABLE_CATES+" VALUES(6,"+R.mipmap.love+",'人情')");
        db.execSQL("insert into "+DbHelper.TABLE_CATES+" VALUES(7,"+R.mipmap.shop+",'购物')");
        db.execSQL("insert into "+DbHelper.TABLE_CATES+" VALUES(8,"+R.mipmap.traffic+",'交通')");
        db.execSQL("insert into "+DbHelper.TABLE_CATES+" VALUES(9,"+R.mipmap.trip+",'旅行')");
        db.execSQL("insert into "+DbHelper.TABLE_CATES+" VALUES(10,"+R.mipmap.other+",'其他')");
        db.close();
        dbHelper.close();
    }
}
