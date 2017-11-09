package calc.rock.calculator.Customs;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;

import calc.rock.calculator.Utils.Formatter;

public class CustomNumberButton extends AngleGradientButton {
    public CustomNumberButton(Context context) {
        super(context);

    }

    public CustomNumberButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomNumberButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public void setTheme(int theme,int backgroundColor){
        setTextColor(theme);
        setEndColor(backgroundColor);
        setStartColor(Formatter.manipulateColor(backgroundColor, 1.3f));
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(2,2,2,2);
        setLayoutParams(layoutParams);
    }
}
