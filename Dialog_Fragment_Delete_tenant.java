package com.patrickgatirigmail.finale;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatDialogFragment;
import java.util.Objects;

public class Dialog_Fragment_Delete_tenant extends AppCompatDialogFragment {
    public static Dialog_Fragment_Delete_tenant newInstance(Entity_TENANTS entity_tenants) {
        Dialog_Fragment_Delete_tenant dialogFragment_delete_tenant = new Dialog_Fragment_Delete_tenant();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Entity_TENANTS.DELETE_TENANT_PARCELABLE_NAME, entity_tenants);
        dialogFragment_delete_tenant.setArguments(bundle);
        return dialogFragment_delete_tenant;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Entity_TENANTS entity_tenants = (Entity_TENANTS) getArguments().getParcelable(Entity_TENANTS.DELETE_TENANT_PARCELABLE_NAME);
        return new Builder((Context) Objects.requireNonNull(getActivity())).setTitle((int) R.string.Delete_house).setIcon((int) R.drawable.ic_delete).setMessage((CharSequence) getString(R.string.Confirm_deleting_tenant, Integer.valueOf(entity_tenants.getHOUSE_NUMBER()))).setNegativeButton((int) R.string.cancel, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Dialog_Fragment_Delete_tenant.this.dismiss();
            }
        }).setPositiveButton((int) R.string.Delete, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ((ViewModel_DELETE_TENANT) ViewModelProviders.of(Dialog_Fragment_Delete_tenant.this.getActivity()).get(ViewModel_DELETE_TENANT.class)).delete_tenant(Dialog_Fragment_Delete_tenant.this.getActivity(), entity_tenants);
            }
        }).create();
    }
}
