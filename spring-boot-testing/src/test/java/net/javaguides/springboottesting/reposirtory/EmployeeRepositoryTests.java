package net.javaguides.springboottesting.reposirtory;

//import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import net.javaguides.springboottesting.model.Employee;
import net.javaguides.springboottesting.repository.EmployeeRepository;

@DataJpaTest
public class EmployeeRepositoryTests {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	//JUnit test for save employee operation
	@DisplayName("JUnit test for save employee operation")
	@Test
	public void givenEmployeeObject_whenSave_thenReturnSavedEmployeeObject(){
		
		///given - precondition or setup
//		Employee employee = Employee.builder()
//				.firstName("Ramesh")
//				.lastName("Ramesh")
//				.email("ramesh@gmail.com")
//				.build();
		Employee employee = new Employee("Ramesh", "Ramesh", "ramesh@gmail.com");
		
		//when - action or behaviour that we are going to test
		Employee savedEmployee = employeeRepository.save(employee);		
		
		//then - verify the output
//		Assertions.assertThat(savedEmployee).isNotNull();
//		Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);
		assertThat(savedEmployee).isNotNull(); //imported till static method as it is static
		assertThat(savedEmployee.getId()).isGreaterThan(0);
	}
	
	@DisplayName("JUnit test case for getting all employees")
	@Test
	public void givenEmployeeList_whenFindAll_thenEmployeesList() {
		//given - precondition or setup
		Employee employee1 = new Employee("Ramesh", "Ramesh", "ramesh@gmail.com");
		Employee employee2 = new Employee("Kamesh", "Kamesh", "kamesh@gmail.com");
		Employee employee3 = new Employee("Jamesh", "Jamesh", "jamesh@gmail.com");
		Employee employee4 = new Employee("Bamesh", "Bamesh", "bamesh@gmail.com");
		Employee employee5 = new Employee("Tamesh", "Tamesh", "tamesh@gmail.com");
		
		employeeRepository.save(employee1);
		employeeRepository.save(employee2);
		employeeRepository.save(employee3);
		employeeRepository.save(employee4);
		employeeRepository.save(employee5);
		
		//when - action or behavior that we are going to test
		List<Employee> employeeList = employeeRepository.findAll();
		
		
		//then - verify the result
		assertThat(employeeList).isNotNull();
		assertThat(employeeList.size()).isEqualTo(5);
	}
	
	@DisplayName("JUnit test for get employee by id operation")
	@Test
	public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {

		//given - precondition or setup
		Employee employee = new Employee("Ramesh", "Ramesh", "ramesh@gmail.com");
		employeeRepository.save(employee);
		
		//when - action or behavior that we are going to test
		Employee employeeDB = employeeRepository.findById(employee.getId()).get();
		
		//then - verify the result
		assertThat(employeeDB).isNotNull();

	}
}






















