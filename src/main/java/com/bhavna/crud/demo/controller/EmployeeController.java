package com.bhavna.crud.demo.controller;

import com.bhavna.crud.demo.exception.RecordNotFoundException;
import com.bhavna.crud.demo.exception.RecordConflict;
import com.bhavna.crud.demo.model.Employee;
import com.bhavna.crud.demo.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * @author Umer Faruque
 * @version 1.0
 * @since 17-04-2019
 */

@RestController
@RequestMapping("employee")
public class EmployeeController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EmployeeService employeeService;

    /**
     *
     * @param empId
     * @return Emplyee object
     */
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long empId) {
        Employee employee = this.employeeService.getEmployee(empId);
        if(employee == null){
            throw new RecordNotFoundException("Employee Id "+empId+" not found");
        }
        LOGGER.info("Employee "+ empId +" found");
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
}

    /**
     *
     * @param pageNumber page number to show
     * @param pageSize number of records to fetch
     * @return List<Employee>
     */
    @GetMapping("list")
    public ResponseEntity<List<Employee>> getEmployeeList(@RequestParam(value = "pageNumber", required = true) int pageNumber,
                                                          @RequestParam(value = "pageSize", required = true) int pageSize) {

        List<Employee> emplyeeList = this.employeeService.getEmployeeList(pageNumber, pageSize);
        if(emplyeeList == null || emplyeeList.size() == 0){
            throw new RecordNotFoundException("Record not found");
        }
        return new ResponseEntity<>(emplyeeList, HttpStatus.OK);

    }

    /**
     *
     * @param employee payload
     * @param builder to build employee uri
     * @return
     */

    @PostMapping
    public ResponseEntity<Void> addEmployee(@RequestBody Employee employee, UriComponentsBuilder builder){
        boolean flag = this.employeeService.addEmployee(employee);
        if (flag == false) {
            throw new RecordConflict("Employee already exists!");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/{id}").buildAndExpand(employee.getEmpId()).toUri());
        LOGGER.info("Employee Created Id "+employee.getEmpId());
        LOGGER.info("Location "+headers.getLocation());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    /**
     *
     * @param employee payload
     * @return
     */

    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        boolean flag = this.employeeService.updateEmp(employee);

        if (flag == false) {
            throw new RecordNotFoundException("Employee not exists!");
        }
        LOGGER.info("Employee "+employee.getEmpId()+ " updated");
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    /**
     *
     * @param empId employee Id
     * @return
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") long empId) {
        boolean flag = this.employeeService.deleteEmployee(empId);

        if (flag == false) {
            throw new RecordNotFoundException("Employee not exists!");
        }
        LOGGER.info("Employee "+empId+ " deleted");
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
