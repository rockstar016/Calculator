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
        ed.apply();
    }

    public static int getTheme(Context context,String  storeKey,int baseColor){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getInt(storeKey, baseColor);
    }

    public static void setCalculator(Context context, String stroeKey,int theme){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor ed = sp.edit();
        ed.putInt(stroeKey, theme);
        ed.apply();
    }

    public static int getCalculator(Context context,String obtainkey){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getInt(obtainkey, Constants.NORMAL_CALC);
    }
}
