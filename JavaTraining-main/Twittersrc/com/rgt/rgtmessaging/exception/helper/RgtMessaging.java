package com.rgt.rgtmessaging.exception.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.rgt.rgtmessaging.entity.Tweet;
import com.rgt.rgtmessaging.entity.User;
import com.rgt.rgtmessaging.exception.exception.UserAlreadyExistsException;
import com.rgt.rgtmessaging.exception.exception.UserNotFoundException;

public class RgtMessaging {
	private HashMap<String, User> users;
	private ArrayList<Tweet> tweets;
	private User currentUser;

	public RgtMessaging() {
		users = new HashMap<>();
		tweets = new ArrayList<>();
	}

	/**
	 * This method is used for Register the user
	 * 
	 * @param username
	 * @param password
	 * @param name
	 * @param bio
	 * @return
	 * @If user Already exists it throws UserAlreadyExistsException
	 */
	public boolean registerUser(String username, String password, String name, String bio)
			throws UserAlreadyExistsException {
		if (users.containsKey(username)) {
			throw new UserAlreadyExistsException("User with  '" + username + "' already exists");
		}
		User user = new User(username, password, name, bio);
		users.put(username, user);
		return true;
	}

	/**
	 * This method is used for Login the User
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @If User is not found  it throws UserNotFoundException
	 */
	public User login(String username, String password) throws UserNotFoundException {
		User user = users.get(username);

		if (user == null) {
			throw new UserNotFoundException("User not found.");
		}

		if (user != null && user.getPassword().equals(password)) {
			currentUser = user;
			return user;
		}
		return null;
	}

	/**
	 * This is method is used to follow the user with userName
	 * 
	 * @param username
	 * @return
	 */
	public boolean follow(String username) {
		if (currentUser == null) {
			return false;
		}
		User user = users.get(username);
		if (user != null && !currentUser.getFollowings().contains(username)) {
			currentUser.follow(username);
			user.getFollowers().add(currentUser.getUsername());
			return true;
		}
		return false;
	}

	/**
	 * This is method is used to unFollow the user
	 * we can unfollow the user by entering username
	 * 
	 * @param username
	 * @return
	 */
	public boolean unfollow(String username) {
		if (currentUser == null) {
			return false;
		}
		User user = users.get(username);
		if (user != null && currentUser.getFollowings().contains(username)) {
			currentUser.unfollow(username);
			user.getFollowers().remove(currentUser.getUsername());
			return true;
		}
		return false;
	}

	/**
	 * This method is used to post the TWEET with the content
	 * 
	 * @param username
	 * @param content
	 */
	public void postTweet(String username, String content) {
		if (users.containsKey(username)) {
			User user = users.get(username);
			user.postTweet(content);
		}
	}

	/**
	 * This method is used to Search the any user with the userName in the user list
	 * we will get UserName,Name,Bio,Followers,Following,Tweets
	 * 
	 * @param username
	 * @return
	 */
	public User searchUsers(String username) {
		for (User user : users.values()) {
			if (user.getUsername().equalsIgnoreCase(username)) {
				return user;
			}
		}
		return null;
	}

	/**
	 * This method is used to Search the Tweet with the TweetContent it will get the
	 * details TweetId,Author,content,Timestamp
	 * 
	 * @param username
	 * @return
	 */
	public List<Tweet> searchTweets(String username) {
		List<Tweet> searchResults = new ArrayList<>();

		for (User user : users.values()) {
			List<Tweet> userTweets = user.getTweets();
			for (Tweet tweet : userTweets) {
				if (tweet.getContent().toLowerCase().contains(username.toLowerCase())) {
					searchResults.add(tweet);
				}
			}
		}
		return searchResults;
	}

	/**
	 * This method is used to get the Profile details of user after giving username
	 * 
	 * @param username
	 * @return UserName,Name,Bio,Followers,Following,Tweets
	 */
	public User getProfile(String username) {
		if (users.containsKey(username)) {
			return users.get(username);
		} else {
			return null;
		}
	}

	/**
	 * This method is used to logout the user
	 */
	public void logout() {
		currentUser = null;
		System.out.println("Logged out successfully.");
	}

	public void saveData(String fileName) {
		DataStore.saveData(users, tweets, fileName);
	}

	public void loadData(String fileName) {
		DataStore.loadData(users, tweets, fileName);
	}
}
