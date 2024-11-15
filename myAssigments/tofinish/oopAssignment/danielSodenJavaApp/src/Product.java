public abstract class Product{
    public double productPrice;
    private String productID;
    private int currentStock;
    private int amountOnOrder;
    
    public Product(double _productPrice, int _currentStock, int _amountOnOrder,String _productID){
        this.productPrice = _productPrice;
        this.currentStock = _currentStock;
        this.productID = _productID;
        this.amountOnOrder = _amountOnOrder;
    }

    public double getProductPrice() {
        return this.productPrice;
    }

    public void changeProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductID() {
        return this.productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getCurrentStock() {
        return this.currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public int getAmountOnOrder() {
        return this.amountOnOrder;
    }

    public void setAmountOnOrder(int amountOnOrder) {
        this.amountOnOrder = amountOnOrder;
    }

    // Extra functionality

    public void orderMore(int orderAmount){
        this.amountOnOrder+=orderAmount;
    }

    public void receiveMore(){
        this.currentStock += this.amountOnOrder;
        this.amountOnOrder = 0;
    }

    public abstract void sellItem();

}