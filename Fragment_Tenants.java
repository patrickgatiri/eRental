package com.patrickgatirigmail.finale;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.PopupMenu.OnMenuItemClickListener;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.patrickgatirigmail.finale.Adapter_Tenants_Recycler_View.OnTenantClickedListener;
import com.patrickgatirigmail.finale.Adapter_Tenants_Recycler_View.OnTenantLongClickedListener;
import com.tapadoo.alerter.Alerter;
import java.util.List;
import java.util.Objects;

public class Fragment_Tenants extends Fragment implements OnTenantClickedListener, OnTenantLongClickedListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int CALL_REQUEST_CODE = 1;
    private static final String TAG = "Fragment_Tenants";
    private static final int TEXT_SMS_REQUEST_CODE = 2;
    /* access modifiers changed from: private */
    public Adapter_Tenants_Recycler_View adapter_tenants_recycler_view;

    public static Fragment_Tenants newInstance() {
        return new Fragment_Tenants();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tenants, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        RecyclerView tenants_recycler_view = (RecyclerView) view.findViewById(R.id.tenants_recycler_view);
        FloatingActionButton add_new_house_button = (FloatingActionButton) view.findViewById(R.id.new_house_button);
        this.adapter_tenants_recycler_view = new Adapter_Tenants_Recycler_View(getActivity());
        Log.d(TAG, "init: Retrieving the list of all tenants");
        ((ViewModel_getAllTenants) ViewModelProviders.of(getActivity()).get(ViewModel_getAllTenants.class)).getAllTenants().observe(getViewLifecycleOwner(), new Observer<List<Entity_TENANTS>>() {
            public void onChanged(@Nullable List<Entity_TENANTS> entity_tenants) {
                Fragment_Tenants.this.adapter_tenants_recycler_view.submitList(entity_tenants);
            }
        });
        tenants_recycler_view.setAdapter(this.adapter_tenants_recycler_view);
        tenants_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity(), 1, false));
        tenants_recycler_view.setHasFixedSize(true);
        this.adapter_tenants_recycler_view.setOnTenantClickedListener(this);
        this.adapter_tenants_recycler_view.setOnTenantLongClickedListener(this);
        add_new_house_button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Fragment_Tenants.this.startActivity(new Intent(Fragment_Tenants.this.getActivity(), Activity_Insert_Tenant.class));
            }
        });
    }

    public void onTenantClicked(Entity_TENANTS tenants) {
        ((ViewModel_Tenant_Clicked) ViewModelProviders.of(getActivity()).get(ViewModel_Tenant_Clicked.class)).setTenant_clicked(tenants);
        new Bottom_Sheet_View_Tenant_Details().show(getActivity().getSupportFragmentManager(), TAG);
    }

    /* access modifiers changed from: private */
    public void check_CALL_permissions() {
        if (ContextCompat.checkSelfPermission((Context) Objects.requireNonNull(getActivity()), "android.permission.CALL_PHONE") != 0) {
            if (shouldShowRequestPermissionRationale("android.permission.CALL_PHONE")) {
                Alerter.create(getActivity()).setIcon((int) R.drawable.ic_phone).setText((int) R.string.call_permission_rationale).setTitle((int) R.string.contact_tenant).setBackgroundColorRes(R.color.green).enableVibration(true).enableProgress(true).setDuration(5000).show();
            }
            ActivityCompat.requestPermissions(getActivity(), new String[]{"android.permission.CALL_PHONE"}, 1);
            return;
        }
        call_tenant();
    }

    private void call_tenant() {
        final Entity_TENANTS[] entity_tenants = new Entity_TENANTS[1];
        ((ViewModel_Tenant_Long_Clicked) ViewModelProviders.of(getActivity()).get(ViewModel_Tenant_Long_Clicked.class)).getTenant_long_clicked().observe(getViewLifecycleOwner(), new Observer<Entity_TENANTS>() {
            public void onChanged(@Nullable Entity_TENANTS tenants) {
                entity_tenants[0] = tenants;
            }
        });
        StringBuilder sb = new StringBuilder();
        sb.append("tel:0");
        sb.append(entity_tenants[0].getPHONE_NUMBER());
        startActivity(new Intent("android.intent.action.CALL", Uri.parse(sb.toString())));
    }

    /* access modifiers changed from: private */
    public void check_SMS_permissions() {
        if (ContextCompat.checkSelfPermission((Context) Objects.requireNonNull(getActivity()), "android.permission.SEND_SMS") != 0) {
            if (shouldShowRequestPermissionRationale("android.permission.SEND_SMS")) {
                Alerter.create(getActivity()).setIcon((int) R.drawable.ic_textsms).setText((int) R.string.sms_permission_rationale).setTitle((int) R.string.contact_tenant).setBackgroundColorRes(R.color.green).enableVibration(true).enableProgress(true).setDuration(5000).show();
            }
            ActivityCompat.requestPermissions(getActivity(), new String[]{"android.permission.SEND_SMS"}, 2);
            return;
        }
        send_text_message();
    }

    private void send_text_message() {
        new Dialog_Fragment_Text_Message().show(getActivity().getSupportFragmentManager(), TAG);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions2, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions2, grantResults);
        if (1 == requestCode) {
            if (grantResults.length > 0 && grantResults[0] == 0) {
                Log.d(TAG, "onRequestPermissionsResult: Calling permission granted");
                call_tenant();
            }
        } else if (2 == requestCode && grantResults.length > 0 && grantResults[1] == 0) {
            Log.d(TAG, "onRequestPermissionsResult: Sending SMS permission granted");
            send_text_message();
        }
    }

    public void onTenantLongClicked(final Entity_TENANTS entity_tenants, View view) {
        ((ViewModel_Tenant_Long_Clicked) ViewModelProviders.of(getActivity()).get(ViewModel_Tenant_Long_Clicked.class)).setTenant_long_clicked(entity_tenants);
        PopupMenu popupMenu = new PopupMenu(getActivity(), view, 17);
        popupMenu.inflate(R.menu.contact_tenant);
        popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.call_tenant_button) {
                    Fragment_Tenants.this.check_CALL_permissions();
                    return true;
                } else if (itemId == R.id.delete_tenant_button) {
                    Dialog_Fragment_Delete_tenant.newInstance(entity_tenants).show(Fragment_Tenants.this.getActivity().getSupportFragmentManager(), Fragment_Tenants.TAG);
                    return true;
                } else if (itemId == R.id.edit_tenant_button) {
                    Intent intent = new Intent(Fragment_Tenants.this.getActivity(), Activity_Update_Tenant.class);
                    intent.putExtra(Activity_Update_Tenant.UPDATE_TENANT, entity_tenants);
                    Fragment_Tenants.this.startActivity(intent);
                    return true;
                } else if (itemId != R.id.text_tenant_button) {
                    return false;
                } else {
                    Fragment_Tenants.this.check_SMS_permissions();
                    return true;
                }
            }
        });
        popupMenu.show();
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.contact_tenant, menu);
    }
}
