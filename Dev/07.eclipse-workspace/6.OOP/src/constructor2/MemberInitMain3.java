package constructor2;

public class MemberInitMain3 {
	
	public static void main(String[] args) {
		MemberInit member1 = new MemberInit();
		member1.initMember("user1", 15, 90);
		
		MemberInit member2 = new MemberInit();
		member2.initMember("user2", 16, 80);
		
		MemberInit[] members = {member1, member2};
		for(MemberInit m : members) {
			System.out.println("이름 : " + m.name + ", 나이 : " + m.age
								+ ", 성적 : " + m.grade);
		}
	}
}
