import java.util.Random;

/**
 * Our granchild class for the inheritance lab<br>.
 */

public class GoldenRetriever extends Dog {
    //---------------------------------------
    //	Fields
    //---------------------------------------

		private int lazinessLevel;

    //---------------------------------------
    //	Constructor
    //---------------------------------------


		public GoldenRetriever(String _name, int _age, int _boldLevel, int _lazinessLevel){
		super(_name, _age, _boldLevel);
		this.lazinessLevel = _lazinessLevel;
	}

	public GoldenRetriever(String _name, int _age, int _boldLevel, DogToy _myToy, int _lazinessLevel){
		super(_name, _age, _boldLevel, _myToy);
		this.lazinessLevel = _lazinessLevel;
	}

    //---------------------------------------
    //	Get Methods
    //---------------------------------------

	public int getLazinessLevel() {
		return lazinessLevel;
	}

    //---------------------------------------
    //	Set Method
    //---------------------------------------

	public void setLazinessLevel(int lazinessLevel) {
		this.lazinessLevel = lazinessLevel;
	}
    //---------------------------------------
    //	Extra Functionality
    //---------------------------------------
		public int hoursLazy(){
			int max = 0;
			int min = 24;
			Random rand = new Random();
			int lazyHours = rand.nextInt((max - min) + 1) + min;

			return lazyHours;
	}

}
