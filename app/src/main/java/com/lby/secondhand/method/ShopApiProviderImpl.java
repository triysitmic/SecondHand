package com.lby.secondhand.method;

import com.lby.secondhand.dao.ShopApiDaoCode;
import com.lby.secondhand.dao.bean.DaoSession;
import com.lby.secondhand.dao.bean.Goods;
import com.lby.secondhand.dao.bean.GoodsDao;
import com.lby.secondhand.dao.bean.GoodsType;
import com.lby.secondhand.dao.bean.GoodsTypeDao;
import com.lby.secondhand.dao.bean.User;
import com.lby.secondhand.dao.bean.UserDao;
import com.lby.secondhand.utils.ListUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopApiProviderImpl implements ShopApiProvider {

    private Map result = null;

    private void resetResult() {
        if (result != null) {
            result = null;
        }
        result = new HashMap();
    }

    @Override
    public void insertNewUser(DaoSession session, User user, ICallback callback) {
        long id = session.getUserDao().insertOrReplace(user);
        resetResult();
        result.put(ShopApiDaoCode.NEW_USER_ID, id);
        callback.onSuccess(result);
    }

    @Override
    public void queryUserByName(DaoSession session, String name, ICallback callback) {
        List<User> users = session.getUserDao().queryBuilder().
                where(UserDao.Properties.Name.eq(name)).list();
        if (!ListUtils.isListNull(users)) {
            resetResult();
            result.put(ShopApiDaoCode.QUERY_USER_BY_NAME, users.get(0));
            callback.onSuccess(result);
        } else {
            callback.onFailure(ShopApiDaoCode.QUERY_USER_BY_NAME_FAILURE);
        }
    }

    @Override
    public void insertNewGoodsType(DaoSession session, GoodsType type, ICallback callback) {
        long id = session.getGoodsTypeDao().insertOrReplace(type);
        resetResult();
        result.put(ShopApiDaoCode.INSERT_NEW_GOODS_TYPE, id);
        callback.onSuccess(result);
    }

    @Override
    public void queryAllGoodsType(DaoSession session, ICallback callback) {
        List<GoodsType> types = session.getGoodsTypeDao().queryBuilder().list();
        if (!ListUtils.isListNull(types)) {
            resetResult();
            result.put(ShopApiDaoCode.QUERY_ALL_GOODS_TYPE, types);
            callback.onSuccess(result);
        } else {
            callback.onFailure(ShopApiDaoCode.GOODS_TYPE_NULL);
        }
    }

    @Override
    public void queryGoodsTypeById(DaoSession session, long id, ICallback callback) {
        List<GoodsType> types = session.getGoodsTypeDao().queryBuilder().
                where(GoodsTypeDao.Properties.Id.eq(id)).list();
        if (!ListUtils.isListNull(types)) {
            resetResult();
            result.put(ShopApiDaoCode.QUERY_GOODS_TYPE_BY_ID, types.get(0));
            callback.onSuccess(result);
        } else {
            callback.onFailure(ShopApiDaoCode.NO_SUCH_GOODS_TYPE);
        }
    }

    @Override
    public void deleteGoodsTypeById(DaoSession session, long id, ICallback callback) {
        session.getGoodsTypeDao().deleteByKey(id);
        List<GoodsType> types = session.getGoodsTypeDao().queryBuilder().
                where(GoodsTypeDao.Properties.Id.eq(id)).list();
        if (!ListUtils.isListNull(types)) {
            resetResult();
            result.put(ShopApiDaoCode.DELETE_GOODS_TYPE_SUCCESS, true);
            callback.onSuccess(result);
        } else {
            callback.onFailure(ShopApiDaoCode.DELETE_GOODS_TYPE_SUCCESS_FAILURE);
        }
    }

    @Override
    public void insertNewGoods(DaoSession session, Goods goods, ICallback callback) {
        long id = session.getGoodsDao().insertOrReplace(goods);
        resetResult();
        result.put(ShopApiDaoCode.QUERY_NEW_GOODS, id);
        callback.onSuccess(result);
    }

    @Override
    public void queryAllGoods(DaoSession session, ICallback callback) {
        List<Goods> goodsList = session.getGoodsDao().loadAll();
        if (!ListUtils.isListNull(goodsList)) {
            resetResult();
            result.put(ShopApiDaoCode.QUERY_ALL_GOODS, goodsList);
            callback.onSuccess(result);
        } else {
            callback.onFailure(ShopApiDaoCode.QUERY_ALL_GOODS_FAILURE);
        }
    }


    @Override
    public void queryGoodsById(DaoSession session, long id, ICallback callback) {
        List<Goods> goods = session.getGoodsDao().queryBuilder().
                where(GoodsDao.Properties.Id.eq(id)).list();
        if (!ListUtils.isListNull(goods)) {
            resetResult();
            result.put(ShopApiDaoCode.QUERY_GOODS_BY_ID, goods.get(0));
            callback.onSuccess(result);
        } else {
            callback.onFailure(ShopApiDaoCode.QUERY_GOODS_BY_ID_FAILURE);
        }
    }

    @Override
    public void queryGoodsByPersonalId(DaoSession session, long id, ICallback callback) {
        List<Goods> goods = session.getGoodsDao().queryBuilder().
                where(GoodsDao.Properties.User_id.eq(id)).list();
        if (!ListUtils.isListNull(goods)) {
            resetResult();
            result.put(ShopApiDaoCode.QUERY_GOODS_BY_PERSONAL_ID, goods);
            callback.onSuccess(result);
        }else {
            callback.onFailure(ShopApiDaoCode.QUERY_GOODS_BY_PERSONAL_ID_FAILURE);
        }
    }

    @Override
    public void deleteGoodsById(DaoSession session, long id, ICallback callback) {
        session.getGoodsDao().deleteByKey(id);
        List<Goods> goods = session.getGoodsDao().queryBuilder().
                where(GoodsTypeDao.Properties.Id.eq(id)).list();
        if (!ListUtils.isListNull(goods)) {
            resetResult();
            result.put(ShopApiDaoCode.DELETE_GOODS_SUCCESS, true);
            callback.onSuccess(result);
        } else {
            callback.onFailure(ShopApiDaoCode.DELETE_GOODS_SUCCESS_FAILURE);
        }
    }
}
