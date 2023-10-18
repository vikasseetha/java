package com.example.springbasic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping()
	public User createUser(@RequestBody User user) throws IOException {
		return userService.createUser(user);
	}

	@GetMapping("/{id}")
	public User getUser(@PathVariable Long id) throws IOException, ClassNotFoundException {

		return userService.getUser(id);

	}

	@GetMapping("/all")
	public List<User> getUsers() throws IOException, ClassNotFoundException {

		return userService.getUsers();

	}

	@PutMapping("/{id}")
	public void updateUser(@PathVariable long id, @RequestBody User user) throws IOException {
		user.setId(id);
		userService.updateUser(id, user);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable long id) throws IOException, ClassNotFoundException {
		userService.deleteUser(id);
	}
}
