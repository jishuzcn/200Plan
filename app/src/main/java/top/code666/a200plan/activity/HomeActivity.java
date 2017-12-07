package top.code666.a200plan.activity;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.qap.ctimelineview.TimelineViewAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import top.code666.a200plan.R;
import top.code666.a200plan.db.DbManager;
import top.code666.a200plan.entity.Expenses;
import top.code666.a200plan.entity.TimelineRow;
import top.code666.a200plan.utils.L;
import top.code666.a200plan.utils.Tools;
import top.code666.a200plan.view.OptionsDialog;


public class HomeActivity extends BaseActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FloatingActionButton fab;
    private ListView timeline;
    private TextView ex_count, in_count;

    private OptionsDialog mOptionsDialog;
    private ArrayList<TimelineRow> timelineRowsList = new ArrayList<>();
    private ArrayAdapter<TimelineRow> myAdapter;
    private ArrayList<Expenses> datas;
    private DbManager dbManager = DbManager.getInstance(this);

    private int mStartIndex = 0,mMaxCount = 8;
    private int mTotalCount = -1;// 数据总数
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (mTotalCount == -1) mTotalCount = dbManager.getExCount();
        initView();
        initData();
        //创建时间轴适配器
        myAdapter = new top.code666.a200plan.adapter.TimelineViewAdapter(this, 0, timelineRowsList,
                false);
        //listView与时间轴绑定
        timeline.setAdapter(myAdapter);
        timeline.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE){
                    int position = timeline.getLastVisiblePosition();
                    // 如果屏幕上可见的最后一项是当前适配器数据源的最后一项，
                    // 并且数据还没有加载完，就加载下一批数据。
                    if (position != mTotalCount - 1) {
                        // 加载下一批数据
                        if(mStartIndex <= mTotalCount) mStartIndex += mMaxCount;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                initData();
                                myAdapter.notifyDataSetChanged();
                            }
                        },500);
                    } else if (position == mTotalCount -1) {
                        Toast.makeText(HomeActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    private void initData() {
        datas = dbManager.getLineData(mStartIndex,mMaxCount);
        Iterator<Expenses> it = datas.iterator();
        while (it.hasNext()){
            timelineRowsList.add(createlineRow(it.next()));
        }
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.category);
        }
        actionBar.setTitle("一些鸡汤什么的一些鸡汤什么的一些鸡汤什么的一些鸡汤什么的一些鸡汤什么的");
    }

    private void initView() {
        toolbar = findViewById(R.id.home_toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        fab = findViewById(R.id.idActionBar);
        timeline = findViewById(R.id.timeline_lv);
        ex_count = findViewById(R.id.home_ex_count);
        in_count = findViewById(R.id.home_in_count);

        ex_count.setText("-" + String.valueOf(dbManager.ExCountForMonth(1)));
        in_count.setText("+" + String.valueOf(dbManager.ExCountForMonth(2)));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOptionsDialog == null) {
                    mOptionsDialog = new OptionsDialog(HomeActivity.this, getFragmentManager());
                }
                Animation animation = AnimationUtils.loadAnimation(HomeActivity.this, R.anim.rotate);
                fab.startAnimation(animation);
                mOptionsDialog.showAtLocation(view, Gravity.CENTER, 0, 0);
            }
        });
        initToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.item_info:
                break;
            default:
                break;
        }
        return true;
    }

    private TimelineRow createlineRow(Expenses expenses) {
        //创建一行新的时间轴
        TimelineRow myRow = new TimelineRow(expenses.getId());
        //设置时间轴相关数据
        myRow.setDate(Tools.getStringTime(expenses.getTimes()));

        if(expenses.getType() == 1){
            myRow.setTitle(dbManager.getNameById(expenses.getCate())+" -"+String.valueOf(expenses.getMoney()));
        }else if(expenses.getType() == 2){
            myRow.setTitle("+"+String.valueOf(expenses.getMoney()));
        }

        myRow.setDescription(expenses.getNotes());
        myRow.setImage(BitmapFactory.decodeResource(getResources(), dbManager.getImgById(expenses.getCate())));
        myRow.setBellowLineColor(getResources().getColor(R.color.colorPrimaryDark));
        myRow.setBellowLineSize(3);
        myRow.setImageSize(35);
        myRow.setBackgroundColor(getResources().getColor(R.color.home_lv_bgColor));
        myRow.setBackgroundSize(35);
        myRow.setDateColor(getResources().getColor(R.color.homeDateColor));
        myRow.setTitleColor(getResources().getColor(R.color.homeTitleColor));
        myRow.setDescriptionColor(getResources().getColor(R.color.homeDescriptionColor));

        return myRow;
    }
}
