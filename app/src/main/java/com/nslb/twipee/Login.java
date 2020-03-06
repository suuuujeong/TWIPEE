package com.nslb.twipee;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import org.jetbrains.annotations.Nullable;

public class Login extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    public GoogleApiClient mGoogleApiClient = null;
    private static final int RC_SIGN_IN = 9001;
    private SignInButton sign_in_button;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        context = this;

        sign_in_button  = (SignInButton)findViewById(R.id.sign_in_button);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE))
                .build();

//        if (mGoogleApiClient != null){
//            Intent intent2 = new Intent(this,MainActivity.class);
//            startActivity(intent2);
//            finish();
//        }

        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoogleApiClient.connect();
            }
        });
    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {

        Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
        String accountName = Plus.AccountApi.getAccountName(mGoogleApiClient);
        Account account = new Account(accountName, GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            mGoogleApiClient.connect();
            if (mGoogleApiClient.isConnecting()) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@Nullable ConnectionResult connectionResult) {

        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this, RC_SIGN_IN);

            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {

        }
    }
}
