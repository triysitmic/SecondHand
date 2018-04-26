package com.lby.secondhand.activity.login;


import com.lby.secondhand.method.BasePresenter;
import com.lby.secondhand.method.BaseView;

public class LoginContract {

    interface View extends BaseView<Presenter> {
        @Override
        void setPresenter(Presenter presenter);

        void showLoadView();

        void disappearLoadView();

        void showToast(String msg);

        void finish();

        void finishToMain();
    }

    interface Presenter extends BasePresenter {
        @Override
        void start();

        void login(String name, String password);

        void register(String name, String password);
    }
}
