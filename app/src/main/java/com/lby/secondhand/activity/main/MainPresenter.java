package com.lby.secondhand.activity.main;

import com.lby.secondhand.dao.ShopApiDaoCode;
import com.lby.secondhand.dao.bean.Goods;
import com.lby.secondhand.method.ICallback;
import com.lby.secondhand.module.LBYApiProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;

    private ArrayList<Goods> goodsList;
    private ArrayList<Goods> allGoods;
    private ArrayList<Goods> mGoods;

    private boolean isInitialized = false;

    public MainPresenter(MainContract.View view) {
        mView = view;
        mView.setPresenter(this);
        goodsList = new ArrayList<>();
        allGoods = new ArrayList<>();
        mGoods = new ArrayList<>();
    }

    @Override
    public void start() {
        initialGoods();
        mView.initialRecycler(goodsList);
    }

    @Override
    public void initialGoods() {
        LBYApiProvider.getInstance().getAllGoods(new ICallback() {
            @Override
            public void onSuccess(Map result) {
                allGoods = (ArrayList<Goods>) result.get(ShopApiDaoCode.QUERY_ALL_GOODS);
                setData(allGoods);
                if (!isInitialized) {
                    isInitialized = true;
                } else {
                    mView.refreshRecycleView();
                    mView.stopRefresh();
                }
            }

            @Override
            public void onFailure(String errorCode) {
                mView.showToast(errorCode);
                mView.stopRefresh();
            }
        });
    }

    @Override
    public void loadPersonalGoods() {
        LBYApiProvider.getInstance().getGoodsByUserId(new ICallback() {
            @Override
            public void onSuccess(Map result) {
                mGoods = (ArrayList<Goods>) result.get(ShopApiDaoCode.QUERY_GOODS_BY_PERSONAL_ID);
                setData(mGoods);
                mView.refreshRecycleView();
                mView.stopRefresh();
            }

            @Override
            public void onFailure(String errorCode) {
                mView.showToast(errorCode);
                mView.stopRefresh();
            }
        });
    }

    @Override
    public void changeRecycleViewData(boolean isPageAll) {
        if (isPageAll){
            setData(allGoods);
        }else {
            setData(mGoods);
        }
        mView.refreshRecycleView();
    }

    private void setData(List<Goods> list) {
        goodsList.clear();
        goodsList.addAll(list);
    }
}
