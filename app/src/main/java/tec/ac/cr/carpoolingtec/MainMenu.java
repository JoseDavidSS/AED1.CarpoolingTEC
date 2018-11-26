package tec.ac.cr.carpoolingtec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.linkedin.platform.APIHelper;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.errors.LIApiError;
import com.linkedin.platform.errors.LIAuthError;
import com.linkedin.platform.listeners.ApiListener;
import com.linkedin.platform.listeners.ApiResponse;
import com.linkedin.platform.listeners.AuthListener;
import com.linkedin.platform.utils.Scope;
import org.json.JSONException;
import org.json.JSONObject;
import tec.ac.cr.carpoolingtec.Data.Driver;
import tec.ac.cr.carpoolingtec.Data.Rider;

public class MainMenu extends AppCompatActivity {

    private CallbackManager callbackManager = CallbackManager.Factory.create();
    public static Driver driver = new Driver();
    public static Rider rider = new Rider();
    public static boolean ractive = true;
    public static boolean dactive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        this.facebookLogin();
    }

    /**
     * Changes activity to Rider from button
     * @param v View
     */
    public void goToRiderView(View v){
        if(ractive){
            Intent intent = new Intent(this, RiderView.class);
            startActivity(intent);
        }
    }

    /**
     * Changes activity to Rider internally
     */
    public void goToRiderViewInternal(){
        Intent intent = new Intent(this, RiderView.class);
        startActivity(intent);
    }

    /**
     * Changes activity to Driver internally
     * @param v View
     */
    public void goToDriverView(View v){
        if(dactive){
            Intent intent = new Intent(this, DriverView.class);
            startActivity(intent);
        }
    }

    /**
     * Changes activity to Driver internally
     */
    public void goToDriverViewInternal(){
        Intent intent = new Intent(this, DriverView.class);
        startActivity(intent);
    }


    /**
     * Changes activity to Barcode reader
     */
    public void goToBarcode(){
        Intent intent = new Intent(this, BarCodeReader.class);
        startActivity(intent);
    }

    /**
     * Manages Facebook login authentication
     */
    private void facebookLogin(){
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                TextView textView = findViewById(R.id.textView);
                textView.setText(R.string.success);
                goToBarcode();
            }

            @Override
            public void onCancel() {
                TextView textView = findViewById(R.id.textView);
                textView.setText(R.string.failure);
            }

            @Override
            public void onError(FacebookException exception) {
                TextView textView = findViewById(R.id.textView);
                textView.setText(R.string.error);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LISessionManager.getInstance(getApplicationContext()).onActivityResult(this, requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Manages LinkedIn login authentication
     */
    private void linkedInLogin(){
        LISessionManager.getInstance(getApplicationContext()).init(this, buildScope(), new AuthListener() {
            @Override
            public void onAuthSuccess() {
                dactive = true;
                goToDriverViewInternal();
            }

            @Override
            public void onAuthError(LIAuthError error) {
                TextView textView = findViewById(R.id.textView);
                textView.setText(R.string.error);
            }
        }, true);
    }

    private static Scope buildScope() {
        return Scope.build(Scope.R_BASICPROFILE, Scope.W_SHARE, Scope.R_EMAILADDRESS);
    }

    public void onLinkedInClick(View v) {
        linkedInLogin();
    }
}
