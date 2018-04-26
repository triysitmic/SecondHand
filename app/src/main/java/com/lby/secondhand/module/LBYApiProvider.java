package com.lby.secondhand.module;


import com.lby.secondhand.dao.api.DAOModule;
import com.lby.secondhand.dao.bean.User;
import com.lby.secondhand.method.ICallback;

public class LBYApiProvider {

    private static final LBYApiProvider ourInstance = new LBYApiProvider();
    private User user;

    private LBYApiProvider() {
    }

    public static LBYApiProvider getInstance() {
        return ourInstance;
    }

    public void login(String name, ICallback callback) {
        DAOModule.getInstance().queryUserByName(name, callback);
    }

    public void register(User user, ICallback callback) {
        DAOModule.getInstance().insertNewUser(user, callback);
    }

    public void getAllGoods(ICallback callback) {
        DAOModule.getInstance().getAllGoods(callback);
    }

    public void getGoodsByUserId(ICallback callback) {
        if (user == null){
            return;
        }
        DAOModule.getInstance().getGoodsByUserId(user.getId(), callback);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
