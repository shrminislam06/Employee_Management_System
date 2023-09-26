package com.ems.employeemanagementsystem.service;

import com.ems.employeemanagementsystem.entity.Designations;

import java.util.List;

public interface DesignationService {
    List<Designations> ListAll();

    void saveDesignation(Designations designations);

    Designations edit(long id);

    void delete(long id);
}
