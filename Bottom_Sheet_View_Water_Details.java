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

public class Bottom_Sheet_View_Water_Details extends BottomSheetDialogFragment {
    /* access modifiers changed from: private */
    public Entity_WATER entity_water;
    /* access modifiers changed from: private */
    public TextView water_details_text_view;
    /* access modifiers changed from: private */
    public Toolbar water_details_toolbar;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_sheet_view_water_details, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        this.water_details_toolbar = (Toolbar) view.findViewById(R.id.water_details_toolbar);
        this.water_details_text_view = (TextView) view.findViewById(R.id.water_details_text_view);
        FloatingActionButton edit_water_details_button = (FloatingActionButton) view.findViewById(R.id.edit_water_details_button);
        ((ViewModel_Water_Bill_Clicked) ViewModelProviders.of(getActivity()).get(ViewModel_Water_Bill_Clicked.class)).getWaterbillClicked().observe(this, new Observer<Entity_WATER>() {
            public void onChanged(@Nullable Entity_WATER water) {
                Bottom_Sheet_View_Water_Details.this.entity_water = water;
                Bottom_Sheet_View_Water_Details.this.water_details_toolbar.setTitle((CharSequence) Bottom_Sheet_View_Water_Details.this.getString(R.string.water_details_title, Integer.valueOf(water.getHOUSE_NUMBER())));
                Bottom_Sheet_View_Water_Details.this.water_details_text_view.setText(((Entity_WATER) Objects.requireNonNull(water)).toString());
            }
        });
        edit_water_details_button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (Bottom_Sheet_View_Water_Details.this.entity_water != null) {
                    Intent intent = new Intent(Bottom_Sheet_View_Water_Details.this.getActivity(), Activity_Update_Water.class);
                    intent.putExtra(Activity_Update_Water.UPDATE_WATER, Bottom_Sheet_View_Water_Details.this.entity_water);
                    Bottom_Sheet_View_Water_Details.this.startActivity(intent);
                    Bottom_Sheet_View_Water_Details.this.dismiss();
                }
            }
        });
    }
}
