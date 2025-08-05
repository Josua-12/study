package main;

import exception.CustomerNotFoundException;
import exception.ShopException;
import model.CartItem;
import model.Product;
import service.ShoppingMall;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingMallSystem {

    private final ShoppingMall mall;
    private final Scanner scanner;

    public ShoppingMallSystem() {
        this.mall = new ShoppingMall("자바 쇼핑몰");
        this.scanner = new Scanner(System.in);
        initializeData();
    }

    private void initializeData() {
        try {
            mall.addProduct("P001", "갤럭시 스마트폰", 800000, 10, "전자제품");
            mall.addProduct("P002", "애플 노트북", 1500000, 5, "전자제품");
            mall.addProduct("P003", "무선 이어폰", 150000, 20, "전자제품");
            mall.addProduct("P004", "게이밍 키보드", 120000, 8, "컴퓨터");
            mall.addProduct("P005", "모니터", 300000, 3, "컴퓨터");
            mall.addProduct("P006", "운동화", 89000, 15, "의류");
            mall.addProduct("P007", "청바지", 65000, 12, "의류");
            mall.addProduct("P008", "백팩", 45000, 7, "가방");

            mall.addCustomer("C001", "김철수", "kim@email.com");
            mall.addCustomer("C002", "이영희", "lee@email.com");
            mall.addCustomer("C003", "박민수", "park@email.com");

            mall.addManager("M001", "정관리", "admin@mall.com", "운영팀", 1001);

        } catch (ShopException e) {
            System.err.println("초기 데이터 로드 중 오류 발생: " + e.getMessage());
        }
    }

    public void start() {
        System.out.println("=== 자바 쇼핑몰에 오신 것을 환영합니다! ===");
        while (true) {
            System.out.println("\n=== 메인 메뉴 ===");
            System.out.println("1. 상품 관리");
            System.out.println("2. 고객 관리");
            System.out.println("3. 장바구니 관리");
            System.out.println("4. 주문 관리");
            System.out.println("5. 통계 정보");
            System.out.println("0. 종료");
            System.out.print("메뉴를 선택하세요: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        productMenu();
                        break;
                    case 2:
                        customerMenu();
                        break;
                    case 3:
                        cartMenu();
                        break;
                    case 4:
                        orderMenu();
                        break;
                    case 5:
                        mall.displayMallStatistics();
                        break;
                    case 0:
                        System.out.println("\n쇼핑몰 시스템을 종료합니다. 감사합니다!");
                        return;
                    default:
                        System.out.println("잘못된 메뉴 선택입니다. 다시 입력해주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }
    }

    private void productMenu() {
        while (true) {
            System.out.println("\n=== 상품 관리 ===");
            System.out.println("1. 상품 추가");
            System.out.println("2. 전체 상품 보기");
            System.out.println("3. 구매 가능한 상품 보기");
            System.out.println("4. 상품 검색 (이름)");
            System.out.println("5. 카테고리별 상품 보기");
            System.out.println("6. 재고 부족 상품 보기");
            System.out.println("0. 메인 메뉴로 돌아가기");
            System.out.print("선택하세요: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        addProductUI();
                        break;
                    case 4:
                        searchProductByNameUI();
                        break;
                    case 6:
                        displayLowStockProductsUI();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("잘못된 메뉴 선택입니다.");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }
    }

    private void addProductUI() {
        System.out.print("\n상품 ID를 입력하세요: ");
        String productId = scanner.nextLine();
        System.out.print("상품명을 입력하세요: ");
        String name = scanner.nextLine();
        System.out.print("가격을 입력하세요: ");
        int price = Integer.parseInt(scanner.nextLine());
        System.out.print("재고 수량을 입력하세요: ");
        int stock = Integer.parseInt(scanner.nextLine());
        System.out.print("카테고리를 입력하세요: ");
        String category = scanner.nextLine();

        try {
            mall.addProduct(productId, name, price, stock, category);
            System.out.println("\n상품이 추가되었습니다: " + name);
        } catch (ShopException e) {
            System.out.println("오류: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("가격과 재고는 숫자로 입력해주세요.");
        }
    }

    private void searchProductByNameUI() {
        System.out.print("\n검색할 상품명을 입력하세요: ");
        String name = scanner.nextLine();
        ArrayList<Product> results = mall.searchProductsByName(name);
        if (results.isEmpty()) {
            System.out.println("검색 결과가 없습니다.");
        } else {
            System.out.printf("=== 상품명 '%s' 검색 결과 ===\n", name);
            results.forEach(System.out::println);
            System.out.printf("총 %d개의 상품을 찾았습니다.\n", results.size());
        }
    }

    private void displayLowStockProductsUI() {
        System.out.println("--- 재고 부족 상품 목록 (5개 이하) ---");
        ArrayList<Product> results = mall.getLowStockProducts();
        if (results.isEmpty()) {
            System.out.println("재고 부족 상품이 없습니다.");
        } else {
            results.forEach(System.out::println);
        }
    }

    private void customerMenu() {
        while (true) {
            System.out.println("\n=== 고객 관리 ===");
            System.out.println("1. 고객 등록");
            System.out.println("2. 관리자 등록");
            System.out.println("3. 고객 주문 내역 조회");
            System.out.println("0. 메인 메뉴로 돌아가기");
            System.out.print("선택하세요: ");
        }
    }

    

    private void cartMenu() {
        while (true) {
            System.out.println("\n=== 장바구니 관리 ===");
            System.out.println("1. 장바구니에 상품 추가");
            System.out.println("2. 장바구니 보기");
            System.out.println("0. 메인 메뉴로 돌아가기");
            System.out.print("선택하세요: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        addToCartUI();
                        break;
                    case 2:
                        displayCartUI();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("잘못된 메뉴 선택입니다.");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }
    }

    private void addToCartUI() {
        System.out.print("\n고객 ID를 입력하세요: ");
        String customerId = scanner.nextLine();
        System.out.print("상품 ID를 입력하세요: ");
        String productId = scanner.nextLine();
        System.out.print("수량을 입력하세요: ");
        try {
            int quantity = Integer.parseInt(scanner.nextLine());
            mall.addToCart(customerId, productId, quantity);
            System.out.printf("\n장바구니에 상품이 추가되었습니다: %s (%d개)\n", mall.getProduct(productId).getName(), quantity);
        } catch (NumberFormatException e) {
            System.out.println("오류: 수량은 숫자로 입력해주세요.");
        } catch (ShopException e) {
            System.out.println("\n장바구니 추가 중 오류: " + e.getMessage());
        }
    }

    private void displayCartUI() {
        System.out.print("\n고객 ID를 입력하세요: ");
        String customerId = scanner.nextLine();
        try {
            mall.displayCart(customerId);
        } catch (CustomerNotFoundException e) {
            System.out.println("오류: " + e.getMessage());
        }
    }

    private void orderMenu() {
        while (true) {
            System.out.println("\n=== 주문 관리 ===");
            System.out.println("1. 주문하기");
            System.out.println("2. 고객 주문 내역 보기");
            System.out.println("0. 메인 메뉴로 돌아가기");
            System.out.print("선택하세요: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        placeOrderUI();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("잘못된 메뉴 선택입니다.");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }
    }

    private void placeOrderUI() {
        System.out.print("주문할 고객 ID를 입력하세요: ");
        String customerId = scanner.nextLine();
        try {
            // 주문 전에 장바구니 내용 출력
            mall.displayCart(customerId);
            System.out.println(); // 한 줄 띄우기
            mall.placeOrder(customerId);
        } catch (ShopException e) {
            System.out.println("주문 처리 중 오류: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ShoppingMallSystem system = new ShoppingMallSystem();
        system.start();
    }
}