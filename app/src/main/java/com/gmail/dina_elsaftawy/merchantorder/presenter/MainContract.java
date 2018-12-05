package com.gmail.dina_elsaftawy.merchantorder.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.gmail.dina_elsaftawy.merchantorder.model.data.order;
import com.gmail.dina_elsaftawy.merchantorder.view.RegistrationActivity;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/**
 * Created by dina_elsaftawy on 12/3/2018.
 */

public interface MainContract {

    interface MainView {
        void updateMainView();

        void showLoginError(String errorMessage);

        void emptyFields();

//        void startRegistrationActivity(Intent intent);

        void makeLogin(FirebaseUser user);
    }

    interface MainPresenter {
        void validateUserData(String userName, String password,Activity activity);
    }

    interface ListOrdersPresenter {
        void getUserOrders();
    }

    interface ListOrdersView {
        void setListOfOrders(ArrayList<order> listOfOrders);
    }

    interface RegirationPresenter {
        void validateAndSendRegistration(String userName, String password,Activity activity);
    }

    interface RegistrationView {
        void updateViewAfterRegistration();

        void showRegistrationMessage(String Message);
    }
}
