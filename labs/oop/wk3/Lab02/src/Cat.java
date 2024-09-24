/*
 * Author: Daniel Soden
 * Purpose: cat class 
 * */
public class Cat {

    //---------------------------------------
    //	Fields
    //---------------------------------------
      private String name;
      private int playLevel; 



    //---------------------------------------
    //	Constructor
    //---------------------------------------

      public Cat(String catName,int playingLevel){
      this.playLevel = playingLevel;
      this.name = catName;
  }


    //---------------------------------------
    //	Get Methods
    //---------------------------------------
    public String getName() {
     return this.name;
    }


    public int getPlayfulLevel() {
     return this.playLevel;
    }

    //---------------------------------------
    //	Set Method
    //---------------------------------------

      public void setName(String newName) {
       this.name = newName; 
      }

      public void setPlayfulLevel(int newPlayfulLevel) {
       this.playLevel = newPlayfulLevel; 
      }


    //---------------------------------------
    //	Extra Functionality
    //---------------------------------------

    public boolean isPayingAttention(){
    int max = 3;
      int attentionVal = (int)(Math.random()* (max)+1); 

    if (attentionVal == 3) {
     return true;
    } else {
     return false;    
    }
  }

    

}
