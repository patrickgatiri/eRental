package com.patrickgatirigmail.finale;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil.ItemCallback;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter_Tenants_Recycler_View extends ListAdapter<Entity_TENANTS, Tenant_View_Holder> {
    private static final String TAG = "Adapter_Tenants_Recycler_View";
    private static final ItemCallback<Entity_TENANTS> diffCallback = new ItemCallback<Entity_TENANTS>() {
        public boolean areItemsTheSame(@NonNull Entity_TENANTS tenants, @NonNull Entity_TENANTS t1) {
            return tenants.getTENANT_ID() == t1.getTENANT_ID();
        }

        public boolean areContentsTheSame(@NonNull Entity_TENANTS tenants, @NonNull Entity_TENANTS t1) {
            boolean z = false;
            if (tenants.getPROFILE_PICTURE_URI() != null) {
                if (tenants.getHOUSE_NUMBER() == t1.getHOUSE_NUMBER() && tenants.getTenantName().getFIRST_NAME().equals(t1.getTenantName().getFIRST_NAME()) && tenants.getTenantName().getLAST_NAME().equals(t1.getTenantName().getLAST_NAME()) && tenants.getPROFILE_PICTURE_URI().equals(t1.getPROFILE_PICTURE_URI()) && tenants.getPHONE_NUMBER() == t1.getPHONE_NUMBER() && tenants.getRENT_PER_MONTH() == t1.getRENT_PER_MONTH()) {
                    z = true;
                }
                return z;
            } else if (tenants.getPROFILE_PICTURE_URI() != null || t1.getPROFILE_PICTURE_URI() == null) {
                if (tenants.getHOUSE_NUMBER() == t1.getHOUSE_NUMBER() && tenants.getTenantName().getFIRST_NAME().equals(t1.getTenantName().getFIRST_NAME()) && tenants.getTenantName().getLAST_NAME().equals(t1.getTenantName().getLAST_NAME()) && tenants.getPHONE_NUMBER() == t1.getPHONE_NUMBER() && tenants.getRENT_PER_MONTH() == t1.getRENT_PER_MONTH()) {
                    z = true;
                }
                return z;
            } else {
                if (tenants.getHOUSE_NUMBER() == t1.getHOUSE_NUMBER() && tenants.getTenantName().getFIRST_NAME().equals(t1.getTenantName().getFIRST_NAME()) && tenants.getTenantName().getLAST_NAME().equals(t1.getTenantName().getLAST_NAME()) && t1.getPROFILE_PICTURE_URI() == null && tenants.getPHONE_NUMBER() == t1.getPHONE_NUMBER() && tenants.getRENT_PER_MONTH() == t1.getRENT_PER_MONTH()) {
                    z = true;
                }
                return z;
            }
        }
    };
    private Context context;
    /* access modifiers changed from: private */
    public OnTenantClickedListener onTenantClickedListener;
    /* access modifiers changed from: private */
    public OnTenantLongClickedListener onTenantLongClickedListener;

    public interface OnTenantClickedListener {
        void onTenantClicked(Entity_TENANTS entity_TENANTS);
    }

    public interface OnTenantLongClickedListener {
        void onTenantLongClicked(Entity_TENANTS entity_TENANTS, View view);
    }

    class Tenant_View_Holder extends ViewHolder {
        /* access modifiers changed from: private */
        public TextView tenant_house_number_tv;
        /* access modifiers changed from: private */
        public TextView tenant_name_tv;
        /* access modifiers changed from: private */
        public ImageView tenant_profile_picture;

        Tenant_View_Holder(View itemView) {
            super(itemView);
            this.tenant_house_number_tv = (TextView) itemView.findViewById(R.id.tenant_house_number_tv);
            this.tenant_name_tv = (TextView) itemView.findViewById(R.id.tenant_name_tv);
            this.tenant_profile_picture = (ImageView) itemView.findViewById(R.id.tenant_profile_picture);
            itemView.setOnClickListener(new OnClickListener(Adapter_Tenants_Recycler_View.this) {
                public void onClick(View view) {
                    if (Adapter_Tenants_Recycler_View.this.onTenantClickedListener != null && Tenant_View_Holder.this.getAdapterPosition() != -1) {
                        Adapter_Tenants_Recycler_View.this.onTenantClickedListener.onTenantClicked((Entity_TENANTS) Adapter_Tenants_Recycler_View.this.getItem(Tenant_View_Holder.this.getAdapterPosition()));
                    }
                }
            });
            itemView.setOnLongClickListener(new OnLongClickListener(Adapter_Tenants_Recycler_View.this) {
                public boolean onLongClick(View view) {
                    if (!(Adapter_Tenants_Recycler_View.this.onTenantLongClickedListener == null || Tenant_View_Holder.this.getAdapterPosition() == -1)) {
                        Adapter_Tenants_Recycler_View.this.onTenantLongClickedListener.onTenantLongClicked((Entity_TENANTS) Adapter_Tenants_Recycler_View.this.getItem(Tenant_View_Holder.this.getAdapterPosition()), view);
                    }
                    return true;
                }
            });
        }
    }

    Adapter_Tenants_Recycler_View(Context context2) {
        super(diffCallback);
        this.context = context2;
    }

    @NonNull
    public Tenant_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: Creating tenant cardviews");
        return new Tenant_View_Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tenant_cardview, parent, false));
    }

    public void onBindViewHolder(@NonNull Tenant_View_Holder holder, int position) {
        Entity_TENANTS tenants = (Entity_TENANTS) getItem(position);
        holder.tenant_house_number_tv.setText(this.context.getString(R.string.house_number_name, new Object[]{Integer.valueOf(tenants.getHOUSE_NUMBER())}));
        holder.tenant_name_tv.setText(this.context.getString(R.string.tenant_full_name, new Object[]{tenants.getTenantName().getFIRST_NAME(), tenants.getTenantName().getLAST_NAME()}));
        if (tenants.getPROFILE_PICTURE_URI() != null) {
            holder.tenant_profile_picture.setImageURI(Uri.parse(tenants.getPROFILE_PICTURE_URI()));
        } else {
            holder.tenant_profile_picture.setImageResource(R.drawable.ic_person);
        }
    }

    public void setOnTenantClickedListener(OnTenantClickedListener onTenantClickedListener2) {
        this.onTenantClickedListener = onTenantClickedListener2;
    }

    public void setOnTenantLongClickedListener(OnTenantLongClickedListener onTenantLongClickedListener2) {
        this.onTenantLongClickedListener = onTenantLongClickedListener2;
    }
}
