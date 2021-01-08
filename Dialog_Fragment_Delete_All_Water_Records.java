package com.patrickgatirigmail.finale;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

public class Dialog_Fragment_Delete_All_Water_Records extends AppCompatDialogFragment {
    public static Dialog_Fragment_Delete_All_Water_Records newInstance(int house_number) {
        Dialog_Fragment_Delete_All_Water_Records dialog_fragment_delete_all_water_records = new Dialog_Fragment_Delete_All_Water_Records();
        Bundle bundle = new Bundle();
        bundle.putInt(Entity_WATER.DELETE_WATER_BILLS_FOR_HOUSE_EXTRA_NAME, house_number);
        dialog_fragment_delete_all_water_records.setArguments(bundle);
        return dialog_fragment_delete_all_water_records;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final int house_number = getArguments().getInt(Entity_WATER.DELETE_WATER_BILLS_FOR_HOUSE_EXTRA_NAME);
        return new Builder(getActivity()).setIcon(R.drawable.ic_delete).setTitle(R.string.delete_water_bill).setMessage(getString(R.string.confirm_all_water_delete, Integer.valueOf(house_number))).setNegativeButton(R.string.cancel, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Dialog_Fragment_Delete_All_Water_Records.this.dismiss();
            }
        }).setPositiveButton(R.string.Delete, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ((ViewModel_DELETE_WaterBillsForHouse) ViewModelProviders.of(Dialog_Fragment_Delete_All_Water_Records.this.getActivity()).get(ViewModel_DELETE_WaterBillsForHouse.class)).delete_water_bills_for_house(Dialog_Fragment_Delete_All_Water_Records.this.getActivity(), house_number);
            }
        }).create();
    }
}
