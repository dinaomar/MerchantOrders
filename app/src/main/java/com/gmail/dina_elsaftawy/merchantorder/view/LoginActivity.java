package com.gmail.dina_elsaftawy.merchantorder.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gmail.dina_elsaftawy.merchantorder.R;
import com.gmail.dina_elsaftawy.merchantorder.model.repository.RepositotyAuthentication;
import com.gmail.dina_elsaftawy.merchantorder.presenter.LoginPresenter;
import com.gmail.dina_elsaftawy.merchantorder.presenter.MainContract;
import com.google.firebase.auth.UserInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements MainContract.MainView {

    MainContract.MainPresenter mainPresenter;
    RepositotyAuthentication repositotyAuthentication = new RepositotyAuthentication();

    @BindView(R.id.userEmail)
    EditText userEmailEditText;
    @BindView(R.id.userName)
    EditText userNameEditText;
    @BindView(R.id.register_button)
    Button registerButton;
    @BindView(R.id.sign_in_button)
    Button signinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mainPresenter = new LoginPresenter(this, repositotyAuthentication);
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.validateUserData(userNameEditText.getText().toString(),
                        userEmailEditText.getText().toString(), LoginActivity.this);
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void updateMainView() {

    }

    @Override
    public void showLoginError(String errorMessage) {
        Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void emptyFields() {
        userNameEditText.setText("");
        userEmailEditText.setText("");
    }

    @Override
    public void gotoOrders(UserInfo user) {
        String userId = user.getUid();
        String userName = user.getEmail();
        Intent intent = new Intent(LoginActivity.this, UserOrdersActivity.class);
        intent.putExtra("userId", userId);
        intent.putExtra("userName", userName);
        startActivity(intent);
    }
}

