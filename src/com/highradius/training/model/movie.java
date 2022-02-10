package com.highradius.training.model;



public class movie {
	
	
	private int id;
	private String title;
	private String description;
	private int releaseYear;
	private String language;
	private String director;
	private String rating;
	private String specialFeatures;
	
	
	public movie(int id, String title, String description, int releaseYear, String language, String director,
			String rating, String specialFeatures) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.language = language;
		this.director = director;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
	}
	
	public movie(String title, String description, int releaseYear, String language, String director, String rating,
			String specialFeatures) {
		super();
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.language = language;
		this.director = director;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getSpecialFeatures() {
		return specialFeatures;
	}
	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}
	
	

}
