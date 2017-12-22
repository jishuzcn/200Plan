package top.code666.a200plan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import top.code666.a200plan.R;
import top.code666.a200plan.db.SpManager;

/**
 * Created by code666 on 2017/12/19.
 */

public class SettingActivity extends BaseActivity {
    private Switch aSwitch01;
    private TextView tv1;
    private SpManager spManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        aSwitch01 = findViewById(R.id.switch01);
        tv1 = findViewById(R.id.setting_rl01_tv2);
        spManager = SpManager.getInstance(this);

        if (spManager.IsTitleChange()){
            aSwitch01.setChecked(true);
            tv1.setText("open");
        }else{
            aSwitch01.setChecked(false);
            tv1.setText("close");
        }
        /*aSwitch01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aSwitch01.isChecked()){
                    spManager.putSp("IsTitleChange",false);
//                    aSwitch01.setChecked(false);
                    tv1.setText("close");
                }else{
                    spManager.putSp("IsTitleChange",true);
//                    aSwitch01.setChecked(true);
                    tv1.setText("open");
                }
            }
        });*/
        aSwitch01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    spManager.putSp("IsTitleChange",true);
                    tv1.setText("open");
                }else{
                    spManager.putSp("IsTitleChange",false);
                    tv1.setText("close");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,HomeActivity.class));
        this.finish();
    }
}
