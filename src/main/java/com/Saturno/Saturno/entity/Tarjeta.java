/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Saturno.Saturno.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author menoc
 */
@Entity
@Table(name = "tarjetas")
public class Tarjeta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombret;
    private String fechaex;
    private int codse;
    private int numerot;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombret() {
        return nombret;
    }

    public void setNombret(String nombret) {
        this.nombret = nombret;
    }

    public String getFechaex() {
        return fechaex;
    }

    public void setFechaex(String fechaex) {
        this.fechaex = fechaex;
    }

    public int getCodse() {
        return codse;
    }

    public void setCodse(int codse) {
        this.codse = codse;
    }
  

    public int getNumerot() {
        return numerot;
    }

    public void setNumerot(int numerot) {
        this.numerot = numerot;
    }
    
}

