package com.bhavna.crud.demo.service;

import com.bhavna.crud.demo.dao.EmployeeDao;
import com.bhavna.crud.demo.model.Employee;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Umer Faruque
 * @version 1.0
 * @since 17-04-2019
 */
@Service
@Transactional
public class EmployeeService {

    @Autowired
    @Qualifier("empDao")
    EmployeeDao empDao;

    public List<Employee> getEmployeeList(int pageNumber, int pageSize) throws HibernateException {
        return  this.empDao.getEmployeeList(pageNumber, pageSize);
    }
    public Employee getEmployee(long empId) throws HibernateException {
       return this.empDao.getEmployee(empId);
    }
    public boolean addEmployee(Employee emp) throws  HibernateException {
        if (this.empDao.employeeExists(emp.getEmpName())) {
            return false;
        } else {
            this.empDao.addEmployee(emp);
            return true;
        }
    }
    public boolean updateEmp(Employee emp) throws HibernateException {
        if (this.empDao.employeeExists(emp.getEmpId())) {
            this.empDao.updateEmp(emp);
            return true;
        } else {
            return false;
        }
    }
    public boolean deleteEmployee(long empId) throws HibernateException {
        if (this.empDao.employeeExists(empId)) {
            this.empDao.deleteEmployee(empId);
            return true;
        } else {
            return false;
        }
    }
}
