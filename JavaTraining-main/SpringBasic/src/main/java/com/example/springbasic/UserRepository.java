package com.example.springbasic;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
	private final String dataDir = "C:/Users/NikhilMengu/rgt_java_sts/springbasic/Data/";
	
	/**
	 * This method will save the user to data dataDir and saved as file  dataDir will represent location of a data directory
	 * @param user
	 * @return
	 * @throws IOException
	 */
	public User save(User user) throws IOException {

		File file = new File(dataDir + user.getId() + "userDetails.ser");

		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {

			out.writeObject(user);

		}

		return user;

	}
	
	
/**
 * This method will get the details from data and fetched file based on id
 * @param id
 * @return
 * @throws IOException
 * @throws ClassNotFoundException
 */
	public User findById(Long id) throws IOException, ClassNotFoundException {

		File file = new File(dataDir + id + "userDetails.ser");

		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {

			return (User) in.readObject();

		}

	}

	public List<User> findAll() throws IOException, ClassNotFoundException {
		List<User> users = new ArrayList<>();
		File dir = new File(dataDir);
		File[] files = dir.listFiles();
		for (File file : files) {
			try (InputStream inputStream = new FileInputStream(file);
					ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
				users.add((User) objectInputStream.readObject());
			}
		}
		return users;
	}

	/**
	 * This method will delete the file which is available in data directory
	 * @param id
	 */
	public void deleteUser(long id) {
		File file = new File(dataDir + id + "userDetails.ser");
		file.delete();
	}

	/**
	 * This method is update the data in directory
	 * @param id
	 * @param user
	 * @return
	 * @throws IOException
	 */
	public User update(long id, User user) throws IOException {

		File file = new File(dataDir + id + "userDetails.ser");

		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {

			out.writeObject(user);

		}

		return user;

	}
}
