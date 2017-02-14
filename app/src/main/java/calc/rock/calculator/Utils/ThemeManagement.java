package calc.rock.calculator.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by rock on 2/12/17.
 */

public class ThemeManagement {
    public static void setTheme(Context context, int theme){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor ed = sp.edit();
        ed.putInt(Constants.CURRENT_THEME, theme);
        ed.commit();
    }

    public static int getTheme(Context context){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getInt(Constants.CURRENT_THEME, Constants.GRAY_THEME);
    }

    public static void setCalculator(Context context, int theme){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor ed = sp.edit();
        ed.putInt(Constants.CURRENT_CALCULATOR, theme);
        ed.commit();
    }

    public static int getCalculator(Context context){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getInt(Constants.CURRENT_CALCULATOR, Constants.NORMAL_CALC);
    }
}
