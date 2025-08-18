
public class Dog {

    //---------------------------------------
    //	Fields
    //---------------------------------------

    private String myName;
    private int myBoldnessLevel;
    private String myBreed;

    //---------------------------------------
    //	Constructor
    //---------------------------------------
    public Dog(String name,int boldLevel, String breed){
    this.myName = name;
    this.myBoldnessLevel = boldLevel;
    this.myBreed = breed;
  }


    //---------------------------------------
    //	Get Methods
    //---------------------------------------
    public String getName(){
    return this.myName;
  }

    public int getBoldnessLevel(){
    return this.myBoldnessLevel;
  }

    public String getBreed(){
    return this.myBreed;
  }
    //---------------------------------------
    //	Set Methods
    //---------------------------------------
    public void setName(String newName){
    this.myName = newName;
  }

    public void setBoldnessLevel(int newBoldLevel){
    this.myBoldnessLevel = newBoldLevel;
  }

    public void setBreed(String newBreed){
    this.myBreed = newBreed;
  }
    //---------------------------------------
    //	Extra Functionality
    //---------------------------------------
    
  public void increaseBoldnessLevel() {
   this.myBoldnessLevel += 1;
  }

  public void bark(int happinessLevel){
    for (int i = 0; i < happinessLevel; i++) {
      System.out.print("bark");
    }
  }



}
