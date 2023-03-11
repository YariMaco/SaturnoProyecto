/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Saturno.Saturno.service;

import com.Saturno.Saturno.entity.Tarjeta;

/**
 *
 * @author menoc
 */
public interface ITarjetaService {
    public Tarjeta getTarjetaById(long id);
    public void saveTarjeta(Tarjeta tarjeta);
    public void delete(long id);   
}
