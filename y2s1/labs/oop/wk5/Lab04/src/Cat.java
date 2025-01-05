import java.util.Random;

/**
 * Our second child class for the inheritance lab<br>.
 */

public class Cat extends Animal {

    //---------------------------------------
    //	Fields
    //---------------------------------------
			private int attentionSpan;
    //---------------------------------------
    //	Constructor
    //---------------------------------------
		public Cat(String name, int age, int _attentionLevel){
		super(name, age);
		this.attentionSpan = _attentionLevel;
	}



    //---------------------------------------
    //	Get Methods
    //---------------------------------------

		public int getAttentionLevel() {
			return attentionSpan;
		}

    //---------------------------------------
    //	Set Method
    //---------------------------------------

		public void setAttentionLevel(int attentionSpan) {
			this.attentionSpan = attentionSpan;
		}



    //---------------------------------------
    //	Extra Functionality
    //---------------------------------------
			public boolean isPayingAttention(){
			int max = 10;
			int min = 1;
			Random rand = new Random();
			int rando = rand.nextInt((max - min) + 1) + min;
			if (rando <= attentionSpan) {
				return true;
			} else {
				return false;
			}
	}

}
