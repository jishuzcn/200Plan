package top.code666.a200plan.adapter;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import top.code666.a200plan.R;
import top.code666.a200plan.entity.Cates;

/**
 * Created by code666 on 2017/11/29.
 */

public class CateAdapter extends RecyclerView.Adapter<CateAdapter.ViewHolder> implements View.OnClickListener{
    private List<Cates> mCates;
    private OnItemClickListener mOnItemClickListener = null;

    public static interface OnItemClickListener {
        void onItemClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public CateAdapter(List<Cates> cates){
        mCates = cates;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cate,parent,false);
        ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Cates cate = mCates.get(position);
        holder.imageView.setImageResource(cate.getImageSrc());
        holder.textView.setText(cate.getName());
        //接口
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mCates.size();
    }

    @Override
    public void onClick(View v) {
        if(mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.adapter_cate_image);
            textView = itemView.findViewById(R.id.adapter_cate_tv);
        }
    }
}
