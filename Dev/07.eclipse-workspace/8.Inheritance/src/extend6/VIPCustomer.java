package extend6;

/*
 * 제품을 살 때 10% 할인을 해줌
 * 포인트는 제품 가격의 5% 적립
 * 담당(agent) 전문 상담원이 배정됨
 */
public class VIPCustomer extends Customer{

	public VIPCustomer(String name) {
        super(name);
        this.grade = "VIP";
        this.point = 10000;
        this.pointRatio = 0.05;
	}
}
