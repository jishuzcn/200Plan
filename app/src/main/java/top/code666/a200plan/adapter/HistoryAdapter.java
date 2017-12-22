package top.code666.a200plan.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import top.code666.a200plan.R;
import top.code666.a200plan.entity.Plan;

/**
 * Created by code666 on 2017/12/13.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{
    private List<Plan> plans;

    public HistoryAdapter(List<Plan> plans){
        this.plans = plans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_history,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Plan plan = plans.get(position);
        holder.publish_time.setText(plan.getPb_time().toString());
        holder.expect_time.setText(plan.getPl_time().toString());
        holder.content.setText(plan.getContent());
    }

    @Override
    public int getItemCount() {
        return plans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView publish_time,expect_time,content;
        public ViewHolder(View itemView) {
            super(itemView);
            publish_time = itemView.findViewById(R.id.adapter_history_publish_time);
            expect_time = itemView.findViewById(R.id.adapter_history_expect_time);
            content = itemView.findViewById(R.id.adapter_history_content);
        }
    }
}
