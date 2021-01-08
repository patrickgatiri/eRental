package com.patrickgatirigmail.finale;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Activity_Update_Rent extends AppCompatActivity {
    private int rent_bill_house_number;
    private int rent_bill_id;
    private TextView update_rent_month;
    private TextInputLayout update_rent_paid;
    private TextView update_rent_year;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity__update__rent);
        init();
    }

    private void init() {
        POJO_RentBills_per_House pojo_rentBills_per_house = (POJO_RentBills_per_House) getIntent().getParcelableExtra(Bottom_Sheet_View_Rent_Details.POJO_EXTRA);
        this.rent_bill_id = pojo_rentBills_per_house.getRENT_BILL_ID();
        this.rent_bill_house_number = pojo_rentBills_per_house.getHOUSE_NUMBER();
        setSupportActionBar((Toolbar) findViewById(R.id.update_rent_toolbar));
        setTitle(R.string.UPDATE_RENT);
        this.update_rent_month = (TextView) findViewById(R.id.update_rent_month);
        this.update_rent_year = (TextView) findViewById(R.id.update_rent_year);
        this.update_rent_paid = (TextInputLayout) findViewById(R.id.update_rent_paid);
        this.update_rent_month.setText(pojo_rentBills_per_house.getMONTH());
        this.update_rent_year.setText(String.valueOf(pojo_rentBills_per_house.getYEAR()));
        this.update_rent_paid.getEditText().setText(String.valueOf(pojo_rentBills_per_house.getRENT_PAID()));
    }

    private boolean input_validation() {
        if (this.update_rent_paid.getEditText().getText().toString().trim().length() != 0) {
            return true;
        }
        this.update_rent_paid.setError(getString(R.string.field_cant_be_empty));
        return false;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.save_button != item.getItemId()) {
            return super.onOptionsItemSelected(item);
        }
        if (input_validation()) {
            Entity_RENT entity_rent = new Entity_RENT(this.rent_bill_house_number, new Time(this.update_rent_month.getText().toString().trim(), Integer.parseInt(this.update_rent_year.getText().toString().trim())), Double.valueOf(Double.parseDouble(this.update_rent_paid.getEditText().getText().toString())));
            entity_rent.setRENT_BILL_ID(this.rent_bill_id);
            ((ViewModel_UPDATERENT) ViewModelProviders.of((FragmentActivity) this).get(ViewModel_UPDATERENT.class)).update_rent(this, entity_rent);
            finish();
            return true;
        }
        this.update_rent_paid.setError(getString(R.string.field_cant_be_empty));
        return true;
    }
}
