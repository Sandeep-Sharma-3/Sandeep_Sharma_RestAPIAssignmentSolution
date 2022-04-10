package employeeManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import employeeManagement.entity.Employee;
import employeeManagement.entity.Role;
import employeeManagement.entity.User;
import employeeManagement.service.EmployeeService;
import employeeManagement.service.RoleService;
import employeeManagement.service.UserService;

@RestController
@RequestMapping("/employeeManagement")
public class EmployeeRestController {
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired 
	private RoleService roleService;

	@Autowired
	private UserService userService;
	
	@PostMapping("/addSingleEmployee")
	public String addSingleEmployee(@RequestBody Employee employee) {
		return employeeService.addEmployeewithSaveAndFlush(employee);
	}
	
	@PostMapping("/addAllEmployees")
	public String addAllEmployees(@RequestBody List<Employee> employee) {
		return employeeService.addAllEmployees(employee);
	}
	
	@GetMapping("/listAllEmployees")
	public List<Employee> listAllEmployees() {
		return employeeService.listAllEmployees();
	}
	
	@GetMapping("/getSortedListOfEmployeesByFirstName/{dir}")
	public List<Employee> getCustomSortedListOfEmployeesByFirstName(@PathVariable Direction dir){
		return employeeService.getCustomSortedListOfEmployeesByFirstName(dir);
	}
	
	@GetMapping("/getSingleEmployeeById/{id}")
	public Employee getSingleEmployeeById(@PathVariable long id) {
		return employeeService.getSingleEmployeeById(id);
	}
	
	@PostMapping("/getEmployeeByFirstName")
	public List<Employee> getEmployeeByFirstName(@RequestBody String firstName){
		return employeeService.getEmployeeByFirstName(firstName);
	}
	
	@PutMapping("/updateEmployeeDetails")
	public Employee updateEmployeeDetails(@RequestBody Employee employee) {
		return employeeService.updateEmployeeDetails(employee);
	}
	
	@DeleteMapping("/deleteEmployeeById/{id}")
	public String deleteEmployeeById(@PathVariable long id) {
		return employeeService.deleteEmployeeById(id);
	}
	
	@PostMapping("/addSingleRole")
	public String addSingleRole(@RequestBody Role role) {
		return roleService.addRolewithSaveAndFlush(role);
	}
	
	@PostMapping("/addAllRoles")
	public String addAllRoles(@RequestBody List<Role> roles) {
		return roleService.addAllRoles(roles);
	}
	
	@PostMapping("/addSingleUser")
	public String addSingleUser(@RequestBody User user) {
		return userService.addUserwithSaveAndFlush(user);
	}
	
	@PostMapping("/addAllUsers")
	public String addAllUsers(@RequestBody List<User> users) {
		return userService.addAllUsers(users);
	}
}
