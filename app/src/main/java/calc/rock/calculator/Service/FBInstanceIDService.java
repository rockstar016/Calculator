package calc.rock.calculator.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.google.firebase.iid.FirebaseInstanceIdService;

public class FBInstanceIDService extends FirebaseInstanceIdService {
    public FBInstanceIDService() {
    }

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
    }

}
