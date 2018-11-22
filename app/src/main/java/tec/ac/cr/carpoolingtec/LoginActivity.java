package tec.ac.cr.carpoolingtec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private CallbackManager callbackManager = CallbackManager.Factory.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.facebookLogin();
        this.initializeControls();
    }

    private void initializeControls(){
        ImageView imgLogin = (ImageView)findViewById(R.id.linkedInButton);
        imgLogin.setOnClickListener(this);
        Button buttonLogout = (Button)findViewById(R.id.button2);
        buttonLogout.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LISessionManager.getInstance(getApplicationContext()).onActivityResult(this, requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

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

    private void linkedInLogin(){
        LISessionManager.getInstance(getApplicationContext()).init(this, buildScope(), new AuthListener() {
            @Override
            public void onAuthSuccess() {
                fetchLinkedInInfo();
            }

            @Override
            public void onAuthError(LIAuthError error) {
                TextView textView = findViewById(R.id.textView);
                textView.setText(R.string.error);
            }
        }, true);
    }

    private void linkedInLogout(){
        LISessionManager.getInstance(getApplicationContext()).clearSession();
    }

    private void fetchLinkedInInfo(){
        String url = "https://api.linkedin.com/v1/people/~:(id,first-name,last-name,public-profile-url,email-address,picture-urls::(original))";

        APIHelper apiHelper = APIHelper.getInstance(getApplicationContext());
        apiHelper.getRequest(this, url, new ApiListener() {
            @Override
            public void onApiSuccess(ApiResponse apiResponse) {
                try {
                    JSONObject jsonObject = apiResponse.getResponseDataAsJson();
                    String firstName = jsonObject.getString("firstName");
                    String lastName = jsonObject.getString("lastName");
                    String pictureURL = jsonObject.getString("pictureURL");
                    String emailAddress = jsonObject.getString("emailAddress");

                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("First Name" + firstName);
                    stringBuilder.append("\n\n");
                    stringBuilder.append("Last Name" + lastName);
                    stringBuilder.append("\n\n");
                    stringBuilder.append("Email" + emailAddress);

                    TextView textView = findViewById(R.id.textView);
                    textView.setText(stringBuilder);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onApiError(LIApiError liApiError) {
                TextView textView = findViewById(R.id.textView);
                textView.setText(R.string.error);
            }
        });
    }

    // Build the list of member permissions our LinkedIn session requires
    private static Scope buildScope() {
        return Scope.build(Scope.R_BASICPROFILE, Scope.W_SHARE, Scope.R_EMAILADDRESS);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linkedInButton:
                linkedInLogin();
                break;
            case R.id.button2:
                linkedInLogout();
                break;
        }
    }

    public void exit(View v){
        finish();
    }

}
