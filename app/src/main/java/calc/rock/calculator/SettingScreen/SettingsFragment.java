package calc.rock.calculator.SettingScreen;

import android.content.Context;
import android.net.Uri;
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
    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
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
        chk_scientific_calc.setChecked(ThemeManagement.getCalculator(getContext()) == Constants.SCIENTIFIC_CALC?true:false);
        chk_scientific_calc.setOnCheckedChangeListener(this);
        return rootView;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView.getId() == R.id.chk_scientic_calc){
            ThemeManagement.setCalculator(getContext(), isChecked == true?Constants.SCIENTIFIC_CALC:Constants.NORMAL_CALC);
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
