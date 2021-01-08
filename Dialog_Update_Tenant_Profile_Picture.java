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

public class Dialog_Update_Tenant_Profile_Picture extends AppCompatDialogFragment {
    /* access modifiers changed from: private */
    public OnButtonSelectedListener onButtonSelectedListener;

    public interface OnButtonSelectedListener {
        void onButtonSelected(int i);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_fragment_update_tenant_profile_picture, null);
        init(view);
        return new Builder(getActivity()).setView(view).setTitle(R.string.choose_tenant_profile_picture).setNegativeButton(R.string.cancel, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Dialog_Update_Tenant_Profile_Picture.this.dismiss();
            }
        }).setIcon(R.drawable.ic_camera).create();
    }

    private void init(View view) {
        Button update_existing_pic_button = (Button) view.findViewById(R.id.update_existing_pic_button);
        Button remove_photo_button = (Button) view.findViewById(R.id.remove_photo_button);
        ((Button) view.findViewById(R.id.take_updated_photo_button)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dialog_Update_Tenant_Profile_Picture.this.onButtonSelectedListener.onButtonSelected(R.id.take_updated_photo_button);
                Dialog_Update_Tenant_Profile_Picture.this.dismiss();
            }
        });
        update_existing_pic_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dialog_Update_Tenant_Profile_Picture.this.onButtonSelectedListener.onButtonSelected(R.id.update_existing_pic_button);
                Dialog_Update_Tenant_Profile_Picture.this.dismiss();
            }
        });
        remove_photo_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dialog_Update_Tenant_Profile_Picture.this.onButtonSelectedListener.onButtonSelected(R.id.remove_photo_button);
                Dialog_Update_Tenant_Profile_Picture.this.dismiss();
            }
        });
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnButtonSelectedListener) {
            try {
                this.onButtonSelectedListener = (OnButtonSelectedListener) context;
            } catch (ClassCastException e) {
            }
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(context.toString());
            sb.append("should implement the OnButtonSelectedListener");
            throw new RuntimeException(sb.toString());
        }
    }

    public void onDetach() {
        super.onDetach();
        this.onButtonSelectedListener = null;
    }
}
