package models;
  
 
public class GemsList {
	
	private SortedCircularDoublyLinkedList<Gems> personList;
	int counter = 1;
	
	private GemsList(){
		
		
		this.personList = new SortedCircularDoublyLinkedList<Gems>();
		
	}
	
	public Gems addGem(Gems obj){
		long id = this.counter++;
		obj.setId(id);
		this.personList.add(obj);
		return obj;
	}
	
	public Gems getGemById(long id){
		for (Gems p : this.personList){
			if (p.getId() == id){
				return p;
			}
		}
		return null;
	}
	
	public Gems[] getAllGems(){
		Gems result[] = new Gems[this.personList.size()];
		for (int i=0; i < this.personList.size(); ++i){
			result[i] = this.personList.get(i);
		}
		return result;
	}
	
 
	
	public boolean deleteGem(long id){
		int target = -1;
		
		for (int i=0; i< this.personList.size(); ++i){
			if (this.personList.get(i).getId() == id){
				target = i;
				break;
			}
		}
		if (target == -1){
			return false;
		}
		else {
			this.personList.remove(target);
			return true;
		}
	}
	
	public Gems updateGem(Gems obj){
		int target = -1;
		
		for (int i=0; i< this.personList.size(); ++i){
			if (this.personList.get(i).getId() == obj.getId()){
				target = i;
				break;
			}
		}
		if (target == -1){
			return null;
		}
		else {
			Gems P = this.personList.get(target);
			P.setCountry(obj.getCountry());
			P.setName(obj.getName());
			P.setDescription(obj.getDescription());
			return P;
		}
	}
	
	private static GemsList singleton= new GemsList();
	
	public static GemsList getInstance(){
		return singleton;
	}
}