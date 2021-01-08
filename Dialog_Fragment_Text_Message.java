package com.patrickgatirigmail.finale;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;
import java.util.Objects;

public class Dialog_Fragment_Text_Message extends AppCompatDialogFragment {
    /* access modifiers changed from: private */
    public TextInputLayout text_message_edit_text;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_fragment_text_messages, null);
        init(view);
        return new Builder(getActivity()).setTitle(R.string.enter_text_message).setView(view).setPositiveButton(R.string.send, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (Dialog_Fragment_Text_Message.this.text_message_edit_text.getEditText().getText().toString().trim().length() > 0) {
                    ((ViewModel_Tenant_Long_Clicked) ViewModelProviders.of((FragmentActivity) Objects.requireNonNull(Dialog_Fragment_Text_Message.this.getActivity())).get(ViewModel_Tenant_Long_Clicked.class)).getTenant_long_clicked().observe(Dialog_Fragment_Text_Message.this, new Observer<Entity_TENANTS>() {
                        public void onChanged(@Nullable Entity_TENANTS tenants) {
                            SmsManager smsManager = SmsManager.getDefault();
                            StringBuilder sb = new StringBuilder();
                            sb.append("0");
                            sb.append(String.valueOf(tenants.getPHONE_NUMBER()));
                            smsManager.sendTextMessage(sb.toString(), null, Dialog_Fragment_Text_Message.this.text_message_edit_text.getEditText().getText().toString().trim(), null, null);
                        }
                    });
                    Toast.makeText(Dialog_Fragment_Text_Message.this.getActivity(), R.string.sending_message, 0).show();
                    return;
                }
                Dialog_Fragment_Text_Message.this.text_message_edit_text.setError(Dialog_Fragment_Text_Message.this.getString(R.string.field_cant_be_empty));
            }
        }).setNegativeButton(R.string.cancel, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Dialog_Fragment_Text_Message.this.dismiss();
                Toast.makeText(Dialog_Fragment_Text_Message.this.getActivity(), R.string.message_not_sent, 0).show();
            }
        }).create();
    }

    private void init(View view) {
        this.text_message_edit_text = (TextInputLayout) view.findViewById(R.id.text_message_edit_text);
    }
}
