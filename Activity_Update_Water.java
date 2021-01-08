package com.patrickgatirigmail.finale;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

public class Activity_Update_Water extends AppCompatActivity {
    private static final String TAG = "Activity_Update_Water";
    public static final String UPDATE_WATER = "UPDATE_WATER";
    /* access modifiers changed from: private */
    public Entity_WATER mentity_water;
    /* access modifiers changed from: private */
    public int next_water_bill_id;
    private EditText update_current_water_reading;
    private TextInputLayout update_water_paid;
    private int water_bill_id;
    private int water_house_number;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity__update__water);
        init();
    }

    private void init() {
        this.mentity_water = (Entity_WATER) getIntent().getParcelableExtra(UPDATE_WATER);
        this.water_bill_id = this.mentity_water.getWATER_BILL_ID();
        this.water_house_number = this.mentity_water.getHOUSE_NUMBER();
        setSupportActionBar((Toolbar) findViewById(R.id.update_water_toolbar));
        setTitle(R.string.UPDATE_WATER);
        TextView update_water_month = (TextView) findViewById(R.id.update_water_month);
        TextView update_water_year = (TextView) findViewById(R.id.update_water_year);
        this.update_current_water_reading = (EditText) findViewById(R.id.update_current_water_reading);
        this.update_water_paid = (TextInputLayout) findViewById(R.id.update_water_paid);
        update_water_month.setText(this.mentity_water.getTime_of_payment().getMONTH());
        update_water_year.setText(String.valueOf(this.mentity_water.getTime_of_payment().getYEAR()));
        this.update_water_paid.getEditText().setText(String.valueOf(this.mentity_water.getAMOUNT_PAID()));
        this.update_current_water_reading.setText(String.valueOf(this.mentity_water.getWater_readings().getCURRENT_READING()));
    }

    private boolean input_validation() {
        if (this.update_current_water_reading.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.field_cant_be_empty, 0).show();
            return false;
        } else if (this.update_water_paid.getEditText().getText().toString().trim().length() != 0) {
            return true;
        } else {
            this.update_water_paid.setError(getString(R.string.field_cant_be_empty));
            return false;
        }
    }

    private void getNextWaterBillId() {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("getNextWaterBillId: House number");
        sb.append(this.water_house_number);
        Log.d(str, sb.toString());
        ((ViewModel_getWaterBillsPerHouse) ViewModelProviders.of((FragmentActivity) this).get(ViewModel_getWaterBillsPerHouse.class)).getWaterBillsPerHouse(this.water_house_number).observe(this, new Observer<List<Entity_WATER>>() {
            public void onChanged(@Nullable List<Entity_WATER> entity_waters) {
                int[] array = new int[entity_waters.size()];
                int i = 0;
                for (int i2 = 0; i2 < entity_waters.size() - 1; i2++) {
                    String str = Activity_Update_Water.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onChanged: ");
                    sb.append(((Entity_WATER) entity_waters.get(i2)).getWATER_BILL_ID());
                    Log.d(str, sb.toString());
                    array[i2] = ((Entity_WATER) entity_waters.get(i2)).getWATER_BILL_ID();
                }
                Activity_Update_Water.this.bubbleSortList(array);
                int length = array.length;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    int i3 = array[i];
                    if (i3 > Activity_Update_Water.this.mentity_water.getWATER_BILL_ID()) {
                        Activity_Update_Water.this.next_water_bill_id = i3;
                        break;
                    }
                    i++;
                }
                Activity_Update_Water.this.updateWaterBill(Activity_Update_Water.this.next_water_bill_id);
            }
        });
    }

    /* access modifiers changed from: private */
    public void bubbleSortList(int[] array) {
        int i = 0;
        while (i < array.length - 1) {
            boolean swapped = false;
            for (int j = 0; j < (array.length - i) - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (swapped) {
                i++;
            } else {
                return;
            }
        }
    }

    public void updateWaterBill(int next_water_bill_id2) {
        Entity_WATER entity_water = new Entity_WATER(this.water_house_number, new Time_Of_Payment(this.mentity_water.getTime_of_payment().getMONTH(), this.mentity_water.getTime_of_payment().getYEAR()), new WATER_READINGS(Double.parseDouble(this.update_current_water_reading.getText().toString().trim()), this.mentity_water.getWater_readings().getPREVIOUS_READING()), Double.parseDouble(this.update_water_paid.getEditText().getText().toString().trim()));
        entity_water.setWATER_BILL_ID(this.water_bill_id);
        ((ViewModel_UPDATEWATER) ViewModelProviders.of((FragmentActivity) this).get(ViewModel_UPDATEWATER.class)).update_water(this, entity_water, next_water_bill_id2);
        finish();
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
            getNextWaterBillId();
        }
        return true;
    }
}
