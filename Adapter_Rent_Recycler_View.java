package com.patrickgatirigmail.finale;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.internal.view.SupportMenu;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil.ItemCallback;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class Adapter_Rent_Recycler_View extends ListAdapter<POJO_RentBills_per_House, Rent_ViewHolder> {
    private static final String TAG = "Adapter_Rent_Recycler_View";
    private static final ItemCallback<POJO_RentBills_per_House> diffCallback = new ItemCallback<POJO_RentBills_per_House>() {
        public boolean areItemsTheSame(@NonNull POJO_RentBills_per_House pojo_rentBills_per_house, @NonNull POJO_RentBills_per_House t1) {
            return pojo_rentBills_per_house.getRENT_BILL_ID() == t1.getRENT_BILL_ID();
        }

        public boolean areContentsTheSame(@NonNull POJO_RentBills_per_House pojo_rentBills_per_house, @NonNull POJO_RentBills_per_House t1) {
            return pojo_rentBills_per_house.getRENT_PER_MONTH() == t1.getRENT_PER_MONTH() && pojo_rentBills_per_house.getRENT_PAID() == t1.getRENT_PAID() && pojo_rentBills_per_house.getMONTH().equals(t1.getMONTH()) && pojo_rentBills_per_house.getYEAR() == t1.getYEAR();
        }
    };
    private Context context;
    /* access modifiers changed from: private */
    public OnRentBillClickedListener onRentBillClickedListener;
    /* access modifiers changed from: private */
    public OnRentBillLongClickedListener onRentBillLongClickedListener;

    public interface OnRentBillClickedListener {
        void onRentBillClicked(POJO_RentBills_per_House pOJO_RentBills_per_House);
    }

    public interface OnRentBillLongClickedListener {
        void onRentBillLongCLicked(POJO_RentBills_per_House pOJO_RentBills_per_House);
    }

    class Rent_ViewHolder extends ViewHolder {
        /* access modifiers changed from: private */
        public TextView rent_balance_tv;
        /* access modifiers changed from: private */
        public TextView rent_month_tv;
        /* access modifiers changed from: private */
        public TextView rent_paid_tv;
        /* access modifiers changed from: private */
        public TextView rent_payment_status_text_view;
        /* access modifiers changed from: private */
        public TextView rent_year_tv;

        Rent_ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.rent_month_tv = (TextView) itemView.findViewById(R.id.rent_month_tv);
            this.rent_year_tv = (TextView) itemView.findViewById(R.id.rent_year_tv);
            this.rent_paid_tv = (TextView) itemView.findViewById(R.id.rent_paid_tv);
            this.rent_balance_tv = (TextView) itemView.findViewById(R.id.rent_balance_tv);
            this.rent_payment_status_text_view = (TextView) itemView.findViewById(R.id.rent_payment_status_text_view);
            itemView.setOnClickListener(new OnClickListener(Adapter_Rent_Recycler_View.this) {
                public void onClick(View view) {
                    if (Adapter_Rent_Recycler_View.this.onRentBillClickedListener != null && -1 != Rent_ViewHolder.this.getAdapterPosition()) {
                        Adapter_Rent_Recycler_View.this.onRentBillClickedListener.onRentBillClicked((POJO_RentBills_per_House) Adapter_Rent_Recycler_View.this.getItem(Rent_ViewHolder.this.getAdapterPosition()));
                    }
                }
            });
            itemView.setOnLongClickListener(new OnLongClickListener(Adapter_Rent_Recycler_View.this) {
                public boolean onLongClick(View view) {
                    if (!(Adapter_Rent_Recycler_View.this.onRentBillLongClickedListener == null || -1 == Rent_ViewHolder.this.getAdapterPosition())) {
                        Adapter_Rent_Recycler_View.this.onRentBillLongClickedListener.onRentBillLongCLicked((POJO_RentBills_per_House) Adapter_Rent_Recycler_View.this.getItem(Rent_ViewHolder.this.getAdapterPosition()));
                    }
                    return true;
                }
            });
        }
    }

    Adapter_Rent_Recycler_View(Context context2) {
        super(diffCallback);
        this.context = context2;
    }

    @NonNull
    public Rent_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Rent_ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rent_bills_cardview, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull Rent_ViewHolder rent_viewHolder, int i) {
        POJO_RentBills_per_House pojo_rentBills_per_house = (POJO_RentBills_per_House) getItem(i);
        Log.d(TAG, "onBindViewHolder: Binding fragment_rent bill cardviews");
        rent_viewHolder.rent_month_tv.setText(this.context.getString(R.string.rent_month, new Object[]{pojo_rentBills_per_house.getMONTH()}));
        rent_viewHolder.rent_year_tv.setText(this.context.getString(R.string.rent_year, new Object[]{Integer.valueOf(pojo_rentBills_per_house.getYEAR())}));
        rent_viewHolder.rent_paid_tv.setText(this.context.getString(R.string.rent_paid, new Object[]{Double.valueOf(pojo_rentBills_per_house.getRENT_PAID())}));
        rent_viewHolder.rent_balance_tv.setText(this.context.getString(R.string.rent_balance, new Object[]{Double.valueOf(pojo_rentBills_per_house.getBALANCE())}));
        if (pojo_rentBills_per_house.getBALANCE() > 0.0d) {
            rent_viewHolder.rent_payment_status_text_view.setTextColor(SupportMenu.CATEGORY_MASK);
            rent_viewHolder.rent_payment_status_text_view.setText(R.string.status_pending);
            return;
        }
        rent_viewHolder.rent_payment_status_text_view.setTextColor(-16711936);
        rent_viewHolder.rent_payment_status_text_view.setText(R.string.status_cleared);
    }

    public void setOnRentBillClickedListener(OnRentBillClickedListener onRentBillClickedListener2) {
        this.onRentBillClickedListener = onRentBillClickedListener2;
    }

    public void setOnRentBillLongClickedListener(OnRentBillLongClickedListener onRentBillLongClickedListener2) {
        this.onRentBillLongClickedListener = onRentBillLongClickedListener2;
    }
}
