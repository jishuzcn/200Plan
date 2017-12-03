package top.code666.a200plan.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import top.code666.a200plan.R;

/**
 * Created by code666 on 2017/11/22.
 */

public class ExDialog extends DialogFragment{

    private ImageButton ok,cancel;
    private EditText thing,morning,noon,evening,note;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_ex,null);
        setCancelable(false);
        initView(view);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        builder.setView(view);
        return builder.create();
    }

    private void initView(View view) {
        ok = view.findViewById(R.id.ex_ok_action);
        cancel = view.findViewById(R.id.ex_cancel_action);
        thing = view.findViewById(R.id.ex_thing_tv);
        morning = view.findViewById(R.id.ex_mornig_tv);
        noon = view.findViewById(R.id.ex_noon_tv);
        evening = view.findViewById(R.id.ex_evening_tv);
        note = view.findViewById(R.id.ex_note_tv);
    }
}
