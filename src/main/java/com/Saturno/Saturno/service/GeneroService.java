/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Saturno.Saturno.service;

import com.Saturno.Saturno.entity.Genero;
import com.Saturno.Saturno.repository.GeneroRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author menoc
 */
@Service
public class GeneroService implements IGeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    @Override
    public List<Genero> listGenero() {
        return (List<Genero>) generoRepository.findAll();
    }

    @Override
    public Genero getGeneroById(long id) {
        return generoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Genero> getGenerosById(List<Long> ids) {
        return (List<Genero>) generoRepository.findAllById(ids);
    }

}
