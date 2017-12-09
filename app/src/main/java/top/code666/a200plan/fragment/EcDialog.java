package top.code666.a200plan.fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import top.code666.a200plan.R;

//已经废弃
public class EcDialog extends DialogFragment{
    private EditText dothing,howMoney;
    private ImageButton cancel,ok;
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_ec, null);
        setCancelable(false);

        dothing = view.findViewById(R.id.dothing_tv);
        howMoney = view.findViewById(R.id.howMoney_tv);
        ok = view.findViewById(R.id.ec_ok_action);
        cancel = view.findViewById(R.id.ec_cancel_action);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Toast.makeText(getActivity(),"功能正在实现...",Toast.LENGTH_SHORT).show();
            }
        });
        builder.setView(view);
        return builder.create();
    }

}
