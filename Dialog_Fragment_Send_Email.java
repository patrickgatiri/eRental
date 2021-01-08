package com.patrickgatirigmail.finale;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.View;
import java.util.Objects;

public class Dialog_Fragment_Send_Email extends AppCompatDialogFragment {
    public static final String MY_EMAIL_ADDRESS = "patrickgatiri@gmail.com";
    private TextInputLayout email_body_edit_text;
    private TextInputLayout email_subject_edit_text;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = ((FragmentActivity) Objects.requireNonNull(getActivity())).getLayoutInflater().inflate(R.layout.dialog_fragment_send_email, null);
        init(view);
        return new Builder(getActivity()).setIcon((int) R.drawable.ic_email).setTitle((int) R.string.enter_email_body).setView(view).setNegativeButton((int) R.string.cancel, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Dialog_Fragment_Send_Email.this.dismiss();
            }
        }).setPositiveButton((int) R.string.send, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (Dialog_Fragment_Send_Email.this.input_validation()) {
                    Dialog_Fragment_Send_Email.this.sendEmail();
                }
            }
        }).create();
    }

    /* access modifiers changed from: private */
    public void sendEmail() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.EMAIL", new String[]{MY_EMAIL_ADDRESS});
        intent.putExtra("android.intent.extra.SUBJECT", this.email_subject_edit_text.getEditText().getText().toString().trim());
        intent.putExtra("android.intent.extra.TEXT", this.email_body_edit_text.getEditText().getText().toString().trim());
        intent.setType("message/rfc822");
        if (intent.resolveActivity(((FragmentActivity) Objects.requireNonNull(getActivity())).getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, getString(R.string.send_with)));
        }
    }

    private void init(View view) {
        this.email_body_edit_text = (TextInputLayout) view.findViewById(R.id.email_body_edit_text);
        this.email_subject_edit_text = (TextInputLayout) view.findViewById(R.id.email_subject_edit_text);
    }

    /* access modifiers changed from: private */
    public boolean input_validation() {
        if (this.email_subject_edit_text.getEditText().getText().toString().trim().length() == 0) {
            this.email_subject_edit_text.setError(getString(R.string.field_cant_be_empty));
            return false;
        } else if (this.email_body_edit_text.getEditText().getText().toString().trim().length() != 0) {
            return true;
        } else {
            this.email_body_edit_text.setError(getString(R.string.field_cant_be_empty));
            return false;
        }
    }
}
