public class Sword {


  // Fields
  public String colour;
  public double sharpness;
  private int durability;

  // Constructors:
  

  public Sword(String myColour, double mySharp, int myDura) {
    this.colour = myColour;
    this.sharpness = mySharp;
    this.durability = myDura;
  }

  // Sets
  
  public void setColour(String newColor){
    this.colour = newColor;
  }
  
  public void setSharpness(Double newSharp){
    this.sharpness = newSharp;
  }

  public void setDurability(int newDura){
    this.durability = newDura;
  }
  
  // Gets
  
  public int getDurability(){
    return this.durability;
  }


  public double getSharpness(){
    return this.sharpness;
  }

  public String getColour(){
    return this.colour;
  }
  // Extra functionality
    public double swingSword(double enemyDefence){
    return this.durability - (0.3*enemyDefence);
  }  
    public double fixSword(double enemyDefence){
    return this.durability + (this.durability*0.5);
  }  
}
