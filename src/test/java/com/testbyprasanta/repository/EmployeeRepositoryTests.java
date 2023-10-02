package com.testbyprasanta.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.testbyprasanta.model.Employee;

@DataJpaTest
public class EmployeeRepositoryTests {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	private Employee employee;
	
	@BeforeEach
	public void setup() {
		employee = Employee.builder()
				.firstName("Ramesh")
				.lastName("Ramesh")
				.email("ramesh@gmail, com")
				.build();
	}

	// JUnit test for save employee operation
	@Test
	public void givenEmployeeObject_whenSave_thenReturnSaved_Employee() {

		// given precondition or setup

//		Employee employee = Employee.builder()
//				.firstName("Ramesh")
//				.lastName("Ramesh")
//				.email("ramesh@gmail, com")
//				.build();

		// when - action or the behaviour that we are going test
		Employee savedEmployee = employeeRepository.save(employee);

		// then - verify the output.
		assertThat(savedEmployee).isNotNull();
		assertThat(savedEmployee.getId()).isGreaterThan(0);
	}

	// JUnit test for get all employee operation
	@Test
	@DisplayName("JUnit test for get all employee operation")
	public void givenEmployeeList_whenFindAll_thenEmployeesList() {

		// given precondition or setup

//		Employee employee = Employee.builder()
//				.firstName("Ramesh")
//				.lastName("Ramesh")
//				.email("ramesh@gmail, com")
//				.build();
		Employee employee1 = Employee.builder()
				.firstName("Ramesh")
				.lastName("Ramesh")
				.email("ramesh@gmail, com")
				.build();

		employeeRepository.save(employee);
		employeeRepository.save(employee1);

		// when - action or the behaviour that we are going test
		List<Employee> employeeList = employeeRepository.findAll();
		// then - verify the output.
		assertThat(employeeList).isNotNull();
		assertThat(employeeList.size()).isEqualTo(2);
	}
	
	// JUnit test for get employee by id operation
		@Test
		@DisplayName("JUnit test for get employee by id operation")
		public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {

			// given precondition or setup

//			Employee employee = Employee.builder()
//					.firstName("Ramesh")
//					.lastName("Ramesh")
//					.email("ramesh@gmail, com")
//					.build();

			employeeRepository.save(employee);

			// when - action or the behaviour that we are going test
			Employee employeeDB=employeeRepository.findById(employee.getId()).get();
			// then - verify the output.
			assertNotNull(employeeDB);
		}
		
	// JUnit test for get employee by id operation
	 @Test
	 @DisplayName("JUnit test for get employee by Email operation")
	 public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject() {

	// given precondition or setup

//	  Employee employee = Employee.builder()
//			   .firstName("Ramesh")
//			   .lastName("Ramesh")
//			   .email("ramesh@gmail, com")
//			   .build();

			 employeeRepository.save(employee);

		// when - action or the behaviour that we are going test
		Employee employeeDB=employeeRepository.findByEmail(employee.getEmail()).get();
		// then - verify the output.
		assertNotNull(employeeDB);
	}
	 
	// JUnit test for update employee operation
		 @Test
		 @DisplayName("JUnit test for Update employee operation")
		 public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {

		// given precondition or setup

//		  Employee employee = Employee.builder()
//				   .firstName("Ramesh")
//				   .lastName("Ramesh")
//				   .email("ramesh@gmail, com")
//				   .build();

				 employeeRepository.save(employee);

				 
			// when - action or the behaviour that we are going test
			Employee savedEmployee=employeeRepository.findById(employee.getId()).get();
			savedEmployee.setEmail("prasanta@gmail.com");
			savedEmployee.setFirstName("Prasanta");
			Employee updatedEmployee=employeeRepository.save(savedEmployee);
			// then - verify the output.
			assertTrue(updatedEmployee.getEmail().contains("prasanta@gmail.com"));
			assertTrue(updatedEmployee.getFirstName().contains("Prasanta"));
		}
		 
		// JUnit test for custom query using JPQL with index
				 @Test
				 @DisplayName("JUnit test for custom query using JPQL with index")
				 public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployeeObject() {

				// given precondition or setup

//				  Employee employee = Employee.builder()
//						   .firstName("Ramesh")
//						   .lastName("Tata")
//						   .email("ramesh@gmail, com")
//						   .build();

						 employeeRepository.save(employee);

						 String firstName="Ramesh";
						 String lastName="Tata";
						 
					// when - action or the behaviour that we are going test
					Employee savedEmployee=employeeRepository.findByJPQL(firstName,lastName);
					
					// then - verify the output.
					assertNotNull(savedEmployee);
					
				}

				
				// JUnit test for custom query using JPQL with named params
				 @Test
				 @DisplayName("JUnit test for custom query using JPQL with named params")
				 public void givenFirstNameAndLastName_whenFindByJPQLNamedParam_thenReturnEmployeeObject() {

				// given precondition or setup

//				  Employee employee = Employee.builder()
//						   .firstName("Ramesh")
//						   .lastName("Tata")
//						   .email("ramesh@gmail, com")
//						   .build();

						 employeeRepository.save(employee);

						 String firstName="Ramesh";
						 String lastName="Tata";
						 
					// when - action or the behaviour that we are going test
					Employee savedEmployee=employeeRepository.findByJPQLNamedParam(firstName,lastName);
					
					// then - verify the output.
					assertNotNull(savedEmployee);
					
				}
				 
				// JUnit test for custom query using native SQL with index params
				 @Test
				 @DisplayName("JUnit test for custom query using native SQL with index params")
				 public void givenFirstNameAndLastName_whenFindByNativeSQL_thenReturnEmployeeObject() {

				// given precondition or setup

//				  Employee employee = Employee.builder()
//						   .firstName("Ramesh")
//						   .lastName("Tata")
//						   .email("ramesh@gmail, com")
//						   .build();

						 employeeRepository.save(employee);
						 
					// when - action or the behaviour that we are going test
					Employee savedEmployee=employeeRepository.findByNativeSQL(employee.getFirstName(),employee.getLastName());
					
					// then - verify the output.
					assertNotNull(savedEmployee);
					
				}
				 
				// JUnit test for custom query using native SQL with named params
				 @Test
				 @DisplayName("JUnit test for custom query using native SQL with named params")
				 public void givenFirstNameAndLastName_whenFindByNativeSQLNamedParam_thenReturnEmployeeObject() {

				// given precondition or setup

//				  Employee employee = Employee.builder()
//						   .firstName("Ramesh")
//						   .lastName("Tata")
//						   .email("ramesh@gmail, com")
//						   .build();
//
						 employeeRepository.save(employee);
						 
					// when - action or the behaviour that we are going test
					Employee savedEmployee=employeeRepository.findByNativeSQLNamed(employee.getFirstName(),employee.getLastName());
					
					// then - verify the output.
					assertNotNull(savedEmployee);
					
				}
				 
				
}
