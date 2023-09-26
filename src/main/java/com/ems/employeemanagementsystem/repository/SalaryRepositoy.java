package com.ems.employeemanagementsystem.repository;

import com.ems.employeemanagementsystem.entity.Department;
import com.ems.employeemanagementsystem.entity.Designations;
import com.ems.employeemanagementsystem.entity.Salaries;

import com.ems.employeemanagementsystem.projection.SalarListProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Repository
public interface SalaryRepositoy extends JpaRepository<Salaries,Long> {


    // getByDesignations(Designations designations);

    @Query(value = "select s.id Id,\n" +
            "e.id employeeId,\n" +
            "e.name name,\n" +
            "d.designation designation,\n" +
            "dpt.dept_name deptName,\n" +
            "s.basic basic,\n"+
            "s.bonus bonus,\n"+
            "s.total total,\n"+
            "s.year year \n"+
            " from salaries s inner join employee e  on s.employee_id=e.id " +
            "inner join department dpt on s.department_id=dpt.id " +
            "inner join designations d on s.designations_id=d.id ",
            nativeQuery = true)
    List<SalarListProjection> salaryList();

    @Query(nativeQuery = true,value = "select s.id          Id,\n" +
            "       e.id          employeeId,\n" +
            "       e.name        name,\n" +
            "       d.designation designation,\n" +
            "       dpt.dept_name deptName,\n" +
            "       s.basic       basic,\n" +
            "       s.bonus       bonus,\n" +
            "       s.total       total,\n" +
            "       s.year        year\n" +
            "from salaries s\n" +
            "         inner join employee e on s.employee_id = e.id\n" +
            "         inner join department dpt on s.department_id = dpt.id\n" +
            "         inner join designations d on s.designations_id = d.id\n" +
            "where  LOWER(concat(e.name, dpt.dept_name)) LIKE CONCAT('%', LOWER(:search), '%')",
            countQuery = "select count(*)\n" +
                    "from salaries s\n" +
                    "         inner join employee e on s.employee_id = e.id\n" +
                    "         inner join department dpt on s.department_id = dpt.id\n" +
                    "         inner join designations d on s.designations_id = d.id\n" +
                    "where  LOWER(concat(e.name, dpt.dept_name)) LIKE CONCAT('%', LOWER(:search), '%')")
    Page<SalarListProjection> salaryListWithPage(@Param("search") String search, Pageable pageable);




    @Query(nativeQuery = true,value = "select s.id          Id,\n" +
            "       e.id          employeeId,\n" +
            "       e.name        name,\n" +
            "       d.designation designation,\n" +
            "       dpt.dept_name deptName,\n" +
            "       s.basic       basic,\n" +
            "       s.bonus       bonus,\n" +
            "       s.total       total,\n" +
            "       s.year        year\n" +
            "from salaries s\n" +
            "         inner join employee e on s.employee_id = e.id\n" +
            "         inner join department dpt on s.department_id = dpt.id\n" +
            "         inner join designations d on s.designations_id = d.id\n" +
            "where  LOWER(concat(e.name, dpt.dept_name)) LIKE CONCAT('%', LOWER(:search), '%')",
            countQuery = "select count(*)\n" +
                    "from salaries s\n" +
                    "         inner join employee e on s.employee_id = e.id\n" +
                    "         inner join department dpt on s.department_id = dpt.id\n" +
                    "         inner join designations d on s.designations_id = d.id\n" +
                    "where  LOWER(concat(e.name, dpt.dept_name)) LIKE CONCAT('%', LOWER(:search), '%')")
    List<Salaries> findSalaryByEmployeeNameContainingIgnoreCase(@Param("search") String search);

    Set<Salaries> getByDepartment(Department department);


    //  Set<Designation>getByDesignation(Designation designation);
}
