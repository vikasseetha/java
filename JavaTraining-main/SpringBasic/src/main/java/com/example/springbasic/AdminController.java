package com.example.springbasic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@GetMapping("/all")
	public List<User> getAllUsers() throws IOException, ClassNotFoundException {
		return adminService.getAllUsers();
	}

	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable long id) throws IOException, ClassNotFoundException {
		return adminService.getUserById(id);
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable long id) throws IOException, ClassNotFoundException {
		adminService.deleteUser(id);
	}
}
