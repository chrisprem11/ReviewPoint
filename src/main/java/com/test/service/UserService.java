package com.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.test.DTO.UserDTO;
import com.test.model.Person;
import com.test.repository.RoleRepository;
import com.test.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	public Person saveDetails(UserDTO userDTO){
		BCryptPasswordEncoder passEncoder =new  BCryptPasswordEncoder();
		Person newUser= new Person();
		newUser.setFirstname(userDTO.getFirstname());
		newUser.setLastname(userDTO.getLastname());
		newUser.setEmail(userDTO.getEmail());
		newUser.setPassword(passEncoder.encode(userDTO.getPassword()));
	
		newUser.setRole(roleRepository.findByUserRole("USER"));
		return userRepository.save(newUser);
	
		
	}
	
	public Optional<Person> findUserByEmail(String email) {
		return userRepository.findOneByEmail(email);
	}
	
	public List <Person> getAllUsers(){
		List <Person> users = (List<Person>) userRepository.findAll();
		return users;
	}
	
	public DataTablesOutput<Person> findAllUsers(DataTablesInput input) {
		return userRepository.findAll(input);
	}
 
}
