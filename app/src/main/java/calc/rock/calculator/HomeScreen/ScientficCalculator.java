package calc.rock.calculator.HomeScreen;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import calc.rock.calculator.BaseFragment;
import calc.rock.calculator.Customs.CustomCButton;
import calc.rock.calculator.Customs.CustomImageButton;
import calc.rock.calculator.Customs.CustomNumberButton;
import calc.rock.calculator.Customs.CustomOperateButton2;
import calc.rock.calculator.Customs.CustomOperatorButton1;
import calc.rock.calculator.R;
import calc.rock.calculator.Utils.Constants;
import calc.rock.calculator.Utils.ThemeManagement;

public class ScientficCalculator extends BaseFragment {

    CustomOperatorButton1 bt_m_c, bt_m_plus, bt_m_minus, bt_m_read, bt_oper_multiple, bt_oper_divide, bt_oper_minus, bt_oper_plus, bt_oper_equals;
    CustomOperateButton2 bt_sin, bt_cos, bt_tan, bt_sin_inverse, bt_cos_inverse, bt_tan_inverse, bt_sinh, bt_cosh, bt_tanh, bt_sinh_inverse, bt_cosh_inverse, bt_tanh_inverse,
                        bt_pi, bt_e, bt_x_pow_n, bt_x_pow_2, bt_x_pow_3, bt_x_inverse, bt_log_10, bt_ln, bt_log_2, bt_x_factorial, bt_x_sqrt, bt_x_triple_sqrt;
    CustomImageButton bt_setting, bt_backspace;
    CustomCButton bt_c;

    CustomNumberButton bt_plus_minus, bt_0, bt_point, bt_bracket_close, bt_bracket_open, bt_percentage, bt_1, bt_2, bt_3, bt_4, bt_5, bt_6, bt_7, bt_8, bt_9;


    CalculatorInterface parent, parentFragment;

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
        parentFragment = (CalculatorInterface)getParentFragment();
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
        bt_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickNumberButton(bt_0.getText().toString());
            }
        });
        bt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickNumberButton(bt_1.getText().toString());
            }
        });
        bt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickNumberButton(bt_2.getText().toString());
            }
        });
        bt_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickNumberButton(bt_3.getText().toString());
            }
        });
        bt_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickNumberButton(bt_4.getText().toString());
            }
        });
        bt_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickNumberButton(bt_5.getText().toString());
            }
        });
        bt_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickNumberButton(bt_6.getText().toString());
            }
        });
        bt_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickNumberButton(bt_7.getText().toString());
            }
        });
        bt_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickNumberButton(bt_8.getText().toString());
            }
        });
        bt_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickNumberButton(bt_9.getText().toString());
            }
        });
        bt_m_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickMemoryKeyButton(Constants.MEM_PLUS);
            }
        });
        bt_m_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickMemoryKeyButton(Constants.MEM_MINUS);
            }
        });
        bt_m_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickMemoryKeyButton(Constants.MEM_READ);
            }
        });
        bt_m_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickMemoryKeyButton(Constants.MEM_CLEAR);
            }
        });
        bt_backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickOperationButton(Constants.BACKSPACE);
            }
        });

        bt_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickOperationButton(Constants.CLEAR);
            }
        });
        bt_oper_equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickOperationButton(Constants.EQUAL);
            }
        });
        bt_plus_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickOperationButton(Constants.SIGN);
            }
        });
        bt_oper_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickOperationButton(Constants.PLUS);
            }
        });
        bt_oper_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickOperationButton(Constants.MINUS);
            }
        });
        bt_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickOperationButton(Constants.DOT_POINT);
            }
        });
        bt_bracket_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickOperationButton(Constants.BRACKET_OPEN);
            }
        });
        bt_bracket_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickOperationButton(Constants.BRACKET_CLOSE);
            }
        });
        bt_oper_multiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickOperationButton(Constants.MUL);
            }
        });
        bt_oper_divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickOperationButton(Constants.DIVIDE);
            }
        });


        bt_sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickSpecialButton(Constants.SIN);
            }
        });
        bt_cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickSpecialButton(Constants.COS);
            }
        });
        bt_tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickSpecialButton(Constants.TAN);
            }
        });
        bt_sin_inverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickSpecialButton(Constants.SIN_INVERSE);
            }
        });
        bt_cos_inverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickSpecialButton(Constants.COS_INVERSE);
            }
        });
        bt_tan_inverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickSpecialButton(Constants.TAN_INVERSE);
            }
        });
        bt_sinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickSpecialButton(Constants.SINH);
            }
        });
        bt_cosh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickSpecialButton(Constants.COSH);
            }
        });
        bt_tanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickSpecialButton(Constants.TANH);
            }
        });
        bt_sinh_inverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickSpecialButton(Constants.SINH_INVERSE);
            }
        });
        bt_cosh_inverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickSpecialButton(Constants.COSH_INVERSE);
            }
        });
        bt_tanh_inverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickSpecialButton(Constants.TANH_INVERSE);
            }
        });
        bt_pi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickSpecialButton(Constants.PI);
            }
        });
        bt_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickSpecialButton(Constants.POWE);
            }
        });
        bt_x_pow_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickSpecialButton(Constants.POW_N);
            }
        });
        bt_x_pow_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickSpecialButton(Constants.POW_2);
            }
        });
        bt_x_pow_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickSpecialButton(Constants.POW_3);
            }
        });
        bt_x_inverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickSpecialButton(Constants.X_INVERSE);
            }
        });
        bt_log_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickSpecialButton(Constants.LOG_10);
            }
        });
        bt_ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickSpecialButton(Constants.ln);
            }
        });
        bt_log_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickSpecialButton(Constants.LOG_2);
            }
        });
        bt_x_factorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickSpecialButton(Constants.X_FACTORIAL);
            }
        });
        bt_x_sqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickSpecialButton(Constants.SQRT);
            }
        });
        bt_x_triple_sqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickSpecialButton(Constants.X_TRIPLE_SQRT);
            }
        });


        bt_oper_equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickEqualButton();
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
        int background_style = ThemeManagement.getTheme(getContext(), Constants.BACKGROUND_COLOR,Constants.RES_BACKGROUNDCOLOR);
        int text_style = ThemeManagement.getTheme(getContext(), Constants.TEXT_COLOR,Constants.RES_TEXTCOLOR);
        int primary_color = ThemeManagement.getTheme(getContext(), Constants.PRIMARY_COLOR,Constants.RES_PRIMARYCOLOR);
        bt_m_c.setTheme(text_style,background_style);
        bt_m_plus.setTheme(text_style,background_style);
        bt_m_minus.setTheme(text_style,background_style);
        bt_m_read.setTheme(text_style,background_style);
        bt_oper_multiple.setTheme(text_style,background_style);
        bt_oper_divide.setTheme(text_style,background_style);
        bt_oper_minus.setTheme(text_style,background_style);
        bt_oper_plus.setTheme(text_style,background_style);
        bt_oper_equals.setTheme(text_style,background_style);

        //CustomOperatorButton2
        bt_sin.setTheme(background_style,primary_color);
        bt_cos.setTheme(background_style,primary_color);
        bt_tan.setTheme(background_style,primary_color);
        bt_sin_inverse.setTheme(background_style,primary_color);
        bt_cos_inverse.setTheme(background_style,primary_color);
        bt_tan_inverse.setTheme(background_style,primary_color);
        bt_sinh.setTheme(background_style,primary_color);
        bt_cosh.setTheme(background_style,primary_color);
        bt_tanh.setTheme(background_style,primary_color);
        bt_sinh_inverse.setTheme(background_style,primary_color);
        bt_cosh_inverse.setTheme(background_style,primary_color);
        bt_tanh_inverse.setTheme(background_style,primary_color);
        bt_pi.setTheme(background_style,primary_color);
        bt_e.setTheme(background_style,primary_color);
        bt_x_pow_n.setTheme(background_style,primary_color);
        bt_x_pow_2.setTheme(background_style,primary_color);
        bt_x_pow_3.setTheme(background_style,primary_color);
        bt_x_inverse.setTheme(background_style,primary_color);
        bt_log_10.setTheme(background_style,primary_color);
        bt_ln.setTheme(background_style,primary_color);
        bt_log_2.setTheme(background_style,primary_color);
        bt_x_factorial.setTheme(background_style,primary_color);
        bt_x_sqrt.setTheme(background_style,primary_color);
        bt_x_triple_sqrt.setTheme(background_style,primary_color);

        bt_setting.setTheme(primary_color,background_style);
        bt_backspace.setTheme(primary_color,background_style);
        bt_c.setTheme(primary_color,background_style);


        bt_plus_minus.setTheme(text_style,background_style);
        bt_0.setTheme(text_style,background_style);
        bt_point.setTheme(text_style,background_style);
        bt_bracket_close.setTheme(text_style,background_style);
        bt_bracket_open.setTheme(text_style,background_style);
        bt_percentage.setTheme(text_style,background_style);
        bt_1.setTheme(text_style,background_style);
        bt_2.setTheme(text_style,background_style);
        bt_3.setTheme(text_style,background_style);
        bt_4.setTheme(text_style,background_style);
        bt_5.setTheme(text_style,background_style);
        bt_6.setTheme(text_style,background_style);
        bt_7.setTheme(text_style,background_style);
        bt_8.setTheme(text_style,background_style);
        bt_9.setTheme(text_style,background_style);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof CalculatorInterface) {
            parent = (CalculatorInterface)getActivity();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        parent = null;
    }
}
