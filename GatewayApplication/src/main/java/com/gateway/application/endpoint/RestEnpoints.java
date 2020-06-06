package com.gateway.application.endpoint;

import com.gateway.application.entity.Employee;
import com.gateway.application.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.function.Supplier;

@RestController
@RequestMapping("/gateway")
public class RestEnpoints {


    @Autowired
    private EmployeeService employeeService;


    @Autowired
    private CircuitBreaker circuitBreaker;

    @GetMapping(value = "/All" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllEmployees(){
        return circuitBreaker.run(new Supplier<ResponseEntity>() {
            @Override
            public ResponseEntity get() {
                return new ResponseEntity(employeeService.sendRequestToFindAllEmployee().getBody(), HttpStatus.OK);
            }
        },throwable -> {
            return new ResponseEntity("Endpoint is down",HttpStatus.INTERNAL_SERVER_ERROR);
        });

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getEmployees(@RequestParam(name = "firstName") String firstName,
                                                      @RequestParam(name = "middleName") String middleName,
                                                      @RequestParam(name = "lastName") String lastName){

        Employee employee=new Employee.EmployeeBuilder().firstName(firstName).middleName(middleName).lastName(lastName).build();

        return circuitBreaker.run(new Supplier<ResponseEntity>() {
            @Override
            public ResponseEntity get() {
              return   new ResponseEntity( employeeService.sendRequestToFindEmployee(employee).getBody(), HttpStatus.OK);
            }
        },throwable -> {
            return new ResponseEntity("Endpoint is down",HttpStatus.INTERNAL_SERVER_ERROR);
        });
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){

        return circuitBreaker.run(new Supplier<ResponseEntity>() {
            @Override
            public ResponseEntity get() {
              return  new ResponseEntity( employeeService.sendRequestToCreateEmployee(employee).getBody(),HttpStatus.OK);
            }
        },throwable -> {
            return new ResponseEntity("Endpoint is down",HttpStatus.INTERNAL_SERVER_ERROR);
        });

    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateEmployee(@RequestBody Employee employee){

        return circuitBreaker.run(new Supplier<ResponseEntity>() {
            @Override
            public ResponseEntity get() {
              return  new ResponseEntity( employeeService.sendRequestToUpdateEmployee(employee).getBody(),HttpStatus.OK);
            }
        },throwable -> {
            return new ResponseEntity("Endpoint is down",HttpStatus.INTERNAL_SERVER_ERROR);
        });

    }


    @DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteEmployees(@PathVariable("id") String id){

        return circuitBreaker.run(new Supplier<ResponseEntity>() {
            @Override
            public ResponseEntity get() {
              return  new ResponseEntity( employeeService.sendRequestToDeleteEmployee(id).getBody(),HttpStatus.OK);
            }
        },throwable -> {
            return new ResponseEntity("Endpoint is down",HttpStatus.INTERNAL_SERVER_ERROR);
        });
    }
}
