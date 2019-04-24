package com.bhavna.crud.demo.dao;

import com.bhavna.crud.demo.model.Employee;

import java.util.List;

/**
 * @author Umer Faruque
 * @version 1.0
 * @since 17-04-2019
 *
 */
public interface EmployeeDao {

    public List<Employee> getEmployeeList(int pageNumber, int pageSize);
    public Employee getEmployee(long empId);
    public void addEmployee(Employee emp);
    public void updateEmp(Employee emp);
    public void deleteEmployee(long empId);
    public boolean employeeExists(String empName);
    public boolean employeeExists(long empId);

}
