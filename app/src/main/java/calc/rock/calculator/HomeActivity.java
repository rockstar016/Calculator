package calc.rock.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import calc.rock.calculator.Customs.CustomMemoryTextView;
import calc.rock.calculator.Customs.CustomNumberTextView;
import calc.rock.calculator.HomeScreen.HomeActivityInterface;
import calc.rock.calculator.HomeScreen.NormalCalculator;
import calc.rock.calculator.HomeScreen.ScientficCalculator;
import calc.rock.calculator.Utils.Constants;
import calc.rock.calculator.Utils.ThemeManagement;

public class HomeActivity extends AppCompatActivity implements HomeActivityInterface{
    CustomMemoryTextView txt_mr_view;
    CustomNumberTextView txt_current_view, txt_input_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initThemes();
        setContentView(R.layout.activity_home);
        initControls();
        loadStyles();
        loadCurrentCalculator();

    }

    public void loadCurrentCalculator(){
        if(ThemeManagement.getCalculator(this) == Constants.NORMAL_CALC){
            attachFragment(NormalCalculator.newInstance("",""));
        }
        else{
            attachFragment(ScientficCalculator.newInstance("",""));
        }
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

    private void initControls(){
        txt_mr_view = (CustomMemoryTextView) findViewById(R.id.txt_memory_view);
        txt_current_view = (CustomNumberTextView) findViewById(R.id.txt_current_value);
        txt_input_view = (CustomNumberTextView) findViewById(R.id.txt_input_value);
    }

    private void loadStyles(){
        int currentTheme = ThemeManagement.getTheme(this);
        txt_mr_view.setTheme(currentTheme);
        txt_input_view.setTheme(currentTheme);
        txt_current_view.setTheme(currentTheme);
    }

    private void attachFragment(Fragment f){
        if (f != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_container, f,null).addToBackStack(null).commit();
        }
    }

    @Override
    public void onClickSettingButton() {
        Intent i = new Intent(HomeActivity.this, SettingActivity.class);
        startActivity(i);
        finish();
    }
}
