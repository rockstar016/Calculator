package calc.rock.calculator.Customs;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import calc.rock.calculator.R;
import calc.rock.calculator.Utils.Constants;

/**
 * Created by rock on 2/12/17.
 */

public class CustomNumberTextView extends TextView {
    public CustomNumberTextView(Context context) {
        super(context);
    }

    public CustomNumberTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomNumberTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomNumberTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setTheme(int theme){
        if(theme == Constants.GRAY_THEME){
            setBackgroundColor(Color.WHITE);
            setTextColor(Color.BLACK);
        }
        else if(theme == Constants.BLUE_THEME){

        }
    }
}
