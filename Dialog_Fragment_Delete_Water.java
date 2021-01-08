package com.patrickgatirigmail.finale;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

public class Dialog_Fragment_Delete_Water extends AppCompatDialogFragment {
    public static Dialog_Fragment_Delete_Water newInstance(Entity_WATER entity_water, double previous_water_reading, int next_water_bill_id) {
        Dialog_Fragment_Delete_Water dialog_fragment_delete_water = new Dialog_Fragment_Delete_Water();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Entity_WATER.DELETE_WATER_PARCELABLE_NAME, entity_water);
        bundle.putDouble(Entity_WATER.PREVIOUS_WATER_READING, previous_water_reading);
        bundle.putInt(Entity_WATER.AFFECTED_WATER_BILL_ID, next_water_bill_id);
        dialog_fragment_delete_water.setArguments(bundle);
        return dialog_fragment_delete_water;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Entity_WATER entity_water = (Entity_WATER) getArguments().getParcelable(Entity_WATER.DELETE_WATER_PARCELABLE_NAME);
        double previous_water_reading = getArguments().getDouble(Entity_WATER.PREVIOUS_WATER_READING);
        int next_water_bill_id = getArguments().getInt(Entity_WATER.AFFECTED_WATER_BILL_ID);
        Builder negativeButton = new Builder(getActivity()).setIcon(R.drawable.ic_delete).setTitle(R.string.delete_water_bill).setMessage(R.string.confirm_water_delete).setNegativeButton(R.string.cancel, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Dialog_Fragment_Delete_Water.this.dismiss();
            }
        });
        final Entity_WATER entity_WATER = entity_water;
        final double d = previous_water_reading;
        final int i = next_water_bill_id;
        AnonymousClass1 r1 = new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ((ViewModel_DELETE_WATER) ViewModelProviders.of(Dialog_Fragment_Delete_Water.this.getActivity()).get(ViewModel_DELETE_WATER.class)).delete_water_bill(Dialog_Fragment_Delete_Water.this.getActivity(), entity_WATER, d, i);
            }
        };
        return negativeButton.setPositiveButton(R.string.Delete, r1).create();
    }
}
