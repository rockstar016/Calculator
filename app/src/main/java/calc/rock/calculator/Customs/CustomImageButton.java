package calc.rock.calculator.Customs;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageButton;

import calc.rock.calculator.R;
import calc.rock.calculator.Utils.Constants;

/**
 * Created by rock on 2/12/17.
 */

public class CustomImageButton extends ImageButton {
    public CustomImageButton(Context context) {
        super(context);
    }

    public CustomImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomImageButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    public void setTheme(int theme){
        if(theme == Constants.GRAY_THEME){
            setColorFilter(getResources().getColor(R.color.colorPink));
            setBackgroundResource(R.drawable.dark_graybutton_background);
        }
        else if(theme == Constants.BLUE_THEME){

        }
    }
}
