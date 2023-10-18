package com.rgt.rgtmessaging.entity;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String username;
	private String password;
	private String name;
	private String bio;
	private List<String> followings;
	private List<String> followers;
	private List<Tweet> tweets;

	public User(String username, String password, String name, String bio) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.bio = bio;
		this.followings = new ArrayList<>();
		this.followers = new ArrayList<>();
		this.tweets = new ArrayList<>();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public List<String> getFollowings() {
		return followings;
	}

	public void setFollowings(List<String> followings) {
		this.followings = followings;
	}

	public List<String> getFollowers() {
		return followers;
	}

	public void setFollowers(List<String> followers) {
		this.followers = followers;
	}

	public List<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}

	public void postTweet(String content) {
		long timestamp = System.currentTimeMillis();
		String tweetId = Long.toString(timestamp);
		Tweet tweet = new Tweet(tweetId, content, this.username);
		tweets.add(tweet);
	}

	public void follow(String followeeUsername) {
		followings.add(followeeUsername);
	}

	public void unfollow(String username) {
		followings.remove(username);
	}

	public void deleteTweet(String tweetId) {
		tweets.removeIf(tweet -> tweet.getId().equals(tweetId));
	}
}
