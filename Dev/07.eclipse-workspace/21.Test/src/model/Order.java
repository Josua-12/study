package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
    private String orderId;              // 주문 ID
    private Customer customer;           // 주문 고객
    private ArrayList<CartItem> items;   // 주문 상품들
    private LocalDateTime orderDate;     // 주문일시
    private int totalAmount;            // 총 주문금액
    private String status;              // 주문 상태

    public Order(String orderId, Customer customer, ArrayList<CartItem> items) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = items;
        this.orderDate = LocalDateTime.now();
        this.status = "주문 완료";
        this.totalAmount = calculateTotalAmount();
    }

    private int calculateTotalAmount() {
        int total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public String getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public ArrayList<CartItem> getItems() { return items; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public int getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("결제금액: %,d원", totalAmount));
        return sb.toString();
    }
}