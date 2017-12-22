package top.code666.a200plan.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import top.code666.a200plan.R;
import top.code666.a200plan.fragment.ReportFragment01;
import top.code666.a200plan.fragment.ReportFragment02;
import top.code666.a200plan.fragment.ReportFragment03;
import top.code666.a200plan.utils.ActivityCollector;

public class ReportActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton back;
    private Button btn1, btn2, btn3;

    private Fragment f1, f2, f3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ActivityCollector.addActivity(this);
        initView();
    }

    private void initView() {
        back = findViewById(R.id.report_top_imageBtn);
        btn1 = findViewById(R.id.report_top_btn1);
        btn2 = findViewById(R.id.report_top_btn2);
        btn3 = findViewById(R.id.report_top_btn3);

        back.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

        setSelect(0);
    }

    private void setSelect(int i){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hidden(transaction);
        switch (i) {
            case 0:
                f1 = new ReportFragment01();
                transaction.add(R.id.report_fragment, f1);

                btn1.setText("支出");
                btn1.setTextColor(getResources().getColor(R.color.colorPrimary));
                btn1.setBackground(getResources().getDrawable(R.drawable.bg_ex_button_selected_left));
                break;
            case 1:
                f2 = new ReportFragment02();
                transaction.add(R.id.report_fragment, f2);

                btn2.setText("收入");
                btn2.setTextColor(getResources().getColor(R.color.colorPrimary));
                btn2.setBackground(getResources().getDrawable(R.drawable.bg_ex_button_selected_middle));
                break;
            case 2:
                f3 = new ReportFragment03();
                transaction.add(R.id.report_fragment,f3);

                btn3.setText("结余");
                btn3.setTextColor(getResources().getColor(R.color.colorPrimary));
                btn3.setBackground(getResources().getDrawable(R.drawable.bg_ex_button_selected_right));
                break;
            default:
                break;
        }
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        reset();
        switch (v.getId()) {
            case R.id.report_top_imageBtn:
                startActivity(new Intent(this,HomeActivity.class));
                finish();
                break;

            case R.id.report_top_btn1:
                setSelect(0);
                break;

            case R.id.report_top_btn2:
                setSelect(1);
                break;

            case R.id.report_top_btn3:
                setSelect(2);
                break;
            default:
                break;
        }
    }



    private void hidden(FragmentTransaction transaction) {
        // TODO Auto-generated method stub
        if(f1 != null){
            transaction.remove(f1);
        }

        if(f2 != null){
            transaction.remove(f2);
        }

        if(f3 != null){
            transaction.remove(f3);
        }
    }

    private void reset(){
        btn1.setText("支出");
        btn1.setTextColor(getResources().getColor(R.color.colorAccent));
        btn1.setBackground(getResources().getDrawable(R.drawable.bg_ex_button_normal_left));

        btn2.setText("收入");
        btn2.setTextColor(getResources().getColor(R.color.colorAccent));
        btn2.setBackground(getResources().getDrawable(R.drawable.bg_ex_button_normal_middle));

        btn3.setText("结余");
        btn3.setTextColor(getResources().getColor(R.color.colorAccent));
        btn3.setBackground(getResources().getDrawable(R.drawable.bg_ex_button_normal_right));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,HomeActivity.class));
        finish();
    }
}
