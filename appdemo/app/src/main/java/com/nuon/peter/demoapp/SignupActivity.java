package com.nuon.peter.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.nuon.peter.demoapp.firebase.DatabaseUtils;
import com.nuon.peter.demoapp.firebase.entity.People;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by manithnuon on 6/19/17.
 */

public class SignupActivity extends AppCompatActivity {

    @BindView(R.id.bg_sign)
    ImageView view;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;

    private final static String TAG = SignupActivity.class.getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.right_in,R.anim.right_out);
        setContentView(R.layout.layout_signup_form);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Signup an account");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        ButterKnife.bind(this);
        Glide.with(this).load(getMovieListBackground().get(new Random().nextInt(getMovieListBackground().size()-1)))
                .bitmapTransform(new BlurTransformation(this))
                .dontAnimate()
                .into(view);



    }

    @OnClick(R.id.sign_btn) public void signUp(){
        registerWithEmailPassword(username.getText().toString(),password.getText().toString());
    }


    public DatabaseUtils instance() {
        return DatabaseUtils.getInstance();
    }

    private void registerWithEmailPassword(String email, String password){

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.w(TAG, "signInWithCredential" + " Success:::" + task.getResult().getUser());
                            instance().getPeopleRef().child(task.getResult().getUser().getUid())
                                    .setValue(new People(task.getResult().getUser().getEmail())).addOnCompleteListener(SignupActivity.this
                                    , new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            finish();
                                        }
                                    });



                        }
                        Log.w(TAG, "signInWithCredential" + " user:::" + "Failed user");
                    }
                });


        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

            }
        });


    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private List<Integer> getMovieListBackground(){
        List<Integer> movieback = new ArrayList<>();
        movieback.add(R.drawable.beauty_1);
        movieback.add(R.drawable.beauty_2);
        movieback.add(R.drawable.beauty_3);
        movieback.add(R.drawable.beauty_4);

        return movieback;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.right_in,R.anim.right_out);
    }
}
