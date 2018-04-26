package com.lby.secondhand.activity.main;


import com.lby.secondhand.dao.bean.Goods;
import com.lby.secondhand.method.BasePresenter;
import com.lby.secondhand.method.BaseView;

import java.util.List;

public class MainContract {

    interface View extends BaseView<Presenter> {
        @Override
        void setPresenter(Presenter presenter);

        void refreshRecycleView();

        void showToast(String msg);

        void initialRecycler(List<Goods> goodsList);

        void stopRefresh();
    }

    interface Presenter extends BasePresenter {
        @Override
        void start();

        void initialGoods();

        void loadPersonalGoods();

        void changeRecycleViewData(boolean isPageAll);
    }
}
