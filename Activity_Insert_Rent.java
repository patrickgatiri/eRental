package com.patrickgatirigmail.finale;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.Calendar;

public class Activity_Insert_Rent extends AppCompatActivity {
    public static final String HOUSE_NUMBER_SELECTED = "HOUSE_NUMBER_SELECTED";
    private int house_number_selected;
    private Spinner insert_rent_month_spinner;
    private TextInputLayout insert_rent_paid;
    private Spinner insert_rent_year_spinner;
    /* access modifiers changed from: private */
    public String month;
    /* access modifiers changed from: private */
    public int year;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity__insert__rent);
        init();
    }

    private void init() {
        this.house_number_selected = getIntent().getIntExtra("HOUSE_NUMBER_SELECTED", 0);
        setSupportActionBar((Toolbar) findViewById(R.id.insert_rent_toolbar));
        setTitle(getString(R.string.new_rent_bill_title, new Object[]{Integer.valueOf(this.house_number_selected)}));
        this.insert_rent_month_spinner = (Spinner) findViewById(R.id.insert_rent_month_spinner);
        this.insert_rent_year_spinner = (Spinner) findViewById(R.id.insert_rent_year_spinner);
        this.insert_rent_paid = (TextInputLayout) findViewById(R.id.insert_rent_paid);
        setUpMonthSpinner();
        setUpYearSpinner();
    }

    private void setUpMonthSpinner() {
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.MONTHS, 17367043);
        arrayAdapter.setDropDownViewResource(17367049);
        this.insert_rent_month_spinner.setAdapter(arrayAdapter);
        this.insert_rent_month_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Activity_Insert_Rent.this.month = adapterView.getItemAtPosition(i).toString().trim();
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
        this.insert_rent_year_spinner.setAdapter(arrayAdapter);
        this.insert_rent_year_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Activity_Insert_Rent.this.year = ((Integer) adapterView.getItemAtPosition(i)).intValue();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != R.id.save_button) {
            return super.onOptionsItemSelected(item);
        }
        if (this.insert_rent_paid.getEditText().getText().toString().trim().length() > 0) {
            ((ViewModel_INSERTRENT) ViewModelProviders.of((FragmentActivity) this).get(ViewModel_INSERTRENT.class)).insert_rent(this, new Entity_RENT(this.house_number_selected, new Time(this.month, this.year), Double.valueOf(Double.parseDouble(this.insert_rent_paid.getEditText().getText().toString().trim()))));
            finish();
        } else {
            this.insert_rent_paid.setErrorEnabled(true);
            this.insert_rent_paid.setError(getResources().getString(R.string.field_cant_be_empty));
        }
        return true;
    }
}
