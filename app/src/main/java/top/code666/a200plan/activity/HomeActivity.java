package top.code666.a200plan.activity;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.qap.ctimelineview.TimelineViewAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import top.code666.a200plan.R;
import top.code666.a200plan.entity.TimelineRow;
import top.code666.a200plan.view.OptionsDialog;


public class HomeActivity extends BaseActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FloatingActionButton fab;
    private ListView timeline;

    private OptionsDialog mOptionsDialog;
    private ArrayList<TimelineRow> timelineRowsList = new ArrayList<>();
    ArrayAdapter<TimelineRow> myAdapter;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOptionsDialog == null){
                    mOptionsDialog = new OptionsDialog(HomeActivity.this,getFragmentManager());
                }
                Animation animation = AnimationUtils.loadAnimation(HomeActivity.this,R.anim.rotate);
                fab.startAnimation(animation);
//                mOptionsDialog.setShowAlpha(0.25f);
                mOptionsDialog.showAtLocation(view, Gravity.CENTER,0,0);
            }
        });
        //Test 添加随机行到list
        for (int i = 0;i<15;i++){
            timelineRowsList.add(createRandomTimelineRow(i));
        }
        //Create the Timeline Adapter
        ArrayAdapter<TimelineRow> myAdapter = new top.code666.a200plan.adapter.TimelineViewAdapter(this,0,timelineRowsList,
                false);
        // Get the ListView and Bind it with the Timeline Adapter
        timeline.setAdapter(myAdapter);
    }

    //Method to create new Timeline Row
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private TimelineRow createRandomTimelineRow(int id) {

        // Create new timeline row (pass your Id)
        TimelineRow myRow = new TimelineRow(id);

        //to set the row Date (optional)
        myRow.setDate(getRandomDate());
        //to set the row Title (optional)
        myRow.setTitle("Title " + id);
        //to set the row Description (optional)
        myRow.setDescription("Description " + id);
        //to set the row bitmap image (optional)
        myRow.setImage(BitmapFactory.decodeResource(getResources(), R.mipmap.trip));
        //to set row Below Line Color (optional)
        myRow.setBellowLineColor(getResources().getColor(R.color.colorPrimaryDark));
        //to set row Below Line Size in dp (optional)
        myRow.setBellowLineSize(3);
        //to set row Image Size in dp (optional)
        myRow.setImageSize(35);
        //to set background color of the row image (optional)
        myRow.setBackgroundColor(getResources().getColor(R.color.home_lv_bgColor));
        //to set the Background Size of the row image in dp (optional)
        myRow.setBackgroundSize(35);
        //to set row Date text color (optional)
        myRow.setDateColor(getResources().getColor(R.color.homeDateColor));
        //to set row Title text color (optional)
        myRow.setTitleColor(getResources().getColor(R.color.homeTitleColor));
        //to set row Description text color (optional)
        myRow.setDescriptionColor(getResources().getColor(R.color.homeDescriptionColor));

        return myRow;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Date getRandomDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date startDate = null;
        Date endDate = new Date();
        try {
            startDate = sdf.parse("26/11/2017 00:00:00");
//            long random = ThreadLocalRandom.current().nextLong(startDate.getTime(), endDate.getTime());
            long random = startDate.getTime();
            endDate = new Date(random);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return endDate;
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
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
        initToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
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
}
