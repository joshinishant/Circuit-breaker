package com.employee.registration.endpoint;

import com.employee.registration.entity.Employee;
import com.employee.registration.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/employeeRegistration")
public class RestEnpoints {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping(value = "/All" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Employee>> getAllEmployees(){

        Set<Employee> allEmployees=employeeService.fetchAllEmployees();
        ResponseEntity<Set<Employee>> response= new ResponseEntity<>(allEmployees,HttpStatus.OK);

        return response;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Employee>> getEmployees(@RequestParam(name = "firstName") String firstName,
                                                      @RequestParam(name = "middleName") String middleName,
                                                      @RequestParam(name = "lastName") String lastName){

        Employee employee=new Employee.EmployeeBuilder().firstName(firstName).middleName(middleName).lastName(lastName).build();
        Set<Employee> fetchedEmployees=employeeService.findEmployee(employee);

        ResponseEntity<Set<Employee>> response= new ResponseEntity<>(fetchedEmployees,HttpStatus.OK);

        return response;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        return upsertEmployee(employee);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        return upsertEmployee(employee);
    }


    @DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteEmployees(@PathVariable("id") String id){

        employeeService.deleteEmployee(id);
        ResponseEntity<String> response=new ResponseEntity<>("Successfully deleted Employee with Id "+id, HttpStatus.OK);

        return response;
    }



    private ResponseEntity<Employee> upsertEmployee(Employee employee){
        Employee persistedEmployee=employeeService.saveEmployee(employee);
        ResponseEntity<Employee> response=new ResponseEntity<>(persistedEmployee, HttpStatus.OK);
        return response;
    }

}
