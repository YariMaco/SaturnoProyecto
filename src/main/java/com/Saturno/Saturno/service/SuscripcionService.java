/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Saturno.Saturno.service;

import com.Saturno.Saturno.entity.Suscripcion;
import com.Saturno.Saturno.repository.SuscripcionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author menoc
 */
@Service
public class SuscripcionService implements ISuscripcionService{
    @Autowired
    private SuscripcionRepository suscripcionRepository;

    @Override
    public List<Suscripcion> getAllSuscripcion() {
        return (List<Suscripcion>)suscripcionRepository.findAll();
    }

    @Override
    public Suscripcion getSuscirpcionById(long id) {
        return suscripcionRepository.findById(id).orElse(null);
    }

    @Override
    public void saveSuscripcion(Suscripcion suscripcion) {
        suscripcionRepository.save(suscripcion);
    }

    @Override
    public void delete(long id) {
        suscripcionRepository.deleteById(id);
    }
    
    
}
