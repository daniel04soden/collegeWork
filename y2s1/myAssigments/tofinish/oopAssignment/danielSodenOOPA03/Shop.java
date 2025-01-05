//------------------
//IMPORTS
//-----------------


//-----------------
//
// INTERFACE SHOP
//
//-----------------

public interface Shop {
	// User methods	

	// Method to add a customer into our system
		
	int addCustomer(String _name, int _id);

	int removeCustomer(String _name, int _id);

	// Product Methods
	
	void addProduct(String _name,int _id);

	void removeProduct(String _name,int _id);

	int returnProduct(int _id, int _purchaseID);

	// Order methods 
	
	int placeOrder();
	int cancelOrder();

}
