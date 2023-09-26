package com.ems.employeemanagementsystem.repository;

import com.ems.employeemanagementsystem.entity.Designations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DesignationRepostiory extends JpaRepository<Designations,Long> {

Set<Designations>findAllByIdIn(Set<Long>dsignationIds);

}
