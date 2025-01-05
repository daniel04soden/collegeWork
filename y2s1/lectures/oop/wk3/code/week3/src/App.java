public class App {
    public static void main(String[] args){
    Sword sword1 = new Sword("Silver", 2.3, 10);
    System.out.println(sword1.getDurability());
    sword1.setDurability(20);
    System.out.println(sword1.getDurability());
    }
}
