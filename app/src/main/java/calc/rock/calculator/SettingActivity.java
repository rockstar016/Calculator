package calc.rock.calculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import calc.rock.calculator.Utils.Constants;
import calc.rock.calculator.Utils.ThemeManagement;

public class SettingActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    CheckBox chk_normal_memKey;
    CheckBox chk_scientific_sperator;
    CheckBox chk_scientific_vibration;
    TextView    txtColor, BackgroundColor, primaryColor, resetColor;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_theme);
        initToolbar();
        initThemes();
        InitViews();
        InitColorButtons();
    }

    void initToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(ThemeManagement.getTheme(this, Constants.PRIMARY_COLOR, Constants.RES_PRIMARYCOLOR));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.setting);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return true;
    }

    private void initThemes(){

    }

    private void InitViews()
    {
        chk_normal_memKey = (CheckBox)findViewById(R.id.chk_show_keys);
        chk_normal_memKey.setChecked(ThemeManagement.getCalculator(this, Constants.MEMORY_KEYS) == 1);
        chk_normal_memKey.setOnCheckedChangeListener(this);

        chk_scientific_sperator = (CheckBox)findViewById(R.id.chk_thousand_seperator);
        chk_scientific_sperator.setChecked(ThemeManagement.getCalculator(this, Constants.THOUSAND_SEPERATOR) == 1);
        chk_scientific_sperator.setOnCheckedChangeListener(this);

        chk_scientific_vibration = (CheckBox)findViewById(R.id.chk_vibration);
        chk_scientific_vibration.setChecked(ThemeManagement.getCalculator(this, Constants.VIBRATION) == 1);
        chk_scientific_vibration.setOnCheckedChangeListener(this);
    }

    private void InitColorButtons()
    {
        int initialColor;
        txtColor        = (TextView)findViewById(R.id.txtColor);
        BackgroundColor = (TextView)findViewById(R.id.backgroundColor);
        primaryColor    = (TextView)findViewById(R.id.primaryColor);
        resetColor      = (TextView)findViewById(R.id.resetColor);
        initialColor = ThemeManagement.getTheme(this, Constants.TEXT_COLOR, Constants.RES_TEXTCOLOR);
        setTextColor(initialColor);
        initialColor = ThemeManagement.getTheme(this, Constants.PRIMARY_COLOR, Constants.RES_PRIMARYCOLOR);
        setprimaryColor(initialColor);
        initialColor = ThemeManagement.getTheme(this, Constants.BACKGROUND_COLOR, Constants.RES_BACKGROUNDCOLOR);
        setBackgroundColor(initialColor);
        txtColor.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent  intent = new Intent();
                intent.setClassName(Constants.PACKAGENAME,
                        Constants.PACKAGENAME+".ColorPickerActivity");
                intent.putExtra("Color", Constants.TEXT_COLOR);
                startActivity(intent);
                finish();
            }
        });

        primaryColor.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent  intent = new Intent();
                intent.setClassName(Constants.PACKAGENAME,
                        Constants.PACKAGENAME+".ColorPickerActivity");
                intent.putExtra("Color", Constants.PRIMARY_COLOR);
                startActivity(intent);
                finish();
            }
        });

        BackgroundColor.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent  intent = new Intent();
                intent.setClassName(Constants.PACKAGENAME,
                        Constants.PACKAGENAME+".ColorPickerActivity");
                intent.putExtra("Color", Constants.BACKGROUND_COLOR);
                startActivity(intent);
                finish();
            }
        });

        resetColor.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                txtColor.setBackgroundColor(Constants.RES_TEXTCOLOR);
                BackgroundColor.setBackgroundColor(Constants.RES_BACKGROUNDCOLOR);
                primaryColor.setBackgroundColor(Constants.RES_PRIMARYCOLOR);
                SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(SettingActivity.this).edit();
                edit.putInt(Constants.TEXT_COLOR, Constants.RES_TEXTCOLOR);
                edit.putInt(Constants.BACKGROUND_COLOR, Constants.RES_BACKGROUNDCOLOR);
                edit.putInt(Constants.PRIMARY_COLOR, Constants.RES_PRIMARYCOLOR);
                edit.apply();
            }
        });

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView.getId() == R.id.chk_show_keys){
            ThemeManagement.setCalculator(this, Constants.MEMORY_KEYS, isChecked ?1:0);
        }
        if(buttonView.getId() == R.id.chk_thousand_seperator){
            ThemeManagement.setCalculator(this, Constants.THOUSAND_SEPERATOR, isChecked ?1:0);
        }
        if(buttonView.getId() == R.id.chk_vibration){
            ThemeManagement.setCalculator(this, Constants.VIBRATION, isChecked ?1:0);
        }
    }

    public void setTextColor(int nColor){
        txtColor.setBackgroundColor(nColor);
    }
    public void setBackgroundColor(int nColor){
        BackgroundColor.setBackgroundColor(nColor);
    }
    public void setprimaryColor(int nColor){
        primaryColor.setBackgroundColor(nColor);
    }
}
