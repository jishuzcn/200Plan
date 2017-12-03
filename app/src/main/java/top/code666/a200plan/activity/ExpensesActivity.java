package top.code666.a200plan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import top.code666.a200plan.R;
import top.code666.a200plan.adapter.CateAdapter;
import top.code666.a200plan.db.DbManager;
import top.code666.a200plan.entity.Cates;
import top.code666.a200plan.utils.ActivityCollector;
import top.code666.a200plan.utils.DialogTime;

/**
 * Created by code666 on 2017/11/28.
 */

public class ExpensesActivity extends AppCompatActivity implements View.OnClickListener,OnDateSetListener {

    private Button btn1,btn2;       //支出收入按钮
    private ImageButton back,daily,editso;      //返回、日历、备注
    private ImageView edit_img;     //类别图片
    private TextView ib_tv;     //时间显示
    private LinearLayout edit_ll;       //备注界面
    private EditText editor;        //备注

    private RecyclerView recyclerView;
    private CateAdapter cateAdapter;
    private ArrayList<Cates> mList;

    SimpleDateFormat sf = new SimpleDateFormat("yy.MM");
    private DbManager dbManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        ActivityCollector.addActivity(this);
        initView();
        initCates();

        recyclerView.setLayoutManager(new GridLayoutManager(this,6));
        cateAdapter = new CateAdapter(mList);
        recyclerView.setAdapter(cateAdapter);
        cateAdapter.setOnItemClickListener(new CateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Cates cate = mList.get(position);
                edit_img.setImageResource(cate.getImageSrc());
            }
        });

    }

    private void initView() {
        btn1 = findViewById(R.id.ex_top_btn1);
        btn2 = findViewById(R.id.ex_top_btn2);
        back = findViewById(R.id.ex_top_imageBtn);
        edit_img = findViewById(R.id.ex_edit_img);
        daily = findViewById(R.id.ex_edit_ib);
        ib_tv = findViewById(R.id.ex_ib_tv);
        recyclerView = findViewById(R.id.ex_recyclerViewForCate);
        editso = findViewById(R.id.ex_editso);
        edit_ll = findViewById(R.id.ex_edit_ll);
        editor = findViewById(R.id.ex_editor);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        back.setOnClickListener(this);
        daily.setOnClickListener(this);
        editso.setOnClickListener(this);
    }

    private void initCates() {
        dbManager = DbManager.getInstance(this);
        mList = dbManager.getAllCates();
        /*for(int i =0;i<15;i++){
            Cates cates = new Cates(1,R.mipmap.shop,"购物");
            mList.add(cates);
            Cates cate = new Cates(1,R.mipmap.doctor,"医院");
            mList.add(cate);
        }*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ex_top_btn1:
                Toast.makeText(this, "btn1 Clicked", Toast.LENGTH_SHORT).show();
                btn1.setText("支出");
                btn1.setTextColor(getResources().getColor(R.color.colorPrimary));
                btn2.setText("收入");
                btn2.setTextColor(getResources().getColor(R.color.colorAccent));
                btn2.setBackground(getResources().getDrawable(R.drawable.bg_ex_button_normal_right));
                btn1.setBackground(getResources().getDrawable(R.drawable.bg_ex_button_selected_left));
                break;
            case R.id.ex_top_btn2:
                Toast.makeText(this, "btn2 Clicked", Toast.LENGTH_SHORT).show();
                btn2.setText("收入");
                btn2.setTextColor(getResources().getColor(R.color.colorPrimary));
                btn1.setText("支出");
                btn1.setTextColor(getResources().getColor(R.color.colorAccent));
                btn1.setBackground(getResources().getDrawable(R.drawable.bg_ex_button_normal_left));
                btn2.setBackground(getResources().getDrawable(R.drawable.bg_ex_button_selected_right));
                break;
            case R.id.ex_top_imageBtn:
                startActivity(new Intent(this,HomeActivity.class));
                finish();
                break;
            case R.id.ex_edit_ib:
                long startTime = System.currentTimeMillis()+(3600*24*30*1000);
                new DialogTime(this,getSupportFragmentManager(),this,startTime).getDialog();
                break;
            case R.id.ex_editso:
                edit_ll.setVisibility(View.VISIBLE);
                editor.setFocusable(true);
                editor.setFocusableInTouchMode(true);
                editor.requestFocus();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        String s = getDateToString(millseconds);
        ib_tv.setVisibility(View.VISIBLE);
        ib_tv.setText(s);
    }

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }
}
