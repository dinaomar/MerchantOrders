package com.gmail.dina_elsaftawy.merchantorder.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gmail.dina_elsaftawy.merchantorder.R;
import com.gmail.dina_elsaftawy.merchantorder.presenter.MainContract;
import com.gmail.dina_elsaftawy.merchantorder.presenter.RegistrationPresenter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RegistrationActivity extends AppCompatActivity implements MainContract.RegistrationView {

    private FirebaseAuth auth;
    private MainContract.RegirationPresenter registrationPresenter;
    @BindView(R.id.registerNewUserButton)
    Button registerButton;
    @BindView(R.id.userNameEdit)
    EditText userNameEditText;
    @BindView(R.id.userPasswordEdit)
    EditText userPasswordText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ButterKnife.bind(this);

        // Initialize FirebaseAuth
        FirebaseApp.initializeApp(this);

        registrationPresenter = new RegistrationPresenter(this, RegistrationActivity.this);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrationPresenter.validateAndSendRegistration(userNameEditText.getText().toString(),
                        userPasswordText.getText().toString(), RegistrationActivity.this);
            }
        });
    }

    @Override
    public void updateViewAfterRegistration() {
        Intent intent = new Intent(RegistrationActivity.this, UserOrdersActivity.class);
        startActivity(intent);
    }

    @Override
    public void showRegistrationMessage(String Message) {
        Toast.makeText(RegistrationActivity.this, Message, Toast.LENGTH_LONG).show();
    }
}
