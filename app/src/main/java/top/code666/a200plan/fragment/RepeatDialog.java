package top.code666.a200plan.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import top.code666.a200plan.R;

/**
 * Created by code666 on 2017/12/9.
 */

public class RepeatDialog extends DialogFragment implements View.OnClickListener{
    private RelativeLayout relativeLayout01,relativeLayout02,relativeLayout03,relativeLayout04,
            relativeLayout05,relativeLayout06,relativeLayout07;
    private ImageView img01,img02,img03,img04,img05,img06,img07;
    private ArrayList<String> repeat = new ArrayList<>();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_repeat,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        initView(view);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RepeatListener repeatListener = (RepeatListener) getActivity();
                repeatListener.RepeatValue(repeat);
            }
        });

        builder.setView(view);
        return builder.create();
    }

    private void initView(View view) {
        relativeLayout01 = view.findViewById(R.id.dialog_repeat_01);
        relativeLayout02 = view.findViewById(R.id.dialog_repeat_02);
        relativeLayout03 = view.findViewById(R.id.dialog_repeat_03);
        relativeLayout04 = view.findViewById(R.id.dialog_repeat_04);
        relativeLayout05 = view.findViewById(R.id.dialog_repeat_05);
        relativeLayout06 = view.findViewById(R.id.dialog_repeat_06);
        relativeLayout07 = view.findViewById(R.id.dialog_repeat_07);
        img01 = view.findViewById(R.id.dialog_repeat_img_01);
        img02 = view.findViewById(R.id.dialog_repeat_img_02);
        img03 = view.findViewById(R.id.dialog_repeat_img_03);
        img04 = view.findViewById(R.id.dialog_repeat_img_04);
        img05 = view.findViewById(R.id.dialog_repeat_img_05);
        img06 = view.findViewById(R.id.dialog_repeat_img_06);
        img07 = view.findViewById(R.id.dialog_repeat_img_07);
        relativeLayout01.setOnClickListener(this);
        relativeLayout02.setOnClickListener(this);
        relativeLayout03.setOnClickListener(this);
        relativeLayout04.setOnClickListener(this);
        relativeLayout05.setOnClickListener(this);
        relativeLayout06.setOnClickListener(this);
        relativeLayout07.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_repeat_01:
                if(img01.getVisibility() == View.INVISIBLE){
                    img01.setVisibility(View.VISIBLE);
                    repeat.add("星期一");
                }else{
                    if (repeat.contains("星期一")) repeat.remove("星期一");
                    img01.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.dialog_repeat_02:
                if(img02.getVisibility() == View.INVISIBLE){
                    img02.setVisibility(View.VISIBLE);
                    repeat.add("星期二");
                }else{
                    if (repeat.contains("星期二")) repeat.remove("星期二");
                    img02.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.dialog_repeat_03:
                if(img03.getVisibility() == View.INVISIBLE){
                    img03.setVisibility(View.VISIBLE);
                    repeat.add("星期三");
                }else{
                    if (repeat.contains("星期三")) repeat.remove("星期三");
                    img03.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.dialog_repeat_04:
                if(img04.getVisibility() == View.INVISIBLE){
                    img04.setVisibility(View.VISIBLE);
                    repeat.add("星期四");
                }else{
                    if (repeat.contains("星期四")) repeat.remove("星期四");
                    img04.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.dialog_repeat_05:
                if(img05.getVisibility() == View.INVISIBLE){
                    img05.setVisibility(View.VISIBLE);
                    repeat.add("星期五");
                }else{
                    if (repeat.contains("星期五")) repeat.remove("星期五");
                    img05.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.dialog_repeat_06:
                if(img06.getVisibility() == View.INVISIBLE){
                    img06.setVisibility(View.VISIBLE);
                    repeat.add("星期六");
                }else{
                    if (repeat.contains("星期六")) repeat.remove("星期六");
                    img06.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.dialog_repeat_07:
                if(img07.getVisibility() == View.INVISIBLE){
                    img07.setVisibility(View.VISIBLE);
                    repeat.add("星期日");
                }else{
                    if (repeat.contains("星期日")) repeat.remove("星期日");
                    img07.setVisibility(View.INVISIBLE);
                }
                break;
            default:
                break;
        }
    }

    public interface RepeatListener{
        void RepeatValue(ArrayList<String> repeatValues);
    }
}
