package calc.rock.calculator;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by Administrator on 2/21/2017.
 */

public class InterstitialActivity extends AppCompatActivity {

    InterstitialAd interstitial = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        AdRequest adRequest = new AdRequest.Builder()
               .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        // Prepare the Interstitial Ad
        interstitial = new InterstitialAd(InterstitialActivity.this);

// Insert the Ad Unit ID
        interstitial.setAdUnitId(getString(R.string.admob_interstitial_id));

        interstitial.loadAd(adRequest);
// Prepare an Interstitial Ad Listener

        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {

                try {
                    displayInterstitial();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            public void onAdClosed() {
                Intent intent = new Intent(InterstitialActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Intent intent = new Intent(InterstitialActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
                super.onAdFailedToLoad(errorCode);
            }

            @Override
            public void onAdLeftApplication() {

            }

            @Override
            public void onAdOpened() {

            }
        });

}

    public void displayInterstitial() {
// If Ads are loaded, show Interstitial else show nothing.
        if (interstitial.isLoaded()) {
            try {
                interstitial.show();
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }
}
