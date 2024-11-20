public class Customer extends Agent {
  public String firstName;
  public String lastName;

  public Customer(String _firstName, String _lastName, int _customerID) {
    super(_customerID);
    this.firstName = _firstName;
    this.lastName = _lastName;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  // Extra functionality

  public void printCustomerDetails() {
    String fullName = firstName.concat(lastName);
    System.out.println("ID:" + super.getId() + "\nName:" + fullName);
  }

}
