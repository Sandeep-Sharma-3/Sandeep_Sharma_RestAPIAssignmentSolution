package employeeManagement.serviceImpl;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import employeeManagement.entity.Role;
import employeeManagement.repository.RoleRepository;
import employeeManagement.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public String addRolewithSaveAndFlush(Role role) {
		role.setName(role.getName().toUpperCase());
		if (roleRepository.getRoleByName(role.getName()) != null) {
			return "Role with name " + role.getName() + " already exists -> " + roleRepository.getRoleByName(role.getName());
		}
		
		Optional<Role> result = roleRepository.findById(role.getId());
		if (result.isPresent()) {
			return "Role with id " + role.getId() + " already exists -> " + result.get();
		}
		else {
			roleRepository.saveAndFlush(role);
			return "Role details -> " + role + " saved." ;
		}
	}

	@Override
	public String addAllRoles(List<Role> roles) {
		for (Iterator<Role> iterator = roles.iterator(); iterator.hasNext();) {
			Role role = (Role) iterator.next();
			
			role.setName(role.getName().toUpperCase());
			if (roleRepository.getRoleByName(role.getName()) != null) {
				return "Role with name " + role.getName() + " already exists -> " + roleRepository.getRoleByName(role.getName());
			}
			
			Optional<Role> result = roleRepository.findById(role.getId());
			if (result.isPresent()) {
				return "Role with id " + role.getId() + " already exists -> " + result.get();
			}
		}
		roleRepository.saveAll(roles);
		roleRepository.flush();
		return "All roles saved.";
	}
}
