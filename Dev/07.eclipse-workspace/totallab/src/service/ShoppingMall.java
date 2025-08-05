package service;

import exception.CustomerNotFoundException;
import exception.ProductNotFoundException;
import exception.ShopException;
import model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingMall {
    private HashMap<String, Product> products;      // 상품 관리
    private HashMap<String, Customer> customers;    // 고객 관리
    private HashMap<String, Manager> managers;      // 관리자 관리
    private ArrayList<Order> orders;               // 주문 내역
    private HashMap<String, ArrayList<CartItem>> carts; // 고객별 장바구니
    private String mallName;
    private int orderCounter; // 주문 번호 생성용

    public ShoppingMall(String mallName) {
        this.mallName = mallName;
        this.products = new HashMap<>();
        this.customers = new HashMap<>();
        this.managers = new HashMap<>();
        this.orders = new ArrayList<>();
        this.carts = new HashMap<>();
        this.orderCounter = 0;
    }

    // --- 상품 관리 메서드
    public void addProduct(String productId, String name, int price, int stock, String category) throws ShopException {
        if (products.containsKey(productId)) {
            throw new ShopException("이미 존재하는 상품 ID입니다: " + productId);
        }
        products.put(productId, new Product(productId, name, price, stock, category));
    }

    public Product getProduct(String productId) throws ProductNotFoundException {
        Product p = products.get(productId);
        if (p == null) {
            throw new ProductNotFoundException("존재하지 않는 상품입니다: " + productId);
        }
        return p;
    }


    public ArrayList<Product> searchProductsByName(String name) {
        return products.values().stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Product> getProductsByCategory(String category) {
        return products.values().stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Product> getLowStockProducts() {
        return products.values().stream()
                .filter(Product::isLowStock)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // --- 고객 관리 메서드
    public void addCustomer(String id, String name, String email) throws ShopException {
        if (customers.containsKey(id) || managers.containsKey(id)) {
            throw new ShopException("이미 존재하는 고객 ID입니다: " + id);
        }
        customers.put(id, new Customer(id, name, email));
    }

    public void addManager(String id, String name, String email, String department, int employeeId) throws ShopException {
        if (customers.containsKey(id) || managers.containsKey(id)) {
            throw new ShopException("이미 존재하는 관리자 ID입니다: " + id);
        }
        managers.put(id, new Manager(id, name, email, department, employeeId));
    }

    public void displayCustomerOrders(String customerId) throws CustomerNotFoundException {
        Customer customer = customers.get(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("존재하지 않는 고객입니다: " + customerId);
        }
        System.out.printf("=== %s님의 주문 내역 ===\n", customer.getName());
        ArrayList<Order> customerOrders = orders.stream()
                .filter(o -> o.getCustomer().getId().equals(customerId))
                .collect(Collectors.toCollection(ArrayList::new));

        if (customerOrders.isEmpty()) {
            System.out.println("주문 내역이 없습니다.");
            return;
        }
        for (Order order : customerOrders) {
            System.out.println(order);
            System.out.println("---------------------------------");
        }
    }

    // --- 장바구니 관리 메서드
    public void addToCart(String customerId, String productId, int quantity)
            throws CustomerNotFoundException, ProductNotFoundException {
        Customer customer = customers.get(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("존재하지 않는 고객입니다: " + customerId);
        }
        Product product = products.get(productId);
        if (product == null) {
            throw new ProductNotFoundException("존재하지 않는 상품입니다: " + productId);
        }

        carts.putIfAbsent(customerId, new ArrayList<>());
        ArrayList<CartItem> customerCart = carts.get(customerId);

        boolean found = false;
        for (CartItem item : customerCart) {
            if (item.getProduct().getProductId().equals(productId)) {
                item.addQuantity(quantity);
                found = true;
                break;
            }
        }
        if (!found) {
            customerCart.add(new CartItem(product, quantity));
        }
    }

    public void displayCart(String customerId) throws CustomerNotFoundException {
        Customer customer = customers.get(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("존재하지 않는 고객입니다: " + customerId);
        }

        ArrayList<CartItem> customerCart = carts.get(customerId);
        if (customerCart == null || customerCart.isEmpty()) {
            System.out.println("장바구니가 비어있습니다.");
            return;
        }

        System.out.printf("\n=== %s님의 장바구니 ===\n", customer.getName());
        int totalPrice = 0;
        for (CartItem item : customerCart) {
            System.out.println(item);
            totalPrice += item.getTotalPrice();
        }
        System.out.printf("\n장바구니 합계 : %,d원\n", totalPrice);
    }

    // --- 주문 처리 메서드
    public void placeOrder(String customerId) throws ShopException {
        Customer customer = customers.get(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("존재하지 않는 고객입니다: " + customerId);
        }

        ArrayList<CartItem> customerCart = carts.get(customerId);
        if (customerCart == null || customerCart.isEmpty()) {
            throw new ShopException("장바구니가 비어있습니다.");
        }

        for (CartItem item : customerCart) {
            item.getProduct().reduceStock(item.getQuantity());
        }

        orderCounter++;
        String orderId = String.format("ORD%04d", orderCounter);
        Order newOrder = new Order(orderId, customer, new ArrayList<>(customerCart));
        orders.add(newOrder);

        carts.remove(customerId);

        System.out.println("\n=== 주문이 완료되었습니다! ===");
        System.out.printf("주문번호: %s\n", orderId);
        System.out.println(newOrder);
    }

    // --- 통계 정보 메서드
    public void displayMallStatistics() {
        System.out.println("\n=== " + mallName + " 쇼핑몰 통계 정보 ===");
        long availableProductsCount = products.values().stream()
                .filter(Product::isInStock)
                .count();
        long lowStockProductsCount = products.values().stream()
                .filter(Product::isLowStock)
                .count();
        int totalSales = orders.stream().mapToInt(Order::getTotalAmount).sum();

        System.out.println("총 상품 수: " + products.size() + "개");
        System.out.println("구매 가능한 상품 수: " + availableProductsCount + "개");
        System.out.println("재고 부족 상품 수 (5개 이하): " + lowStockProductsCount + "개");
        System.out.println("등록된 고객 수: " + customers.size() + "명");
        System.out.println("총 주문 수: " + orders.size() + "건");
        System.out.printf("총 매출: %,d원\n", totalSales);
        System.out.println();
        System.out.println("카테고리별 상품 수:");
        products.values().stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()))
                .forEach((category, count) -> System.out.printf("  - %s: %d개\n", category, count));
    }

}