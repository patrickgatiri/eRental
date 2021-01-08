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
import com.patrickgatirigmail.finale.Adapter_Rent_Recycler_View.OnRentBillClickedListener;
import com.patrickgatirigmail.finale.Adapter_Rent_Recycler_View.OnRentBillLongClickedListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Fragment_Rent extends Fragment {
    private static final String TAG = "Fragment_Rent";
    /* access modifiers changed from: private */
    public int house_number;
    /* access modifiers changed from: private */
    public FloatingActionButton new_rent_bill_button;
    private RecyclerView rent_bills_recycler_view;
    /* access modifiers changed from: private */
    public Spinner rent_house_number_spinner;

    public static Fragment_Rent newInstance() {
        return new Fragment_Rent();
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (savedInstanceState != null) {
            ((ViewModel_Rent_House_Number_Selected) ViewModelProviders.of(getActivity()).get(ViewModel_Rent_House_Number_Selected.class)).setHouse_number_selected(savedInstanceState.getInt(TAG));
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rent, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    public void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);
        ((ViewModel_Rent_House_Number_Selected) ViewModelProviders.of(getActivity()).get(ViewModel_Rent_House_Number_Selected.class)).getHouseNumber_Selected().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            public void onChanged(@Nullable Integer integer) {
                outState.putInt(Fragment_Rent.TAG, integer.intValue());
            }
        });
    }

    private void init(View view) {
        this.rent_bills_recycler_view = (RecyclerView) view.findViewById(R.id.rent_bills_recycler_view);
        this.new_rent_bill_button = (FloatingActionButton) view.findViewById(R.id.new_rent_bill_button);
        this.rent_house_number_spinner = (Spinner) view.findViewById(R.id.rent_house_number_spinner);
        setUpSpinner();
        setUpRecycler_View();
        this.new_rent_bill_button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                final int[] house_number_selected = new int[1];
                ((ViewModel_Rent_House_Number_Selected) ViewModelProviders.of(Fragment_Rent.this.getActivity()).get(ViewModel_Rent_House_Number_Selected.class)).getHouseNumber_Selected().observe(Fragment_Rent.this.getViewLifecycleOwner(), new Observer<Integer>() {
                    public void onChanged(@Nullable Integer integer) {
                        house_number_selected[0] = integer.intValue();
                    }
                });
                Intent intent = new Intent(Fragment_Rent.this.getActivity(), Activity_Insert_Rent.class);
                intent.putExtra("HOUSE_NUMBER_SELECTED", house_number_selected[0]);
                Fragment_Rent.this.startActivity(intent);
            }
        });
    }

    private void setUpRecycler_View() {
        final Adapter_Rent_Recycler_View adapter_rent_recycler_view = new Adapter_Rent_Recycler_View(getActivity());
        ((ViewModel_Rent_House_Number_Selected) ViewModelProviders.of(getActivity()).get(ViewModel_Rent_House_Number_Selected.class)).getHouseNumber_Selected().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            public void onChanged(@Nullable Integer integer) {
                ((ViewModel_getRentBillsPerHouse) ViewModelProviders.of(Fragment_Rent.this.getActivity()).get(ViewModel_getRentBillsPerHouse.class)).getRentBillsPerHouse(integer.intValue()).observe(Fragment_Rent.this.getViewLifecycleOwner(), new Observer<List<POJO_RentBills_per_House>>() {
                    public void onChanged(@Nullable List<POJO_RentBills_per_House> pojo_rentBills_per_houses) {
                        adapter_rent_recycler_view.submitList(pojo_rentBills_per_houses);
                    }
                });
            }
        });
        this.rent_bills_recycler_view.setHasFixedSize(true);
        this.rent_bills_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        this.rent_bills_recycler_view.setAdapter(adapter_rent_recycler_view);
        adapter_rent_recycler_view.setOnRentBillClickedListener(new OnRentBillClickedListener() {
            public void onRentBillClicked(POJO_RentBills_per_House pojo_rentBills_per_house) {
                ((ViewModel_Rent_Bill_Clicked) ViewModelProviders.of(Fragment_Rent.this.getActivity()).get(ViewModel_Rent_Bill_Clicked.class)).setRent_bill_clicked(pojo_rentBills_per_house);
                new Bottom_Sheet_View_Rent_Details().show(((FragmentActivity) Objects.requireNonNull(Fragment_Rent.this.getActivity())).getSupportFragmentManager(), Fragment_Rent.TAG);
            }
        });
        adapter_rent_recycler_view.setOnRentBillLongClickedListener(new OnRentBillLongClickedListener() {
            public void onRentBillLongCLicked(final POJO_RentBills_per_House pojo_rentBills_per_house) {
                final int[] rent_bill_id = {pojo_rentBills_per_house.getRENT_BILL_ID()};
                ((ViewModel_getRentBillById) ViewModelProviders.of(Fragment_Rent.this.getActivity()).get(ViewModel_getRentBillById.class)).getRentBillById(pojo_rentBills_per_house.getRENT_BILL_ID()).observe(Fragment_Rent.this.getViewLifecycleOwner(), new Observer<Entity_RENT>() {
                    public void onChanged(@Nullable Entity_RENT rent) {
                        if (rent_bill_id[0] == pojo_rentBills_per_house.getRENT_BILL_ID()) {
                            Dialog_Fragment_Delete_Rent.newInstance(rent).show(((FragmentActivity) Objects.requireNonNull(Fragment_Rent.this.getActivity())).getSupportFragmentManager(), Fragment_Rent.TAG);
                            int[] iArr = rent_bill_id;
                            iArr[0] = iArr[0] + 1;
                        }
                    }
                });
            }
        });
    }

    private void setUpSpinner() {
        ((ViewModel_getAllTenants) ViewModelProviders.of(getActivity()).get(ViewModel_getAllTenants.class)).getAllTenants().observe(getViewLifecycleOwner(), new Observer<List<Entity_TENANTS>>() {
            public void onChanged(@Nullable List<Entity_TENANTS> entity_tenants) {
                List<Integer> allHouseNumbers = new ArrayList<>();
                List<String> house_numbers = new ArrayList<>();
                for (Entity_TENANTS tenants : entity_tenants) {
                    allHouseNumbers.add(Integer.valueOf(tenants.getHOUSE_NUMBER()));
                    house_numbers.add(Fragment_Rent.this.getString(R.string.house_numbers, Integer.valueOf(tenants.getHOUSE_NUMBER())));
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(Fragment_Rent.this.getActivity(), 17367043, house_numbers);
                arrayAdapter.setDropDownViewResource(17367049);
                Fragment_Rent.this.rent_house_number_spinner.setAdapter(arrayAdapter);
                if (house_numbers.size() == 0) {
                    Fragment_Rent.this.rent_house_number_spinner.setVisibility(4);
                    Fragment_Rent.this.new_rent_bill_button.setEnabled(false);
                    return;
                }
                Fragment_Rent.this.rent_house_number_spinner.setVisibility(0);
                Fragment_Rent.this.new_rent_bill_button.setEnabled(true);
            }
        });
        this.rent_house_number_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {
                final List<Integer> getAllHouseNumbers = new ArrayList<>();
                ((ViewModel_getAllTenants) ViewModelProviders.of(Fragment_Rent.this.getActivity()).get(ViewModel_getAllTenants.class)).getAllTenants().observe(Fragment_Rent.this.getViewLifecycleOwner(), new Observer<List<Entity_TENANTS>>() {
                    public void onChanged(@Nullable List<Entity_TENANTS> entity_tenants) {
                        for (Entity_TENANTS tenants : entity_tenants) {
                            getAllHouseNumbers.add(Integer.valueOf(tenants.getHOUSE_NUMBER()));
                        }
                        if (getAllHouseNumbers.size() > 0) {
                            Fragment_Rent.this.house_number = ((Integer) getAllHouseNumbers.get(i)).intValue();
                        }
                        ((ViewModel_Rent_House_Number_Selected) ViewModelProviders.of(Fragment_Rent.this.getActivity()).get(ViewModel_Rent_House_Number_Selected.class)).setHouse_number_selected(((Integer) getAllHouseNumbers.get(i)).intValue());
                    }
                });
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.delete_all_details, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.delete_records_button != item.getItemId()) {
            return super.onOptionsItemSelected(item);
        }
        Dialog_Fragment_Delete_All_Rent_Records.newInstance(this.house_number).show(getActivity().getSupportFragmentManager(), TAG);
        return true;
    }
}
