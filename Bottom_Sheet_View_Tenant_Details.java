package com.patrickgatirigmail.finale;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.util.Objects;

public class Bottom_Sheet_View_Tenant_Details extends BottomSheetDialogFragment {
    /* access modifiers changed from: private */
    public AppBarLayout appBarLayout;
    /* access modifiers changed from: private */
    public Toolbar toolbar;
    /* access modifiers changed from: private */
    public TextView view_tenant_details_textview;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_sheet_view_tenant_details, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        this.toolbar = (Toolbar) view.findViewById(R.id.view_tenant_details_toolbar);
        this.view_tenant_details_textview = (TextView) view.findViewById(R.id.tenant_details_textview);
        this.appBarLayout = (AppBarLayout) view.findViewById(R.id.view_tenant_details_app_bar_layout);
        ((ViewModel_Tenant_Clicked) ViewModelProviders.of((FragmentActivity) Objects.requireNonNull(getActivity())).get(ViewModel_Tenant_Clicked.class)).getTenant_clicked().observe(getViewLifecycleOwner(), new Observer<Entity_TENANTS>() {
            public void onChanged(@Nullable Entity_TENANTS tenants) {
                if (tenants != null) {
                    Bottom_Sheet_View_Tenant_Details.this.toolbar.setTitle((CharSequence) Bottom_Sheet_View_Tenant_Details.this.getString(R.string.House, Integer.valueOf(tenants.getHOUSE_NUMBER())));
                    Bottom_Sheet_View_Tenant_Details.this.view_tenant_details_textview.setText(tenants.toString());
                    if (tenants.getPROFILE_PICTURE_URI() == null) {
                        Bottom_Sheet_View_Tenant_Details.this.appBarLayout.setBackgroundResource(R.drawable.ic_person);
                        return;
                    }
                    try {
                        Bottom_Sheet_View_Tenant_Details.this.appBarLayout.setBackground(Drawable.createFromStream(Bottom_Sheet_View_Tenant_Details.this.getActivity().getContentResolver().openInputStream(Uri.parse(tenants.getPROFILE_PICTURE_URI())), "r"));
                    } catch (IOException e) {
                        e.printStackTrace();
                        Bottom_Sheet_View_Tenant_Details.this.updateTenant(tenants);
                        Bottom_Sheet_View_Tenant_Details.this.appBarLayout.setBackgroundResource(R.drawable.ic_person);
                    } catch (SecurityException e2) {
                        e2.printStackTrace();
                        Toast.makeText(Bottom_Sheet_View_Tenant_Details.this.getActivity(), R.string.media_file_unavailable, 0).show();
                        Bottom_Sheet_View_Tenant_Details.this.updateTenant(tenants);
                        Bottom_Sheet_View_Tenant_Details.this.appBarLayout.setBackgroundResource(R.drawable.ic_person);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateTenant(Entity_TENANTS entity_tenants) {
        Entity_TENANTS tenants = new Entity_TENANTS(entity_tenants.getHOUSE_NUMBER(), new TenantName(entity_tenants.getTenantName().getFIRST_NAME(), entity_tenants.getTenantName().getLAST_NAME()), null, entity_tenants.getPHONE_NUMBER(), entity_tenants.getRENT_PER_MONTH());
        tenants.setTENANT_ID(entity_tenants.getTENANT_ID());
        ((ViewModel_UPDATETENANT) ViewModelProviders.of(getActivity()).get(ViewModel_UPDATETENANT.class)).update_tenant(getActivity(), tenants);
    }
}
