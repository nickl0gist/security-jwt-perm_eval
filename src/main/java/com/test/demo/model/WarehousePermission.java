package com.test.demo.model;

import lombok.Data;

/**
 * Created on 26.07.2020
 * @author Mykola Horkov
 * mykola.horkov@gmail.com
 */
@Data
public class WarehousePermission {
    private User user;
    private Warehouse warehouse;
    private WarehousePermissionType warehousePermissionType;

    public WarehousePermission() {}

    public WarehousePermission(User user, Warehouse warehouse, WarehousePermissionType warehousePermissionType) {
        this.user = user;
        this.warehouse = warehouse;
        this.warehousePermissionType = warehousePermissionType;
    }
}
