package com.bhavna.crud.demo;

import com.bhavna.crud.demo.controller.EmployeeController;
import com.bhavna.crud.demo.model.Employee;
import com.bhavna.crud.demo.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
@AutoConfigureRestDocs(outputDir = "build/generated-snippets")
public class EmployeeTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void getEmployeeById() throws Exception{
        Employee employee = new Employee();
        employee.setEmpId(1);
        employee.setEmpName("Umer");
        employee.setDesignation("Developer");
        employee.setRepotingMngId(1);
        employee.setApptDate("10-04-2019");
        employee.setSalary(1000);
        employee.setDeptId(100);
        employee.setCreatedDate(new Date());
        BDDMockito.given(employeeService.getEmployee(1L)).willReturn(employee);

        this.mockMvc.perform(get("/employee/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("/employee"));
    }
}
