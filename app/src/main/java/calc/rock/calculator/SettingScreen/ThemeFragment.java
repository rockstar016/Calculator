package calc.rock.calculator.SettingScreen;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import calc.rock.calculator.R;
import calc.rock.calculator.Utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThemeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThemeFragment extends Fragment {


    TextView    txtColor;
    TextView    BackgroundColor;
    TextView    primaryColor;
    TextView    resetColor;
    SharedPreferences prefs;

    public ThemeFragment() {
        // Required empty public constructor
    }

    public static ThemeFragment newInstance(String param1, String param2) {
        ThemeFragment fragment = new ThemeFragment();
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
        View rootView = inflater.inflate(R.layout.item_theme, container, false);
        int initialColor;

        txtColor        = (TextView)rootView.findViewById(R.id.txtColor);
        BackgroundColor = (TextView)rootView.findViewById(R.id.backgroundColor);
        primaryColor    = (TextView)rootView.findViewById(R.id.primaryColor);
        resetColor      = (TextView)rootView.findViewById(R.id.resetColor);

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());


        initialColor = prefs.getInt(Constants.TEXT_COLOR, Constants.RES_TEXTCOLOR);
        setTextColor(initialColor);
        initialColor = prefs.getInt(Constants.PRIMARY_COLOR, Constants.RES_PRIMARYCOLOR);
        setprimaryColor(initialColor);
        initialColor = prefs.getInt(Constants.BACKGROUND_COLOR, Constants.RES_BACKGROUNDCOLOR);
        setBackgroundColor(initialColor);


        txtColor.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent  intent = new Intent();
                intent.setClassName(Constants.PACKAGENAME,
                        Constants.PACKAGENAME+".ColorPickerActivity");
                intent.putExtra("Color", Constants.TEXT_COLOR);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });
        primaryColor.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent  intent = new Intent();
                intent.setClassName(Constants.PACKAGENAME,
                        Constants.PACKAGENAME+".ColorPickerActivity");
                intent.putExtra("Color", Constants.PRIMARY_COLOR);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });
        BackgroundColor.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent  intent = new Intent();
                intent.setClassName(Constants.PACKAGENAME,
                        Constants.PACKAGENAME+".ColorPickerActivity");
                intent.putExtra("Color", Constants.BACKGROUND_COLOR);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });

        resetColor.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                txtColor.setBackgroundColor(Constants.RES_TEXTCOLOR);
                BackgroundColor.setBackgroundColor(Constants.RES_BACKGROUNDCOLOR);
                primaryColor.setBackgroundColor(Constants.RES_PRIMARYCOLOR);

                SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
                edit.putInt(Constants.TEXT_COLOR, Constants.RES_TEXTCOLOR);
                edit.putInt(Constants.BACKGROUND_COLOR, Constants.RES_BACKGROUNDCOLOR);
                edit.putInt(Constants.PRIMARY_COLOR, Constants.RES_PRIMARYCOLOR);
                edit.apply();
            }
        });





        return rootView;
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
