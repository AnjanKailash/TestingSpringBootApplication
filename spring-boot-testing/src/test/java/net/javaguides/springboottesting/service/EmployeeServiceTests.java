package net.javaguides.springboottesting.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import net.javaguides.springboottesting.model.Employee;
import net.javaguides.springboottesting.repository.EmployeeRepository;
import net.javaguides.springboottesting.service.impl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

	@Mock
	private EmployeeRepository employeeRepository;
	@InjectMocks
	private EmployeeServiceImpl employeeService;
	
	private Employee employee;
	
	@BeforeEach
	public void setup() {
		//instead of below code we are using @Mock and @InjectMock annotations
		//employeeRepository = Mockito.mock(EmployeeRepository.class);
		//employeeService = new EmployeeServiceImpl(employeeRepository);
		employee = new Employee("Ramesh", "Fadatare", "ramesh@gmail.com");
	}
	
	@DisplayName("JUnit test case for save employee method")
	@Test
	public void givenEmployeeObject_whenSaveEmployee_thenEmployeeObject() {

		//given - precondition or setup
		//Employee employee = new Employee("Ramesh", "Fadatare", "ramesh@gmail.com");
		
		given(employeeRepository.findByEmail(employee.getEmail()))
		.willReturn(Optional.empty());
		
		given(employeeRepository.save(employee)).willReturn(employee);
		
		//when - action or behavior that we are going to test
		Employee savedEmployee = employeeService.saveEmployee(employee);
		
		//then - verify the result
		assertThat(savedEmployee).isNotNull();
	}
}
