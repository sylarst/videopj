package com.example.hg.uselibrary.user;

import java.util.List;

public class SourceBean {

    private String token;   //token
    private SourceUserInfo userInfo;  //角色id
    private List<SourceWareHouseEntity> warehouseList;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SourceUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(SourceUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<SourceWareHouseEntity> getWarehouseList() {
        return warehouseList;
    }

    public void setWarehouseList(List<SourceWareHouseEntity> warehouseList) {
        this.warehouseList = warehouseList;
    }
}
