package com.employee.registration.service;

import com.employee.registration.entity.Employee;
import com.employee.registration.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

@Service
public class EmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;



    public Employee saveEmployee(Employee employee){

        Employee existingEmployee =null;

        if((!StringUtils.isEmpty(employee.getFirstName()) && employee.getFirstName().trim().length() >= 0 ) &&
                (!StringUtils.isEmpty(employee.getMiddleName()) && employee.getMiddleName().trim().length() >= 0 ) &&
                (!StringUtils.isEmpty(employee.getLastName()) && employee.getLastName().trim().length() >= 0)) {

            Set<Employee> existingEmployees = findEmployee(employee);

            if(existingEmployees!=null && existingEmployees.size() > 0){
                existingEmployee=existingEmployees.iterator().next();
            }
        }

        if(existingEmployee==null){
            if(employee.getId()==null){
                employee.setId(UUID.randomUUID().toString());
            }
        }else {

            employee.setId(existingEmployee.getId());

            if(employee.getContactNumber()==null)
            employee.setContactNumber(existingEmployee.getContactNumber());

            if(employee.getAddress()==null)
            employee.setAddress(existingEmployee.getAddress());

            if(employee.getDateOfBirth()==null)
            employee.setDateOfBirth(existingEmployee.getDateOfBirth());

            if(employee.getFirstName()==null)
            employee.setFirstName(existingEmployee.getFirstName());

            if(employee.getMiddleName()==null)
            employee.setMiddleName(existingEmployee.getMiddleName());

            if(employee.getLastName()==null)
            employee.setLastName(existingEmployee.getLastName());
        }

        Employee savedEmployee=employeeRepository.save(employee);

        return savedEmployee;
    }


    public Set<Employee> findEmployee(Employee employee){

        Set<Employee> fetchedEmployees= new HashSet<Employee>();

        if(employee==null){
            return fetchedEmployees;
        }

        if((!StringUtils.isEmpty(employee.getFirstName()) && employee.getFirstName().trim().length() >= 0 ) &&
                (!StringUtils.isEmpty(employee.getMiddleName()) && employee.getMiddleName().trim().length() >= 0 ) &&
                (!StringUtils.isEmpty(employee.getLastName()) && employee.getLastName().trim().length() >= 0)){
            Employee queriedEmployee=employeeRepository.findByFirstNameAndMiddleNameAndLastName(employee.getFirstName(),employee.getMiddleName(),employee.getLastName());

            if(queriedEmployee!=null ){
                fetchedEmployees.add(queriedEmployee);
            }
        }else if((!StringUtils.isEmpty(employee.getFirstName()) && employee.getFirstName().trim().length() >= 0 ) &&
                (!StringUtils.isEmpty(employee.getLastName()) && employee.getLastName().trim().length() >= 0)){

            Set<Employee> queriedEmployees=employeeRepository.findByFirstNameAndLastName(employee.getFirstName(),employee.getLastName());

            if(queriedEmployees!=null && queriedEmployees.size()>0){
                fetchedEmployees.addAll(queriedEmployees);
            }
        }else if(!StringUtils.isEmpty(employee.getFirstName()) && employee.getFirstName().trim().length() >= 0){

            Set<Employee> queriedEmployees=employeeRepository.findByFirstName(employee.getFirstName());

            if(queriedEmployees!=null && queriedEmployees.size()>0){
                fetchedEmployees.addAll(queriedEmployees);
            }

        }

        return fetchedEmployees;
    }


    public Set<Employee> fetchAllEmployees(){
        Set<Employee> allEmployees=new HashSet<>();

        Iterable<Employee> iterableEmployee=employeeRepository.findAll();

        if(iterableEmployee!=null){
            Iterator<Employee> employeeIterator=iterableEmployee.iterator();

            while (employeeIterator.hasNext()){
                allEmployees.add(employeeIterator.next());
            }

        }
        return allEmployees;
    }


    public void deleteEmployee(String employeeId){

        if(StringUtils.isEmpty(employeeId) && employeeId.trim().length()<=0){
            throw new NullPointerException("Employee ID is empty");
        }

        employeeRepository.deleteById(employeeId);

        return;
    }

}
