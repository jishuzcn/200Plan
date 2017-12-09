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

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import top.code666.a200plan.R;
import top.code666.a200plan.adapter.CateAdapter;
import top.code666.a200plan.db.DbManager;
import top.code666.a200plan.entity.Cates;
import top.code666.a200plan.entity.Expenses;
import top.code666.a200plan.utils.ActivityCollector;
import top.code666.a200plan.utils.DialogTime;
import top.code666.a200plan.utils.Tools;

/**
 * Created by code666 on 2017/11/28.
 */

public class ExpensesActivity extends BaseActivity implements View.OnClickListener, OnDateSetListener {

    private Button btn1, btn2;       //支出收入按钮
    private ImageButton back, daily, editso, ok;      //返回、日历、备注、确认
    private ImageView edit_img;     //类别图片
    private TextView ib_tv;     //时间显示
    private LinearLayout edit_ll;       //备注界面
    private EditText editor, editText;        //备注
    private String type = "支出";
    private String times="";

    private RecyclerView recyclerView;
    private CateAdapter cateAdapter;
    private ArrayList<Cates> mList;

    SimpleDateFormat sf = new SimpleDateFormat("yy.MM");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private DbManager dbManager = null;
    private Timestamp timestamp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        ActivityCollector.addActivity(this);
        initView();
        dbManager = DbManager.getInstance(this);
        mList = dbManager.getCates("ex"); //获取支出分类信息

        edit_img.setTag(R.mipmap.cook);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 6));
        cateAdapter = new CateAdapter(mList);
        recyclerView.setAdapter(cateAdapter);
        cateAdapter.setOnItemClickListener(new CateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Cates cate = mList.get(position);
                edit_img.setImageResource(cate.getImageSrc());
                edit_img.setTag(cate.getImageSrc());
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
        ok = findViewById(R.id.ex_top_ok);
        editText = findViewById(R.id.editText);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        back.setOnClickListener(this);
        daily.setOnClickListener(this);
        editso.setOnClickListener(this);
        ok.setOnClickListener(this);
    }

    //btn1 与 btn2 点击事件还可以优化一下
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ex_top_btn1:
                btn1.setText("支出");
                btn1.setTextColor(getResources().getColor(R.color.colorPrimary));
                btn2.setText("收入");
                btn2.setTextColor(getResources().getColor(R.color.colorAccent));
                btn2.setBackground(getResources().getDrawable(R.drawable.bg_ex_button_normal_right));
                btn1.setBackground(getResources().getDrawable(R.drawable.bg_ex_button_selected_left));
                if (dbManager != null) {
                    mList = dbManager.getCates("ex"); //获取支出分类信息
                } else {
                    dbManager = DbManager.getInstance(this);
                    mList = dbManager.getCates("ex"); //获取收入分类信息
                }
                cateAdapter = new CateAdapter(mList);
                recyclerView.setAdapter(cateAdapter);
                edit_img.setImageResource(R.mipmap.cook);
                edit_img.setTag(R.mipmap.cook);
                editor.setText("");
                edit_ll.setVisibility(View.GONE);
                type = "支出";
                cateAdapter.setOnItemClickListener(new CateAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Cates cate = mList.get(position);
                        edit_img.setImageResource(cate.getImageSrc());
                        edit_img.setTag(cate.getImageSrc());
                    }
                });
                break;
            case R.id.ex_top_btn2:
                btn2.setText("收入");
                btn2.setTextColor(getResources().getColor(R.color.colorPrimary));
                btn1.setText("支出");
                btn1.setTextColor(getResources().getColor(R.color.colorAccent));
                btn1.setBackground(getResources().getDrawable(R.drawable.bg_ex_button_normal_left));
                btn2.setBackground(getResources().getDrawable(R.drawable.bg_ex_button_selected_right));
                if (dbManager != null) {
                    mList = dbManager.getCates("in"); //获取支出分类信息
                } else {
                    dbManager = DbManager.getInstance(this);
                    mList = dbManager.getCates("in"); //获取支出分类信息
                }
                cateAdapter = new CateAdapter(mList);
                recyclerView.setAdapter(cateAdapter);
                edit_img.setImageResource(R.mipmap.gong);
                edit_img.setTag(R.mipmap.gong);
                editor.setText("");
                edit_ll.setVisibility(View.GONE);
                type = "收入";
                cateAdapter.setOnItemClickListener(new CateAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Cates cate = mList.get(position);
                        edit_img.setImageResource(cate.getImageSrc());
                        edit_img.setTag(cate.getImageSrc());
                    }
                });
                break;
            case R.id.ex_top_imageBtn:
                startActivity(new Intent(this, HomeActivity.class));
                finish();
                break;
            case R.id.ex_edit_ib:
                new DialogTime(this, getSupportFragmentManager(), this).getDialogForEx();
                break;
            case R.id.ex_editso:
                edit_ll.setVisibility(View.VISIBLE);
                editor.setFocusable(true);
                editor.setFocusableInTouchMode(true);
                editor.requestFocus();
                break;
            case R.id.ex_top_ok:
                if (dbManager == null) dbManager = DbManager.getInstance(this);
                int cate = dbManager.getCateIdByimg((int) edit_img.getTag());
                String num = editText.getText().toString();
                if("".equals(num) || ".".equals(num)) break;
                if("".equals(times)) {
                    timestamp = Tools.getStampTime();
                }else{
                    timestamp = Tools.getStampTime(times);
                }
                if (!num.startsWith(".") && num.lastIndexOf(".") >= num.length() - 3 && !num.endsWith(".")|| !num.contains(".")) {
                    //小数点不能在首位 && 小数点后只能有两位 || 不包含小数点
                    if(!num.contains(".")) num +=".00";
                    if(num.lastIndexOf(".") == num.length() -2) num += "0"; //一位小数
                    switch (type){
                        case "支出":
                            if (dbManager.insertExTable(new Expenses(cate, editor.getText().toString(),
                                    timestamp, Double.valueOf(num),1))) {
                                Intent intent = new Intent(this,HomeActivity.class);
                                /* 不知为啥传过去为空 debug这里没问题
                                intent.putExtra("ok",new Expenses(dbManager.getExId(timestamp),cate, editor.getText().toString(),
                                        timestamp, Double.valueOf(num),1));*/
                                startActivity(intent);
                                finish();
                                Toast.makeText(this, "添加成功", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(this, "添加失败", Toast.LENGTH_LONG).show();
                            }
                            break;
                        case "收入":
                            if (dbManager.insertExTable(new Expenses(cate, editor.getText().toString(),
                                    timestamp, Double.valueOf(num),2))) {
                                Intent intent = new Intent(this,HomeActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(this, "添加成功", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(this, "添加失败", Toast.LENGTH_LONG).show();
                            }
                            break;
                        default:
                            Toast.makeText(this,"未知错误",Toast.LENGTH_SHORT).show();
                            break;
                    }
                    editText.setText("");
                    times = "";
                    editor.setText("");
                    ib_tv.setText("");
                } else {
                    Toast.makeText(this, "你的数字输入错误---小数点后面只能有两位小数", Toast.LENGTH_SHORT).show();
                }
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
        times = sdf.format(new Date(millseconds));
    }

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }
}
