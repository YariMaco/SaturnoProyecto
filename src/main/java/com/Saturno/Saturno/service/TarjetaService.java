/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Saturno.Saturno.service;

import com.Saturno.Saturno.entity.Tarjeta;
import com.Saturno.Saturno.repository.TarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author menoc
 */
@Service
public class TarjetaService implements ITarjetaService{
    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Override
    public void saveTarjeta(Tarjeta tarjeta) {
        tarjetaRepository.save(tarjeta);
        
    }

    @Override
    public void delete(long id) {
        tarjetaRepository.deleteById(id);
        
    }

    @Override
    public Tarjeta getTarjetaById(long id) {
        return tarjetaRepository.findById(id).orElse(null);
    }
    
    
}
