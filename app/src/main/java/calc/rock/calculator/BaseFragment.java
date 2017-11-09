package calc.rock.calculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.View;

import calc.rock.calculator.HomeScreen.CalculatorInterface;
import calc.rock.calculator.Utils.Constants;

/**
 * Created by rock on 2/12/17.
 */

public class BaseFragment extends Fragment {
    protected void initControls(View rootView){}
    protected void initStyles(){}
    protected CalculatorInterface parentFragment;
    protected int loadCurrentTheme(){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getContext());
        int current_theme = sp.getInt(Constants.CURRENT_THEME, R.style.AppTheme_NoActionBar);
        return current_theme;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentFragment = (CalculatorInterface)getParentFragment();
    }

}
