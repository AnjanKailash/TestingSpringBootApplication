package net.javaguides.springboottesting.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.javaguides.springboottesting.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Optional<Employee> findByEmail(String email);
	
	//jpql custom query with index parameters
	@Query("select e from Employee e where e.firstName = ?1 and e.lastName = ?2")
	Employee findByJPQL(String firstName, String lastName);
}
