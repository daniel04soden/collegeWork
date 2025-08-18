/*
 * Author: Daniel Soden
 * Purpose: Main for cat class
 * */

public class App {
    public static void main(String[] args){
    Cat c1 = new Cat("Jessy",3,'F',true);
    Cat c2 = new Cat("Daniel",3,'M',false);

    System.out.println(c2.name);
    System.out.println(c1.gender);
    c2.setPlay(true);

    int x = 2;

    System.out.println(c2.numMeow());
    }
}
