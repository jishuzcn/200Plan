package top.code666.a200plan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import top.code666.a200plan.activity.HomeActivity;
import top.code666.a200plan.db.DbInit;
import top.code666.a200plan.db.DbManager;
import top.code666.a200plan.utils.ActivityCollector;

public class WelcomeActivity extends AppCompatActivity {

    private DbManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        dbManager = DbManager.getInstance(this);
        if(dbManager.getCatesCount() == 0){
            new DbInit(this).init();
        }
        startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
        ActivityCollector.addActivity(this);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
