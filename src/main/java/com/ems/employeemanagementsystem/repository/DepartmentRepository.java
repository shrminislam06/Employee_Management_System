package com.ems.employeemanagementsystem.repository;

import com.ems.employeemanagementsystem.entity.Department;
import com.ems.employeemanagementsystem.entity.Employee;
import com.ems.employeemanagementsystem.projection.DepartmentsDesignation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

    @Query(value = "select dp.id id,\n"+
            "e.name name,\n"+
    "dp.dept_name deptName\n"+
            " from department dp inner join department_employees de on de.department_id=dp.id"+
            " inner join employee e on de.employees_id = e.id",

            nativeQuery = true)
    List<DepartmentsDesignation> departmentsList();

    Optional<Department> findDepartmentByDeptName(String deptName);

Department findByDeptName(String deptName);
    Optional<Department> findAllByDeptName(String deptName);





    @Query(value = "select dp.id id,\n"+
            "e.name name,\n"+
            "dp.dept_name deptName\n"+
            " from department dp inner join department_employees de on de.department_id=dp.id"+
            " inner join employee e on de.employees_id = e.id"+
            " where  LOWER(concat(e.name, dp.dept_name)) LIKE CONCAT('%', LOWER(:search), '%')",
            nativeQuery = true)
    List<Department> findAllDepartmentByDeptNameContainingIgnoreCase(@Param("search") String deptName);
    //  Set<Department> findAllByIdIn(Set<Long>departmentIds);


  //  Optional<Department> findByName(String deptName);
}
