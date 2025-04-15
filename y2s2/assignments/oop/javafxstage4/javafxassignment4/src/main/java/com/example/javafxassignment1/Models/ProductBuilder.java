package com.example.javafxassignment1.Models;

import com.example.javafxassignment1.Models.Product;

public interface ProductBuilder {
    public void setStock(int stock_);
    public void setInStock(boolean inStock_);
    public void setPrice(double price_);
		public Product getProduct();	
}
