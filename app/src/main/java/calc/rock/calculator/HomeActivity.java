package calc.rock.calculator;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.codemybrainsout.ratingdialog.RatingDialog;

import java.util.ArrayList;

import calc.rock.calculator.Models.HistoryModel;
import calc.rock.calculator.Utils.Constants;
import calc.rock.calculator.Utils.ThemeManagement;


public class HomeActivity extends AppCompatActivity implements CalculateFragment.HistoryInterface{

    //additional
    ActionBarDrawerToggle drawerToggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    static ArrayList<HistoryModel> buffer = new ArrayList<>();
    FragmentManager fragmentManager;
    NavigationView navigationView;
    FrameLayout frameLayout;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        setupView();
        if (savedInstanceState == null) showCalculate();
    }

    private void setupView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(ThemeManagement.getTheme(this, Constants.PRIMARY_COLOR, Constants.RES_PRIMARYCOLOR));

        frameLayout = (FrameLayout) findViewById(R.id.main_container);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //recyclerView.setBackgroundColor(prefs.getInt(Constants.BACKGROUND_COLOR, Constants.RES_BACKGROUNDCOLOR));
        recyclerViewAdapter = new RecyclerViewAdapter(buffer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(toolbar != null)
        {
            toolbar.setBackgroundColor(ThemeManagement.getTheme(this, Constants.PRIMARY_COLOR, Constants.RES_PRIMARYCOLOR));
        }
    }

    private void showCalculate() {
        selectDrawerItem(navigationView.getMenu().getItem(0));
    }

    private void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.calculate:
                fragment = CalculateFragment.newInstance();
                break;
            case R.id.settings:
                startActivity(new Intent(HomeActivity.this, SettingActivity.class));
                break;
            case R.id.rate_it:
                launchMarket();
                break;
            case R.id.exit:
                onBackPressed();
                break;
        }

        if(fragment != null) fragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit();
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }

    private void launchMarket() {
        RatingDialog ratingDialog = new RatingDialog.Builder(this)
                .threshold(3)
                .title("How was your experience with us?")
                .titleTextColor(R.color.black)
                .positiveButtonText("Not Now")
                .negativeButtonText("Never")
                .positiveButtonTextColor(R.color.white)
                .negativeButtonTextColor(R.color.grey_500)
                .formTitle("Submit Feedback")
                .formHint("Tell us where we can improve")
                .formSubmitText("Submit")
                .formCancelText("Cancel")
                .ratingBarColor(R.color.colorBlue)
                .build();

        ratingDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_actionbar, menu);
        MenuItem switchItem = menu.findItem(R.id.mySwitch);
        switchItem.setActionView(R.layout.switch_layout);
        final Switch sw = (Switch)menu.findItem(R.id.mySwitch).getActionView().findViewById(R.id.actionbar_switch);
        sw.setChecked(ThemeManagement.getCalculator(getApplicationContext(), Constants.CURRENT_CALCULATOR) != Constants.NORMAL_CALC);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ThemeManagement.setCalculator(getApplicationContext(), Constants.CURRENT_CALCULATOR, isChecked ? Constants.SCIENTIFIC_CALC: Constants.NORMAL_CALC);
                Class fragmentClass;
                fragmentClass = CalculateFragment.class;
                try {
                    Fragment fragment = (Fragment) fragmentClass.newInstance();
                    fragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home
                && drawerLayout.isDrawerOpen(recyclerView)) {
            drawerLayout.closeDrawer(recyclerView);
            return true;
        }
        if (drawerToggle.onOptionsItemSelected(item)) {
            drawerLayout.closeDrawer(recyclerView);
            return true;
        }

        if (item.getItemId() == R.id.history) {
            drawerLayout.closeDrawer(navigationView);
            if (!drawerLayout.isDrawerOpen(recyclerView))
                drawerLayout.openDrawer(recyclerView);
            else
                drawerLayout.closeDrawer(recyclerView);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
    private void attachFragment(Fragment f) {
        if (f != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_container, f, null).addToBackStack(null).commit();
        }
    }

    @Override
    public void updateHistoryModel(HistoryModel model) {
        buffer.add(model);
        recyclerViewAdapter.notifyDataSetChanged();
    }

}