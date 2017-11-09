package calc.rock.calculator.Customs;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import me.grantland.widget.AutofitTextView;

/**
 * Created by rock on 2/12/17.
 */

public class CustomNumberTextView extends AutofitTextView {
    public CustomNumberTextView(Context context) {
        super(context);
    }

    public CustomNumberTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomNumberTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setTheme(int theme){

        setSingleLine(true);
        setTextColor(theme);
    }
}
