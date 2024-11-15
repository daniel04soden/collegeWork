public class Customer{
    public String firstName;
    public String lastName;
    private int customerID;

    public Customer(String _firstName, String _lastName, int _customerID){
        this.firstName = _firstName;
        this.lastName = _lastName;
        this.customerID = _customerID; // Will be implemented as an automatic incrimentation
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

    public int getCustomerID() {
        return this.customerID;
    }

    private void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    // Extra functionality

    public void printCustomerDetails(){
        String fullName = firstName.concat(lastName);
        System.out.println("ID:" + this.customerID + "\nName:" + fullName);
    }

    
    
}