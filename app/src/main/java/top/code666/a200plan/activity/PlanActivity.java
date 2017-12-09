package top.code666.a200plan.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import top.code666.a200plan.R;
import top.code666.a200plan.utils.ActivityCollector;
import top.code666.a200plan.utils.DialogTime;

public class PlanActivity extends BaseActivity implements View.OnClickListener,OnDateSetListener{

    private ImageButton back,ok;
    private LinearLayout pickTime;
    private TextView time_tv;

    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        ActivityCollector.addActivity(this);
        initView();

    }

    private void initView() {
        back = findViewById(R.id.plan_top_back);
        ok = findViewById(R.id.plan_top_ok);
        pickTime = findViewById(R.id.plan_content_01);
        time_tv = findViewById(R.id.plan_pickTime_tv);
        back.setOnClickListener(this);
        ok.setOnClickListener(this);
        pickTime.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.plan_top_back:
                startActivity(new Intent(this,HomeActivity.class));
                finish();
                break;
            case R.id.plan_top_ok:
                break;
            case R.id.plan_content_01:
                // mDialogAll.show(getSupportFragmentManager(), "all");
                new DialogTime(this,getSupportFragmentManager(),this).getDialogForPlan();
                break;
            default:
                break;
        }
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        String s = getDateToString(millseconds);
        time_tv.setText(s);
    }

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }
}
