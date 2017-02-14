package calc.rock.calculator.SettingScreen;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by rock on 2/14/17.
 */

public class SettingPagerAdapter extends FragmentStatePagerAdapter {
    int tab_count;
    public SettingPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tab_count = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f;
        switch (position){
            case 0:
                //theme
                f = ThemeFragment.newInstance("","");
                break;
            case 1:
                f = SettingsFragment.newInstance("","");
                //setting
                break;
            case 2:
                //advance
                f = AdvancedFragment.newInstance("","");
                break;
            default:
                f = ThemeFragment.newInstance("","");
        }
        return f;
    }

    @Override
    public int getCount() {
        return tab_count;
    }
}
