package com.lby.secondhand.activity.shop_content;


import com.lby.secondhand.method.BasePresenter;
import com.lby.secondhand.method.BaseView;

public class ShopContentContract {

    interface View extends BaseView<Presenter> {
        @Override
        void setPresenter(Presenter presenter);
    }

    interface Presenter extends BasePresenter {
        @Override
        void start();
    }
}
