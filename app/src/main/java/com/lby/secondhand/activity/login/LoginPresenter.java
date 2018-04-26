package com.lby.secondhand.activity.login;


import com.lby.secondhand.dao.ShopApiDaoCode;
import com.lby.secondhand.dao.bean.User;
import com.lby.secondhand.method.ICallback;
import com.lby.secondhand.module.LBYApiProvider;

import java.util.Map;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;

    public LoginPresenter(LoginContract.View mView) {
        this.mView = mView;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void login(String name, final String password) {
        mView.showLoadView();
        LBYApiProvider.getInstance().login(name, new ICallback() {
            @Override
            public void onSuccess(Map result) {
                User user = (User) result.get(ShopApiDaoCode.QUERY_USER_BY_NAME);
                if (user.getPassword().equals(password)) {
                    LBYApiProvider.getInstance().setUser(user);
                    mView.showToast("登录成功");
                    mView.disappearLoadView();
                    mView.finishToMain();
                } else {
                    mView.showToast("密码错误");
                    mView.disappearLoadView();
                }
            }

            @Override
            public void onFailure(String errorCode) {
                mView.showToast(errorCode);
                mView.disappearLoadView();
            }
        });
    }

    @Override
    public void register(final String name, final String password) {
        mView.showLoadView();
        LBYApiProvider.getInstance().login(name, new ICallback() {
            @Override
            public void onSuccess(Map result) {
                mView.disappearLoadView();
                mView.showToast("用户名已存在");
            }

            @Override
            public void onFailure(String errorCode) {
                final User user = new User(null, name, password);
                LBYApiProvider.getInstance().register(user, new ICallback() {
                    @Override
                    public void onSuccess(Map result) {
                        Long id = (Long) result.get(ShopApiDaoCode.NEW_USER_ID);
                        user.setId(id);
                        LBYApiProvider.getInstance().setUser(user);
                        mView.showToast("创建成功 userId :" + id);
                        mView.disappearLoadView();
                        mView.finishToMain();
                    }

                    @Override
                    public void onFailure(String errorCode) {
                        mView.showToast("创建失败");
                        mView.disappearLoadView();
                    }
                });
            }
        });
    }
}
