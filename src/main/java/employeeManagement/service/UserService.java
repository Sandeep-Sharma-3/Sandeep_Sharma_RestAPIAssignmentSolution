package employeeManagement.service;

import java.util.List;

import employeeManagement.entity.User;

public interface UserService {

	String addUserwithSaveAndFlush(User user);

	String addAllUsers(List<User> users);

}