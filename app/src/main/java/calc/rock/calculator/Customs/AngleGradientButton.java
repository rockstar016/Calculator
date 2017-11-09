package calc.rock.calculator.Customs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.IntRange;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

import calc.rock.calculator.R;

public class AngleGradientButton extends AppCompatButton {

    private float cornerRadius = 20, permanentAlpha = 1f;
    private int startColor;
    private int endColor;
    private boolean smooth = false;

    private Paint paint;

    public AngleGradientButton(Context context) {
        super(context);
        init(context, null, 0);
    }

    public AngleGradientButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public AngleGradientButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attributeSet, int defStyleAttr) {
        paint = new Paint();
        paint.setAntiAlias(true);
        if (attributeSet != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.AngleGradientButton);

            startColor = typedArray.getColor(R.styleable.AngleGradientButton_startColor, Color.WHITE);
            endColor = typedArray.getColor(R.styleable.AngleGradientButton_endColor, Color.BLACK);

            typedArray.recycle();
        } else {
            startColor = Color.WHITE;
            endColor = Color.BLACK;
        }
        initSmoothDrawable();
    }

    @Override
    public void draw(Canvas canvas) {
        if (!smooth) {
            int width = getWidth();
            int height = getHeight();
            int halfWidth = width / 2;
            int halfHeight = height / 2;
            //for more beautiful))
            double offset = halfWidth * 0.4;
            if (offset > halfHeight) offset = halfHeight;
            float widthMinusCorners = (width - (2 * cornerRadius));
            float heightMinusCorners = (height - (2 * cornerRadius));

            Path path = new Path();
            path.moveTo(0 + cornerRadius, 0);
            path.rQuadTo(-cornerRadius, 0, -cornerRadius, cornerRadius);
            path.rLineTo(0, heightMinusCorners);
            path.rQuadTo(0, cornerRadius, cornerRadius, cornerRadius);
            path.rLineTo((float) (widthMinusCorners / 2 - offset), 0);
            path.rLineTo((float) (offset * 2), -height);

            path.close();
            paint.setColor(startColor);
            canvas.drawPath(path, paint);

            path.reset();
            path.moveTo(width - cornerRadius, 0);
            path.rQuadTo(cornerRadius, 0, cornerRadius, cornerRadius);
            path.rLineTo(0, heightMinusCorners);
            path.rQuadTo(0, cornerRadius, -cornerRadius, cornerRadius);
            path.rLineTo(-(float) (widthMinusCorners / 2 + offset), 0);
            path.rLineTo((float) (offset * 2) , -height);
            path.close();

            paint.setColor(endColor);
            canvas.drawPath(path, paint);
        }
        super.draw(canvas);
    }

    public void setStartColor(int startColor) {
        this.startColor = startColor;
        invalidate();
        initSmoothDrawable();
    }

    public void setEndColor(int endColor) {
        this.endColor = endColor;
        invalidate();
        initSmoothDrawable();
    }

    public int getStartColor() {
        return startColor;
    }

    public int getEndColor() {
        return endColor;
    }

    public void setSmooth(boolean smooth) {
        this.smooth = smooth;
        invalidate();
        initSmoothDrawable();
    }

    public boolean isSmooth() {
        return smooth;
    }

    private void initSmoothDrawable() {
        if (smooth) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setOrientation(GradientDrawable.Orientation.TL_BR);
            gradientDrawable.setColors(new int[]{startColor, endColor});
            gradientDrawable.setCornerRadius(cornerRadius);
            setBackground(gradientDrawable);
        } else {
            setBackground(null);
        }
    }

    public void setPermanentAlpha(float alphaValue)
    {
        this.permanentAlpha = alphaValue;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setAlpha(.99f * permanentAlpha);
                setScaleX(.99f);
                setScaleY(.99f);
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                setAlpha(1 * permanentAlpha);
                setScaleX(1);
                setScaleY(1);
                break;
        }
        return super.onTouchEvent(event);
    }
}