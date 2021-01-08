package com.patrickgatirigmail.finale;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.internal.view.SupportMenu;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil.ItemCallback;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class Adapter_Water_Recycler_View extends ListAdapter<Entity_WATER, Water_View_Holder> {
    private static ItemCallback<Entity_WATER> diffCallback = new ItemCallback<Entity_WATER>() {
        public boolean areItemsTheSame(@NonNull Entity_WATER water, @NonNull Entity_WATER t1) {
            return water.getWATER_BILL_ID() == t1.getWATER_BILL_ID();
        }

        public boolean areContentsTheSame(@NonNull Entity_WATER water, @NonNull Entity_WATER t1) {
            return water.getHOUSE_NUMBER() == t1.getHOUSE_NUMBER() && water.getTime_of_payment().getMONTH().equals(t1.getTime_of_payment().getMONTH()) && water.getTime_of_payment().getYEAR() == t1.getTime_of_payment().getYEAR() && water.getWater_readings().getCURRENT_READING() == t1.getWater_readings().getCURRENT_READING() && water.getWater_readings().getPREVIOUS_READING() == t1.getWater_readings().getPREVIOUS_READING() && water.getAMOUNT_PAID() == t1.getAMOUNT_PAID();
        }
    };
    private Context context;
    /* access modifiers changed from: private */
    public OnWaterBillLongClickedlistener onWaterBillLongClickedlistener;
    /* access modifiers changed from: private */
    public OnWaterBillSelectedListener onWaterBillSelectedListener;

    public interface OnWaterBillLongClickedlistener {
        void onWaterBillLongClicked(Entity_WATER entity_WATER);
    }

    public interface OnWaterBillSelectedListener {
        void onWaterBillSelected(Entity_WATER entity_WATER);
    }

    class Water_View_Holder extends ViewHolder {
        /* access modifiers changed from: private */
        public TextView current_reading_tv;
        /* access modifiers changed from: private */
        public TextView water_balance_tv;
        /* access modifiers changed from: private */
        public TextView water_due_tv;
        /* access modifiers changed from: private */
        public TextView water_month_tv;
        /* access modifiers changed from: private */
        public TextView water_payment_status_text_view;
        /* access modifiers changed from: private */
        public TextView water_year_tv;

        Water_View_Holder(@NonNull View itemView) {
            super(itemView);
            this.water_month_tv = (TextView) itemView.findViewById(R.id.water_month_tv);
            this.water_year_tv = (TextView) itemView.findViewById(R.id.water_year_tv);
            this.current_reading_tv = (TextView) itemView.findViewById(R.id.current_reading_tv);
            this.water_due_tv = (TextView) itemView.findViewById(R.id.water_due_tv);
            this.water_balance_tv = (TextView) itemView.findViewById(R.id.water_balance_tv);
            this.water_payment_status_text_view = (TextView) itemView.findViewById(R.id.water_payment_status_text_view);
            itemView.setOnClickListener(new OnClickListener(Adapter_Water_Recycler_View.this) {
                public void onClick(View view) {
                    if (Adapter_Water_Recycler_View.this.onWaterBillSelectedListener != null && -1 != Water_View_Holder.this.getAdapterPosition()) {
                        Adapter_Water_Recycler_View.this.onWaterBillSelectedListener.onWaterBillSelected((Entity_WATER) Adapter_Water_Recycler_View.this.getItem(Water_View_Holder.this.getAdapterPosition()));
                    }
                }
            });
            itemView.setOnLongClickListener(new OnLongClickListener(Adapter_Water_Recycler_View.this) {
                public boolean onLongClick(View view) {
                    if (!(Adapter_Water_Recycler_View.this.onWaterBillLongClickedlistener == null || -1 == Water_View_Holder.this.getAdapterPosition())) {
                        Adapter_Water_Recycler_View.this.onWaterBillLongClickedlistener.onWaterBillLongClicked((Entity_WATER) Adapter_Water_Recycler_View.this.getItem(Water_View_Holder.this.getAdapterPosition()));
                    }
                    return true;
                }
            });
        }
    }

    Adapter_Water_Recycler_View(Context context2) {
        super(diffCallback);
        this.context = context2;
    }

    @NonNull
    public Water_View_Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Water_View_Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.water_bills_cardview, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull Water_View_Holder water_view_holder, int i) {
        Entity_WATER entity_water = (Entity_WATER) getItem(i);
        water_view_holder.water_month_tv.setText(entity_water.getTime_of_payment().getMONTH());
        water_view_holder.water_year_tv.setText(String.valueOf(entity_water.getTime_of_payment().getYEAR()));
        water_view_holder.current_reading_tv.setText(this.context.getString(R.string.water_current_reading, new Object[]{Double.valueOf(entity_water.getWater_readings().getCURRENT_READING())}));
        water_view_holder.water_due_tv.setText(this.context.getString(R.string.water_amount_due, new Object[]{Double.valueOf(entity_water.getWater_readings().getAMOUNT_DUE())}));
        water_view_holder.water_balance_tv.setText(this.context.getString(R.string.water_balance, new Object[]{Double.valueOf(entity_water.getBALANCE())}));
        if (entity_water.getBALANCE() > 0.0d) {
            water_view_holder.water_payment_status_text_view.setTextColor(SupportMenu.CATEGORY_MASK);
            water_view_holder.water_payment_status_text_view.setText(R.string.status_pending);
            return;
        }
        water_view_holder.water_payment_status_text_view.setTextColor(-16711936);
        water_view_holder.water_payment_status_text_view.setText(R.string.status_cleared);
    }

    public void setOnWaterBillSelectedListener(OnWaterBillSelectedListener onWaterBillSelectedListener2) {
        this.onWaterBillSelectedListener = onWaterBillSelectedListener2;
    }

    public void setOnWaterBillLongClickedlistener(OnWaterBillLongClickedlistener onWaterBillLongClickedlistener2) {
        this.onWaterBillLongClickedlistener = onWaterBillLongClickedlistener2;
    }
}
