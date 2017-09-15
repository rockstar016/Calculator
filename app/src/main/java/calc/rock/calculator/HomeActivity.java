package calc.rock.calculator;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

import calc.rock.calculator.Customs.CustomMemoryTextView;
import calc.rock.calculator.Customs.CustomNumberTextView;
import calc.rock.calculator.HomeScreen.HomeActivityInterface;
import calc.rock.calculator.HomeScreen.NormalCalculator;
import calc.rock.calculator.HomeScreen.ScientficCalculator;
import calc.rock.calculator.Utils.Calculator;
import calc.rock.calculator.Utils.Constants;
import calc.rock.calculator.Utils.ThemeManagement;

import static calc.rock.calculator.R.id.txt_current_value;
import static calc.rock.calculator.R.id.txt_input_value;

public class HomeActivity extends AppCompatActivity implements HomeActivityInterface{
    CustomMemoryTextView    txt_mr_view;
    CustomNumberTextView    txt_current_view, txt_input_view;
    SharedPreferences       prefs;
    String                  commaString="";
    String                  mem_Value;
    Calculator              calc;
    boolean                 numberInput;
    boolean                 equalclicked;
    Vibrator                vi;
    boolean                 specialClicked;
    boolean                 percentageClicked;
    boolean                 specialPowNClicked;
    String                  specialpowNumber;
    int                     vibration;
    int                     seperator;
    BigDecimal              memValue;
    private InterstitialAd interstitial = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        Runtime.getRuntime().maxMemory();




        initControls();
        loadStyles();
        loadCurrentCalculator();
        init();
    }
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        System.runFinalizersOnExit(true);
        android.os.Process.killProcess(android.os.Process
                .myPid());
        System.exit(0);

    }




    public void loadCurrentCalculator(){
        if(ThemeManagement.getCalculator(this,Constants.CURRENT_CALCULATOR) == Constants.NORMAL_CALC){
            attachFragment(NormalCalculator.newInstance("",""));
        }
        else{
            attachFragment(ScientficCalculator.newInstance("",""));
        }
    }



    private void initControls(){
        txt_mr_view = (CustomMemoryTextView) findViewById(R.id.txt_memory_view);
        txt_current_view = (CustomNumberTextView) findViewById(txt_current_value);
        txt_input_view = (CustomNumberTextView) findViewById(R.id.txt_input_value);
        numberInput = false;
        calc  = new Calculator();
        mem_Value = "";
        equalclicked  =false;
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        vibration = prefs.getInt(Constants.VIBRATION,0);
        vi = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        seperator = prefs.getInt(Constants.THOUSAND_SEPERATOR, 0);
        memValue = BigDecimal.ZERO;

    }

    private void loadStyles(){
        int currentTheme = ThemeManagement.getTheme(this,Constants.BACKGROUND_COLOR,Constants.RES_BACKGROUNDCOLOR);
        txt_mr_view.setTheme(currentTheme);
        txt_input_view.setTheme(currentTheme);
        txt_current_view.setTheme(currentTheme);

    }

    private void attachFragment(Fragment f){
        if (f != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_container, f,null).addToBackStack(null).commit();
        }
    }

    @Override
    public void onClickSettingButton() {
        if(vibration == 1){
            vi.vibrate(120);
        }

        Intent i = new Intent(HomeActivity.this, SettingActivity.class);
        startActivity(i);
        finish();
    }
    public void onClickNumberButton(String inputNumber)
    {


        String  inputStr,equationString;

        if(!txt_input_view.getText().toString().isEmpty() && equalclicked)
        {
            txt_input_view.setText("");
            equalclicked = false;
            commaString = "";
            if(calc.infix.size() > 1)
            {
                calc.infix.remove(1);
            }
        }
        if(specialClicked)
            return;



        inputStr = txt_input_view.getText().toString();
        equationString = txt_current_view.getText().toString();
        if(!equationString.isEmpty() && percentageClicked)
            return;
        if(inputStr.isEmpty() && !equationString.isEmpty())
        {
            char ch = equationString.charAt(equationString.length() - 1);
            if(ch == Constants.BRACKET_CLOSE)
                return;
        }


        if(seperator == 1)
        {


            if(!commaString.isEmpty()&& (inputStr.indexOf(Constants.DOT_POINT)== -1))
            {

                String formatInputNumber = getDecimalFormattedString(commaString + inputNumber);
                if(!inputStr.isEmpty())
                {
                    if(inputStr.charAt(0) == Constants.MINUS)
                        formatInputNumber  = Constants.MINUS + formatInputNumber;
                }

                txt_input_view.setText(formatInputNumber);
            }else
            {

                txt_input_view.append(inputNumber);
            }



        }else
        {
            txt_input_view.append(inputNumber);
        }
        commaString +=inputNumber;
        if(vibration == 1)
        {
            vi.vibrate(120);
        }

        if(numberInput)
        {
            if(calc.infix.get(calc.infix.size() -1).indexOf(Constants.DOT_POINT) == -1)
            {
                if(inputStr.indexOf(Constants.DOT_POINT) != -1)
                    inputNumber = Constants.DOT_POINT + inputNumber;

            }
            calc.infix.set(calc.infix.size()-1, calc.infix.get(calc.infix.size()-1) + inputNumber);
        }
        else
            calc.infix.add(inputNumber);
        numberInput = true;


    }

    public void onClickOperationButton(char operation)
    {

        String  inputString,equationString;
        inputString = txt_input_view.getText().toString();
        equationString = txt_current_view.getText().toString();

        byte i;
        if(!inputString.isEmpty())
        {
            if(!(operation == Constants.BRACKET_OPEN || operation == Constants.CLEAR || operation == Constants.BACKSPACE ))
            {

                char ch = inputString.charAt(inputString.length() - 1);
                if(ch != Constants.BRACKET_CLOSE)
                {
                    for(i = 0; i < Constants.MAX_NUM ; i++ )
                    {
                        if(i == Character.getNumericValue(ch))
                            break;
                    }
                    if( i >= Constants.MAX_NUM)
                        return;
                }

            }
            if(equalclicked)
            {
                if(operation == Constants.BRACKET_OPEN)
                {
                    if(!inputString.equals(Constants.ERROR))
                        calc.infix.remove(1);
                    equalclicked =false;
                }else if(operation == Constants.BACKSPACE)
                {
                    if(inputString.equals(Constants.ERROR))
                    {
                        txt_input_view.setText("");
                        return;
                    }


                }else
                {
                    equalclicked = false;
                }
            }

            if(percentageClicked)
            {
                if(!(operation == Constants.PLUS || operation == Constants.MINUS  || operation == Constants.MUL  ||operation == Constants.DIVIDE || operation == Constants.CLEAR ))
                    return;
                txt_input_view.setText("");
                commaString = "";
            }
            if(specialClicked)
            {
                if(!(operation == Constants.PLUS || operation == Constants.MINUS  || operation == Constants.MUL  ||operation == Constants.DIVIDE || operation == Constants.CLEAR ))
                    return;
                specialClicked = false;
                inputString="";
            }
            if(specialPowNClicked)
            {
                if(specialpowNumber.isEmpty())
                    return;
                if(!(operation == Constants.CLEAR || operation == Constants.BACKSPACE))
                {
                    //if()
                    {
                        calculator_POWN(inputString);
                        equationString = txt_current_view.getText().toString();
                        inputString = "";


                    }
                }


            }
        }else
        {

            if(operation == Constants.BRACKET_OPEN || operation == Constants.CLEAR)
            {

            }else
            {
                if(!equationString.isEmpty())
                {
                    if(specialPowNClicked)
                    {
                        int index = equationString.lastIndexOf(String.valueOf(Constants.BRACKET_OPEN));
                        equationString = equationString.substring(0,index);
                        equationString +=specialpowNumber;
                        specialpowNumber = "";
                        specialPowNClicked = false;
                        if(calc.infix.size() > 2)
                        {
                            calc.infix.remove(calc.infix.size() - 1);
                        }

                    }else if(equationString.charAt(equationString.length() - 1) != Constants.BRACKET_CLOSE)
                        return;
                }else {
                    return;
                }
            }




        }
        if(vibration == 1){
            vi.vibrate(120);
        }


        switch (operation)
        {
            case Constants.BACKSPACE:
            {

                if(inputString.isEmpty() || inputString.length() == 1)
                {
                    numberInput = false;
                    calc.infix.remove(calc.infix.size() -1 );
                    txt_input_view.setText("");
                    commaString = "";
                    return;
                }


                inputString = inputString.substring(0,inputString.length()-1);
                commaString = commaString.substring(0,commaString.length()-1);
                char    lastChar = inputString.charAt(inputString.length()-1);
                if(lastChar == ',')
                    inputString = inputString.substring(0,inputString.length()-1);
                if(seperator == 1)
                {
                    inputString = getDecimalFormattedString(commaString);
                }
                txt_input_view.setText(inputString);
                String  saveNum;
                saveNum = calc.infix.get(calc.infix.size()-1);
                saveNum = saveNum.substring(0,saveNum.length() - 1);
                calc.infix.set(calc.infix.size()-1, saveNum);
                return;
            }
            case Constants.CLEAR:
            {
                init();
                txt_input_view.setText("");
                txt_current_view.setText("");
                return;
            }
            case Constants.SIGN:
            {

                if(inputString.isEmpty())
                    return;
                String saveNum;
                char signChar = inputString.charAt(0);
                saveNum = calc.infix.get(calc.infix.size()-1);
                if(signChar == Constants.MINUS)
                {
                    inputString = inputString.substring(1,inputString.length());
                    txt_input_view.setText(inputString);
                    saveNum = saveNum.substring(1,saveNum.length());
                    calc.infix.set(calc.infix.size()-1, saveNum);

                }else
                {
                    inputString = Constants.MINUS + inputString;
                    txt_input_view.setText(inputString);
                    saveNum  = Constants.MINUS + saveNum;
                    calc.infix.set(calc.infix.size()-1, saveNum);
                }
                return;
            }

            case Constants.DIVIDE:
            {
                inputString = inputString + Constants.DIVIDE ;
                txt_current_view.setText(equationString + inputString);
                txt_input_view.setText("");
            }
            break;
            case Constants.MUL:
            {
                inputString += Constants.MUL;
                txt_current_view.setText(equationString + inputString);
                txt_input_view.setText("");
            }
            break;
            case Constants.PLUS:
            {
                inputString += Constants.PLUS;
                txt_current_view.setText(equationString + inputString);
                txt_input_view.setText("");
            }
            break;
            case Constants.MINUS:
            {
                inputString += Constants.MINUS;
                txt_current_view.setText(equationString + inputString);
                txt_input_view.setText("");
            }
            break;
            case Constants.PERSENTAGE:
            {

                if(equationString.isEmpty())
                    return;
                if(inputString.isEmpty())
                    return;
                char ch = equationString.charAt(equationString.length() - 1);
                if(ch == Constants.PLUS ||ch == Constants.MINUS ||ch == Constants.MUL || ch == Constants.DIVIDE )
                {
                    String temp = calc.infix.get(calc.infix.size()-3);
                    if(temp.equals(String.valueOf(Constants.BRACKET_CLOSE)))
                        return;
                    float number_1 = Float.valueOf(temp);
                    float number_2 = Float.valueOf(commaString);
                    float result = number_1 * number_2/100.0f;
                    inputString = String.valueOf(result);
                    percentageClicked = true;
                    calc.infix.set(calc.infix.size()-1,inputString);
                    txt_current_view.setText(equationString + inputString);

                    txt_input_view.setText(inputString);
                    commaString = "";
                    return;
                }

            }
            break;
            case Constants.BRACKET_OPEN:
            {
                if(!equationString.isEmpty())
                {
                    char ch = equationString.charAt(equationString.length() - 1);
                    if(ch == Constants.BRACKET_CLOSE)
                        return;
                    for(i = 0; i < Constants.MAX_NUM ; i++ )
                    {
                        if(i == Character.getNumericValue(ch))
                            break;
                    }
                    if( i < Constants.MAX_NUM)
                        return;
                }
                if(!inputString.isEmpty())
                {
                    if(calc.infix.size() > 1)
                    {
                        calc.infix.remove(calc.infix.size()-1);
                    }
                }
                equationString += Constants.BRACKET_OPEN;
                txt_current_view.setText(equationString);
                txt_input_view.setText("");
            }
            break;
            case Constants.BRACKET_CLOSE:
            {
                char ch;
                if(inputString.isEmpty())
                {
                    if(!isNumberic(equationString)) {
                        ch = equationString.charAt(equationString.length() - 1);
                        if (ch != Constants.BRACKET_CLOSE)
                            return;

                    }
                }else
                {
                    ch = inputString.charAt(inputString.length() - 1);
                    for(i = 0; i < Constants.MAX_NUM ; i++ )
                    {
                        if(i == Character.getNumericValue(ch))
                            break;
                    }
                    if( i >= Constants.MAX_NUM)
                        return;

                }

                int bracketOpenCount = equationString.length() - equationString.replace("(", "").length();
                int bracketCloseCount = equationString.length() - equationString.replace(")", "").length();

                if(bracketOpenCount == 0 || bracketOpenCount <= bracketCloseCount)
                    return;

                inputString += Constants.BRACKET_CLOSE;
                txt_current_view.setText(equationString + inputString);
                txt_input_view.setText("");
            }
            break;
            case Constants.DOT_POINT:
            {
                if(inputString.isEmpty())
                    return;
                if(inputString.contains(String.valueOf(Constants.DOT_POINT)))
                    return;
                inputString += Constants.DOT_POINT;
                txt_input_view.setText(inputString);
                return;
            }


        }
        percentageClicked = false;
        commaString = "";
        numberInput = false;
        calc.infix.add(String.valueOf(operation));


    }
    public void onClickSpecialButton(String operation)
    {
        if(specialPowNClicked || specialClicked)
        {
            String equationString = txt_current_view.getText().toString();
            char ch = equationString.charAt(equationString.length() - 1);
            if(!(ch == Constants.PLUS ||ch == Constants.MINUS ||ch == Constants.MUL || ch == Constants.DIVIDE ))
                return;
        }

        String inputNumber = txt_input_view.getText().toString();
        inputNumber = inputNumber.replaceAll(String.valueOf(Constants.COMMA),"");
        if(operation == Constants.PI)
        {
            if(calc.infix.size() > 1)
            {
                String operator = calc.infix.get(calc.infix.size() - 1);
                if(!(operator.equals(String.valueOf(Constants.PLUS ))|| operator.equals(String.valueOf(Constants.MINUS))   || operator.equals(String.valueOf(Constants.MUL )) ||operator.equals(String.valueOf(Constants.DIVIDE ))))
                    return;
            }
            inputNumber = "3.141592653589793";
        }
        if(inputNumber.isEmpty())
            return;
        if(!isNumberic(inputNumber))
            return;
        String[] displayString = new String[2];

        if(operation == Constants.POW_N)
        {
            specialpowNumber = inputNumber;
            specialPowNClicked = true;
            numberInput = false;
            displayString[1] = "";
            displayString[0] = displayString[0] =  String.valueOf(Constants.BRACKET_OPEN )  + inputNumber  + Constants.POW_N;

        }else
        {

            if(operation == Constants.POWE)
            {
                BigDecimal  specialResult = new BigDecimal(Math.E,MathContext.DECIMAL64);
                BigDecimal  specialValue = new BigDecimal(1,MathContext.DECIMAL64);
                int n;
                try {
                    n = Integer.valueOf(inputNumber);
                    specialValue = specialResult.pow(n,MathContext.DECIMAL64);
                }catch (Exception e)
                {
                    txt_current_view.setText("");
                    txt_input_view.setText(Constants.ERROR);
                    return;
                }
                calc.infix.set(calc.infix.size()-1,specialValue.toString());
                displayString[1] = specialValue.toString();
                displayString[0]  = Constants.POWE + String.valueOf(Constants.BRACKET_OPEN )  + inputNumber +String.valueOf(Constants.BRACKET_CLOSE);
            }else if(operation != Constants.PI)
            {
                displayString =  calc.triangleFunc(inputNumber,operation);

            }else
            {
                displayString[1] = inputNumber;
                displayString[0] = String.valueOf(Constants.BRACKET_OPEN )  + Constants.PI + String.valueOf(Constants.BRACKET_CLOSE);
                calc.infix.add(inputNumber);
            }

            specialClicked = true;

        }
        txt_current_view.append(displayString[0]);
        txt_input_view.setText(displayString[1]);
        commaString="";
        if(vibration == 1){
            vi.vibrate(120);
        }

    }

    public String getDecimalFormattedString(String number){
        StringBuilder strB = new StringBuilder();
        strB.append(number);
        int Three = 0;

        for(int i=number.length();i>1;i--){
            Three++;
            if(Three == 3){

                strB.insert(i-1, Constants.COMMA);
                Three = 0;
            }
        }
        return strB.toString();
    }
    public boolean  isNumberic(String paramString)
    {
        int     i;
        char    ch;
        ch = paramString.charAt(paramString.length()-1);
        for(i = 0; i < Constants.MAX_NUM ; i++ )
        {
            if(i == Character.getNumericValue(ch))
                break;
        }
        if( i >= Constants.MAX_NUM)
            return false;
        return true;
    }

    public void onClickMemoryKeyButton(String inputMemkey)
    {
        mem_Value = txt_input_view.getText().toString();
        switch (inputMemkey)
        {
            case  Constants.MEM_PLUS:
            {
                if(txt_input_view.getText().toString().equals(Constants.ERROR))
                    return;

                if(mem_Value.isEmpty())
                    return;
                mem_Value = mem_Value.replaceAll(String.valueOf(Constants.COMMA),"");
                BigDecimal temp = new BigDecimal(mem_Value);
                memValue = memValue.add(temp,MathContext.DECIMAL64);
                int mem_textColor = prefs.getInt(Constants.PRIMARY_COLOR, Constants.RES_PRIMARYCOLOR);
                txt_mr_view.setTextColor(mem_textColor);
                String formatString = getFormatString(memValue.toString());
                txt_mr_view.setText( "M " + formatString);
            }
            break;
            case  Constants.MEM_MINUS:
            {
                if(mem_Value.isEmpty())
                    return;
                mem_Value = mem_Value.replaceAll(String.valueOf(Constants.COMMA),"");
                BigDecimal temp = new BigDecimal(mem_Value);
                memValue = memValue.subtract(temp,MathContext.DECIMAL64);
                txt_mr_view.setText("");
                String formatString = getFormatString(memValue.toString());
                txt_mr_view.setText("M " + formatString);
            }
            break;
            case  Constants.MEM_READ:
            {
                BigDecimal temp = BigDecimal.ZERO;
                if(memValue.equals(temp))
                {
                    return;
                }

                String mem_valueTemp =memValue.toString();
                if(!txt_input_view.getText().toString().isEmpty())
                {
                    if(txt_input_view.getText().toString().equals(Constants.ERROR))
                    {
                        calc.infix.add(mem_valueTemp);
                    }else
                    {
                        calc.infix.set(calc.infix.size()-1,mem_valueTemp);
                    }
                }else
                {
                    calc.infix.add(mem_valueTemp);
                }
                mem_valueTemp = getFormatString(memValue.toString());
                txt_input_view.setText(mem_valueTemp);

            }
            break;
            case  Constants.MEM_CLEAR:
            {
                txt_mr_view.setText("");
                memValue = BigDecimal.ZERO;
            }
            break;
        }
        if(vibration == 1)
            vi.vibrate(120);
    }
    public void onClickEqualButton()
    {
        if(txt_input_view.getText().toString().equals(Constants.NOTHING))
            return;
        equalclicked = true;
        String  equationString;
        String  temp;
        equationString = txt_current_view.getText().toString();
        int bracketOpenCount = equationString.length() - equationString.replace("(", "").length();
        int bracketCloseCount = equationString.length() - equationString.replace(")", "").length();
        if(bracketCloseCount != bracketOpenCount)
        {
            if(specialPowNClicked)
            {
                calculator_POWN(txt_input_view.getText().toString());

            }else
            {
                init();
                txt_input_view.setText(Constants.ERROR);
                return;
            }

        }
        if(vibration == 1)
        {
            vi.vibrate(120);
        }

        String result = calc.equaltion();
        txt_current_view.setText("");
        if(result.equals(Constants.ERROR))
        {
            commaString = "";
        }else
        {
            if(seperator == 1)
            {
                result = getFormatString(result);

            }
            commaString = result;
        }

        txt_input_view.setText(result);



        if(result.equals(Constants.ERROR))
        {
            commaString = "";
        }else
        {
            commaString = result;
        }
        numberInput = false;
        percentageClicked = false;
        specialPowNClicked = false;
        specialClicked = false;
    }

    public void init()
    {
        commaString = "";
        calc.infix = new ArrayList<>();
        calc.infix.add(String.valueOf(Constants.BRACKET_OPEN));
        numberInput = false;
        percentageClicked = false;
        specialPowNClicked = false;
        specialClicked = false;
        txt_current_view.setText("");


    }
    public void calculator_POWN(String inputString)
    {
        if(inputString.isEmpty())
            return;
        BigDecimal specialValue;
        String  equationString;
        equationString = txt_current_view.getText().toString();
        int  squareNumber  = Integer.valueOf(inputString);
        BigDecimal  specialResult = new BigDecimal(specialpowNumber);
        specialValue = specialResult.pow(squareNumber,MathContext.DECIMAL64);

        if(calc.infix.size() > 2)
        {
            calc.infix.remove(calc.infix.size()-1);
            calc.infix.set(calc.infix.size()-1,specialValue.toString());

        }
        equationString = equationString +  inputString + Constants.BRACKET_CLOSE;
        txt_current_view.setText(equationString);
        specialPowNClicked = false;
    }
    public String getFormatString(String result)
    {
        String temp;
        if(!result.contains("E"))
        {
            String     sign = "";
            if(result.contains("."))
            {
                int index = result.indexOf(String.valueOf(Constants.DOT_POINT));
                temp = result.substring(0,index);

                if(temp.charAt(0) == Constants.MINUS)
                {
                    temp = temp.substring(1,temp.length());
                    sign = "-";
                }
                result = result.substring(index,result.length());
                temp = getDecimalFormattedString(temp);
                temp += result;
                result = sign + temp;


            }else if(result.charAt(0) == Constants.MINUS)
            {
                temp = result.substring(1,result.length());
                temp = getDecimalFormattedString(temp);
                result = Constants.MINUS + temp;

            }else
            {
                result = getDecimalFormattedString(result);
            }
        }
        return result;
    }

}
