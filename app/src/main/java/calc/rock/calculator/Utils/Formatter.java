package calc.rock.calculator.Utils;

import android.graphics.Color;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Formatter {
    public static String doubleToString(double d) {
        final DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator(',');

        final DecimalFormat formatter = new DecimalFormat();
        formatter.setMaximumFractionDigits(12);
        formatter.setDecimalFormatSymbols(symbols);
        formatter.setGroupingUsed(true);
        return formatter.format(d);
    }

    public static Double stringToDouble(String str) {
        str = str.replaceAll(",", "");
        return Double.parseDouble(str);
    }

    public static int manipulateColor(int color, float factor) {
        int a = Color.alpha(color);
        int r = Math.round(Color.red(color) * factor);
        int g = Math.round(Color.green(color) * factor);
        int b = Math.round(Color.blue(color) * factor);
        return Color.argb(a,
                Math.min(r,255),
                Math.min(g,255),
                Math.min(b,255));
    }
}
