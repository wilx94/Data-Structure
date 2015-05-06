import models.Gems;
import models.GemsList;
import models.Review;


public class Main {

	public static void main(String[] args) {
	//	Gems g = new Gems(1, "tu", "si se puede", "Pr", 4545, new Review("si", "sadf", 5, "adsf"));
		
		GemsList gl = GemsList.getInstance();
		
		//gl.addGem(g);
		
		System.out.println(gl.deleteGem((long)1));

	}

}
