package models;

import java.util.ArrayList;


public class Gems implements Comparable<Gems>{
 
	private long Id;
 
	private String name;
	
	private String description;
 
	private String country;
	
	private long price;
	
	private ArrayList<Review> reviews = new ArrayList<Review>();
 
	/**
	 * This Create a Gem
	 * @param id - long, id number
	 * @param name - String, name of the Gem
	 * @param description - String, a description of the Gem
	 * @param country - String, the country of where is the Gem
	 * @param price - long, price of the Gem
	 */
	public Gems(long id, String name, String description, String country,long price, Review review) {
		super();
		Id = id;
		this.name = name;
		this.description = description;
		this.country = country;
		this.price = price;
		this.reviews.add(review);
		
	}
	
	public Gems(){
		
	}
 
	/**
	 *Give the id of the Gem
	 * @return - long, id of the Gem
	 */
	public long getId() {
		return Id;
	}
	/**
	 * Set the id of the Gem
	 * @param id - long, put the id of the Gem
	 */
	public Gems setId(long id) {
		Id = id;
		return this;
	}
	/**
	 * Return the Gem's name
	 * @return - String, return the name of the Gem
	 */
	public String getName() {
		return name;
	}
	/**
	 * Set name of the Gem
	 * @param name - String, the name that you want to give to the Gem.
	 */
	public Gems setName(String name) {
		this.name = name;
		return this;
	}
	/**
	 * Return the description of the Gem
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Set the Gem description
	 * @param description - String, the description of the Gem
	 */
	public Gems setDescription(String description) {
		this.description = description;
		return this;
	}
	/**
	 * Return the country of the Gem
	 * @return - String
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * Set the Gem's country
	 * @param country - String
	 */
	public Gems setCountry(String country) {
		this.country = country;
		
		return this;
	}
	/**
	 * Set the price of the Gem
	 * @param price - long
	 */
	public Gems setPrice(long price){
		this.price = price;
		return this;
		
	}
	/**
	 * Get the price of the Gem
	 * @return - long
	 */
	public long getPrice(){
		return this.price;
	}
	/**
	 * Return the Strig of the Gem.
	 */
	public String toString(){
		return "(" + this.Id + ", " + this.name + ", " + this.description 
				+ ", " + this.country + ")";
	}

	/**
	 * Compare two Gems.
	 */
	@Override
	public int compareTo(Gems arg0) {
		return this.getName().compareTo(arg0.getName());
	}
	
	public Gems addReview(Review review){
		this.reviews.add(review);
		return this;
	}

	public ArrayList<Review> getReviews(){
		return this.reviews;
	}

	
}