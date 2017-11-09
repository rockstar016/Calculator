package calc.rock.calculator.HomeScreen;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import calc.rock.calculator.BaseFragment;
import calc.rock.calculator.Customs.CustomCButton;
import calc.rock.calculator.Customs.CustomImageButton;
import calc.rock.calculator.Customs.CustomNumberButton;
import calc.rock.calculator.Customs.CustomOperatorButton1;
import calc.rock.calculator.R;
import calc.rock.calculator.SettingActivity;
import calc.rock.calculator.Utils.Constants;
import calc.rock.calculator.Utils.ThemeManagement;

public class NormalCalculator extends BaseFragment {
    CustomCButton bt_clear;//
    CustomOperatorButton1 bt_m_r, bt_m_c, bt_m_plus, bt_m_minus, bt_divide, bt_multi, bt_minus,bt_plus, bt_equals;
    CustomNumberButton bt_percentage, bt_brack_open, bt_brack_close,
            bt_num_7, bt_num_8, bt_num_9, bt_num_4, bt_num_5, bt_num_6, bt_num_1, bt_num_2, bt_num_3, bt_sign, bt_num_0, bt_point;
    CustomImageButton bt_setting,bt_backspace;
    LinearLayout    memoryKey;
    int             vibration;
    public NormalCalculator() {

    }

    public static NormalCalculator newInstance() {
        NormalCalculator fragment = new NormalCalculator();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
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
               Intent i = new Intent(getActivity(), SettingActivity.class);
                startActivity(i);
            }
        });

        bt_num_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickNumberButton(bt_num_0.getText().toString());
            }
        });
        bt_num_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickNumberButton(bt_num_1.getText().toString());
            }
        });
        bt_num_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickNumberButton(bt_num_2.getText().toString());
            }
        });
        bt_num_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickNumberButton(bt_num_3.getText().toString());
            }
        });
        bt_num_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickNumberButton(bt_num_4.getText().toString());
            }
        });
        bt_num_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickNumberButton(bt_num_5.getText().toString());
            }
        });
        bt_num_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickNumberButton(bt_num_6.getText().toString());
            }
        });
        bt_num_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickNumberButton(bt_num_7.getText().toString());
            }
        });
        bt_num_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickNumberButton(bt_num_8.getText().toString());
            }
        });
        bt_num_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickNumberButton(bt_num_9.getText().toString());
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
        bt_m_r.setOnClickListener(new View.OnClickListener() {
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
        bt_percentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickOperationButton(Constants.PERSENTAGE);
            }
        });
        bt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickOperationButton(Constants.CLEAR);
            }
        });
        bt_equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickOperationButton(Constants.EQUAL);
            }
        });
        bt_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickOperationButton(Constants.SIGN);
            }
        });
        bt_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickOperationButton(Constants.PLUS);
            }
        });
        bt_minus.setOnClickListener(new View.OnClickListener() {
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
        bt_brack_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickOperationButton(Constants.BRACKET_OPEN);
            }
        });
        bt_brack_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickOperationButton(Constants.BRACKET_CLOSE);
            }
        });
        bt_multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickOperationButton(Constants.MUL);
            }
        });
        bt_divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickOperationButton(Constants.DIVIDE);
            }
        });
        bt_percentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickOperationButton(Constants.PERSENTAGE);
            }
        });

        bt_equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.onClickEqualButton();
            }
        });
    }
    @Override
    protected void initControls(View rootView) {
        super.initControls(rootView);
        bt_m_r = (CustomOperatorButton1)rootView.findViewById(R.id.bt_m_read);
        bt_m_r.setTextSize(24);
        bt_m_c = (CustomOperatorButton1)rootView.findViewById(R.id.bt_m_clear);
        bt_m_c.setTextSize(24);
        bt_m_plus = (CustomOperatorButton1)rootView.findViewById(R.id.bt_m_plus);
        bt_m_plus.setTextSize(24);
        bt_m_minus = (CustomOperatorButton1)rootView.findViewById(R.id.bt_m_minus);
        bt_m_minus.setTextSize(24);

        bt_setting = (CustomImageButton) rootView.findViewById(R.id.bt_setting);
        bt_backspace = (CustomImageButton) rootView.findViewById(R.id.bt_backspace);
        bt_clear = (CustomCButton) rootView.findViewById(R.id.bt_clear);
        bt_clear.setTextSize(24);
        bt_divide = (CustomOperatorButton1)rootView.findViewById(R.id.bt_divide);
        bt_divide.setTextSize(24);
        bt_percentage = (CustomNumberButton)rootView.findViewById(R.id.bt_percentage);
        bt_percentage.setTextSize(24);
        bt_brack_open = (CustomNumberButton)rootView.findViewById(R.id.bt_brack_open);
        bt_brack_open.setTextSize(24);
        bt_brack_close = (CustomNumberButton)rootView.findViewById(R.id.bt_brack_close);
        bt_brack_close.setTextSize(24);
        bt_multi = (CustomOperatorButton1)rootView.findViewById(R.id.bt_multiple);
        bt_multi.setTextSize(24);

        bt_num_7 = (CustomNumberButton)rootView.findViewById(R.id.bt_num_7);
        bt_num_7.setTextSize(24);
        bt_num_8 = (CustomNumberButton)rootView.findViewById(R.id.bt_num_8);
        bt_num_8.setTextSize(24);
        bt_num_9 = (CustomNumberButton)rootView.findViewById(R.id.bt_num_9);
        bt_num_9.setTextSize(24);
        bt_num_4 = (CustomNumberButton)rootView.findViewById(R.id.bt_num_4);
        bt_num_4.setTextSize(24);
        bt_num_5 = (CustomNumberButton)rootView.findViewById(R.id.bt_num_5);
        bt_num_5.setTextSize(24);
        bt_num_6 = (CustomNumberButton)rootView.findViewById(R.id.bt_num_6);
        bt_num_6.setTextSize(24);
        bt_num_0 = (CustomNumberButton)rootView.findViewById(R.id.bt_num_0);
        bt_num_0.setTextSize(24);
        bt_num_1 = (CustomNumberButton)rootView.findViewById(R.id.bt_num_1);
        bt_num_1.setTextSize(24);
        bt_num_2 = (CustomNumberButton)rootView.findViewById(R.id.bt_num_2);
        bt_num_2.setTextSize(24);
        bt_num_3 = (CustomNumberButton)rootView.findViewById(R.id.bt_num_3);
        bt_num_3.setTextSize(24);
        bt_equals = (CustomOperatorButton1)rootView.findViewById(R.id.bt_equal);
        bt_equals.setTextSize(24);
        bt_sign = (CustomNumberButton)rootView.findViewById(R.id.bt_plus_minus);
        bt_sign.setTextSize(24);
        bt_plus = (CustomOperatorButton1)rootView.findViewById(R.id.bt_num_plus);
        bt_plus.setTextSize(24);
        bt_minus = (CustomOperatorButton1)rootView.findViewById(R.id.bt_num_minus);
        bt_minus.setTextSize(24);
        bt_point = (CustomNumberButton)rootView.findViewById(R.id.bt_point);
        bt_point.setTextSize(24);
        memoryKey = (LinearLayout)rootView.findViewById(R.id.memorykey);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        int initialShow = prefs.getInt(Constants.MEMORY_KEYS, 0);
        if(initialShow == 1)
            memoryKey.setVisibility(View.VISIBLE);
        else
            memoryKey.setVisibility(View.GONE);
        vibration = prefs.getInt(Constants.VIBRATION,0);
        if(vibration == 1)
        {
            Vibrator vi = (Vibrator)getContext().getSystemService(Context.VIBRATOR_SERVICE);
            vi.vibrate(120);
        }
    }

    @Override
    protected void initStyles() {
        super.initStyles();
        int background_style = ThemeManagement.getTheme(getContext(), Constants.BACKGROUND_COLOR,Constants.RES_BACKGROUNDCOLOR);
        int text_style  = ThemeManagement.getTheme(getContext(), Constants.TEXT_COLOR,Constants.RES_TEXTCOLOR);
        int primary_style    = ThemeManagement.getTheme(getContext(), Constants.PRIMARY_COLOR,Constants.RES_PRIMARYCOLOR);
        bt_m_r.setTheme(text_style,background_style);
        bt_m_c.setTheme(text_style,background_style);
        bt_m_plus.setTheme(text_style,background_style);
        bt_m_minus.setTheme(text_style,background_style);
        bt_divide.setTheme(text_style,background_style);
        bt_multi.setTheme(text_style,background_style);
        bt_equals.setTheme(text_style,background_style);
        bt_plus.setTheme(text_style,background_style);
        bt_minus.setTheme(text_style,background_style);
        bt_setting.setTheme(primary_style,background_style);
        bt_backspace.setTheme(primary_style,background_style);
        bt_clear.setTheme(text_style,background_style);
        bt_percentage.setTheme(text_style,background_style);
        bt_brack_open.setTheme(text_style,background_style);
        bt_brack_close.setTheme(text_style,background_style);
        bt_num_7.setTheme(text_style,background_style);
        bt_num_8.setTheme(text_style,background_style);
        bt_num_9.setTheme(text_style,background_style);
        bt_num_4.setTheme(text_style,background_style);
        bt_num_5.setTheme(text_style,background_style);
        bt_num_6.setTheme(text_style,background_style);
        bt_num_0.setTheme(text_style,background_style);
        bt_num_1.setTheme(text_style,background_style);
        bt_num_2.setTheme(text_style,background_style);
        bt_num_3.setTheme(text_style,background_style);
        bt_sign.setTheme(text_style,background_style);
        bt_point.setTheme(text_style,background_style);
    }
}
