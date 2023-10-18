package com.rgt.rgtmessaging.exception;

import com.rgt.rgtmessaging.entity.Tweet;
import com.rgt.rgtmessaging.entity.User;
import com.rgt.rgtmessaging.exception.exception.UserAlreadyExistsException;
import com.rgt.rgtmessaging.exception.exception.UserNotFoundException;
import com.rgt.rgtmessaging.exception.helper.RgtMessaging;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class RgtMain {
	private static RgtMessaging rgtMessaging;
	private static Scanner scanner;
	private static User currentuser;
	private static HashMap<String, User> users;
	private static String DATA_FILE = "data.json";

	public static void main(String[] args) throws UserNotFoundException {
		rgtMessaging = new RgtMessaging();
		rgtMessaging.loadData(DATA_FILE);
		scanner = new Scanner(System.in);
		users = new HashMap<>();
		currentuser = null;
		boolean exit = true;
		System.out.println("Welcome to RGTMessaging!");
		while (exit) {
			System.out.println("Please choose an option:");
			System.out.println("1. Register");
			System.out.println("2. Log in");
			System.out.println("3. Exit");

			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
				case 1:
					registerUser();
					break;
				case 2:
					loginUser();
					break;
				case 3:
					exit = false;
					break;
				default:
					System.out.println("Invalid option. Please try again.");
			}
			rgtMessaging.saveData(DATA_FILE);
		}
		System.out.println("Thank you for using RGTMessaging!");

	}

	private static void registerUser() throws UserAlreadyExistsException {
		System.out.println("Enter a username:");
		String username = scanner.nextLine();

		System.out.println("Enter a password:");
		String password = scanner.nextLine();

		System.out.println("Enter your name:");
		String name = scanner.nextLine();

		System.out.println("Enter your bio:");
		String bio = scanner.nextLine();
		try {
			if (rgtMessaging.registerUser(username, password, name, bio)) {
				System.out.println("User registered successfully!");
			}
		} catch (UserAlreadyExistsException e) {
			System.out.println("User registration failed: " + e.getMessage());
		}
	}

	private static void loginUser() {
		System.out.println("Enter your username:");
		String username = scanner.nextLine();

		System.out.println("Enter your password:");
		String password = scanner.nextLine();

		try {
			User login = rgtMessaging.login(username, password);
			if (login != null) {
				currentuser = login;
				System.out.println("Login successful. Welcome, " + username + "!");
				loggedInMenu(username);
			} else {
				System.out.println("Login failed. Invalid username or password.");
			}
		} catch (UserNotFoundException e) {
			System.out.println("Login failed. " + e.getMessage());
		}
	}

	private static void loggedInMenu(String username) {
		boolean isLoggedIn = true;
		while (isLoggedIn) {
			System.out.println("\n WELCOME USER : " + username);
			System.out.println("Please choose an option:");
			System.out.println("1. Follow user");
			System.out.println("2. Post tweet");
			System.out.println("3. View your timeline");
			System.out.println("4. Search for users");
			System.out.println("5. Search for tweets");
			System.out.println("6. Delete a tweet");
			System.out.println("7. Display profile");
			System.out.println("8. Unfollow user");
			System.out.println("9. Logout");

			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
				case 1:
					followUser();
					break;
				case 2:
					postTweet(username);
					break;
				case 3:
					viewTimeline();
					break;
				case 4:
					searchForUsers();
					break;
				case 5:
					searchForTweets();
					break;
				case 6:
					deleteTweets();
					break;
				case 7:
					viewProfile(username);
					break;
				case 8:
					unFollowUser();
					break;
				case 9:
					logout();
					isLoggedIn = false;
					break;
				default:
					System.out.println("Invalid option. Please try again.");
			}
		}
	}

	private static void followUser() {
		System.out.println("Enter the username of the user you want to follow:");
		String username = scanner.nextLine();

		if (rgtMessaging.follow(username)) {
			System.out.println(" User followed successfully with username " + username);
		} else {
			System.out.println("User not found.");
		}
	}

	private static void postTweet(String username) {
		System.out.println("\n--- Post Tweet ---");
		System.out.print("Enter your tweet: ");
		String tweetContent = scanner.nextLine();
		rgtMessaging.postTweet(username, tweetContent);
		System.out.println("Tweet posted successfully!");

	}

	/**
	 * This method view Timeline is used to view the user timeline it will return
	 * the TweetId,Author, content,Timestamp
	 */
	public static void viewTimeline() {
		if (currentuser == null) {
			System.out.println("You are not logged in. Please log in first.");
			return;
		}

		List<Tweet> timelineTweets = new ArrayList<>();
		for (String username : currentuser.getFollowings()) {
			User user = users.get(username);
			if (user != null) {
				timelineTweets.addAll(user.getTweets());
			}
		}

		timelineTweets.addAll(currentuser.getTweets());
		timelineTweets.sort((t1, t2) -> Long.compare(t2.getTimestamp(), t1.getTimestamp()));
		if (timelineTweets.isEmpty()) {
			System.out.println("Your timeline is empty.");
		} else {
			System.out.println("Your timeline:");

			for (Tweet tweet : timelineTweets) {
				long times = tweet.getTimestamp();
				Timestamp timestamp = new Timestamp(times);
				System.out.println("Tweet ID: " + tweet.getId());
				System.out.println("Author: @" + tweet.getAuthor());
				System.out.println("Content: " + tweet.getContent());
				System.out.println("Timestamp: " + timestamp);
				System.out.println();
			}
		}
	}

	private static void searchForUsers() {
		System.out.println("\n--- Search User ---");
		System.out.print("Enter the username you want to search for: ");
		String searchUsername = scanner.nextLine();

		User user = rgtMessaging.searchUsers(searchUsername);
		if (user != null) {
			System.out.println("User found:   ");
			System.out.println("Username: " + user.getUsername());
			System.out.println("Name: " + user.getName());
			System.out.println("Bio: " + user.getBio());
			System.out.println("Followers: " + user.getFollowers().size());
			System.out.println("Following: " + user.getFollowings().size());
			System.out.println("Tweets: " + user.getTweets().size());
		} else {
			System.out.println("User not found.");
		}
	}

	private static void searchForTweets() {
		System.out.println("\n--- Search Tweet ---");
		System.out.print("Enter the tweet content you want to search for: ");
		String searchContent = scanner.nextLine();
		List<Tweet> tweets = rgtMessaging.searchTweets(searchContent);
		if (!tweets.isEmpty()) {
			for (Tweet tweet : tweets) {
				long times = tweet.getTimestamp();
				Timestamp timestamp = new Timestamp(times);
				System.out.println("ID: " + tweet.getId());
				System.out.println("Content: " + tweet.getContent());
				System.out.println("Author: " + tweet.getAuthor());
				System.out.println("Timestamp: " + timestamp);
				System.out.println();
			}
		} else {
			System.out.println("No tweets found.");
		}
	}

	/**
	 * This method is used to delete the tweet with the help of TweetID
	 */
	private static void deleteTweets() {
		if (currentuser == null) {
			System.out.println("You are not logged in. Please log in first.");
			return;
		}
		System.out.println("Enter tweet ID:");
		String tweetId = scanner.nextLine();
		String tweet = currentuser.getTweets().get(0).getId();
		if (tweet.equalsIgnoreCase(tweetId)) {
			currentuser.deleteTweet(tweetId);
			System.out.println("Tweet deleted successfully.");
		} else {
			System.out.println("Tweet ID NOT Found");
		}

	}
	private static void viewProfile(String username) {
		System.out.println("\n--- View Profile ---");
		User profile = rgtMessaging.getProfile(username);
		if (profile != null) {
			System.out.println("Username: " + profile.getUsername());
			System.out.println("Name: " + profile.getName());
			System.out.println("Bio: " + profile.getBio());
			System.out.println("Followers: " + profile.getFollowers().size());
			System.out.println("Following: " + profile.getFollowings().size());
			System.out.println("Tweets: " + profile.getTweets().size());
		} else {
			System.out.println("Profile not found.");
		}
	}

	private static void unFollowUser() {
		System.out.println("Enter the username of the user you want to unfollow:");
		String username = scanner.nextLine();

		if (rgtMessaging.unfollow(username)) {
			System.out.println("User unfollowed successfully " + username);
		} else {
			System.out.println("User not found");
		}
	}

	private static void logout() {
		System.out.println("\n--- Logout ---");
		rgtMessaging.logout();
	}
}
