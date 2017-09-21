package calc.rock.calculator.Utils;

/**
 * Created by Administrator on 2/18/2017.
 */




import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;

public class Calculator {
    public ArrayList<String> infix;
    public BigDecimal number;
    public BigDecimal answer;
    public BigDecimal specialValue;
    final static BigInteger HUNDRED = BigInteger.valueOf(100);

    public  Calculator()
    {
        infix = new ArrayList<String>();
        infix.add(String.valueOf(Constants.BRACKET_OPEN));

    }

    Exception sinException = new Exception(){

    };
    public String equaltion(){
        infix.add(String.valueOf(Constants.BRACKET_CLOSE));
        ArrayList<String> postfix = infixToPostfix(infix);
        String result = postfixEvaluation(postfix);
        infix = new ArrayList<>();
        infix.add(String.valueOf(Constants.BRACKET_OPEN));
        if(!result.equals(Constants.ERROR))
            infix.add(result);
        return result;
    }

    public ArrayList<String> infixToPostfix(ArrayList<String> infixLocal){
        Stack infixStack = new Stack();
        ArrayList<String > postfix = new ArrayList<>();
        int i = 0;

        i=0;
        while(i < infixLocal.size()) {
            String s = infixLocal.get(i);
            if(((int)s.charAt(0) >=48 && (int)s.charAt(0) <= 57) || s.charAt(0) == '.'|| (s.charAt(0) == '-' && s.length() > 1)){
                postfix.add(s);
                i++;
            }
            else if(s.equals(String.valueOf(Constants.PI))){
                postfix.add((22.0/7.0) + "");
                i++;
            }
            else if(s.equals(String.valueOf(Constants.POWE))){
                postfix.add((2.71828182846) + "");
                i++;
            }
            else if(s.equals(String.valueOf(Constants.BRACKET_OPEN))){
                infixStack.push("" + Constants.BRACKET_OPEN);
                i++;
            }
            else if(s.equals(String.valueOf(Constants.BRACKET_CLOSE))){
                String st = infixStack.pop();
                while(!st.equals(String.valueOf(Constants.BRACKET_OPEN))){
                    postfix.add(st);
                    st = infixStack.pop();
                }
                i++;
            }
            else if(s.equals(String.valueOf(Constants.PLUS)) || s.equals(String.valueOf(Constants.MINUS))){
                String st = infixStack.viewLast();
                while(st.equals(String.valueOf(Constants.PLUS)) || st.equals(String.valueOf(Constants.MINUS)) ||
                        st.equals(String.valueOf(Constants.MUL)) || st.equals(String.valueOf(Constants.DIVIDE))){
                    postfix.add(infixStack.pop());
                    st = infixStack.viewLast();
                }
                infixStack.push(s);
                i++;
            }
            else if(s.equals(String.valueOf(Constants.MUL)) || s.equals(String.valueOf(Constants.DIVIDE )) ||s.equals(String.valueOf(Constants.PACKAGENAME))){
                infixStack.push(s);
                i++;
            }

            else if(s.equals(String.valueOf(Constants.COMMA))){
                // Issue Expression
            }


        }
        return postfix;
    }

    public String postfixEvaluation(ArrayList<String> postfixLocal){
        Stack postfixStack = new Stack();
        boolean     operationSuccess;
        for(int i=0; i<postfixLocal.size(); i++ ){
            operationSuccess =false;
                String current = postfixLocal.get(i);
            if(((int)current.charAt(0) >=48 && (int) current.charAt(0) <= 57) ||
                    (current.length()>1 && (int)current.charAt(1) >=48 && (int) current.charAt(1) <= 57)){
                postfixStack.push(current);
                operationSuccess = true;
            }
            else if(postfixStack.size() > 1){
                answer = new BigDecimal(postfixStack.pop());
                number = new BigDecimal(postfixStack.pop());

                String op = postfixLocal.get(i);
                if(op.equals(String.valueOf(Constants.PLUS))){
                    postfixStack.push(answer.add(number,MathContext.DECIMAL64)+"");
                }
                else if(op.equals(String.valueOf(Constants.MINUS))){
                    postfixStack.push(number.subtract(answer,MathContext.DECIMAL64) +"");
                }
                else if(op.equals(String.valueOf(Constants.MUL))){
                    postfixStack.push(answer.multiply(number,MathContext.DECIMAL32) +"");
                }
                else if(op.equals(String.valueOf(Constants.DIVIDE))){
                    try {
                        postfixStack.push(number.divide(answer, MathContext.DECIMAL64) + "");
                    }catch (Exception e){
                        postfixStack.push(Constants.ERROR);
                    }
                }else if(op.equals(String.valueOf(Constants.PERSENTAGE))){

                }
                operationSuccess = true;
            }
            if(!operationSuccess)
                return Constants.ERROR;

        }
        if(!postfixStack.isSingleElement()){
            //definetely an error
            return Constants.ERROR;
        }
        return postfixStack.pop();
    }

    public double stringToDecimal(String num){


        double n = 0.0;
        boolean periodEnc = false;
        int j = 1, k = 1;
        for(int i=0; i<num.length(); i++){
            if(i==0 && num.charAt(i) == '-'){
                k = -1;
            }
            else if(num.charAt(i) == '.') {
                if (periodEnc) {
                    //Invalid number

                    // TO DO: Clear everything
                }
                periodEnc = true;
            }
            else if (periodEnc)
                n = n + ((int) num.charAt(i) -48) *Math.pow(10, -1 * (j++));
            else
                n = n * 10 + ((int) num.charAt(i) -48);
        }
        return n * k;
    }

    public String[] triangleFunc(String   inputNumber,String operator)//uses the sin, arcsin or sinh trig function according to the value of the inverse and hyperbolic flags
    {
        double temp = 0.0;
        String displayString[] = new String[2];
        try {
            specialValue = new BigDecimal(inputNumber);
            switch (operator) {
                case Constants.SIN: {
                    temp = Math.sin(specialValue.doubleValue());
                    specialValue = BigDecimal.valueOf(temp);
                    displayString[0] = Constants.SIN + String.valueOf(Constants.BRACKET_OPEN) + inputNumber + String.valueOf(Constants.BRACKET_CLOSE);
                }
                break;
                case Constants.SINH: {
                        temp = Math.sinh(specialValue.doubleValue());
                    displayString[0] = Constants.SINH + String.valueOf(Constants.BRACKET_OPEN) + inputNumber + String.valueOf(Constants.BRACKET_CLOSE);
                        specialValue = BigDecimal.valueOf(temp);
                }
                break;
                case Constants.SIN_INVERSE: {
                    temp = specialValue.doubleValue();
                    displayString[0] = Constants.SIN_INVERSE + String.valueOf(Constants.BRACKET_OPEN) + inputNumber + String.valueOf(Constants.BRACKET_CLOSE);
                    if ((-Constants.PIVALUE / 2 > temp) || (temp > Constants.PIVALUE / 2)) {
                        displayString[1] = Constants.ERROR;
                        return displayString;
                    } else {
                        temp = Math.asin(temp);
                        specialValue = BigDecimal.valueOf(temp);

                    }

                }
                break;
                case Constants.SINH_INVERSE: {
                    temp = asinh(specialValue.doubleValue());
                    displayString[0] = Constants.SINH_INVERSE + String.valueOf(Constants.BRACKET_OPEN) + inputNumber + String.valueOf(Constants.BRACKET_CLOSE);
                    specialValue = BigDecimal.valueOf(temp);

                }
                break;
                case Constants.COS: {
                    temp = Math.cos(specialValue.doubleValue());
                    displayString[0] = Constants.COS + String.valueOf(Constants.BRACKET_OPEN) + inputNumber + String.valueOf(Constants.BRACKET_CLOSE);
                    specialValue = BigDecimal.valueOf(temp);

                }
                break;
                case Constants.COSH: {
                        temp = Math.cosh(specialValue.doubleValue());
                        displayString[0] = Constants.COSH + String.valueOf(Constants.BRACKET_OPEN) + inputNumber + String.valueOf(Constants.BRACKET_CLOSE);
                        specialValue = BigDecimal.valueOf(temp);
                }
                break;
                case Constants.COS_INVERSE: {
                    displayString[0] = Constants.COS_INVERSE + String.valueOf(Constants.BRACKET_OPEN) + inputNumber + String.valueOf(Constants.BRACKET_CLOSE);
                    if ((-Constants.PIVALUE / 2 > temp) || (temp > Constants.PIVALUE / 2)) {
                        displayString[1] = Constants.ERROR;
                        return displayString;
                    } else {
                        temp = Math.acos(specialValue.doubleValue());
                        specialValue = BigDecimal.valueOf(temp);
                    }
                }
                break;
                case Constants.COSH_INVERSE: {
                    temp = acosh(specialValue.doubleValue());
                    displayString[0] = Constants.COSH_INVERSE + String.valueOf(Constants.BRACKET_OPEN) + inputNumber + String.valueOf(Constants.BRACKET_CLOSE);
                    specialValue = BigDecimal.valueOf(temp);
                }
                break;
                case Constants.TAN: {
                    temp = Math.tan(specialValue.doubleValue());
                    displayString[0] = Constants.TAN + String.valueOf(Constants.BRACKET_OPEN) + inputNumber + String.valueOf(Constants.BRACKET_CLOSE);
                    specialValue = BigDecimal.valueOf(temp);
                }
                break;
                case Constants.TANH: {

                    displayString[0] = Constants.TANH + String.valueOf(Constants.BRACKET_OPEN) + inputNumber + String.valueOf(Constants.BRACKET_CLOSE);
                    if ((-Constants.PIVALUE / 2 > temp) || (temp > Constants.PIVALUE / 2)) {
                        displayString[1] = Constants.ERROR;
                        return displayString;
                    } else {
                        temp = Math.atan(specialValue.doubleValue());
                        specialValue = BigDecimal.valueOf(temp);

                    }
                }
                break;
                case Constants.TAN_INVERSE: {
                    temp = Math.tanh(specialValue.doubleValue());
                    displayString[0] = Constants.TAN_INVERSE + String.valueOf(Constants.BRACKET_OPEN) + inputNumber + String.valueOf(Constants.BRACKET_CLOSE);
                    specialValue = BigDecimal.valueOf(temp);

                }
                break;
                case Constants.TANH_INVERSE: {
                    temp = atanh(specialValue.doubleValue());
                    displayString[0] = Constants.TANH_INVERSE + String.valueOf(Constants.BRACKET_OPEN) + inputNumber + String.valueOf(Constants.BRACKET_CLOSE);
                    specialValue = BigDecimal.valueOf(temp);
                }
                break;
                case Constants.PI: {
                    displayString[0] = String.valueOf(Constants.BRACKET_OPEN) + Constants.PI + String.valueOf(Constants.BRACKET_CLOSE);
                }
                break;
                case Constants.POWE: {
                    int n = Integer.valueOf(inputNumber);

                    displayString[0] = Constants.SIN + String.valueOf(Constants.BRACKET_OPEN) + inputNumber + String.valueOf(Constants.BRACKET_CLOSE);
                }
                break;

                case Constants.POW_2: {
                    displayString[0] = Constants.POW_2 + String.valueOf(Constants.BRACKET_OPEN) + inputNumber + " " + String.valueOf(Constants.BRACKET_CLOSE);
                    specialValue = specialValue.pow(2, MathContext.DECIMAL64);
                }
                break;
                case Constants.POW_3: {
                    displayString[0] = Constants.POW_3 + String.valueOf(Constants.BRACKET_OPEN) + inputNumber + String.valueOf(Constants.BRACKET_CLOSE);
                    specialValue = specialValue.pow(3, MathContext.DECIMAL64);
                }
                break;
                case Constants.X_INVERSE: {
                    BigDecimal number = BigDecimal.ONE;
                    displayString[0] = Constants.X_INVERSE + String.valueOf(Constants.BRACKET_OPEN) + inputNumber + String.valueOf(Constants.BRACKET_CLOSE);
                    specialValue = number.divide(specialValue, MathContext.DECIMAL32);

                }
                break;
                case Constants.LOG_10: {
                    temp = Math.log10(specialValue.doubleValue());
                    displayString[0] = Constants.LOG_10 + String.valueOf(Constants.BRACKET_OPEN) + inputNumber + String.valueOf(Constants.BRACKET_CLOSE);
                    specialValue = BigDecimal.valueOf(temp);
                }
                break;
                case Constants.LOG_2: {
                    temp = log2(specialValue.doubleValue());
                    displayString[0] = Constants.LOG_2 + String.valueOf(Constants.BRACKET_OPEN) + inputNumber + String.valueOf(Constants.BRACKET_CLOSE);
                    specialValue = BigDecimal.valueOf(temp);

                }
                break;
                case Constants.ln: {
                    temp = Math.log(specialValue.doubleValue());
                    displayString[0] = Constants.ln + String.valueOf(Constants.BRACKET_OPEN) + inputNumber + String.valueOf(Constants.BRACKET_CLOSE);
                    specialValue = BigDecimal.valueOf(temp);
                }
                break;
                case Constants.X_FACTORIAL: {

                    if (Double.valueOf(inputNumber) > 3000.0) {
                        displayString[1] = Constants.OVERFLOW;
                        displayString[0] = "";
                        return displayString;
                    }
                    double i;
                    displayString[0] = Constants.X_FACTORIAL + String.valueOf(Constants.BRACKET_OPEN) + inputNumber + String.valueOf(Constants.BRACKET_CLOSE);
                    for (i = 2; i < Double.valueOf(inputNumber); i++) {
                        specialValue = specialValue.multiply(BigDecimal.valueOf(i), MathContext.DECIMAL64);
                    }

                }
                break;
                case Constants.SQRT: {

                    temp = Math.sqrt(specialValue.doubleValue());
                    displayString[0] = Constants.SQRT + String.valueOf(Constants.BRACKET_OPEN) + inputNumber + String.valueOf(Constants.BRACKET_CLOSE);
                    specialValue = BigDecimal.valueOf(temp);
                }
                break;
                case Constants.X_TRIPLE_SQRT: {
                    temp = Math.cbrt(specialValue.doubleValue());
                    temp = Math.scalb(temp, 4);
                    displayString[0] = Constants.X_TRIPLE_SQRT + String.valueOf(Constants.BRACKET_OPEN) + inputNumber + String.valueOf(Constants.BRACKET_CLOSE);
                    specialValue = BigDecimal.valueOf(temp);

                }
                break;
            }
            infix.set(infix.size() - 1, specialValue.toString());
            displayString[1] = specialValue.toString();
        }catch (Exception e){
            displayString[1] = Constants.ERROR;
        }
        return displayString;
    }

    double asinh(double x)
    {
        return Math.log(x + Math.sqrt(x*x + 1.0));
    }
    double atanh(double x)
    {
        return 0.5*Math.log( (x + 1.0) / (x - 1.0));
    }
    double acosh(double x)
    {
        return Math.log(x + Math.sqrt(x*x - 1.0));
    }
    public static double log2(double n)
    {
        return (Math.log(n) / Math.log(2));
    }


}
