package map;

public class MemberHashMapTest {
	public static void main(String[] args) {
		MemberHashMap memberHashMap = new MemberHashMap();
		
		Member memberLee = new Member(1001, "이순신");
		Member memberShin = new Member(1002, "신사임당"); 
		Member memberRyu = new Member(1003, "류승룡");
		Member memberSon = new Member(1004, "손흥민");
		
		memberHashMap.addMember(memberLee);
		memberHashMap.addMember(memberShin);
		memberHashMap.addMember(memberRyu);
		memberHashMap.addMember(memberSon);
		
		memberHashMap.showAllMember();
		
		memberHashMap.removeMember(1004);
		memberHashMap.showAllMember();
		
		memberHashMap.addMember(memberLee);
		memberHashMap.showAllMember();
		
	}
}
