package model;

public class CartItem {
    private Product product;  // 상품
    private int quantity;    // 수량

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }

    public void addQuantity(int amount) {
        this.quantity += amount;
    }

    public int getTotalPrice() {
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return String.format("- %s X %d개 = %,d원",
                product.getName(), quantity, getTotalPrice());
    }
}