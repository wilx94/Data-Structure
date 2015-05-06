package models;

 
import models.LongComparator;

import java.util.ArrayList;
 
 
public class GemsList {
	
	private BSTImp<Long,Gems> personList;
	private ArrayList<Long> keyList = new ArrayList<Long>();
	int counter = 1;
	int size = 0;
	
	private GemsList(){
		this.personList = new BSTImp<Long,Gems>(new LongComparator());
	}
	
	public Gems addGem(Gems obj){
		long id = this.counter++;
		obj.setId(id);
		this.personList.add(obj.getId(),obj);
		System.out.println(this.personList.get(obj.getId()));
		size++;
		return obj;
	}
	
	public Gems getGemById(long id){
		for (Gems p : this.personList){
			if(p.getId() == id){
			System.out.println(p.toString());
			return p;
			}
		}
		return null;
	}
	
	public Gems[] getAllGems(){
		Gems result[] = new Gems[this.personList.size()+1];
		int i = 0;
		for(Gems gem: this.personList){
			result[i] = gem;
			i++;
		
		}
		return result;
	}
	
 
	
	public boolean deleteGem(long id){
	Gems g = this.personList.remove((long)id);
		if(g!=null){
			this.size--;
			return true;
		}
		return false;
	}
	
	public Gems updateGem(Gems obj){
		
		for(Gems gem : this.personList){
			if(gem.getId()==obj.getId()){
				gem.setCountry(obj.getCountry());
				gem.setName(obj.getName());
				gem.setDescription(obj.getDescription());
				gem.setPrice(obj.getPrice());
				return gem;
			}
		}
		return null;
		
	}
	
	public ArrayList<Gems> searchByName(String word){
		ArrayList<Gems> result = new ArrayList<Gems>();
		
		for(Gems gem : this.personList){
			if(gem.getName().contains(word)){
				result.add(gem);
				
			}
		}
		
		return result;
		
	}
	
	public ArrayList<Gems> searchByPrice(long min, long max){
		
	
		
		ArrayList<Gems> result = new ArrayList<Gems>();
		
		for(Gems gem : this.personList){
			if(gem.getPrice()<=max && gem.getPrice()>=min){
				result.add(gem);
			}
		}
		
		return result;
		
	}
	
	public ArrayList<Gems> searchByDescription(String word){
		ArrayList<Gems> resultList = new ArrayList<Gems>();
		
		for(Gems gem: this.personList){
			if(gem.getDescription().contains(word))
				resultList.add(gem);
		}
		
		return resultList;
	}
	
	public ArrayList<Gems> searchByReview(String word){
		ArrayList<Gems> resultList = new ArrayList<Gems>();
		
		
		//primero antes de esto quiero verificar si hay espacio para dividirlo 
		//entre todos y asi buscar por cada palabra individual
		
		
		for(Gems gem: this.personList){
			for(int i = 0; i < gem.getReviews().size(); i++){
			if(gem.getReviews().get(i).getAuthor().contains(word) || 
					gem.getReviews().get(i).getBody().contains(word)){
									resultList.add(gem);
			}
			}
		}
		
		return resultList;
	}
	
	
	public int size(){
		return this.size;
	}
	
	private static GemsList singleton= new GemsList();
	
	public static GemsList getInstance(){
		return singleton;
	}
}