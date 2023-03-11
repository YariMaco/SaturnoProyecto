/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Saturno.Saturno.service;

import com.Saturno.Saturno.entity.Plan;
import com.Saturno.Saturno.repository.PlanRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author menoc
 */
@Service
public class PlanService implements IPlanService {
    @Autowired
    private PlanRepository planRepository;

    @Override
    public List<Plan> listPlan() {
        return(List<Plan>)planRepository.findAll();
    }
    
}
