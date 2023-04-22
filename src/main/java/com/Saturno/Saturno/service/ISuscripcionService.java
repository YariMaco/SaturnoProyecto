/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Saturno.Saturno.service;

import com.Saturno.Saturno.entity.Suscripcion;
import java.util.List;

/**
 *
 * @author menoc
 */
public interface ISuscripcionService {

    public List<Suscripcion> getAllSuscripcion();

    public Suscripcion getSuscirpcionById(long id);

    public void saveSuscripcion(Suscripcion suscripcion);

    public void delete(long id);
    
    public Suscripcion findByNickname(String nickname);

}
