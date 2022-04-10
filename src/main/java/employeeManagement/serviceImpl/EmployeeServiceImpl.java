package employeeManagement.serviceImpl;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import employeeManagement.entity.Employee;
import employeeManagement.repository.EmployeeRepository;
import employeeManagement.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public String addEmployeewithSaveAndFlush(Employee employee) {
		Optional<Employee> result = employeeRepository.findById(employee.getId());
		if (result.isPresent()) {
			return "Employee with id " + employee.getId() + " already exists -> " + result.get();
		}
		else {
			employeeRepository.saveAndFlush(employee);
			return "Employee details -> " + employee + " saved." ;
		}
	}

	@Override
	public String addAllEmployees(List<Employee> employees) {
		for (Iterator<Employee> iterator = employees.iterator(); iterator.hasNext();) {
			Employee employee = (Employee) iterator.next();
			Optional<Employee> result = employeeRepository.findById(employee.getId());
			if (result.isPresent()) {
				return "Employee with id " + employee.getId() + " already exists -> " + result.get();
			}
		}
		employeeRepository.saveAll(employees);
		employeeRepository.flush();
		return "All employees saved.";
	}

	@Override
	public List<Employee> listAllEmployees() {
		return employeeRepository.findAll();
	}
	
	@Override
	public List<Employee> getCustomSortedListOfEmployeesByFirstName(Direction dir) {
		return employeeRepository.findAll(Sort.by(dir, "firstName"));
	}

	@Override
	public Employee getSingleEmployeeById(long id) {
		Optional<Employee> result = employeeRepository.findById(id);

		Employee employee = null;

		if (result.isPresent()) {
			employee = result.get();
		} else {
			// we didn't find the employee
			throw new RuntimeException("Did not find employee id - " + id);
		}

		return employee;
	}

	@Override
	public Employee updateEmployeeDetails(Employee employee) {
		Optional<Employee> result = employeeRepository.findById(employee.getId());

		if (result.isPresent()) {
			employeeRepository.saveAndFlush(employee);
		} else {
			// we didn't find the employee
			throw new RuntimeException("Did not find employee id - " + employee.getId());
		}

		return employee;
	}

	@Override
	public String deleteEmployeeById(long id) {
		Optional<Employee> result = employeeRepository.findById(id);

		if (result.isPresent()) {
			employeeRepository.deleteById(id);
		} else {
			// we didn't find the employee
			throw new RuntimeException("Did not find employee record to delete with id - " + id);
		}

		return "Deleted employee id - " + id;
	}

	@Override
	public List<Employee> getEmployeeByFirstName(String firstName) {
		Employee employee = new Employee();
		employee.setFirstName(firstName);

		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				.withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.exact())
				.withIgnorePaths("id", "lastName", "email");
		
		Example<Employee> example = Example.of(employee, exampleMatcher);
		return employeeRepository.findAll(example);
	}
}
