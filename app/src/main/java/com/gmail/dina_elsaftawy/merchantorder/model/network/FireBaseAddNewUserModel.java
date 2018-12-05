package com.gmail.dina_elsaftawy.merchantorder.model.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.gmail.dina_elsaftawy.merchantorder.presenter.MainContract;
import com.gmail.dina_elsaftawy.merchantorder.presenter.RegistrationPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.support.constraint.Constraints.TAG;

public class FireBaseAddNewUserModel {
    Context context;
    private FirebaseAuth mFirebaseAuth;

    public FireBaseAddNewUserModel(Context context) {
        this.context = context;
        // Initialize FirebaseAuth
        mFirebaseAuth = FirebaseAuth.getInstance();
    }


}




