package calc.rock.calculator.Customs;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by rock on 2/12/17.
 */

public class CustomNumberButton extends Button {
    public CustomNumberButton(Context context) {
        super(context);

    }

    public CustomNumberButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomNumberButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomNumberButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }


    public void setTheme(int theme,int backgroundColor){
        setTextColor(theme);
        setBackgroundColor(backgroundColor);

    }
}
