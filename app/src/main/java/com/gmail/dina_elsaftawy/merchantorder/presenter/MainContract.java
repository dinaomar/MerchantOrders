package com.gmail.dina_elsaftawy.merchantorder.presenter;

import android.app.Activity;
import android.content.Intent;

import com.gmail.dina_elsaftawy.merchantorder.view.RegistrationActivity;

/**
 * Created by dina_elsaftawy on 12/3/2018.
 */

public interface MainContract {

    interface MainView {
        void updateMainView();

        void showLoginError(String errorMessage);

        void emptyFields();

//        void startRegistrationActivity(Intent intent);

        void makeLogin();
    }

    interface MainPresenter {
        void validateUserData(String userName, String email);

//        void startRegisterationActivity(Activity currentActivity, Class<RegistrationActivity> registerActivity);

        void callFireBase();

    }
}
