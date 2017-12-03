package top.code666.a200plan.view;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import top.code666.a200plan.R;
import top.code666.a200plan.activity.ExpensesActivity;
import top.code666.a200plan.activity.PlanActivity;

/**
 * Created by code666 on 2017/11/21.
 */

public class OptionsDialog extends BasePopupWindow implements View.OnClickListener{
    private LinearLayout linearLayout01,linearLayout02,linearLayout03;
    private FragmentManager fragmentManager;
    public OptionsDialog(Context context, FragmentManager fragmentManager) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.view_options,null);
        linearLayout01 = view.findViewById(R.id.ll_ex);
        linearLayout02 = view.findViewById(R.id.ll_pl);
        linearLayout01.setOnClickListener(this);
        linearLayout02.setOnClickListener(this);
        setContentView(view);
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void onClick(View view) {
        dismiss();
        switch (view.getId()){
            case R.id.ll_ex:
//                Toast.makeText(getContext(),"okok",Toast.LENGTH_SHORT).show();
                /*EcDialog ecDialog = new EcDialog();
                ecDialog.show(fragmentManager,"ecDialog");
                ExDialog exDialog = new ExDialog();
                exDialog.show(fragmentManager,"exDialog");*/
                Intent intent1 = new Intent();
                intent1.setClass(view.getContext(),ExpensesActivity.class);
                view.getContext().startActivity(intent1);
                break;
            case R.id.ll_pl:
                Intent intent2 = new Intent();
                intent2.setClass(view.getContext(),PlanActivity.class);
                view.getContext().startActivity(intent2);
                break;
            default:
                break;
        }
    }

    /*public void dismiss(FloatingActionButton fab) {
        super.dismiss();
        Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.rotate);
        fab.startAnimation(animation);
    }*/
}
