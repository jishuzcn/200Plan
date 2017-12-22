package top.code666.a200plan.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import top.code666.a200plan.db.DbInit;
import top.code666.a200plan.db.DbManager;
import top.code666.a200plan.db.SpManager;
import top.code666.a200plan.utils.ActivityCollector;
import top.code666.a200plan.utils.L;

/**
 * Created by code666 on 2017/11/20.
 */

public class BaseActivity extends AppCompatActivity {
    private SpManager sp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        L.init();
        sp = SpManager.getInstance(this);
        initSetting();//初始化设置
    }

    private void initSetting() {
        //程序第一次运行时,初始话相关设置信息
        if (sp.IsFirstRun()){
            sp.putSp("IsTitleChange",true);
            sp.putSp("IsFirstRun",false);
            sp.putSp("username","code666");
            sp.putSp("describe","emmmm");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
