import models.Gems;
import models.GemsList;
import models.Review;


public class Main {

	public static void main(String[] args) {
		Gems g = new Gems(1, "tu", "si se puede", "Pr", 4545, new Review());
		
		GemsList gl = GemsList.getInstance();
		
		gl.addGem(g);
		
		Gems[] gems = new Gems[gl.getAllGems().length];
		
		gems = gl.getAllGems();
		for(int i = 0; i < gems.length; i++){
		System.out.println(gems[i].getName()+" "+gems[i].getCountry());
		}

	}

}
