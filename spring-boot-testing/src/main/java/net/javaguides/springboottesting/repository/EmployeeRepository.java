package net.javaguides.springboottesting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboottesting.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
