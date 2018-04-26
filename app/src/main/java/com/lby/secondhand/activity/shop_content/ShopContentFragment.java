package com.lby.secondhand.activity.shop_content;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lby.secondhand.R;

import butterknife.ButterKnife;


public class ShopContentFragment extends Fragment implements ShopContentContract.View {

    private ShopContentContract.Presenter mPresenter;

    public static ShopContentFragment newInstance() {
        Bundle args = new Bundle();
        ShopContentFragment fragment = new ShopContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_shop_content, container);

        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void setPresenter(ShopContentContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
