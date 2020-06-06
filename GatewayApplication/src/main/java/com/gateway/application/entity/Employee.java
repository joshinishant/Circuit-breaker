package com.gateway.application.entity;

import java.util.Objects;

public class Employee {

    private String Id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String dateOfBirth;
    private String address;
    private String contactNumber;


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    private Employee(){

    }

    public Employee(String id, String firstName, String middleName, String lastName, String dateOfBirth, String address, String contactNumber) {
        Id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.contactNumber = contactNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return firstName.equals(employee.firstName) &&
                middleName.equals(employee.middleName) &&
                lastName.equals(employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, middleName, lastName);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "Id='" + Id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }


    public static class EmployeeBuilder{
        private String Id;
        private String firstName;
        private String middleName;
        private String lastName;
        private String dateOfBirth;
        private String address;
        private String contactNumber;


        public EmployeeBuilder Id(String id) {
            Id = id;
            return this;
        }

        public EmployeeBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public EmployeeBuilder middleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public EmployeeBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public EmployeeBuilder dateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public EmployeeBuilder address(String address) {
            this.address = address;
            return this;
        }

        public EmployeeBuilder contactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
            return this;
        }


        public Employee build(){
            Employee employee=new Employee();
            employee.setId(this.Id);
            employee.setFirstName(this.firstName);
            employee.setMiddleName(this.middleName);
            employee.setLastName(this.lastName);
            employee.setDateOfBirth(this.dateOfBirth);
            employee.setAddress(this.address);
            employee.setContactNumber(this.contactNumber);

            return employee;
        }

    }


}
