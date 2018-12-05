package com.gmail.dina_elsaftawy.merchantorder.presenter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by dina_elsaftawy on 12/3/2018.
 */

public class LoginPresenter implements MainContract.MainPresenter {

    MainContract.MainView mainView;
    private FirebaseAuth auth;

    public LoginPresenter(MainContract.MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void validateUserData(String userName, String password, Activity loginActivity) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            mainView.showLoginError("please enter valid data");
            mainView.emptyFields();
        } else {
            auth = FirebaseAuth.getInstance();
            //authenticate UserProfile
            auth.signInWithEmailAndPassword(userName, password)
                    .addOnCompleteListener(loginActivity, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // If sign in fails, display a message to the UserProfile. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in UserProfile can be handled in the listener.
                            if (!task.isSuccessful()) {
                                // there was an error
                                mainView.showLoginError(task.getException().getMessage());
                            } else {
                                FirebaseUser user = auth.getCurrentUser();
                                mainView.makeLogin(user);
                            }
                        }
                    });
        }

    }
}


