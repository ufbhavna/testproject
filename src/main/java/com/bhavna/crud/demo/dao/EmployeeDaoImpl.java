package com.bhavna.crud.demo.dao;

import com.bhavna.crud.demo.model.Employee;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Umer Faruque
 * @version 1.0
 * @since 17-04-2019
 */
@Repository
@Qualifier("empDao")
public class EmployeeDaoImpl implements EmployeeDao {


    @PersistenceContext
    private EntityManager entityManager;

    /**
     *
     * @param pageNumber list page number
     * @param pageSize number of records
     * @return List<Employe>
     */
    public List<Employee> getEmployeeList(int pageNumber, int pageSize) {
        String hql = "FROM Employee as emp ORDER BY emp.empName";
        return (List<Employee>) entityManager.createQuery(hql).getResultList();
    }

    /**
     *
     *
     * @param empId employee ID
     * @return Employee
     */
    public Employee getEmployee(long empId) {
        return entityManager.find(Employee.class, empId);
    }

    /**
     *
     * @param emp Employee payload
     */
    public void addEmployee(Employee emp) {
        entityManager.persist(emp);
    }

    /**
     *
     * @param emp Employee payload
     */
    public void updateEmp(Employee emp) {
        Employee oldEmp = getEmployee(emp.getEmpId());
        //oldEmp.setEmpName(emp.getEmpName());
        oldEmp.setDesignation(emp.getDesignation());
        oldEmp.setSalary(emp.getSalary());
        emp.setCreatedDate(oldEmp.getCreatedDate());
        entityManager.flush();
    }

    /**
     *
     * @param empId eployee id
     */
    public void deleteEmployee(long empId) {
        entityManager.remove(getEmployee(empId));
    }

    /**
     *
     * @param empName employee name
     * @return return boolean whether employee exists or not
     */
    public boolean employeeExists(String empName) {
        String hql = "FROM Employee emp WHERE emp.empName = :empName";
        int count = entityManager.createQuery(hql).setParameter("empName", empName)
                .getResultList().size();
        return count > 0 ? true : false;
    }

    public boolean employeeExists(long empId) {
        Employee employee = getEmployee(empId);
        return employee != null ? true : false;
    }
}
