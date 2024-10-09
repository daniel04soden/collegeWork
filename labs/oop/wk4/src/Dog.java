
public class Dog {

    //---------------------------------------
    //	Fields
    //---------------------------------------
		private String name;
		private String breed;
		private int boldnessLevel;
		private Toy dogToy = new Toy(true, 12);
		private boolean toySound = dogToy.getSound();
		private int resistanceToy = dogToy.getResistance();


    //---------------------------------------
    //	Constructor
    //---------------------------------------
		public Dog(String n,int bl,String b,boolean s,int r  ){
		this.name = n;
		this.boldnessLevel = bl;
		this.breed = b;
		this.toySound = s;
		this.resistanceToy = r;
	}
		

		public Dog(String n,int bl,String b,Toy t){
		this.name = n;
		this.boldnessLevel = bl;
		this.breed = b;
		this.dogToy = t;
	}
		public Dog(Dog d, boolean shallowMode){
		if (shallowMode==true) {
			Dog d1 = new Dog(this.name, this.boldnessLevel, this.breed, this.dogToy);
		} else {
			Dog d1 = d;
		}
		}

		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getBreed() {
			return breed;
		}


		public void setBreed(String breed) {
			this.breed = breed;
		}


		public int getBoldnessLevel() {
			return boldnessLevel;
		}


		public void setBoldnessLevel(int boldnessLevel) {
			this.boldnessLevel = boldnessLevel;
		}


		public Toy getToy() {
			return dogToy;
		}


		public void setToy(Toy dogToy) {
			this.dogToy = dogToy;
		}


		public boolean getToySound() {
			return toySound;
		}


		public void setToySound(boolean toySound) {
			this.toySound = toySound;
		}


		public int getResistanceToy() {
			return resistanceToy;
		}


		public void setResistanceToy(int resistanceToy) {
			this.resistanceToy = resistanceToy;
		}
		
		public void increaseBoldnessLevel(){
	if (this.boldnessLevel < 10) {
		this.boldnessLevel++;
	} else {
	System.out.println("Bark level at max");
		}
	}
	public void Bark(int happinessLevel){
	for (int i = 0; i < happinessLevel; i++) {
	System.out.println("bark");	
	}

}
}
