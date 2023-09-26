package com.ems.employeemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Salaries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double basic;
    private double bonus;
    private String year;
    private double total;

    @ManyToOne
    private Employee employee;
    @ManyToOne
    private Designations designations;
    @ManyToOne
    private Department department;

    public double getBonus() {
        if(basic<20000)
        {
            bonus=basic* 10/100;
        }
        else if (basic>20000)
        {
            bonus=basic* 20/100;
        } else {
            bonus=0;
        }
        return bonus;
    }

    public double getTotal() {
        total=basic+bonus;
        return total;
    }

    public long getId() {
        return id;
    }

    public double getBasic() {
        return basic;
    }

    public String getYear() {
        return year;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Designations getDesignations() {
        return designations;
    }

    public Department getDepartment() {
        return department;
    }
}
