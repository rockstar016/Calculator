package calc.rock.calculator.Customs;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageButton;

import calc.rock.calculator.R;
import calc.rock.calculator.Utils.Constants;

/**
 * Created by rock on 2/12/17.
 */

public class CustomCButton extends Button {
    public CustomCButton(Context context) {
        super(context);
    }

    public CustomCButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomCButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomCButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    public void setTheme(int theme){
        if(theme == Constants.GRAY_THEME){
            setTextColor(getResources().getColor(R.color.colorPink));
            setBackgroundResource(R.drawable.dark_graybutton_background);
        }
        else if(theme == Constants.BLUE_THEME){

        }
    }
}
