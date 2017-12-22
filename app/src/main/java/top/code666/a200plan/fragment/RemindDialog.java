package top.code666.a200plan.fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import top.code666.a200plan.R;

/**
 * Created by code666 on 2017/12/9.
 */

public class RemindDialog extends DialogFragment implements View.OnClickListener{
    private RelativeLayout relativeLayout01,relativeLayout02,relativeLayout03,relativeLayout04,relativeLayout05,relativeLayout06;
    private ImageView img01,img02,img03,img04,img05,img06;
    private String isSelected = "不提醒";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_remind,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        setCancelable(false);
        initView(view);

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RemindListener remindListener = (RemindListener) getActivity();
                remindListener.RemindParam(isSelected);
            }
        });

        builder.setView(view);
        return builder.create();
    }

    private void initView(View view) {
        relativeLayout01 = view.findViewById(R.id.dialog_remind_01);
        relativeLayout02 = view.findViewById(R.id.dialog_remind_02);
        relativeLayout03 = view.findViewById(R.id.dialog_remind_03);
        relativeLayout04 = view.findViewById(R.id.dialog_remind_04);
        relativeLayout05 = view.findViewById(R.id.dialog_remind_05);
        relativeLayout06 = view.findViewById(R.id.dialog_remind_06);
        img01 = view.findViewById(R.id.dialog_remind_img_01);
        img02 = view.findViewById(R.id.dialog_remind_img_02);
        img03 = view.findViewById(R.id.dialog_remind_img_03);
        img04 = view.findViewById(R.id.dialog_remind_img_04);
        img05 = view.findViewById(R.id.dialog_remind_img_05);
        img06 = view.findViewById(R.id.dialog_remind_img_06);
        relativeLayout01.setOnClickListener(this);
        relativeLayout02.setOnClickListener(this);
        relativeLayout03.setOnClickListener(this);
        relativeLayout04.setOnClickListener(this);
        relativeLayout05.setOnClickListener(this);
        relativeLayout06.setOnClickListener(this);
        img01.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        reset();
        switch (v.getId()){
            case R.id.dialog_remind_01:
                isSelected = "不提醒";
                img01.setVisibility(View.VISIBLE);
                break;
            case R.id.dialog_remind_02:
                isSelected = "到点提醒";
                img02.setVisibility(View.VISIBLE);
                break;
            case R.id.dialog_remind_03:
                isSelected = "提前十分钟";
                img03.setVisibility(View.VISIBLE);
                break;
            case R.id.dialog_remind_04:
                isSelected = "提前半小时";
                img04.setVisibility(View.VISIBLE);
                break;
            case R.id.dialog_remind_05:
                isSelected = "提前一小时";
                img05.setVisibility(View.VISIBLE);
                break;
            case R.id.dialog_remind_06:
                isSelected = "提前一天";
                img06.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    private void reset(){
        if(img01.getVisibility() == View.VISIBLE){
            img01.setVisibility(View.INVISIBLE);
        }
        if(img02.getVisibility() == View.VISIBLE){
            img02.setVisibility(View.INVISIBLE);
        }
        if(img03.getVisibility() == View.VISIBLE){
            img03.setVisibility(View.INVISIBLE);
        }
        if(img04.getVisibility() == View.VISIBLE){
            img04.setVisibility(View.INVISIBLE);
        }
        if(img05.getVisibility() == View.VISIBLE){
            img05.setVisibility(View.INVISIBLE);
        }
        if(img06.getVisibility() == View.VISIBLE){
            img06.setVisibility(View.INVISIBLE);
        }
    }

    public interface RemindListener{
        void RemindParam(String remindTime);
    }
}
