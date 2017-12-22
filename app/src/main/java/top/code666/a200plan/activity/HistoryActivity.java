package top.code666.a200plan.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import top.code666.a200plan.R;
import top.code666.a200plan.adapter.HistoryAdapter;
import top.code666.a200plan.db.DbManager;
import top.code666.a200plan.entity.Plan;
import top.code666.a200plan.utils.ActivityCollector;

public class HistoryActivity extends BaseActivity implements View.OnClickListener{
    private RecyclerView recyclerView;
    private HistoryAdapter historyAdapter;
    private ArrayList<Plan> arrayPlans;
    private DbManager dbManager = null;
    private ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ActivityCollector.addActivity(this);
        dbManager = DbManager.getInstance(this);
        initView();
        int count = dbManager.getPlansCount();
//        Toast.makeText(this,""+count,Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        recyclerView = findViewById(R.id.history_recycler);
        back = findViewById(R.id.history_top_back);
        arrayPlans = dbManager.getAllPlans();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        historyAdapter = new HistoryAdapter(arrayPlans);

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new SpaceItemDecoration(30));
        recyclerView.setAdapter(historyAdapter);

        back.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.history_top_back:
                startActivity(new Intent(HistoryActivity.this,HomeActivity.class));
                finish();
                break;
            default:
                break;
        }
    }

    class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        int mSpace;
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.left = mSpace;
            outRect.right = mSpace;
            outRect.bottom = mSpace;
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = mSpace;
            }
        }

        public SpaceItemDecoration(int space) {
            this.mSpace = space;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,HomeActivity.class));
        finish();
    }
}
