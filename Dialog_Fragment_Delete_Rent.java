package com.patrickgatirigmail.finale;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatDialogFragment;
import java.util.Objects;

public class Dialog_Fragment_Delete_Rent extends AppCompatDialogFragment {
    private static Dialog_Fragment_Delete_Rent dialog_fragment_delete_rent;

    public static Dialog_Fragment_Delete_Rent newInstance(Entity_RENT entity_rent) {
        dialog_fragment_delete_rent = new Dialog_Fragment_Delete_Rent();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Entity_RENT.DELETE_RENT_PARCELABLE_NAME, entity_rent);
        dialog_fragment_delete_rent.setArguments(bundle);
        return dialog_fragment_delete_rent;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Entity_RENT entity_rent = (Entity_RENT) getArguments().getParcelable(Entity_RENT.DELETE_RENT_PARCELABLE_NAME);
        return new Builder(getActivity()).setIcon(R.drawable.ic_delete).setTitle(R.string.delete_rent_bill).setMessage(R.string.confirm_rent_delete).setNegativeButton(R.string.cancel, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Dialog_Fragment_Delete_Rent.this.dismiss();
            }
        }).setPositiveButton(R.string.Delete, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ((ViewModel_DELETE_RENT) ViewModelProviders.of((FragmentActivity) Objects.requireNonNull(Dialog_Fragment_Delete_Rent.this.getActivity())).get(ViewModel_DELETE_RENT.class)).delete_rent(Dialog_Fragment_Delete_Rent.this.getActivity(), entity_rent);
            }
        }).create();
    }
}
