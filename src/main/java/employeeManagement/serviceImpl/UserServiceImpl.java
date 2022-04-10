package employeeManagement.serviceImpl;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import employeeManagement.entity.Role;
import employeeManagement.entity.User;
import employeeManagement.repository.RoleRepository;
import employeeManagement.repository.UserRepository;
import employeeManagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	BCryptPasswordEncoder bcryptEncoder;

	@Override
	public String addUserwithSaveAndFlush(User user) {
		if (userRepository.getUserByUsername(user.getUsername()) != null) {
			return "User with username " + user.getUsername() + " already exists -> " + userRepository.getUserByUsername(user.getUsername());
		}
		
		Optional<User> result1 = userRepository.findById(user.getId());
		if (result1.isPresent()) {
			return "User with id " + user.getId() + " already exists -> " + result1.get();
		} else {
			List<Role> roles = user.getRoles();
			for (Iterator<Role> iterator = roles.iterator(); iterator.hasNext();) {
				Role role = (Role) iterator.next();
				if (roleRepository.findById(role.getId()).isPresent()
						&& roleRepository.findById(role.getId()).get().getName().trim().equals(role.getName().trim())) {
					continue;
				} else {
					return "Role Id - Name mismatch! ( id = " + role.getId() + " doesn't have name = " + role.getName()
							+ ")";
				}
			}
			user.setPassword(bcryptEncoder.encode(user.getPassword()));
			userRepository.saveAndFlush(user);
			return "User details -> " + user + " saved.";
		}
	}

	@Override
	public String addAllUsers(List<User> users) {
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			if (userRepository.getUserByUsername(user.getUsername()) != null) {
				return "User with username " + user.getUsername() + " already exists -> " + userRepository.getUserByUsername(user.getUsername());
			}
			user.setPassword(bcryptEncoder.encode(user.getPassword()));
			Optional<User> result = userRepository.findById(user.getId());
			if (result.isPresent()) {
				return "User with id " + user.getId() + " already exists -> " + result.get();
			} else {
				List<Role> roles = user.getRoles();
				for (Iterator<Role> iterator1 = roles.iterator(); iterator1.hasNext();) {
					Role role = (Role) iterator1.next();
					if (roleRepository.findById(role.getId()).isPresent()
							&& roleRepository.findById(role.getId()).get().getName().trim().equals(role.getName().trim())) {
						continue;
					} else {
						return "Role Id - Name mismatch! ( id = " + role.getId() + " doesn't have name = " + role.getName()
								+ ")";
					}
				}
			}
		}
		userRepository.saveAll(users);
		userRepository.flush();
		return "All users saved.";
	}
}
