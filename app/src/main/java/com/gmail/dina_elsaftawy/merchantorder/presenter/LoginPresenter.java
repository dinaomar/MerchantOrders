package com.gmail.dina_elsaftawy.merchantorder.presenter;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.gmail.dina_elsaftawy.merchantorder.view.RegistrationActivity;

/**
 * Created by dina_elsaftawy on 12/3/2018.
 */

public class LoginPresenter implements MainContract.MainPresenter {

    MainContract.MainView mainView;

    public LoginPresenter(MainContract.MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void validateUserData(String userName, String email) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(email)) {
            mainView.showLoginError("please enter valid data");
            mainView.emptyFields();
        }
    }

//    @Override
//    public void startRegisterationActivity(Activity currentActivity, Class<RegistrationActivity> registerActivity) {
//        Intent intent = new Intent(currentActivity.getApplicationContext(),registerActivity.getClass());
//        mainView.startRegistrationActivity(intent);
//    }


    @Override
    public void callFireBase() {

    }
}
