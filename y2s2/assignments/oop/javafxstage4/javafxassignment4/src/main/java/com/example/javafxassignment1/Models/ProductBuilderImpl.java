package com.example.javafxassignment1.Models;

import com.example.javafxassignment1.Models.Product;

public class ProductBuilderImpl implements ProductBuilder {
		private final Product product;

		public ProductBuilderImpl(){
			this.product = new Product();
		}

    public void setStock(int stock_){
			product.setStock(stock_);
		}

    public void setInStock(boolean inStock_){
			product.setInStock(inStock_);
		}

    public void setPrice(double price_){
			product.setPrice(price_);
		}

    @Override
		public Product getProduct(){
		return this.product;
	}
}
