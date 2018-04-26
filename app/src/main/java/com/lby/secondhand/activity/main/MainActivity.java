package com.lby.secondhand.activity.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lby.secondhand.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment fragment = (MainFragment) getSupportFragmentManager()
                .findFragmentById(R.id.main_contain);

        if (fragment == null) {
            fragment = MainFragment.newInstance();
            getSupportFragmentManager().beginTransaction().
                    add(R.id.main_contain, fragment).commit();
        }

        MainPresenter presenter = new MainPresenter(fragment);
    }
}
