package com.lby.secondhand.activity.login;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.lby.secondhand.R;

public class LoginActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginFragment fragment =
                (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.login_contain);

        if (fragment == null) {
            fragment = LoginFragment.newInstance();
            getSupportFragmentManager().beginTransaction().
                    add(R.id.login_contain, fragment).commit();
        }
        LoginPresenter presenter = new LoginPresenter(fragment);
    }
}
