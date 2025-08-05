package model;

import exception.InsufficientStockException;

public class Product {
    private String productId;       // 상품 ID
    private String name;           // 상품명
    private int price;            // 가격
    private int stock;            // 재고
    private String category;      // 카테고리
    private double rating;        // 평점 (0.0 ~ 5.0)
    private int reviewCount;      // 리뷰 개수

    public Product(String productId, String name, int price, int stock, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.rating = 0.0;
        this.reviewCount = 0;
    }

    public String getProductId() { return productId; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getStock() { return stock; }
    public String getCategory() { return category; }
    public double getRating() { return rating; }
    public int getReviewCount() { return reviewCount; }

    public void reduceStock(int quantity) throws InsufficientStockException {
        if (this.stock < quantity) {
            throw new InsufficientStockException(
                    String.format("주문 실패: 재고 부족: %s (요청: %d, 현재: %d)", this.name, quantity, this.stock));
        }
        this.stock -= quantity;
    }

    public void addStock(int quantity) {
        this.stock += quantity;
    }

    public boolean isInStock() {
        return this.stock > 0;
    }

    public boolean isLowStock() {
        return this.stock > 0 && this.stock <= 5;
    }

    public void addReview(double newRating) {
        double totalRating = this.rating * this.reviewCount;
        this.reviewCount++;
        this.rating = (totalRating + newRating) / this.reviewCount;
    }

    @Override
    public String toString() {
    	String stockCount = (stock > 5) ? "재고충분" : "재고부족";
    	
        return String.format("상품ID: %s, 상품명: %s, 가격: %,d원, 재고: %d개, 카테고리: %s, 평점: %.1f (%d리뷰), 상태 : %s",
                productId, name, price, stock, category, rating, reviewCount, stockCount);
    }
}