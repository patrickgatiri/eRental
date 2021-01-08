package com.patrickgatirigmail.finale;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.View;
import android.widget.Button;

public class Dialog_Tenant_Profile_Picture extends AppCompatDialogFragment {
    /* access modifiers changed from: private */
    public OnButtonClickedListener onButtonClickedListener;

    public interface OnButtonClickedListener {
        void onButtonClick(int i);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog__fragment_take_tenant_profile_picture, null);
        init(view);
        return new Builder(getActivity()).setTitle(R.string.choose_tenant_profile_picture).setView(view).setNegativeButton(R.string.cancel, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Dialog_Tenant_Profile_Picture.this.dismiss();
            }
        }).setIcon(R.drawable.ic_camera).create();
    }

    private void init(View view) {
        Button take_new_photo_button = (Button) view.findViewById(R.id.take_new_photo_button);
        ((Button) view.findViewById(R.id.choose_existing_pic_button)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dialog_Tenant_Profile_Picture.this.onButtonClickedListener.onButtonClick(R.id.choose_existing_pic_button);
                Dialog_Tenant_Profile_Picture.this.dismiss();
            }
        });
        take_new_photo_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dialog_Tenant_Profile_Picture.this.onButtonClickedListener.onButtonClick(R.id.take_new_photo_button);
                Dialog_Tenant_Profile_Picture.this.dismiss();
            }
        });
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnButtonClickedListener) {
            try {
                this.onButtonClickedListener = (OnButtonClickedListener) context;
            } catch (ClassCastException e) {
            }
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(context.toString());
            sb.append("has to implement the OnButtonClickedListener");
            throw new RuntimeException(sb.toString());
        }
    }

    public void onDetach() {
        super.onDetach();
        this.onButtonClickedListener = null;
    }
}
