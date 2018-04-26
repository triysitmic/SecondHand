package com.lby.secondhand.dao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Goods {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String icon_url;
    private double price;
    private String desc;
    private Long type_id;
    private Long user_id;
    @Generated(hash = 2079789911)
    public Goods(Long id, String name, String icon_url, double price, String desc,
            Long type_id, Long user_id) {
        this.id = id;
        this.name = name;
        this.icon_url = icon_url;
        this.price = price;
        this.desc = desc;
        this.type_id = type_id;
        this.user_id = user_id;
    }
    @Generated(hash = 1770709345)
    public Goods() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIcon_url() {
        return this.icon_url;
    }
    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }
    public double getPrice() {
        return this.price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getDesc() {
        return this.desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public Long getType_id() {
        return this.type_id;
    }
    public void setType_id(Long type_id) {
        this.type_id = type_id;
    }
    public Long getUser_id() {
        return this.user_id;
    }
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
