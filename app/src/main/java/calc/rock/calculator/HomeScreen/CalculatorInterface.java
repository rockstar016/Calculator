package calc.rock.calculator.HomeScreen;

/**
 * Created by rock on 2/14/17.
 */

public interface CalculatorInterface {
    void onClickNumberButton(String inputNUmber);
    void onClickOperationButton(char operation);
    void onClickMemoryKeyButton(String inputMemkey);
    void onClickSpecialButton(String specialOperator);
    void onClickEqualButton();
}
