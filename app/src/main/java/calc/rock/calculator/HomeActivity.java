package calc.rock.calculator;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

import calc.rock.calculator.Database.HistoryListViewModel;
import calc.rock.calculator.Database.HistoryModel;
import calc.rock.calculator.Utils.Constants;
import calc.rock.calculator.Utils.ThemeManagement;

public class HomeActivity extends AppCompatActivity implements CalculateFragment.HistoryInterface{

    //additional
    ActionBarDrawerToggle drawerToggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ArrayList<HistoryModel> buffer = new ArrayList<>();

    FragmentManager fragmentManager;
    NavigationView navigationView;
    FrameLayout frameLayout;

    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    HistoryListViewModel viewModel;

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        makeNewObserver();
        setupView();
        if (savedInstanceState == null) showCalculate();
    }

    private void makeNewObserver()
    {

    }


    private void setupView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        frameLayout = (FrameLayout) findViewById(R.id.main_container);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerViewAdapter = new RecyclerViewAdapter(buffer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);

        //read database
//        viewModel = ViewModelProviders.of(this).get(HistoryListViewModel.class);
//
//        viewModel.getItemAndPersonList().observe(this, new Observer<List<HistoryModel>>() {
//            @Override
//            public void onChanged(@Nullable List<HistoryModel> historyModelList) {
//                buffer.addAll(historyModelList);
//                //recyclerViewAdapter.addItems(historyModelList);
//            }
//        });
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });
    }
    private void showCalculate() {
        selectDrawerItem(navigationView.getMenu().getItem(0));
        //drawerLayout.openDrawer(GravityCompat.START);
    }

    private void selectDrawerItem(MenuItem menuItem) {
        //boolean specialToolbarBehaviour = false;
        Class fragmentClass;

        switch (menuItem.getItemId()) {
            case R.id.calculate:
                Log.d("aaaaa", "aaaaa");
                fragmentClass = CalculateFragment.class;
                break;
            case R.id.settings:
                Log.d("bbbb", "bbbb");
                fragmentClass = CalculateFragment.class;
                startActivity(new Intent(HomeActivity.this, SettingActivity.class));
                break;
            case R.id.exit:
                fragmentClass = CalculateFragment.class;
                onBackPressed();
                break;
            default:
                fragmentClass = CalculateFragment.class;
                break;
        }

        try {
            Fragment fragment = (Fragment) fragmentClass.newInstance();
            fragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //setToolbarElevation(specialToolbarBehaviour);
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_actionbar, menu);
        MenuItem switchItem = menu.findItem(R.id.mySwitch);
        switchItem.setActionView(R.layout.switch_layout);
        final Switch sw = (Switch)menu.findItem(R.id.mySwitch).getActionView().findViewById(R.id.actionbar_switch);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ThemeManagement.setCalculator(getApplicationContext(), Constants.CURRENT_CALCULATOR,isChecked == true? Constants.SCIENTIFIC_CALC: Constants.NORMAL_CALC);
                Class fragmentClass;
                fragmentClass = CalculateFragment.class;
                try {
                    Fragment fragment = (Fragment) fragmentClass.newInstance();
                    fragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit();

                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
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
        //view datamodel=> inserttodatabase(model)
        recyclerViewAdapter.notifyDataSetChanged();
    }

}