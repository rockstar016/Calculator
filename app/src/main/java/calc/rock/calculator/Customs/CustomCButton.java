package calc.rock.calculator.Customs;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;

import calc.rock.calculator.Utils.Formatter;

/**
 * Created by rock on 2/12/17.
 */

public class CustomCButton extends AngleGradientButton {
    public CustomCButton(Context context) {
        super(context);
    }

    public CustomCButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomCButton(Context context, AttributeSet attrs, int defStyleAttr) {
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
