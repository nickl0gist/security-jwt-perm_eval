package com.test.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created on 25.07.2020
 * @author Mykola Horkov
 * mykola.horkov@gmail.com
 */
@Entity
@Table(name = "manifest")
public class Manifest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long manifestId;

    private String code;

    @JsonIgnore
    @ManyToOne
    private TPA tpa;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "tttID", nullable = false)
    private TTT ttt;

    public Manifest(long id, String s, TPA tpa, TTT ttt) {
        this.manifestId = id;
        this.code = s;
        this.tpa = tpa;
        this.ttt = ttt;
    }


    public Long getManifestId() {
        return manifestId;
    }

    public TPA getTpa() {
        return tpa;
    }

    public void setTpa(TPA tpa) {
        this.tpa = tpa;
    }

    @Override
    public String toString() {
        return "Manifest{" +
                "id=" + manifestId +
                ", code='" + code + '\'' +
                '}';
    }

    public Manifest() {
    }

}
