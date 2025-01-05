import java.util.Random;

/**
 * Our first child class for the inheritance lab<br>.
 */

public class Dog extends Animal {
			
    //---------------------------------------
    //	Fields
    //---------------------------------------
		protected int boldnessLevel;
			protected DogToy toy;


    //---------------------------------------
    //	Constructor
    //---------------------------------------
		public Dog(String _name,int _age,int _boldLevel){
			super(_name, _age);
			this.boldnessLevel = _boldLevel;
	}

		public Dog(String _name,int _age,int _boldLevel,DogToy _myToy){
			super(_name, _age);
			this.toy = _myToy;
			this.boldnessLevel = _boldLevel;
	}


    //---------------------------------------
    //	Get Methods
    //---------------------------------------

		public int getBoldnessLevel() {
			return boldnessLevel;
		}
		public DogToy getMyToy() {
			return toy;
		}


    //---------------------------------------
    //	Set Method
    //---------------------------------------


		public void setBoldnessLevel(int boldnessLevel) {
			this.boldnessLevel = boldnessLevel;
		}


		public void setMyToy(DogToy toy) {
			this.toy = toy;
		}



    //---------------------------------------
    //	Extra Functionality
    //---------------------------------------
			
		public void playWithToy() {
			int max = 1;
			int min = 2;
			Random rand = new Random();
			int rando = rand.nextInt((max - min) + 1) + min;
			switch (rando) {
				case 1:
					if (boldnessLevel >= 10) {
						;	
					} else {
					boldnessLevel++;
					}	
					break;
				case 2:
					;	
					break;

				default:
					;	
					break;
			}
		}
		
	public void bark(int happinessLevel) {
		for (int i = 0; i < happinessLevel; i++) {
		System.out.println("woof");	
		}
	}


}
