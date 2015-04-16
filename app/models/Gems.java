package models;

public class Gems implements Comparable<Gems>{
 
	private long Id;
 
	private String name;
	
	private String description;
 
	private String country;
	
	private long price;
 
	public Gems(long id, String name, String description, String country,long price) {
		super();
		Id = id;
		this.name = name;
		this.description = description;
		this.country = country;
		this.price = price;
	}
	
	public Gems(){
		
	}
 
	public long getId() {
		return Id;
	}
 
	public void setId(long id) {
		Id = id;
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	public String getDescription() {
		return description;
	}
 
	public void setDescription(String description) {
		this.description = description;
	}
 
	public String getCountry() {
		return country;
	}
 
	public void setCountry(String country) {
		this.country = country;
	}
	public void setPrice(long price){
		this.price = price;
		
	}
	public long getPrice(){
		return this.price;
	}
	
	public String toString(){
		return "(" + this.Id + ", " + this.name + ", " + this.description 
				+ ", " + this.country + ")";
	}

	@Override
	public int compareTo(Gems arg0) {
		return this.getName().compareTo(arg0.getName());
	}


	
}