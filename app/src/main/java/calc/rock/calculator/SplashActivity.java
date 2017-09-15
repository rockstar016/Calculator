package calc.rock.calculator;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import java.util.Locale;

import calc.rock.calculator.HomeActivity;
import calc.rock.calculator.R;



public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Locale current = getResources().getConfiguration().locale;
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        setContentView(R.layout.activity_splash);
        LinearLayout splashImage = (LinearLayout) findViewById(R.id.splashImage);


        if(current.toString().equals("ja_JP")){
            splashImage.setBackgroundResource(R.drawable.calc_theme_1);
        }else
        {
            splashImage.setBackgroundResource(R.drawable.calc_theme_2);
        }

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, InterstitialActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000 / 1);
    }
}
