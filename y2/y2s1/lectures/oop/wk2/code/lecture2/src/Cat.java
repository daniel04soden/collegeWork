// Author: Daniel Soden
// Purpopse: Modelling a cat

public class Cat {
  

  // Fields 

  public int age;
  public char gender; // M or F
  private int lives = 9;
  public int whiskerCount;
  public String name;
  private boolean wantsToPlay;

  // Constructors
  public Cat(String myName, int catAge, char catGender, boolean playTime){
    this.name = myName;
    this.age = catAge;
    this.gender = catGender;
    this.wantsToPlay = playTime;
  }

  // Methods 
  
  public void setPlay(boolean value) {

    this.wantsToPlay = value;
  }
  
  public int numMeow() { 
    int meowNum = 0;

    meowNum = this.age * 10;

    if (this.wantsToPlay == true) {
      meowNum = meowNum*2;
    }

    return meowNum;
    
  }

}
