package com.gateway.application.service;

import com.gateway.application.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class EmployeeService {


    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity sendRequestToCreateEmployee(Employee employee){
        HttpEntity<Employee> httpEntity=new HttpEntity<>(employee);
        return restTemplate.exchange("http://localhost:8083/employeeRegistration",HttpMethod.POST,httpEntity,String.class);
    }

    public ResponseEntity sendRequestToUpdateEmployee(Employee employee){
        HttpEntity<Employee> httpEntity=new HttpEntity<>(employee);
        return restTemplate.exchange("http://localhost:8083/employeeRegistration", HttpMethod.PUT,httpEntity,String.class);
    }


    public ResponseEntity sendRequestToFindEmployee(Employee employee){

        UriComponentsBuilder builder=UriComponentsBuilder.fromUriString("http://localhost:8083/employeeRegistration")
                .queryParam("firstName",employee.getFirstName())
                .queryParam("middleName",employee.getMiddleName())
                .queryParam("lastName",employee.getLastName());

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET,null,String.class);
    }


    public ResponseEntity sendRequestToFindAllEmployee(){
        return restTemplate.exchange("http://localhost:8083/employeeRegistration/All",HttpMethod.GET,null,String.class);
    }


    public ResponseEntity sendRequestToDeleteEmployee(String employeeId) {
        return restTemplate.exchange("http://localhost:8083/employeeRegistration/{id}",HttpMethod.DELETE,null,String.class,employeeId);
    }

}
