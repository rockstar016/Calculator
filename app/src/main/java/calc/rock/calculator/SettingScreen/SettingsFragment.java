package calc.rock.calculator.SettingScreen;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import calc.rock.calculator.R;
import calc.rock.calculator.Utils.Constants;
import calc.rock.calculator.Utils.ThemeManagement;


public class SettingsFragment extends Fragment implements CheckBox.OnCheckedChangeListener{

    CheckBox chk_scientific_calc;
    CheckBox chk_normal_memKey;
    CheckBox chk_scientific_sperator;
    CheckBox chk_scientific_vibration;

    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        chk_scientific_calc = (CheckBox)rootView.findViewById(R.id.chk_scientic_calc);
        chk_scientific_calc.setChecked(ThemeManagement.getCalculator(getContext(), Constants.CURRENT_CALCULATOR) == Constants.SCIENTIFIC_CALC ? true : false);
        chk_scientific_calc.setOnCheckedChangeListener(this);

        chk_normal_memKey = (CheckBox)rootView.findViewById(R.id.chk_show_keys);
        chk_normal_memKey.setChecked(ThemeManagement.getCalculator(getContext(), Constants.MEMORY_KEYS) == 1? true : false);
        chk_normal_memKey.setOnCheckedChangeListener(this);

        chk_scientific_sperator = (CheckBox)rootView.findViewById(R.id.chk_thousand_seperator);
        chk_scientific_sperator.setChecked(ThemeManagement.getCalculator(getContext(), Constants.THOUSAND_SEPERATOR) == 1 ? true : false);
        chk_scientific_sperator.setOnCheckedChangeListener(this);

        chk_scientific_vibration = (CheckBox)rootView.findViewById(R.id.chk_vibration);
        chk_scientific_vibration.setChecked(ThemeManagement.getCalculator(getContext(), Constants.VIBRATION) == 1 ? true : false);
        chk_scientific_vibration.setOnCheckedChangeListener(this);

        return rootView;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView.getId() == R.id.chk_scientic_calc){
            ThemeManagement.setCalculator(getContext(), Constants.CURRENT_CALCULATOR,isChecked == true? Constants.SCIENTIFIC_CALC: Constants.NORMAL_CALC);
        }
        if(buttonView.getId() == R.id.chk_show_keys){
            ThemeManagement.setCalculator(getContext(), Constants.MEMORY_KEYS,isChecked == true?1:0);
        }
        if(buttonView.getId() == R.id.chk_thousand_seperator){
            ThemeManagement.setCalculator(getContext(), Constants.THOUSAND_SEPERATOR,isChecked == true?1:0);
        }
        if(buttonView.getId() == R.id.chk_vibration){
            ThemeManagement.setCalculator(getContext(), Constants.VIBRATION,isChecked == true?1:0);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}
