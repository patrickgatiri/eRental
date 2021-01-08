package com.patrickgatirigmail.finale;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.patrickgatirigmail.finale.Adapter_Water_Recycler_View.OnWaterBillLongClickedlistener;
import com.patrickgatirigmail.finale.Adapter_Water_Recycler_View.OnWaterBillSelectedListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Fragment_Water extends Fragment {
    private static final String TAG = "Fragment_Water";
    /* access modifiers changed from: private */
    public Entity_WATER latest_water_bill;
    /* access modifiers changed from: private */
    public FloatingActionButton new_water_bill_button;
    /* access modifiers changed from: private */
    public int next_water_bill_id;
    /* access modifiers changed from: private */
    public double previous_water_bill_reading;
    private RecyclerView water_bills_recycler_view;
    /* access modifiers changed from: private */
    public Spinner water_house_number_spinner;

    public static Fragment_Water newInstance() {
        return new Fragment_Water();
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_water, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        this.water_bills_recycler_view = (RecyclerView) view.findViewById(R.id.water_bills_recycler_view);
        this.new_water_bill_button = (FloatingActionButton) view.findViewById(R.id.new_water_bill_button);
        this.water_house_number_spinner = (Spinner) view.findViewById(R.id.water_house_number_spinner);
        setUpSpinner();
        setUpRecyclerView();
        setUpButton();
    }

    private void setUpSpinner() {
        ((ViewModel_getAllTenants) ViewModelProviders.of(getActivity()).get(ViewModel_getAllTenants.class)).getAllTenants().observe(getViewLifecycleOwner(), new Observer<List<Entity_TENANTS>>() {
            public void onChanged(@Nullable List<Entity_TENANTS> entity_tenants) {
                List<String> house_numbers = new ArrayList<>();
                for (Entity_TENANTS tenants : entity_tenants) {
                    house_numbers.add(Fragment_Water.this.getString(R.string.house_numbers, Integer.valueOf(tenants.getHOUSE_NUMBER())));
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(Fragment_Water.this.getActivity(), 17367043, house_numbers);
                arrayAdapter.setDropDownViewResource(17367049);
                Fragment_Water.this.water_house_number_spinner.setAdapter(arrayAdapter);
                if (house_numbers.size() == 0) {
                    Fragment_Water.this.water_house_number_spinner.setVisibility(4);
                    Fragment_Water.this.new_water_bill_button.setEnabled(false);
                    return;
                }
                Fragment_Water.this.water_house_number_spinner.setVisibility(0);
                Fragment_Water.this.new_water_bill_button.setEnabled(true);
            }
        });
        this.water_house_number_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {
                final List<Integer> getAllHouseNumbers = new ArrayList<>();
                ((ViewModel_getAllTenants) ViewModelProviders.of(Fragment_Water.this.getActivity()).get(ViewModel_getAllTenants.class)).getAllTenants().observe(Fragment_Water.this.getViewLifecycleOwner(), new Observer<List<Entity_TENANTS>>() {
                    public void onChanged(@Nullable List<Entity_TENANTS> entity_tenants) {
                        for (Entity_TENANTS tenants : entity_tenants) {
                            getAllHouseNumbers.add(Integer.valueOf(tenants.getHOUSE_NUMBER()));
                        }
                        ((ViewModel_Water_House_Number_Clicked) ViewModelProviders.of(Fragment_Water.this.getActivity()).get(ViewModel_Water_House_Number_Clicked.class)).setHouse_number_clicked(((Integer) getAllHouseNumbers.get(i)).intValue());
                        String str = Fragment_Water.TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("onItemSelected: House number selected");
                        sb.append(getAllHouseNumbers.get(i));
                        Log.d(str, sb.toString());
                    }
                });
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void setUpRecyclerView() {
        final Adapter_Water_Recycler_View adapter_water_recycler_view = new Adapter_Water_Recycler_View(getActivity());
        ((ViewModel_Water_House_Number_Clicked) ViewModelProviders.of(getActivity()).get(ViewModel_Water_House_Number_Clicked.class)).getHouseNumberClicked().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            public void onChanged(@Nullable Integer integer) {
                ((ViewModel_getWaterBillsPerHouse) ViewModelProviders.of(Fragment_Water.this.getActivity()).get(ViewModel_getWaterBillsPerHouse.class)).getWaterBillsPerHouse(integer.intValue()).observe(Fragment_Water.this.getViewLifecycleOwner(), new Observer<List<Entity_WATER>>() {
                    public void onChanged(@Nullable List<Entity_WATER> entity_waters) {
                        adapter_water_recycler_view.submitList(entity_waters);
                    }
                });
            }
        });
        this.water_bills_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity(), 1, false));
        this.water_bills_recycler_view.setHasFixedSize(true);
        this.water_bills_recycler_view.setAdapter(adapter_water_recycler_view);
        adapter_water_recycler_view.setOnWaterBillSelectedListener(new OnWaterBillSelectedListener() {
            public void onWaterBillSelected(Entity_WATER entity_water) {
                ((ViewModel_Water_Bill_Clicked) ViewModelProviders.of(Fragment_Water.this.getActivity()).get(ViewModel_Water_Bill_Clicked.class)).setWaterbillClicked(entity_water);
                new Bottom_Sheet_View_Water_Details().show(Fragment_Water.this.getActivity().getSupportFragmentManager(), Fragment_Water.TAG);
            }
        });
        adapter_water_recycler_view.setOnWaterBillLongClickedlistener(new OnWaterBillLongClickedlistener() {
            public void onWaterBillLongClicked(Entity_WATER entity_water) {
                Fragment_Water.this.getPreviousWaterBillReading(entity_water);
            }
        });
        getMostRecentRentBillHouse();
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

    /* access modifiers changed from: private */
    public void getPreviousWaterBillReading(final Entity_WATER water) {
        final int[] house_number_clicked = new int[1];
        ((ViewModel_Water_House_Number_Clicked) ViewModelProviders.of(getActivity()).get(ViewModel_Water_House_Number_Clicked.class)).getHouseNumberClicked().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            public void onChanged(@Nullable Integer integer) {
                house_number_clicked[0] = integer.intValue();
                String str = Fragment_Water.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onChanged: Add water bill for house number: ");
                sb.append(house_number_clicked[0]);
                Log.d(str, sb.toString());
            }
        });
        ((ViewModel_getWaterBillsPerHouse) ViewModelProviders.of(getActivity()).get(ViewModel_getWaterBillsPerHouse.class)).getWaterBillsPerHouse(house_number_clicked[0]).observe(getViewLifecycleOwner(), new Observer<List<Entity_WATER>>() {
            public void onChanged(@Nullable List<Entity_WATER> entity_waters) {
                Iterator it = entity_waters.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Entity_WATER entity_water = (Entity_WATER) it.next();
                    if (entity_water.getWATER_BILL_ID() < water.getWATER_BILL_ID()) {
                        Fragment_Water.this.previous_water_bill_reading = entity_water.getWater_readings().getCURRENT_READING();
                        break;
                    }
                }
                int[] array = new int[entity_waters.size()];
                for (int i = 0; i < entity_waters.size() - 1; i++) {
                    array[i] = ((Entity_WATER) entity_waters.get(i)).getWATER_BILL_ID();
                }
                Fragment_Water.this.bubbleSortList(array);
                for (int i2 : array) {
                    if (i2 > water.getWATER_BILL_ID()) {
                        Fragment_Water.this.next_water_bill_id = i2;
                        return;
                    }
                }
            }
        });
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onChanged: Previous water bill reading: ");
        sb.append(this.previous_water_bill_reading);
        Log.d(str, sb.toString());
        String str2 = TAG;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("onChanged: Next water bill id: ");
        sb2.append(this.next_water_bill_id);
        Log.d(str2, sb2.toString());
        Dialog_Fragment_Delete_Water.newInstance(water, this.previous_water_bill_reading, this.next_water_bill_id).show(getActivity().getSupportFragmentManager(), TAG);
    }

    private void setUpButton() {
        this.new_water_bill_button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                final int[] house_number_selected = new int[1];
                ((ViewModel_Water_House_Number_Clicked) ViewModelProviders.of(Fragment_Water.this.getActivity()).get(ViewModel_Water_House_Number_Clicked.class)).getHouseNumberClicked().observe(Fragment_Water.this.getViewLifecycleOwner(), new Observer<Integer>() {
                    public void onChanged(@Nullable Integer integer) {
                        house_number_selected[0] = integer.intValue();
                    }
                });
                Bundle bundle = new Bundle();
                bundle.putInt("HOUSE_NUMBER_SELECTED", house_number_selected[0]);
                bundle.putParcelable(Activity_Insert_Water.LATEST_WATER_BILL, Fragment_Water.this.latest_water_bill);
                Intent intent = new Intent(Fragment_Water.this.getActivity(), Activity_Insert_Water.class);
                intent.putExtras(bundle);
                Fragment_Water.this.startActivity(intent);
            }
        });
    }

    private void getMostRecentRentBillHouse() {
        ((ViewModel_Water_House_Number_Clicked) ViewModelProviders.of(getActivity()).get(ViewModel_Water_House_Number_Clicked.class)).getHouseNumberClicked().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            public void onChanged(@Nullable Integer integer) {
                String str = Fragment_Water.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("getMostRecentRentBillHouse: House number clicked: ");
                sb.append(integer);
                Log.d(str, sb.toString());
                ((ViewModel_getWaterBillsPerHouse) ViewModelProviders.of(Fragment_Water.this.getActivity()).get(ViewModel_getWaterBillsPerHouse.class)).getWaterBillsPerHouse(integer.intValue()).observe(Fragment_Water.this.getViewLifecycleOwner(), new Observer<List<Entity_WATER>>() {
                    public void onChanged(@Nullable List<Entity_WATER> entity_waters) {
                        ArrayList<Integer> water_bill_ids_per_house = new ArrayList<>();
                        for (Entity_WATER entity_water : entity_waters) {
                            water_bill_ids_per_house.add(Integer.valueOf(entity_water.getWATER_BILL_ID()));
                        }
                        String str = Fragment_Water.TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("getMostRecentRentBillHouse: Water bills for house: ");
                        sb.append(water_bill_ids_per_house.size());
                        Log.d(str, sb.toString());
                        ((ViewModel_getWaterBillById) ViewModelProviders.of(Fragment_Water.this.getActivity()).get(ViewModel_getWaterBillById.class)).getWaterBillById(Fragment_Water.this.getHighestWaterBillId(water_bill_ids_per_house)).observe(Fragment_Water.this.getViewLifecycleOwner(), new Observer<Entity_WATER>() {
                            public void onChanged(@Nullable Entity_WATER entity_water) {
                                Fragment_Water.this.latest_water_bill = entity_water;
                            }
                        });
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public int getHighestWaterBillId(ArrayList<Integer> water_bill_ids) {
        int highest_id = 0;
        Iterator it = water_bill_ids.iterator();
        while (it.hasNext()) {
            int water_bill_id = ((Integer) it.next()).intValue();
            if (water_bill_id > highest_id) {
                highest_id = water_bill_id;
            }
        }
        return highest_id;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.delete_all_details, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.delete_records_button != item.getItemId()) {
            return super.onOptionsItemSelected(item);
        }
        final int[] house_number_clicked = new int[1];
        ((ViewModel_Water_House_Number_Clicked) ViewModelProviders.of(getActivity()).get(ViewModel_Water_House_Number_Clicked.class)).getHouseNumberClicked().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            public void onChanged(@Nullable Integer integer) {
                house_number_clicked[0] = integer.intValue();
            }
        });
        Dialog_Fragment_Delete_All_Water_Records.newInstance(house_number_clicked[0]).show(((FragmentActivity) Objects.requireNonNull(getActivity())).getSupportFragmentManager(), TAG);
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onOptionsItemSelected: Delete all water records for house number: ");
        sb.append(house_number_clicked[0]);
        Log.d(str, sb.toString());
        return true;
    }
}
