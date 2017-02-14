package calc.rock.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import calc.rock.calculator.SettingScreen.SettingPagerAdapter;
import calc.rock.calculator.Utils.Constants;
import calc.rock.calculator.Utils.ThemeManagement;

public class SettingActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{
    ViewPager pager;
    SettingPagerAdapter adapter;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initThemes();

        setContentView(R.layout.activity_setting);
        pager = (ViewPager)findViewById(R.id.vp_settings);
        tabLayout = (TabLayout) findViewById(R.id.tablayout_setting);
        pagerInit();
    }
    private void initThemes(){
        int currentTheme = ThemeManagement.getTheme(this);
        if(currentTheme == Constants.GRAY_THEME){
            setTheme(R.style.AppTheme_GrayTheme);
        }
        else if(currentTheme == Constants.BLUE_THEME){
            setTheme(R.style.AppTheme_BlueTheme);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(SettingActivity.this, HomeActivity.class);
        startActivity(i);
        finish();

    }

    public void pagerInit(){

        SettingPagerAdapter adapter = new SettingPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setScrollPosition(position,0f,true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.addOnTabSelectedListener(this);
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
