package com.test.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
@Table(name = "ttt")
public class TTT {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tttId;

    @Setter
    @Getter
    private String code;

    @Getter
    @OneToMany(mappedBy = "ttt")
    private List<Manifest> manifestList = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "warehouseID")
    private Warehouse warehouse;

    public TTT() {}

    public TTT(long id, String code, Warehouse warehouse) {
        this.tttId = id;
        this.code = code;
        this.warehouse = warehouse;
    }

    @Override
    public String toString() {
        return "TTT{" +
                "id=" + tttId +
                ", code='" + code + '\'' +
                ", manifestList=" + manifestList +
                '}';
    }
}
