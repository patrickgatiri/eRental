package com.patrickgatirigmail.finale;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Objects;

public class Bottom_Sheet_View_Rent_Details extends BottomSheetDialogFragment {
    public static final String POJO_EXTRA = "POJO_EXTRA";
    /* access modifiers changed from: private */
    public POJO_RentBills_per_House pojo_rentBills_per_houses;
    /* access modifiers changed from: private */
    public TextView rent_details_text_view;
    /* access modifiers changed from: private */
    public Toolbar rent_details_toolbar;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_sheet_view_rent_details, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        this.rent_details_text_view = (TextView) view.findViewById(R.id.rent_details_text_view);
        this.rent_details_toolbar = (Toolbar) view.findViewById(R.id.rent_details_toolbar);
        ((FloatingActionButton) view.findViewById(R.id.edit_rent_details_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (Bottom_Sheet_View_Rent_Details.this.pojo_rentBills_per_houses != null) {
                    Intent intent = new Intent(Bottom_Sheet_View_Rent_Details.this.getActivity(), Activity_Update_Rent.class);
                    intent.putExtra(Bottom_Sheet_View_Rent_Details.POJO_EXTRA, Bottom_Sheet_View_Rent_Details.this.pojo_rentBills_per_houses);
                    Bottom_Sheet_View_Rent_Details.this.startActivity(intent);
                }
                Bottom_Sheet_View_Rent_Details.this.dismiss();
            }
        });
        ((ViewModel_Rent_Bill_Clicked) ViewModelProviders.of(getActivity()).get(ViewModel_Rent_Bill_Clicked.class)).getRentBillClicked().observe(this, new Observer<POJO_RentBills_per_House>() {
            public void onChanged(@Nullable POJO_RentBills_per_House pojo_rentBills_per_house) {
                Bottom_Sheet_View_Rent_Details.this.pojo_rentBills_per_houses = pojo_rentBills_per_house;
                Bottom_Sheet_View_Rent_Details.this.rent_details_toolbar.setTitle((CharSequence) Bottom_Sheet_View_Rent_Details.this.getString(R.string.rent_details_title, Integer.valueOf(pojo_rentBills_per_house.getHOUSE_NUMBER())));
                Bottom_Sheet_View_Rent_Details.this.rent_details_text_view.setText(((POJO_RentBills_per_House) Objects.requireNonNull(pojo_rentBills_per_house)).toString());
            }
        });
    }
}
