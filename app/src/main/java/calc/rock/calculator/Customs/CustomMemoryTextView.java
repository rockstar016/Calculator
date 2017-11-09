package calc.rock.calculator.Customs;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by rock on 2/12/17.
 */

public class CustomMemoryTextView extends android.support.v7.widget.AppCompatTextView {
    public CustomMemoryTextView(Context context) {
        super(context);
    }

    public CustomMemoryTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomMemoryTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTheme(int theme){

    }
}
