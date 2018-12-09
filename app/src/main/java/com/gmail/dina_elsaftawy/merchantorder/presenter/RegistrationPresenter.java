package com.gmail.dina_elsaftawy.merchantorder.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.gmail.dina_elsaftawy.merchantorder.model.entity.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationPresenter implements MainContract.RegirationPresenter {

    MainContract.RegistrationView registrationView;
    private FirebaseAuth mFirebaseAuth;


    public RegistrationPresenter(MainContract.RegistrationView registrationView, Context context) {
        this.registrationView = registrationView;
        FirebaseApp.initializeApp(context);
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void validateAndSendRegistration(final String userName, final String password, Activity activity) {
        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)) {
            mFirebaseAuth.createUserWithEmailAndPassword(userName, password)
                    .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                registrationView.showRegistrationMessage(task.getException().getMessage());
                            } else {
                                DatabaseReference mDatabase;
                                mDatabase = FirebaseDatabase.getInstance().getReference();
                                User user = new User(userName, password);
                                mDatabase.child("users").child(mFirebaseAuth.getUid()).setValue(user);
                                registrationView.updateViewAfterRegistration(userName, mFirebaseAuth.getUid());
                            }
                        }
                    });

        } else registrationView.showRegistrationMessage("Please enter valid Data");
    }
}

