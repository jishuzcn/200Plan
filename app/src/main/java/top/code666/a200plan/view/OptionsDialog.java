package top.code666.a200plan.view;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import top.code666.a200plan.R;

/**
 * Created by code666 on 2017/11/21.
 */

public class OptionsDialog extends BasePopupWindow{
    public OptionsDialog(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.view_options,null);
        setContentView(view);
    }

    /*public void dismiss(FloatingActionButton fab) {
        super.dismiss();
        Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.rotate);
        fab.startAnimation(animation);
    }*/
}
