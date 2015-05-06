package models;

public class Review {
		
		String body;
		long stars;
		String author;
		Long createdOn;
		
		public Review(){
			
		}
		
		public Review(long stars,String body,  String author, Long createdOn){
			
			this.body = body;
			this.stars = stars;
			this.author = author;
			this.createdOn = createdOn;
		}
		
		
		
		public String getBody(){
			return this.body;
		}
		
		public String getAuthor(){
			return this.author;
		}
		
		public long getStars(){
			return this.stars;
		}
		public Long getCreatedOn(){
			return this.createdOn;
		}
		
		
}
