package com.example.springbasic;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
	@Autowired
    private UserRepository userRepository;

    public AdminService() {
        this.userRepository = new UserRepository();
    }

    public List<User> getAllUsers() throws IOException, ClassNotFoundException {
        return userRepository.findAll();
    }

    public User getUserById(long id) throws IOException, ClassNotFoundException {
        return userRepository.findById(id);
    }

    public void deleteUser(long user) {
        userRepository.deleteUser(user);
    }
}

