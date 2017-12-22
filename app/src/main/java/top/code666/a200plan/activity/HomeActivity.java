package top.code666.a200plan.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

import top.code666.a200plan.R;
import top.code666.a200plan.db.DbManager;
import top.code666.a200plan.db.SpManager;
import top.code666.a200plan.entity.Expenses;
import top.code666.a200plan.entity.TimelineRow;
import top.code666.a200plan.utils.ActivityCollector;
import top.code666.a200plan.utils.L;
import top.code666.a200plan.utils.Tools;
import top.code666.a200plan.view.OptionsDialog;

public class HomeActivity extends BaseActivity implements OnNavigationItemSelectedListener, View.OnClickListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FloatingActionButton fab;
    private ActionBar actionBar;
    private ListView timeline;
    private TextView ex_count, in_count;
    private OptionsDialog mOptionsDialog;
    private TextView toolbarTitle;
    private EditText et1, et2;

    //navigationView  for  headerView;
    private ImageView headerImg;
    private TextView headerTv1, headerTv2;
    private ImageButton headerBtn;
    private RelativeLayout headerBg;

    private ArrayList<TimelineRow> timelineRowsList = new ArrayList<>();
    private ArrayAdapter<TimelineRow> myAdapter;
    private ArrayList<Expenses> datas;
    private DbManager dbManager = DbManager.getInstance(this);
    private SpManager sp = SpManager.getInstance(this);

    private int mStartIndex = 0, mMaxCount = 8; //时间戳数据查询参数
    private int mTotalCount = -1;// 数据总数

    private Handler handler = new Handler(); //刷新标题栏
    private MyHandler UI = new MyHandler(this);  //刷新界面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ActivityCollector.addActivity(this);
        if (mTotalCount == -1) mTotalCount = dbManager.getExCount(); //等于Ex（支出收入）表的总数

        initView();//初始化视图
        initData();//初始化数据

        //标题栏文字是否改变
        if (sp.IsTitleChange()) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    String homeTitles[] = getResources().getStringArray(R.array.homeTitles);
                    int id = (int) (Math.random() * (homeTitles.length - 1));
                    toolbarTitle.setText(homeTitles[id]);
                    handler.postDelayed(this, 30000);//10s
                }
            };
            handler.postDelayed(runnable, 2000);
        } else {
            toolbarTitle.setText(getResources().getString(R.string.app_name));
        }

    }

    private void initData() {
        datas = dbManager.getLineData(mStartIndex, mMaxCount);
        Iterator<Expenses> it = datas.iterator();
        while (it.hasNext()) {
            timelineRowsList.add(createlineRow(it.next()));
        }
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.category);
        }
        actionBar.setDisplayShowTitleEnabled(false);
    }

    private void initView() {
        toolbar = findViewById(R.id.home_toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        fab = findViewById(R.id.idActionBar);
        timeline = findViewById(R.id.timeline_lv);
        ex_count = findViewById(R.id.home_ex_count);
        in_count = findViewById(R.id.home_in_count);
        toolbarTitle = findViewById(R.id.toolbar_title);

        ex_count.setText("-" + String.valueOf(dbManager.ExCountForMonth(1)));
        in_count.setText("+" + String.valueOf(dbManager.ExCountForMonth(2)));

        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);
        headerImg = view.findViewById(R.id.navHeader_img);
        headerTv1 = view.findViewById(R.id.navHeader_tv1);
        headerTv2 = view.findViewById(R.id.navHeader_tv2);
        headerBtn = view.findViewById(R.id.navHeader_ib);
        headerBg = view.findViewById(R.id.navHeader_bg);

        headerTv1.setText(sp.getMyName());
        headerTv2.setText(sp.getMyDes());

        headerBtn.setOnClickListener(this);
        fab.setOnClickListener(this);

        //创建时间轴适配器
        myAdapter = new top.code666.a200plan.adapter.TimelineViewAdapter(this, 0, timelineRowsList,
                false);
        //listView与时间轴绑定
        timeline.setAdapter(myAdapter);
        timeline.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE) {
                    int position = timeline.getLastVisiblePosition();
                    // 如果屏幕上可见的最后一项是当前适配器数据源的最后一项，
                    // 并且数据还没有加载完，就加载下一批数据。
                    if (position != mTotalCount - 1) {
                        // 加载下一批数据
                        // 40  <= 42  start+max --->error
                        if ((mStartIndex + mMaxCount) >= mTotalCount) {
                            mStartIndex = mTotalCount;
                            mMaxCount = 0;
                        } else {
                            mStartIndex += mMaxCount;
                        }
                        Message message = UI.obtainMessage();
                        message.what = 2;
                        UI.handleMessage(message);
                    } else if (position == mTotalCount - 1) {
                        Toast.makeText(HomeActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
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
                /*关于我---一个小小程序员，目前再找实习工作*/
                Snackbar.make(getWindow().getDecorView().findViewById(R.id.drawerLayout),
                        "关于我 --- 一个小小程序员，目前再找实习工作", Snackbar.LENGTH_INDEFINITE).setAction("明白了",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();
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

        if (expenses.getType() == 1) {
            myRow.setTitle(dbManager.getNameById(expenses.getCate()) + " -" + String.valueOf(expenses.getMoney()));
        } else if (expenses.getType() == 2) {
            myRow.setTitle(dbManager.getNameById(expenses.getCate()) + " +" + String.valueOf(expenses.getMoney()));
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

    @Override
    protected void onRestart() {
        super.onRestart();
        //重新加载时间轴
        Message message = UI.obtainMessage();
        message.what = 1;
        UI.handleMessage(message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("你是否要退出应用？");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
//                        new ActivityCollector().finishAll();
                    ActivityCollector.finishAll();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    dialog.dismiss();
                }
            });
            builder.create().show();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.expenses_item:
                startActivity(new Intent(HomeActivity.this, ReportActivity.class));
                finish();
                break;
            case R.id.plan_item:
                startActivity(new Intent(HomeActivity.this, HistoryActivity.class));
                finish();
                break;
            case R.id.setting_item:
                startActivity(new Intent(HomeActivity.this, SettingActivity.class));
                finish();
                break;
            case R.id.exit_item:
                // TODO Auto-generated method stub
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("提示");
                builder.setMessage("你确定要一键退出所有activity吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
//                        new ActivityCollector().finishAll();
                        ActivityCollector.finishAll();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                    }
                });
                builder.create().show();
                break;
            default:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.idActionBar:
                if (mOptionsDialog == null) {
                    mOptionsDialog = new OptionsDialog(HomeActivity.this, getFragmentManager());
                }
                Animation animation = AnimationUtils.loadAnimation(HomeActivity.this, R.anim.rotate);
                fab.startAnimation(animation);
                mOptionsDialog.showAtLocation(v, Gravity.CENTER, 0, 0);
                break;

            case R.id.navHeader_ib:
                drawerLayout.closeDrawer(GravityCompat.START);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                View view = LayoutInflater.from(this).inflate(R.layout.view_name, null);
                et1 = view.findViewById(R.id.view_name_et1);
                et2 = view.findViewById(R.id.view_name_et2);

                builder.setTitle("修改名称和签名").setView(R.layout.view_name).setPositiveButton("确定", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String s1 = et1.getText().toString();
                                String s2 = et2.getText().toString();
                                L.e(s1 + "--||--" + s2);
                                if (!"".equals(s1)) {
                                    sp.putSp("username", s1);
                                }
                                if (!"".equals(s2)) {
                                    sp.putSp("describe", s2);
                                }
                                headerTv1.setText(sp.getMyName());
                                headerTv2.setText(sp.getMyDes());
                                Toast.makeText(HomeActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
                break;

            default:
                break;
        }
    }

    //Google推荐的handler写法
    private static class MyHandler extends Handler {
        private final WeakReference<HomeActivity> weakReference;

        private MyHandler(HomeActivity activity) {
            weakReference = new WeakReference<>(activity);
        }

        public void handleMessage(Message message) {
            HomeActivity t = weakReference.get();
            if (t != null) {
                switch (message.what) {
                    case 1: // 当Activity -- onRestart时重新获取界面数据
                        t.ex_count.setText("-" + String.valueOf(t.dbManager.ExCountForMonth(1)));
                        t.in_count.setText("+" + String.valueOf(t.dbManager.ExCountForMonth(2)));
                        t.mStartIndex = 0;
                        t.myAdapter.clear();
                        t.initData();
                        t.myAdapter.notifyDataSetChanged();
                        break;
                    case 2: //时间轴滑到底部加载数据
                        t.initData();
                        t.myAdapter.notifyDataSetChanged();
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
