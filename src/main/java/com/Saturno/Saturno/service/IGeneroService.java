/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Saturno.Saturno.service;

import com.Saturno.Saturno.entity.Genero;
import java.util.List;

/**
 *
 * @author menoc
 */
public interface IGeneroService {
    public List<Genero> listGenero();
    public Genero getGeneroById(long id);
    public List<Genero> getGenerosById(List<Long> ids);
    
}
