package com.nuon.peter.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.nuon.peter.demoapp.app.App;
import com.nuon.peter.demoapp.firebase.DatabaseUtils;
import com.nuon.peter.demoapp.firebase.entity.People;
import com.nuon.peter.demoapp.rest.RestActivityWithFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by manithnuon on 6/19/17.
 */

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.big_bg)
    ImageView imageView;
    @BindView(R.id.facebook)
    ImageView facebook;
    @BindView(R.id.username)
    EditText email;
    @BindView(R.id.password)
    EditText password;

    private final static String TAG = LoginActivity.class.getSimpleName();
    private final static String USER = "User::::" ;
    public static final int GOOGLE_SIGN_IN_RC = 9001;
    public GoogleApiClient mGoogleApiClient;
    public CallbackManager mCallbackManager;
    private FirebaseAuth mAuth;
    //facebook button and managers
    private LoginButton mFacebookButton;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login_form);
        ButterKnife.bind(this);
        Glide.with(this).load(getMovieListBackground().get(new Random().nextInt(getMovieListBackground().size()-1)))
                .bitmapTransform(new BlurTransformation(this))
                .dontAnimate()
                .into(imageView);

        // [START config_signin]
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        Log.d(TAG, "gso requestIdToken ="+ getString(R.string.default_web_client_id));
        // [END config_signin]

        // [START google Clint]
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener(){
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(LoginActivity.this, "Failed google client",
                                Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "mGoogleApiClient onConnectionFailed");
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        // [END google Clint]

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        Log.d(TAG, "gso requestIdToken ="+ getString(R.string.default_web_client_id));


        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        mFacebookButton = (LoginButton) findViewById(R.id.facebook_btn);
        mFacebookButton.setReadPermissions("email", "public_profile");
        mFacebookButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        // Application code
                        try {
                            Log.i("Response",response.toString());

                            String email = response.getJSONObject().getString("email");
                            String ids = response.getJSONObject().getString("id");
                            String name = response.getJSONObject().getString("name");
                            String gender = response.getJSONObject().getString("gender");

                            Profile profile = Profile.getCurrentProfile();
                            String id = profile.getId();
                            String link = profile.getLinkUri().toString();
                            Log.i("Link",link);
                            if (Profile.getCurrentProfile()!=null)
                            {
                                Log.i("LoginMe::::", "ProfilePic" + Profile.getCurrentProfile().getProfilePictureUri(200, 200));
                            }

                            handleFacebookAccessToken(loginResult.getAccessToken(),name,Profile.getCurrentProfile().getProfilePictureUri(200, 200).toString());

                            Log.i("LoginMe::::" + "Email", email);
                            Log.i("LoginMe::::"+ "Ids", ids);
                            Log.i("LoginMe::::" + "name", name);
                            Log.i("LoginMe::::" + "Gender", gender);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday,picture.type(large)");
                request.setParameters(parameters);
                request.executeAsync();
                //request.executeAndWait();
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);

                Toast.makeText(LoginActivity.this, "Fail facebook",
                        Toast.LENGTH_SHORT).show();
            }
        });


        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFacebookButton.performClick();
            }
        });


        //initialize the AuthStateListener method
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(USER, "onAuthStateChanged:signed_in:" + user.getUid());
                    Log.d(USER, "onAuthStateChanged:signed_in_getDisplayName:" + user.getDisplayName());
                    Log.d(USER, "onAuthStateChanged:signed_in_getEmail():" + user.getEmail());
                    Log.d(USER, "onAuthStateChanged:signed_in_getPhotoUrl():" + user.getPhotoUrl());
                    Log.d(USER, "onAuthStateChanged:signed_in_emailVerified?:" + user.isEmailVerified());
                    Log.d(USER, "onAuthStateChanged:signed_in_Provider" + user.getProviders());
                    Intent mIntent = new Intent(LoginActivity.this, RestActivityWithFragment.class);
                    startActivity(mIntent);
                    finish();

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    @OnClick(R.id.signup) public void goToSignup() {
        Intent intent = new Intent(this,SignupActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.signin_btn) public void signIn() {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Intent intent = new Intent(App.getContext(),RestActivityWithFragment.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }

    @OnClick(R.id.google) public void signupWithGoogle() {
        googleSignIn();
    }

    @OnClick(R.id.anonym) public void anonymousSignin() {
        signAnonymous();
    }

    private void signAnonymous(){
        FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInAnonymously:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInAnonymously:failure", task.getException());
                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private List<Integer> getMovieListBackground(){
        List<Integer> movieback = new ArrayList<>();
        movieback.add(R.drawable.beauty_1);
        movieback.add(R.drawable.beauty_2);
        movieback.add(R.drawable.beauty_3);
        movieback.add(R.drawable.beauty_4);

        return movieback;
    }

    // Google sing in methods
    // Intent to open activity enables user to select one of his google account
    private void googleSignIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN_RC);
        Log.d(TAG, "signInIntent Activity started");
    }

    // Activity result after user selects the account he wants to use
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)  {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "requestCode ="+ requestCode);

        switch (requestCode){

            case GOOGLE_SIGN_IN_RC:
                // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                if (result.isSuccess()) {
                    // Google Sign In was successful, authenticate with Firebase
                    GoogleSignInAccount account = result.getSignInAccount();
                    firebaseAuthWithGoogle(account);
                    Log.d(TAG, "Google Sign In was successful lets Auth");
                } else {
                    // Google Sign In failed, update UI appropriately
                    Toast.makeText(LoginActivity.this,"Failed to signin",
                            Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Google Sign In failed");
                }
                break;

            case 64206: //facebook
                Log.d(TAG, "Facebook requestCode= " + requestCode);
                // Pass the activity result back to the Facebook SDK
                mCallbackManager.onActivityResult(requestCode, resultCode, data);
                break;
            case 140:  //twitter
                // Pass the activity result to the Twitter login button.
                //mTwitter.onActivityResult(requestCode, resultCode, data);
                break;
            default: //do twitter again just in case
                // Pass the activity result to the Twitter login button.
                break;
        }
    }

    //After Successfully login we need to authenticate the user with firebase to trigger the listener
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(LoginActivity.this, "Fail login",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            DatabaseUtils.getInstance().getPeopleRef().child(task.getResult().getUser().getUid()).setValue(new People(task.getResult().getUser().getEmail()
                            ,task.getResult().getUser().getPhotoUrl().toString(),task.getResult().getUser().getDisplayName())).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(LoginActivity.this, "Finished login",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    }
                });
    }

    private void handleFacebookAccessToken(AccessToken token, final String name, final String url) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(LoginActivity.this, "Failed facebook",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            Log.w(TAG, "signInWithCredential" + " user:::" + user);
                            DatabaseUtils.getInstance().getPeopleRef().child(task.getResult().getUser().getUid()).setValue(new People(task.getResult().getUser().getEmail()
                                    ,url,name)).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    /*Toast.makeText(LoginActivity.this, "Finished login",
                                            Toast.LENGTH_SHORT).show();*/
                                    Intent intent = new Intent(App.getContext(), RestActivityWithFragment.class);
                                    startActivity(intent);
                                }
                            });

                        }

                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        Log.d(TAG, "LoginActivity onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "LoginActivity onStop");
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
