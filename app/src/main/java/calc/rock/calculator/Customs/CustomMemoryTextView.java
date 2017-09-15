package calc.rock.calculator.Customs;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by rock on 2/12/17.
 */

public class CustomMemoryTextView extends TextView {
    public CustomMemoryTextView(Context context) {
        super(context);
    }

    public CustomMemoryTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomMemoryTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomMemoryTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setTheme(int theme){

    }
}
