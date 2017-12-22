package top.code666.a200plan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.math.BigDecimal;

import top.code666.a200plan.R;
import top.code666.a200plan.db.DbManager;

/**
 * Created by code666 on 2017/12/17.
 */

public class ReportFragment03 extends Fragment {
    private View view;
    private TextView textView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.fragment_report03,container,false);
        }
        textView = view.findViewById(R.id.report03_value);
        BigDecimal b1 = new BigDecimal(DbManager.getInstance(getContext()).ExCountForAll(1));
        BigDecimal b2 = new BigDecimal(DbManager.getInstance(getContext()).ExCountForAll(2));
        String str = "0.00";
        if (b2.subtract(b1).doubleValue() > 0){
            str = "+"+(b2.subtract(b1)).toString();
        }else{
            str = (b2.subtract(b1)).toString();
        }
        textView.setText(str);
        return view;
    }
}
