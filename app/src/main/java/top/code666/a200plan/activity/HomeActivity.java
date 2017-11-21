package top.code666.a200plan.activity;

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

import top.code666.a200plan.R;
import top.code666.a200plan.view.BasePopupWindow;
import top.code666.a200plan.view.OptionsDialog;


public class HomeActivity extends BaseActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FloatingActionButton fab;

    private OptionsDialog mOptionsDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        mOptionsDialog = new OptionsDialog(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(HomeActivity.this,R.anim.rotate);
                fab.startAnimation(animation);
                mOptionsDialog.setShowAlpha(0.75f);
                mOptionsDialog.showAtLocation(view, Gravity.CENTER,0,0);
            }
        });
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
