package com.bhavna.crud.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Umer Faruque
 * @version 1.0
 * @since 16-04-2019
 */

@Entity
@Table(name = "emplyee")
public class Employee implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="empid")
    private long empId;
    @Column(name="empname")
    @NotBlank
    private String empName;
    @Column(name="designation")
    @NotBlank
    private String designation;
    @Column(name="reptmgrid")
    private long repotingMngId;
    @Column(name="apptdate")
    @NotBlank
    private String apptDate;
    @Column(name="salary")
    private double salary;
    @Column(name="deptid")
    private long deptId;
    @Column(name="createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public long getRepotingMngId() {
        return repotingMngId;
    }

    public void setRepotingMngId(long repotingMngId) {
        this.repotingMngId = repotingMngId;
    }

    public String getApptDate() {
        return apptDate;
    }

    public void setApptDate(String apptDate) {
        this.apptDate = apptDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public long getDeptId() {
        return deptId;
    }

    public void setDeptId(long deptId) {
        this.deptId = deptId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
