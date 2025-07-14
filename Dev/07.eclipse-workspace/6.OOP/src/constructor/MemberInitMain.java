package constructor;

public class MemberInitMain {

	public static void main(String[] args) {
		MemberInit member1 = new MemberInit();
		member1.name = "user1";
		member1.age = 20;
		member1.grade = 90;
		
		MemberInit member2 = new MemberInit();
		member2.name = "user2";
		member2.age = 40;
		member2.grade = 80;
		
		MemberInit[] members = {member1, member2};
		for(MemberInit m : members) {
			System.out.println
					("이름: " + m.name + "나이: " + m.age +"성적: " + m.grade);
			System.out.println();
		}
	}
}
