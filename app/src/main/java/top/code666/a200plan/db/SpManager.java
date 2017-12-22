package top.code666.a200plan.db;

import android.content.Context;
import android.content.SharedPreferences;

import java.nio.channels.FileChannel;

/**
 * Created by code666 on 2017/12/8.
 * SharedPreferences相关操作
 */
public class SpManager {
    private Context mContext;
    private static final String s = "setting"; //设置
    private static SpManager instance = null;
    private SharedPreferences sp; // SharedPreferences  for  setting
    private SharedPreferences.Editor editor_setting;//Editor  for  setting

    private SpManager(Context context) {
        this.mContext = context;
    }

    public static synchronized SpManager getInstance(Context context) {
        if (instance == null) {
            instance = new SpManager(context);
        }
        return instance;
    }

    public Boolean IsTitleChange() {
        sp = mContext.getSharedPreferences(s, Context.MODE_PRIVATE);
        return sp.getBoolean("IsTitleChange", true);
    }

    public Boolean IsFirstRun() {
        sp = mContext.getSharedPreferences(s, Context.MODE_PRIVATE);
        return sp.getBoolean("IsFirstRun", true);
    }

    public String getMyName() {
        sp = mContext.getSharedPreferences(s, Context.MODE_PRIVATE);
        return sp.getString("username", "name");
    }

    public String getMyDes() {
        sp = mContext.getSharedPreferences(s, Context.MODE_PRIVATE);
        return sp.getString("describe", "describe");
    }

    //提交相关的设置参数 如果写入成功返回true  反之返回false
    public Boolean putSp(String name, Object def) {
        sp = mContext.getSharedPreferences(s, Context.MODE_PRIVATE);
        editor_setting = sp.edit();
        if (def instanceof Boolean) {
            editor_setting.putBoolean(name, (Boolean) def);
        } else if (def instanceof String) {
            editor_setting.putString(name, (String) def);
        } else if (def instanceof Integer) {
            editor_setting.putInt(name, (Integer) def);
        } else if (def instanceof Float) {
            editor_setting.putFloat(name, (Float) def);
        } else if (def instanceof Long) {
            editor_setting.putLong(name, (Long) def);
        }
        if (editor_setting.commit()) {
            editor_setting.clear();
            return true;
        }
        return false;
    }
}
