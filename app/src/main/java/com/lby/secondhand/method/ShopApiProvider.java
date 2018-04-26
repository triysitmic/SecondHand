package com.lby.secondhand.method;


import com.lby.secondhand.dao.bean.DaoSession;
import com.lby.secondhand.dao.bean.Goods;
import com.lby.secondhand.dao.bean.GoodsType;
import com.lby.secondhand.dao.bean.User;

public interface ShopApiProvider {
    void insertNewUser(DaoSession session, User user, ICallback callback);

    void queryUserByName(DaoSession session, String name, ICallback callback);

    void insertNewGoodsType(DaoSession session, GoodsType type, ICallback callback);

    void queryAllGoodsType(DaoSession session, ICallback callback);

    void queryGoodsTypeById(DaoSession session, long id, ICallback callback);

    void deleteGoodsTypeById(DaoSession session, long id, ICallback callback);

    void insertNewGoods(DaoSession session, Goods goods, ICallback callback);

    void queryAllGoods(DaoSession session, ICallback callback);

    void queryGoodsById(DaoSession session, long id, ICallback callback);

    void queryGoodsByPersonalId(DaoSession session, long id, ICallback callback);

    void deleteGoodsById(DaoSession session, long id, ICallback callback);
}
