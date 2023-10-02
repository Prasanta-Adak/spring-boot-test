package com.testbyprasanta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.testbyprasanta.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByEmail(String email);

	// define custom query using JPQL index params
	@Query("select e from Employee e where e.firstName=?1 and e.lastName=?2")
	Employee findByJPQL(String firstName, String lastName);

	// define custom query using JPQL named params
	@Query("select e from Employee e where e.firstName=:firstName and e.lastName=:lastName")
	Employee findByJPQLNamedParam(@Param("firstName") String firstName, @Param("lastName") String lastName);
	
	// define custom query using Native SQL index params
	@Query(value="select * from Employee e where e.firstName=?1 and e.lastName=?2",nativeQuery = true)
	Employee findByNativeSQL(String firstName, String lastName);
	
	// define custom query using Native SQL named params
		@Query(value="select * from Employee e where e.firstName=:firstName and e.lastName=:lastName",nativeQuery = true)
		Employee findByNativeSQLNamed(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
