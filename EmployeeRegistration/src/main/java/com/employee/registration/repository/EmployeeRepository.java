package com.employee.registration.repository;

import com.employee.registration.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,String> {

    Set<Employee> findByFirstName(String firstName);

    Set<Employee> findByFirstNameAndLastName(String firstName,String lastName);

    Employee findByFirstNameAndMiddleNameAndLastName(String firstName,String middleName,String lastName);

}
