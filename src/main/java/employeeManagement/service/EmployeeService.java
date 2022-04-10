package employeeManagement.service;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import employeeManagement.entity.Employee;

public interface EmployeeService {

	String addEmployeewithSaveAndFlush(Employee employee);

	String addAllEmployees(List<Employee> employees);
	
	List<Employee> listAllEmployees();
	
	Employee getSingleEmployeeById(long id);
	
	Employee updateEmployeeDetails(Employee employee);
	
	String deleteEmployeeById(long id);
	
	List<Employee> getEmployeeByFirstName(String firstName);

	List<Employee> getCustomSortedListOfEmployeesByFirstName(Direction dir);
}