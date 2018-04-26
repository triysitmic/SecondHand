package com.lby.secondhand.activity.shop_content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lby.secondhand.R;

public class ShopContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_content);

        ShopContentFragment fragment = (ShopContentFragment) getSupportFragmentManager().
                findFragmentById(R.id.shop_content_contain);
        if (fragment == null) {
            fragment = ShopContentFragment.newInstance();
            getSupportFragmentManager().beginTransaction().
                    add(R.id.shop_content_contain, fragment).commit();
        }

        ShopContentPresent present = new ShopContentPresent(fragment);
    }
}
