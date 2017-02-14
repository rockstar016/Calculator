package calc.rock.calculator.HomeScreen;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import calc.rock.calculator.BaseFragment;
import calc.rock.calculator.Customs.CustomCButton;
import calc.rock.calculator.Customs.CustomImageButton;
import calc.rock.calculator.Customs.CustomNumberButton;
import calc.rock.calculator.Customs.CustomOperateButton2;
import calc.rock.calculator.Customs.CustomOperatorButton1;
import calc.rock.calculator.HomeActivity;
import calc.rock.calculator.R;
import calc.rock.calculator.Utils.ThemeManagement;

public class ScientficCalculator extends BaseFragment {

    CustomOperatorButton1 bt_m_c, bt_m_plus, bt_m_minus, bt_m_read, bt_oper_multiple, bt_oper_divide, bt_oper_minus, bt_oper_plus, bt_oper_equals;
    CustomOperateButton2 bt_sin, bt_cos, bt_tan, bt_sin_inverse, bt_cos_inverse, bt_tan_inverse, bt_sinh, bt_cosh, bt_tanh, bt_sinh_inverse, bt_cosh_inverse, bt_tanh_inverse,
                        bt_pi, bt_e, bt_x_pow_n, bt_x_pow_2, bt_x_pow_3, bt_x_inverse, bt_log_10, bt_ln, bt_log_2, bt_x_factorial, bt_x_sqrt, bt_x_triple_sqrt;
    CustomImageButton bt_setting, bt_backspace;
    CustomCButton bt_c;

    CustomNumberButton bt_plus_minus, bt_0, bt_point, bt_bracket_close, bt_bracket_open, bt_percentage, bt_1, bt_2, bt_3, bt_4, bt_5, bt_6, bt_7, bt_8, bt_9;


    HomeActivityInterface parent;
    public ScientficCalculator() {
        // Required empty public constructor
    }

    public static ScientficCalculator newInstance(String param1, String param2) {
        ScientficCalculator fragment = new ScientficCalculator();
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
        View rootView = inflater.inflate(R.layout.fragment_scientfic_calculator, container, false);

        initControls(rootView);
        initStyles();
        initEventListener();
        return rootView;
    }

    private void initEventListener(){
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
        //CustomOperatorButton1
        bt_m_c = (CustomOperatorButton1)rootView.findViewById(R.id.bt_m_c);
        bt_m_plus = (CustomOperatorButton1)rootView.findViewById(R.id.bt_m_plus);
        bt_m_minus = (CustomOperatorButton1)rootView.findViewById(R.id.bt_m_minus);
        bt_m_read = (CustomOperatorButton1)rootView.findViewById(R.id.bt_m_read);
        bt_oper_multiple = (CustomOperatorButton1)rootView.findViewById(R.id.bt_multiple);
        bt_oper_divide = (CustomOperatorButton1)rootView.findViewById(R.id.bt_divide);
        bt_oper_minus = (CustomOperatorButton1)rootView.findViewById(R.id.bt_minus);
        bt_oper_plus = (CustomOperatorButton1)rootView.findViewById(R.id.bt_plus);
        bt_oper_equals = (CustomOperatorButton1)rootView.findViewById(R.id.bt_equal);

        //CustomOperatorButton2
        bt_sin = (CustomOperateButton2)rootView.findViewById(R.id.bt_sin);
        bt_cos = (CustomOperateButton2)rootView.findViewById(R.id.bt_cos);
        bt_tan = (CustomOperateButton2)rootView.findViewById(R.id.bt_tan);
        bt_sin_inverse = (CustomOperateButton2)rootView.findViewById(R.id.bt_arc_sin);
        bt_cos_inverse = (CustomOperateButton2)rootView.findViewById(R.id.bt_arc_cos);
        bt_tan_inverse = (CustomOperateButton2)rootView.findViewById(R.id.bt_arc_tan);
        bt_sinh = (CustomOperateButton2)rootView.findViewById(R.id.bt_sinh);
        bt_cosh = (CustomOperateButton2)rootView.findViewById(R.id.bt_cosh);
        bt_tanh = (CustomOperateButton2)rootView.findViewById(R.id.bt_tanh);
        bt_sinh_inverse = (CustomOperateButton2)rootView.findViewById(R.id.bt_arc_sinh);
        bt_cosh_inverse = (CustomOperateButton2)rootView.findViewById(R.id.bt_arc_cosh);
        bt_tanh_inverse = (CustomOperateButton2)rootView.findViewById(R.id.bt_arc_tanh);
        bt_pi = (CustomOperateButton2)rootView.findViewById(R.id.bt_pi);
        bt_e = (CustomOperateButton2)rootView.findViewById(R.id.bt_e_pow_n);
        bt_x_pow_n = (CustomOperateButton2)rootView.findViewById(R.id.bt_x_pow_n);
        bt_x_pow_2 = (CustomOperateButton2)rootView.findViewById(R.id.bt_x_pow_2);
        bt_x_pow_3 = (CustomOperateButton2)rootView.findViewById(R.id.bt_x_pow_3);
        bt_x_inverse = (CustomOperateButton2)rootView.findViewById(R.id.bt_inverse_x);
        bt_log_10 = (CustomOperateButton2)rootView.findViewById(R.id.bt_log);
        bt_ln = (CustomOperateButton2)rootView.findViewById(R.id.bt_ln);
        bt_log_2 = (CustomOperateButton2)rootView.findViewById(R.id.bt_log2);
        bt_x_factorial = (CustomOperateButton2)rootView.findViewById(R.id.bt_factorial);
        bt_x_sqrt = (CustomOperateButton2)rootView.findViewById(R.id.bt_sqrt);
        bt_x_triple_sqrt = (CustomOperateButton2)rootView.findViewById(R.id.bt_cubic_sqrt);

        bt_setting = (CustomImageButton)rootView.findViewById(R.id.bt_setting);
        bt_backspace = (CustomImageButton)rootView.findViewById(R.id.bt_backspace);

        bt_c = (CustomCButton)rootView.findViewById(R.id.bt_c);


        bt_plus_minus = (CustomNumberButton)rootView.findViewById(R.id.bt_plus_minus);
        bt_0 = (CustomNumberButton)rootView.findViewById(R.id.bt_0);
        bt_point = (CustomNumberButton)rootView.findViewById(R.id.bt_point);
        bt_bracket_close  = (CustomNumberButton)rootView.findViewById(R.id.bt_bracket_close);
        bt_bracket_open = (CustomNumberButton)rootView.findViewById(R.id.bt_bracket_open);
        bt_percentage = (CustomNumberButton)rootView.findViewById(R.id.bt_percentage);
        bt_1  = (CustomNumberButton)rootView.findViewById(R.id.bt_1);
        bt_2 = (CustomNumberButton)rootView.findViewById(R.id.bt_2);
        bt_3 = (CustomNumberButton)rootView.findViewById(R.id.bt_3);
        bt_4 = (CustomNumberButton)rootView.findViewById(R.id.bt_4);
        bt_5 = (CustomNumberButton)rootView.findViewById(R.id.bt_5);
        bt_6 = (CustomNumberButton)rootView.findViewById(R.id.bt_6);
        bt_7 = (CustomNumberButton)rootView.findViewById(R.id.bt_7);
        bt_8 = (CustomNumberButton)rootView.findViewById(R.id.bt_8);
        bt_9 = (CustomNumberButton)rootView.findViewById(R.id.bt_9);


    }

    @Override
    protected void initStyles() {
        super.initStyles();
        int current_style = ThemeManagement.getTheme(getContext());
        bt_m_c.setTheme(current_style);
        bt_m_plus.setTheme(current_style);
        bt_m_minus.setTheme(current_style);
        bt_m_read.setTheme(current_style);
        bt_oper_multiple.setTheme(current_style);
        bt_oper_divide.setTheme(current_style);
        bt_oper_minus.setTheme(current_style);
        bt_oper_plus.setTheme(current_style);
        bt_oper_equals.setTheme(current_style);

        //CustomOperatorButton2
        bt_sin.setTheme(current_style);
        bt_cos.setTheme(current_style);
        bt_tan.setTheme(current_style);
        bt_sin_inverse.setTheme(current_style);
        bt_cos_inverse.setTheme(current_style);
        bt_tan_inverse.setTheme(current_style);
        bt_sinh.setTheme(current_style);
        bt_cosh.setTheme(current_style);
        bt_tanh.setTheme(current_style);
        bt_sinh_inverse.setTheme(current_style);
        bt_cosh_inverse.setTheme(current_style);
        bt_tanh_inverse.setTheme(current_style);
        bt_pi.setTheme(current_style);
        bt_e.setTheme(current_style);
        bt_x_pow_n.setTheme(current_style);
        bt_x_pow_2.setTheme(current_style);
        bt_x_pow_3.setTheme(current_style);
        bt_x_inverse.setTheme(current_style);
        bt_log_10.setTheme(current_style);
        bt_ln.setTheme(current_style);
        bt_log_2.setTheme(current_style);
        bt_x_factorial.setTheme(current_style);
        bt_x_sqrt.setTheme(current_style);
        bt_x_triple_sqrt.setTheme(current_style);

        bt_setting.setTheme(current_style);
        bt_backspace.setTheme(current_style);

        bt_c.setTheme(current_style);


        bt_plus_minus.setTheme(current_style);
        bt_0.setTheme(current_style);
        bt_point.setTheme(current_style);
        bt_bracket_close.setTheme(current_style);
        bt_bracket_open.setTheme(current_style);
        bt_percentage.setTheme(current_style);
        bt_1.setTheme(current_style);
        bt_2.setTheme(current_style);
        bt_3.setTheme(current_style);
        bt_4.setTheme(current_style);
        bt_5.setTheme(current_style);
        bt_6.setTheme(current_style);
        bt_7.setTheme(current_style);
        bt_8.setTheme(current_style);
        bt_9.setTheme(current_style);
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
