package com.patrickgatirigmail.finale;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.TabLayoutOnPageChangeListener;
import android.support.design.widget.TabLayout.ViewPagerOnTabSelectedListener;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;
import java.util.Objects;

public class Activity_Home extends AppCompatActivity implements OnNavigationItemSelectedListener {
    private static final String TAG = "Activity_Home";
    private long backpressed_time;
    private Home_Fragment_Pager_Adapter homeFragmentPagerAdapter;
    private ViewPager home_fragments_container;
    private NavigationView home_navigation_drawer;
    private TabLayout home_tab_layout;
    private Toolbar home_toolbar;
    private DrawerLayout homedrawerLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_home);
        init();
        this.home_fragments_container.setAdapter(this.homeFragmentPagerAdapter);
        this.home_tab_layout.addOnTabSelectedListener(new ViewPagerOnTabSelectedListener(this.home_fragments_container));
        this.home_fragments_container.addOnPageChangeListener(new TabLayoutOnPageChangeListener(this.home_tab_layout));
    }

    private void init() {
        this.homeFragmentPagerAdapter = new Home_Fragment_Pager_Adapter(getSupportFragmentManager());
        this.home_toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        this.home_fragments_container = (ViewPager) findViewById(R.id.home_fragments_container);
        this.home_tab_layout = (TabLayout) findViewById(R.id.home_tab_layout);
        this.home_navigation_drawer = (NavigationView) findViewById(R.id.home_navigation_drawer);
        this.homedrawerLayout = (DrawerLayout) findViewById(R.id.home_drawer_layout);
        setSupportActionBar(this.home_toolbar);
        setUpNavigationDrawer();
    }

    private void setUpNavigationDrawer() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, this.homedrawerLayout, this.home_toolbar, R.string.open_navigation_drawer, R.string.close_navigation_drawer);
        this.homedrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        this.home_navigation_drawer.setNavigationItemSelectedListener(this);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.email_button /*2131296334*/:
                open_send_email_dialog_fragment();
                break;
        }
        this.homedrawerLayout.closeDrawer((int) GravityCompat.START);
        return true;
    }

    public void onBackPressed() {
        if (this.homedrawerLayout.isDrawerOpen((int) GravityCompat.START)) {
            this.homedrawerLayout.closeDrawer((int) GravityCompat.START);
            return;
        }
        if (this.backpressed_time + 5000 > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, R.string.press_back_again_to_exit, 0).show();
        }
        this.backpressed_time = System.currentTimeMillis();
    }

    private boolean checkForInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService("connectivity");
        return ((ConnectivityManager) Objects.requireNonNull(connectivityManager)).getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    private void open_send_email_dialog_fragment() {
        new Dialog_Fragment_Send_Email().show(getSupportFragmentManager(), TAG);
    }
}
