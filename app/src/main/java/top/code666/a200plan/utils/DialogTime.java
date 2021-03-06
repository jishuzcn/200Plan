package top.code666.a200plan.utils;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import top.code666.a200plan.R;

/**
 * Created by code666 on 2017/12/2.
 */

public class DialogTime {
    private Context mContext;
    private FragmentManager manager;
    private OnDateSetListener listener;

    TimePickerDialog mDialogAll;
    long twoYears = 3L * 365 * 1000 * 60 * 60 * 24L;
    long fiveYsers = 5L * 365 * 1000 * 60 * 60 * 24L;

    public DialogTime(Context context, FragmentManager manager,OnDateSetListener listener){
        this.mContext = context;
        this.manager = manager;
        this.listener = listener;
    }

    public void getDialogForEx(){
        mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(listener)
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setTitleStringId("请选择时间")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setHourText("点")
                .setMinuteText("分")
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis() - twoYears)
                .setMaxMillseconds(System.currentTimeMillis())
                .setCurrentMillseconds(System.currentTimeMillis())
                .setThemeColor(mContext.getResources().getColor(R.color.colorAccent))
                .setType(Type.ALL)
                .setWheelItemTextNormalColor(mContext.getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(mContext.getResources().getColor(R.color.timepicker_toolbar_bg))
                .setWheelItemTextSize(16)
                .build();
        mDialogAll.show(manager,"All");
    }

    public void getDialogForPlan(){
        mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(listener)
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setTitleStringId("请选择时间")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setHourText("点")
                .setMinuteText("分")
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis())
                .setMaxMillseconds(System.currentTimeMillis() + fiveYsers)
                .setCurrentMillseconds(System.currentTimeMillis())
                .setThemeColor(mContext.getResources().getColor(R.color.plan_top_bg))
                .setType(Type.ALL)
                .setWheelItemTextNormalColor(mContext.getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(mContext.getResources().getColor(R.color.timepicker_toolbar_bg))
                .setWheelItemTextSize(16)
                .build();
        mDialogAll.show(manager,"All");
    }
}
