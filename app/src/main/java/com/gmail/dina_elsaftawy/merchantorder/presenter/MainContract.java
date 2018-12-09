package com.gmail.dina_elsaftawy.merchantorder.presenter;

import android.app.Activity;

import com.gmail.dina_elsaftawy.merchantorder.model.entity.Order;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

/**
 * Created by dina_elsaftawy on 12/3/2018.
 */

public interface MainContract {

    interface MainView {
        void updateMainView();

        void showLoginError(String errorMessage);

        void emptyFields();

//        void startRegistrationActivity(Intent intent);

        void gotoOrders(UserInfo user);
    }

    interface MainPresenter {
        void validateUserData(String userName, String password,Activity activity);
    }

    interface ListOrdersPresenter {
        void getUserOrders();
    }

    interface ListOrdersView {
        void setOrder(Order order);
    }

    interface RegirationPresenter {
        void validateAndSendRegistration(String userName, String password,Activity activity);
    }

    interface RegistrationView {
        void updateViewAfterRegistration(String email,String userId);

        void showRegistrationMessage(String Message);
    }

    interface AddOrder{
        void addNewOrder(String userId,String orderTitle,String orderDesc);
    }
}
