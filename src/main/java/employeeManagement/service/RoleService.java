package employeeManagement.service;

import java.util.List;

import employeeManagement.entity.Role;

public interface RoleService {

	String addRolewithSaveAndFlush(Role role);

	String addAllRoles(List<Role> roles);

}