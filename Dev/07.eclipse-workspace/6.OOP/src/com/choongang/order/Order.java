package com.choongang.order;

import com.choongang.product.Product;
import com.choongang.user.User;

public class Order {
	User user;
	Product product;
	
	public Order(User user, Product product) {
		//super();
		this.user = user;
		this.product = product;
	}
	
	
}
