package com.patrickgatirigmail.finale;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

public class Dialog_Fragment_Delete_All_Rent_Records extends AppCompatDialogFragment {
    public static Dialog_Fragment_Delete_All_Rent_Records newInstance(int house_number) {
        Dialog_Fragment_Delete_All_Rent_Records dialog_fragment_delete_all_rent_records = new Dialog_Fragment_Delete_All_Rent_Records();
        Bundle bundle = new Bundle();
        bundle.putInt(Entity_RENT.DELETE_RENT_BILLS_FOR_HOUSE_EXTRA_NAME, house_number);
        dialog_fragment_delete_all_rent_records.setArguments(bundle);
        return dialog_fragment_delete_all_rent_records;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final int house_number = getArguments().getInt(Entity_RENT.DELETE_RENT_BILLS_FOR_HOUSE_EXTRA_NAME);
        return new Builder(getActivity()).setTitle(R.string.Delete_Rent).setMessage(getString(R.string.confirm_all_rent_delete, Integer.valueOf(house_number))).setIcon(R.drawable.ic_delete).setNegativeButton(R.string.cancel, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Dialog_Fragment_Delete_All_Rent_Records.this.dismiss();
            }
        }).setPositiveButton(R.string.Delete, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ((ViewModel_DELETE_RentBillsForHouse) ViewModelProviders.of(Dialog_Fragment_Delete_All_Rent_Records.this.getActivity()).get(ViewModel_DELETE_RentBillsForHouse.class)).delete_RentBillForHouse(Dialog_Fragment_Delete_All_Rent_Records.this.getActivity(), house_number);
            }
        }).create();
    }
}
