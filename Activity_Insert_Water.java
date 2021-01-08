package com.patrickgatirigmail.finale;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetBehavior.BottomSheetCallback;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import java.util.ArrayList;
import java.util.Calendar;

public class Activity_Insert_Water extends AppCompatActivity {
    public static final String HOUSE_NUMBER_SELECTED = "HOUSE_NUMBER_SELECTED";
    public static final String LATEST_WATER_BILL = "LATEST_WATER_BILL";
    /* access modifiers changed from: private */
    public BottomSheetBehavior bottomSheetBehavior;
    private int house_number;
    /* access modifiers changed from: private */
    public Entity_WATER latest_water_bill;
    /* access modifiers changed from: private */
    public String month;
    private TextInputLayout new_water_paid;
    private TextInputLayout new_water_reading;
    /* access modifiers changed from: private */
    public Button previos_water_reading_button;
    private View previous_reading_nested_scroll_view;
    private Spinner water_month_spinner;
    private Spinner water_year_spinner;
    /* access modifiers changed from: private */
    public int year;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity__insert__water);
        init();
    }

    private void init() {
        Bundle bundle = getIntent().getExtras();
        this.house_number = bundle.getInt("HOUSE_NUMBER_SELECTED");
        this.latest_water_bill = (Entity_WATER) bundle.getParcelable(LATEST_WATER_BILL);
        setSupportActionBar((Toolbar) findViewById(R.id.insert_water_toolbar));
        setTitle(R.string.INSERT_WATER);
        this.water_month_spinner = (Spinner) findViewById(R.id.water_month_spinner);
        this.water_year_spinner = (Spinner) findViewById(R.id.water_year_spinner);
        this.new_water_reading = (TextInputLayout) findViewById(R.id.new_water_reading);
        this.new_water_paid = (TextInputLayout) findViewById(R.id.new_water_paid);
        this.previos_water_reading_button = (Button) findViewById(R.id.previous_water_reading_button);
        TextView previous_reading_text_view = (TextView) findViewById(R.id.previous_reading_text_view);
        this.previous_reading_nested_scroll_view = findViewById(R.id.previous_reading_nested_scroll_view);
        if (this.latest_water_bill == null) {
            this.previos_water_reading_button.setText(R.string.this_is_the_first_reading);
            this.previos_water_reading_button.setEnabled(false);
        } else {
            YoYo.with(Techniques.DropOut).repeat(5).playOn(this.previos_water_reading_button);
            previous_reading_text_view.setText(getString(R.string.Previous_water_reading, new Object[]{this.latest_water_bill.toString()}));
        }
        setUpBottomSheet();
        setUpMonthSpinner();
        setUpYearSpinner();
    }

    private void setUpBottomSheet() {
        this.bottomSheetBehavior = BottomSheetBehavior.from(this.previous_reading_nested_scroll_view);
        this.bottomSheetBehavior.setHideable(true);
        this.bottomSheetBehavior.setState(5);
        this.previos_water_reading_button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (Activity_Insert_Water.this.latest_water_bill == null) {
                    Activity_Insert_Water.this.bottomSheetBehavior.setState(4);
                } else {
                    Activity_Insert_Water.this.bottomSheetBehavior.setState(3);
                }
            }
        });
        this.bottomSheetBehavior.setBottomSheetCallback(new BottomSheetCallback() {
            public void onStateChanged(@NonNull View view, int i) {
                switch (i) {
                    case 3:
                        Activity_Insert_Water.this.previos_water_reading_button.setEnabled(false);
                        return;
                    case 4:
                        Activity_Insert_Water.this.previos_water_reading_button.setEnabled(true);
                        return;
                    case 5:
                        Activity_Insert_Water.this.previos_water_reading_button.setEnabled(true);
                        return;
                    default:
                        Activity_Insert_Water.this.previos_water_reading_button.setEnabled(true);
                        return;
                }
            }

            public void onSlide(@NonNull View view, float v) {
            }
        });
    }

    private void setUpMonthSpinner() {
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.MONTHS, 17367043);
        arrayAdapter.setDropDownViewResource(17367049);
        this.water_month_spinner.setAdapter(arrayAdapter);
        this.water_month_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Activity_Insert_Water.this.month = adapterView.getItemAtPosition(i).toString().trim();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void setUpYearSpinner() {
        int Current_Year = Calendar.getInstance().get(1);
        ArrayList<Integer> years = new ArrayList<>();
        for (int i = Current_Year; i < Current_Year + 5; i++) {
            years.add(Integer.valueOf(i));
        }
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(this, 17367043, years);
        arrayAdapter.setDropDownViewResource(17367049);
        this.water_year_spinner.setAdapter(arrayAdapter);
        this.water_year_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Activity_Insert_Water.this.year = ((Integer) adapterView.getItemAtPosition(i)).intValue();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private boolean input_validation() {
        if (this.new_water_reading.getEditText().getText().toString().trim().length() == 0) {
            this.new_water_reading.setError(getString(R.string.field_cant_be_empty));
            return false;
        } else if (this.new_water_paid.getEditText().getText().toString().trim().length() != 0) {
            return true;
        } else {
            this.new_water_paid.setError(getString(R.string.field_cant_be_empty));
            return false;
        }
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
            if (this.latest_water_bill != null) {
                ViewModel_INSERTWATER viewModel_INSERTWATER = (ViewModel_INSERTWATER) ViewModelProviders.of((FragmentActivity) this).get(ViewModel_INSERTWATER.class);
                Entity_WATER entity_WATER = new Entity_WATER(this.house_number, new Time_Of_Payment(this.month, this.year), new WATER_READINGS(Double.parseDouble(this.new_water_reading.getEditText().getText().toString().trim()), this.latest_water_bill.getWater_readings().getCURRENT_READING()), Double.parseDouble(this.new_water_paid.getEditText().getText().toString().trim()));
                viewModel_INSERTWATER.insert_water(this, entity_WATER);
            } else {
                ViewModel_INSERTWATER viewModel_INSERTWATER2 = (ViewModel_INSERTWATER) ViewModelProviders.of((FragmentActivity) this).get(ViewModel_INSERTWATER.class);
                Entity_WATER entity_WATER2 = new Entity_WATER(this.house_number, new Time_Of_Payment(this.month, this.year), new WATER_READINGS(Double.parseDouble(this.new_water_reading.getEditText().getText().toString().trim()), 0.0d), Double.parseDouble(this.new_water_paid.getEditText().getText().toString().trim()));
                viewModel_INSERTWATER2.insert_water(this, entity_WATER2);
            }
            finish();
        }
        return true;
    }

    public void onBackPressed() {
        if (this.bottomSheetBehavior.getState() == 3) {
            this.bottomSheetBehavior.setState(4);
        } else if (this.bottomSheetBehavior.getState() == 4) {
            this.bottomSheetBehavior.setState(5);
        } else {
            super.onBackPressed();
        }
    }
}
