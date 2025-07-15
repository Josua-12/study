package com.choongang.order;

import com.choongang.product.Product;
import com.choongang.user.User;

public class OrderService {
	public void order() {
		User user = new User();
		Product product = new Product();
		Order order = new Order(user, product);
	}
}
