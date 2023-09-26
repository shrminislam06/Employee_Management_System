package com.ems.employeemanagementsystem.service.IMPL;

import com.ems.employeemanagementsystem.entity.Designations;
import com.ems.employeemanagementsystem.repository.DesignationRepostiory;
import com.ems.employeemanagementsystem.service.DesignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesignationIMPL implements DesignationService {
    @Autowired
    private DesignationRepostiory designationRepostiory;
    @Override
    public List<Designations> ListAll() {
        return designationRepostiory.findAll();
    }

    @Override
    public void saveDesignation(Designations designations) {
        designationRepostiory.save(designations);
    }

    @Override
    public Designations edit(long id) {
        return designationRepostiory.findById(id).get();
    }

    @Override
    public void delete(long id) {
designationRepostiory.deleteById(id);
    }
}
