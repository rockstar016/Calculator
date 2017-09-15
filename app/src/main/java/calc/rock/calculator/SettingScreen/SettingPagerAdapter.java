package calc.rock.calculator.SettingScreen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.lang.ref.WeakReference;

/**
 * Created by rock on 2/14/17.
 */

public class SettingPagerAdapter extends FragmentStatePagerAdapter {
    int tab_count;
    final WeakReference<Fragment>[] m_fragments = null;

    public SettingPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tab_count = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;

        switch (position){
            case 0:
                //theme
                f = ThemeFragment.newInstance("","");
                break;
            case 1:
                f = SettingsFragment.newInstance("","");
                //setting
                break;

        }
        return f;

    }

    @Override
    public int getCount() {
        return tab_count;
    }
}
