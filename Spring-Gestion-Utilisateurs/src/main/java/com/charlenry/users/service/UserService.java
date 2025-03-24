package com.charlenry.users.service;

import java.util.List;

import com.charlenry.users.entities.Role;
import com.charlenry.users.entities.User;
import com.charlenry.users.service.register.RegistrationRequest;

public interface UserService {
	User saveUser(User user);
	User findUserByUsername (String username);
	Role addRole(Role role);
	User addRoleToUser(String username, String rolename);
	List<User> findAllUsers();
	
	// Inscrire un nouvel utilisateur
	User registerUser(RegistrationRequest request);
	
	// Envoie un email à l'utilisateur après inscription pour valider l'adresse email
	public void sendEmailUser(User u, String code);
	
	// Valide le token saisi par l'utilisateur
	public User validateToken(String code);
	
}
