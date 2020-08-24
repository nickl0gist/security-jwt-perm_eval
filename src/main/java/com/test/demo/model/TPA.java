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
@Table(name = "tpa")
public class TPA {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @Getter
    private String name;

    @Getter
    @OneToMany(mappedBy = "tpa")
    private List<Manifest> manifestList = new ArrayList<>();

    public TPA() {}

    public TPA(long id, String name, Warehouse warehouse) {
        this.id = id;
        this.name = name;
        this.warehouse = warehouse;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "warehouseID")
    private Warehouse warehouse;

    @Override
    public String toString() {
        return "TPA{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manifestList=" + manifestList +
                '}';
    }
}
