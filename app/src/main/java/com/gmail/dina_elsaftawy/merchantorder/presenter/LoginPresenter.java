package com.gmail.dina_elsaftawy.merchantorder.presenter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.gmail.dina_elsaftawy.merchantorder.model.repository.RepositotyAuthentication;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

/**
 * Created by dina_elsaftawy on 12/3/2018.
 */

public class LoginPresenter implements MainContract.MainPresenter {

    private MainContract.MainView mainView;
    private RepositotyAuthentication repositotyAuthentication;

    public LoginPresenter(MainContract.MainView mainView, RepositotyAuthentication repositotyAuthentication) {
        this.mainView = mainView;
        this.repositotyAuthentication = repositotyAuthentication;
    }

    @Override
    public void validateUserData(String userName, String password, Activity loginActivity) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            mainView.showLoginError("please enter valid data");
            mainView.emptyFields();
        } else {
            repositotyAuthentication.authenticate(userName, password)
                    .addOnCompleteListener(loginActivity, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                mainView.gotoOrders(repositotyAuthentication.getUser());
                            } else {
                                mainView.showLoginError(task.getException().getMessage());
                            }
                        }
                    });
        }

    }
}


