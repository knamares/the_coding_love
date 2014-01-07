package com.kna.the_coding_love.model;

public class GifPost {

	private String title;
	private String imageUrl;
	private String username;
	private String email;
	private String gifPostUrl;

	public GifPost() {
		super();
	}

	public GifPost(String title, String imageUrl, String username, String email, String gifPostUrl) {
		super();
		this.title = title;
		this.imageUrl = imageUrl;
		this.username = username;
		this.email = email;
		this.gifPostUrl = gifPostUrl;
	}
	
	public String getGifPostUrl() {
		return gifPostUrl;
	}

	public void setGifPostUrl(String gifPostUrl) {
		this.gifPostUrl = gifPostUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
