package com.gmail.dina_elsaftawy.merchantorder.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationPresenter implements MainContract.RegirationPresenter {

    MainContract.RegistrationView registrationView;
    private FirebaseAuth mFirebaseAuth;


    public RegistrationPresenter(MainContract.RegistrationView registrationView, Context context) {
        this.registrationView = registrationView;
        FirebaseApp.initializeApp(context);
        mFirebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void validateAndSendRegistration(String userName, String password,Activity activity) {
        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)) {
            mFirebaseAuth.createUserWithEmailAndPassword(userName, password)
                    .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                registrationView.showRegistrationMessage(task.getException().getMessage());
                            } else {
                                registrationView.updateViewAfterRegistration();
                            }
                        }
                    });

        } else registrationView.showRegistrationMessage("Please enter valid Data");
    }
}

