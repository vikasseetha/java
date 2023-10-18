package com.example.springbasic;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
/**
 * This method is used for create user based on user object and generate id based on time and send it to repository class save method
 * @param user
 * @return
 * @throws IOException
 */
	public User createUser(User user) throws IOException {

		user.setId(System.currentTimeMillis());

		return userRepository.save(user);

	}
/**
 * This ,Method is used for get the details of a particular user based on id from repositoy class
 * @param id
 * @return
 * @throws IOException
 * @throws ClassNotFoundException
 */
	public User getUser(Long id) throws IOException, ClassNotFoundException {

		return userRepository.findById(id);

	}
/**
 * This method is used for get the details of all users 
 * @return
 * @throws IOException
 * @throws ClassNotFoundException
 */
	public List<User> getUsers() throws IOException, ClassNotFoundException {

		return userRepository.findAll();

	}

/**
 * This method is used for delete the user based on id
 * @param id
 */
	public void deleteUser(long id) {
		userRepository.deleteUser(id);
	}
/**
 * Based On id this method will update details of a particular user
 * @param id
 * @param user
 * @return
 * @throws IOException
 */
	public User updateUser(long id, User user) throws IOException {

		return userRepository.update(id, user);

	}
}
