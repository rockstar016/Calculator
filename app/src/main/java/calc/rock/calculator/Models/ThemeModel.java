package calc.rock.calculator.Models;

/**
 * Created by rock on 2/14/17.
 */

public class ThemeModel {
    int ThemeName;
    int ThemeImage;
    boolean current_theme;

    public boolean isCurrent_theme() {
        return current_theme;
    }

    public void setCurrent_theme(boolean current_theme) {
        this.current_theme = current_theme;
    }

    public int getThemeName() {
        return ThemeName;
    }

    public void setThemeName(int themeName) {
        ThemeName = themeName;
    }

    public int getThemeImage() {
        return ThemeImage;
    }

    public void setThemeImage(int themeImage) {
        ThemeImage = themeImage;
    }
}
