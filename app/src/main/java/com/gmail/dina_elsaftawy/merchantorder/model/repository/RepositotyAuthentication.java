package com.gmail.dina_elsaftawy.merchantorder.model.repository;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;

public class RepositotyAuthentication {

    private FirebaseAuth auth;

    public RepositotyAuthentication() {
        auth = FirebaseAuth.getInstance();
    }

    public Task<AuthResult> authenticate(String username, String password) {
        return auth.signInWithEmailAndPassword(username, password);
    }

    public UserInfo getUser(){
        return auth.getCurrentUser();
    }

}
