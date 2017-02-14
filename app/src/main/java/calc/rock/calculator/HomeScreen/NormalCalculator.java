package calc.rock.calculator.HomeScreen;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import calc.rock.calculator.BaseFragment;
import calc.rock.calculator.Customs.CustomCButton;
import calc.rock.calculator.Customs.CustomImageButton;
import calc.rock.calculator.Customs.CustomNumberButton;
import calc.rock.calculator.Customs.CustomOperatorButton1;
import calc.rock.calculator.R;
import calc.rock.calculator.Utils.ThemeManagement;

public class NormalCalculator extends BaseFragment {
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    CustomCButton bt_clear;
    CustomOperatorButton1 bt_m_r, bt_m_c, bt_m_plus, bt_m_minus, bt_divide, bt_multi, bt_minus,bt_plus, bt_equals;


    CustomNumberButton bt_percentage, bt_brack_open, bt_brack_close,
            bt_num_7, bt_num_8, bt_num_9, bt_num_4, bt_num_5, bt_num_6, bt_num_1, bt_num_2, bt_num_3, bt_sign, bt_num_0, bt_point;
    CustomImageButton bt_setting,bt_backspace;

    HomeActivityInterface parent;
    public NormalCalculator() {
        // Required empty public constructor
    }

    public static NormalCalculator newInstance(String param1, String param2) {
        NormalCalculator fragment = new NormalCalculator();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_normal_calculator, container, false);
        initControls(rootView);
        initStyles();
        initEventListener();
        return rootView;
    }

    public void initEventListener(){
        bt_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parent.onClickSettingButton();
            }
        });
    }
    @Override
    protected void initControls(View rootView) {
        super.initControls(rootView);
        bt_m_r = (CustomOperatorButton1)rootView.findViewById(R.id.bt_m_read);
        bt_m_c = (CustomOperatorButton1)rootView.findViewById(R.id.bt_m_clear);
        bt_m_plus = (CustomOperatorButton1)rootView.findViewById(R.id.bt_m_plus);
        bt_m_minus = (CustomOperatorButton1)rootView.findViewById(R.id.bt_m_minus);
        bt_setting = (CustomImageButton) rootView.findViewById(R.id.bt_setting);
        bt_backspace = (CustomImageButton) rootView.findViewById(R.id.bt_backspace);
        bt_clear = (CustomCButton) rootView.findViewById(R.id.bt_clear);
        bt_divide = (CustomOperatorButton1)rootView.findViewById(R.id.bt_divide);
        bt_percentage = (CustomNumberButton)rootView.findViewById(R.id.bt_percentage);
        bt_brack_open = (CustomNumberButton)rootView.findViewById(R.id.bt_brack_open);
        bt_brack_close = (CustomNumberButton)rootView.findViewById(R.id.bt_brack_close);
        bt_multi = (CustomOperatorButton1)rootView.findViewById(R.id.bt_multiple);
        bt_num_7 = (CustomNumberButton)rootView.findViewById(R.id.bt_num_7);
        bt_num_8 = (CustomNumberButton)rootView.findViewById(R.id.bt_num_8);
        bt_num_9 = (CustomNumberButton)rootView.findViewById(R.id.bt_num_9);
        bt_num_4 = (CustomNumberButton)rootView.findViewById(R.id.bt_num_4);
        bt_num_5 = (CustomNumberButton)rootView.findViewById(R.id.bt_num_5);
        bt_num_6 = (CustomNumberButton)rootView.findViewById(R.id.bt_num_6);
        bt_num_0 = (CustomNumberButton)rootView.findViewById(R.id.bt_num_0);
        bt_num_1 = (CustomNumberButton)rootView.findViewById(R.id.bt_num_1);
        bt_num_2 = (CustomNumberButton)rootView.findViewById(R.id.bt_num_2);
        bt_num_3 = (CustomNumberButton)rootView.findViewById(R.id.bt_num_3);
        bt_equals = (CustomOperatorButton1)rootView.findViewById(R.id.bt_equal);
        bt_sign = (CustomNumberButton)rootView.findViewById(R.id.bt_plus_minus);
        bt_plus = (CustomOperatorButton1)rootView.findViewById(R.id.bt_num_plus);
        bt_minus = (CustomOperatorButton1)rootView.findViewById(R.id.bt_num_minus);
        bt_point = (CustomNumberButton)rootView.findViewById(R.id.bt_point);
    }

    @Override
    protected void initStyles() {
        super.initStyles();
        int current_style = ThemeManagement.getTheme(getContext());
        bt_m_r.setTheme(current_style);
        bt_m_c.setTheme(current_style);
        bt_m_plus.setTheme(current_style);
        bt_m_minus.setTheme(current_style);
        bt_setting.setTheme(current_style);
        bt_backspace.setTheme(current_style);
        bt_clear.setTheme(current_style);
        bt_divide.setTheme(current_style);
        bt_percentage.setTheme(current_style);
        bt_brack_open.setTheme(current_style);
        bt_brack_close.setTheme(current_style);
        bt_multi.setTheme(current_style);
        bt_num_7.setTheme(current_style);
        bt_num_8.setTheme(current_style);
        bt_num_9.setTheme(current_style);
        bt_num_4.setTheme(current_style);
        bt_num_5.setTheme(current_style);
        bt_num_6.setTheme(current_style);
        bt_num_0.setTheme(current_style);
        bt_num_1.setTheme(current_style);
        bt_num_2.setTheme(current_style);
        bt_num_3.setTheme(current_style);
        bt_equals.setTheme(current_style);
        bt_sign.setTheme(current_style);
        bt_plus.setTheme(current_style);
        bt_minus.setTheme(current_style);
        bt_point.setTheme(current_style);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof HomeActivityInterface) {
            parent = (HomeActivityInterface)getActivity();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        parent = null;
    }
}
