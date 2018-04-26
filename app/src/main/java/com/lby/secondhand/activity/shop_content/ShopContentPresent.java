package com.lby.secondhand.activity.shop_content;


public class ShopContentPresent implements ShopContentContract.Presenter {

    private ShopContentContract.View mView;

    public ShopContentPresent(ShopContentContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
