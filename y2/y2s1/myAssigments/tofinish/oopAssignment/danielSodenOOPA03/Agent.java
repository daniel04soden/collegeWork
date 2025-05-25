public class Agent {
  private final int id;

  public Agent(int _id) {
    this.id = _id;
  }

  public final int getId() {
    return id;
  }

  public boolean checkEqualTo(Agent a) {
    return this.id == a.id;
  }

}
