package com.lby.secondhand.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lby.secondhand.R;
import com.lby.secondhand.activity.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends Fragment implements LoginContract.View {

    private static final String TAG = "LoginFragment";

    @BindView(R.id.et_account)
    EditText mAccountEt;
    @BindView(R.id.et_password)
    EditText mPasswordEt;


    @BindView(R.id.img_title)
    ImageView titleImage;

    @BindView(R.id.img_loading)
    ImageView loadingImage;

    private LoginContract.Presenter mPresenter;
    private int count = 0;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoadView() {
        Glide.with(getContext()).load(R.drawable.fuck_loading).into(loadingImage);
        loadingImage.setVisibility(View.VISIBLE);
    }

    @Override
    public void disappearLoadView() {
        loadingImage.setVisibility(View.GONE);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    @Override
    public void finishToMain() {
        finish();
        startActivity(new Intent(getContext(), MainActivity.class));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, v);
        initialView();
        return v;
    }

    private void initialView() {
        Glide.with(getContext()).load(R.drawable.fuck).into(titleImage);
        titleImage.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @OnClick(R.id.v_top)
    public void v233() {
        if (titleImage.getVisibility() == View.VISIBLE) {
            return;
        }
        count++;
        if (count >= 7) {
            titleImage.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.tv_login)
    public void onLogin() {
//        String name = mAccountEt.getText().toString();
//        String password = mPasswordEt.getText().toString();
//        if (name.equals("") || password.equals("")) {
//            return;
//        }
        String name = "user1";
        String password = "123456";
        mPresenter.login(name, password);
    }

    @OnClick(R.id.tv_register)
    public void onRegister() {
        String name = mAccountEt.getText().toString();
        String password = mPasswordEt.getText().toString();
        if (name.equals("") || password.equals("")) {
            return;
        }
        mPresenter.register(name, password);
    }
}
