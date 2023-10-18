package com.rgt.rgtmessaging.exception.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import com.rgt.rgtmessaging.entity.Tweet;
import com.rgt.rgtmessaging.entity.User;

	public class DataStore 
	{
	    public static void saveData(HashMap<String, User> users, ArrayList<Tweet> tweets, String fileName) {
	        try (FileWriter fileWriter = new FileWriter(fileName);
	             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
	            for (User user : users.values()) {
	                bufferedWriter.write(user.getUsername() + "," + user.getPassword() + "," + user.getName() + "," + user.getBio());
	                bufferedWriter.newLine();
	            }

	            bufferedWriter.write("---TWEETS---");
	            bufferedWriter.newLine();

	            for (Tweet tweet : tweets) {
	                bufferedWriter.write(tweet.getId() + "," + tweet.getContent() + "," + tweet.getAuthor() + "," + tweet.getTimestamp());
	                bufferedWriter.newLine();
	            }
	        } catch (IOException e) {
	            System.out.println("Error saving data to file: " + e.getMessage());
	        }
	    }

	    public static void loadData(HashMap<String, User> users, ArrayList<Tweet> tweets, String fileName) {
	        try (FileReader fileReader = new FileReader(fileName);
	             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
				String line;
				boolean isTweetSection = false;

				while ((line = bufferedReader.readLine()) != null) {
					if (line.equals("---TWEETS---")) {
						isTweetSection = true;
						continue;
					}

					String[] data = line.split(",");

					if (!isTweetSection) {
						String username = data[0];
						String password = data[1];
						String name = data[2];
						String bio = data[3];
						User user = new User(username, password, name, bio);
						users.put(username, user);
					} else {
						String tweetId = data[0];
						String content = data[1];
						String author = data[2];
						long timestamp = Long.parseLong(data[3]);
						Tweet tweet = new Tweet(tweetId, content, author, timestamp);
						tweets.add(tweet);
					}
				}
			} catch (IOException e) {
	            System.out.println("Error loading data from file: " + e.getMessage());
	        }
	    }
}
