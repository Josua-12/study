package extend6;

public class VIPCustomer extends Customer {
    private int agentID;
    private double salesRatio;

    public VIPCustomer() {
        super();
        setCustomerGrade("VIP");
        setBonusRatio(0.05);
        this.salesRatio = 0.1;
        this.bonusPoint = 10000;
    }

    @Override
    public int calcPrice(int price) {
        bonusPoint += (int)(price * getBonusRatio());
        return price - (int)(price * salesRatio);
    }

    @Override
    public String showCustomerInfo() {
        return getCustomerName() + "님의 등급은 " + getCustomerGrade()
            + "이며, 보너스 포인트는 " + getBonusPoint()
            + "이고, 담당 상담원 번호는 " + agentID + "입니다";
    }

    public int getAgentID() {
        return agentID;
    }

    public void setAgentID(int agentID) {
        this.agentID = agentID;
    }
}
