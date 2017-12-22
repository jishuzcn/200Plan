package top.code666.a200plan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import top.code666.a200plan.R;
import top.code666.a200plan.db.DbManager;
import top.code666.a200plan.entity.Plan;
import top.code666.a200plan.fragment.RemindDialog;
import top.code666.a200plan.fragment.RepeatDialog;
import top.code666.a200plan.utils.ActivityCollector;
import top.code666.a200plan.utils.DialogTime;
import top.code666.a200plan.utils.Tools;

public class PlanActivity extends BaseActivity implements View.OnClickListener,OnDateSetListener,RemindDialog.RemindListener,RepeatDialog.RepeatListener{

    private ImageButton back,ok;
    private LinearLayout pickTime;
    private TextView time_tv,remind_tv,repeat_tv;
//    private RelativeLayout remind,repeat;
    private DbManager dbManager = null;
    private EditText content;
    private Timestamp pl_time,pb_time;

    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        ActivityCollector.addActivity(this);
        initView();
//        repeat_tv.setText("无");
        time_tv.setText(Tools.getStringTime()+" 今天");
    }

    private void initView() {
        back = findViewById(R.id.plan_top_back);
        ok = findViewById(R.id.plan_top_ok);
        pickTime = findViewById(R.id.plan_content_01);
        time_tv = findViewById(R.id.plan_pickTime_tv);
        content = findViewById(R.id.plan_content_et);
        /*remind = findViewById(R.id.plan_remind_ll);
        repeat = findViewById(R.id.plan_repeat_ll);
        remind_tv = findViewById(R.id.plan_remind_tv);
        repeat_tv = findViewById(R.id.plan_repeat_tv);*/

        back.setOnClickListener(this);
        ok.setOnClickListener(this);
        pickTime.setOnClickListener(this);
        /*remind.setOnClickListener(this);
        repeat.setOnClickListener(this);*/
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
                dbManager = DbManager.getInstance(this);
                String c = content.getText().toString();
                if (!"".equals(c)){
                    pb_time = Tools.getStampTime();
                    pl_time = Tools.getStampTime(time_tv.getText().toString());
                    if(dbManager.insertPlanTable(new Plan(c,pl_time,pb_time))){
                        Intent intent = new Intent(this,HomeActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(this, "添加成功", Toast.LENGTH_LONG).show();
                    } else {
                      Toast.makeText(this,"添加失败",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "内容不能为空...", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.plan_content_01:
                // mDialogAll.show(getSupportFragmentManager(), "all");
                new DialogTime(this,getSupportFragmentManager(),this).getDialogForPlan();
                break;
            /*case R.id.plan_remind_ll:
                RemindDialog remindDialog = new RemindDialog();
                remindDialog.show(getFragmentManager(),"remindDialog");
                break;
            case R.id.plan_repeat_ll:
                RepeatDialog repeatDialog = new RepeatDialog();
                repeatDialog.show(getFragmentManager(),"repeatDialog");
                break;*/
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

    @Override
    public void RemindParam(String remindTime) {
        remind_tv.setText(remindTime);
    }

    @Override
    public void RepeatValue(ArrayList<String> repeatValues) {
        ArrayList<String> repeat = repeatValues;
        if(repeat.isEmpty()){
            repeat_tv.setText("无");
        }else{
            repeat_tv.setText(repeat.toString());
        }
    }
}
