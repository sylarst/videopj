package com.example.hg.uselibrary.user;

import java.io.Serializable;

public class SourceWareHouseEntity implements Serializable {

    private String warehouseId;
    private String warehouseName;

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }
}

