package com.test.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 25.07.2020
 * @author Mykola Horkov
 * mykola.horkov@gmail.com
 */
@Data
@Entity
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long warehouseId;
    private String name;
    private String code;
    private WarehouseType warehouseType;

    @OneToMany(mappedBy = "warehouse")
    private List<TTT> tttList;

    @OneToMany(mappedBy = "warehouse")
    private List<TPA> tpaList;

/*    public Warehouse(long id, String name, String code, WarehouseType warehouseType) {
        this.warehouseId = id;
        this.name = name;
        this.code = code;
        this.warehouseType = warehouseType;
    }*/

    public Warehouse(long id, String name, String code, WarehouseType warehouseType, List<TTT> tttList, List<TPA> tpaList) {
        this.warehouseId = id;
        this.name = name;
        this.code = code;
        this.warehouseType = warehouseType;
        this.tttList = tttList;
        this.tpaList = tpaList;
    }

    public Warehouse() {}

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + warehouseId +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", warehouseType=" + warehouseType +
                '}';
    }
}
