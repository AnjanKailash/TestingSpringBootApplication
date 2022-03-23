package net.javaguides.springboottesting.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.javaguides.springboottesting.model.Employee;
import net.javaguides.springboottesting.service.EmployeeService;

@WebMvcTest
public class EmployeeControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmployeeService employeeService;
	
	@Autowired
	private ObjectMapper objectMapper; //for json
	
	@Test
	public void givenEmployeeObject_whenCreateEmployee_theReturnSavedEmployee() throws JsonProcessingException, Exception {
		
		//given - precondition or setup
		Employee employee = new Employee("Ramesh", "Fadatare", "ramesh@gmail.com");
		given(employeeService.saveEmployee(any(Employee.class)))
		.willAnswer(invocation->invocation.getArgument(0));
		
		//when - action or behavior that we are going test
		ResultActions response = mockMvc.perform(post("/api/employees")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(employee)));
		
		//then - verify the result or output using assert statements
		response.andExpect(status().isCreated())
				.andExpect(jsonPath("$.firstName", CoreMatchers.is(employee.getFirstName())))
				.andExpect(jsonPath("$.lastName", CoreMatchers.is(employee.getLastName())))
				.andExpect(jsonPath("$.email", CoreMatchers.is(employee.getEmail())));
	}
	
	@DisplayName("Given list of employees given all employees get all employees")
	@Test
	public void given_when_then() throws Exception {

		//given - precondition or setup
		List<Employee> listOfEmployees = new ArrayList<>();
		listOfEmployees.add(new Employee("Rmesh", "Fatadare", "ramesh@gmail.com"));
		listOfEmployees.add(new Employee("Tony", "Start", "tony@gmail.com"));
		given(employeeService.getAllEmployees()).willReturn(listOfEmployees);
		
		//when - action or behavior that we are going to test
		ResultActions response = mockMvc.perform(get("/api/employees"));
		
		//then - verify the result
		response.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.size()", CoreMatchers.is(listOfEmployees.size())));
	}
	
	
	
	
	
	
}
