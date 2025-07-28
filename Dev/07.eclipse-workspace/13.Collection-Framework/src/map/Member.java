package map;

public class Member {

	private int memberId;
	private String memberName;
	
	
	public Member(int memberId, String memberName) {
		//super();
		this.memberId = memberId;
		this.memberName = memberName;
	}


	public int getMemberId() {
		return memberId;
	}


	public String getMemberName() {
		return memberName;
	}


	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberName=" + memberName + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Member) {
			Member member = (Member)obj;
			if(this.memberId == member.memberId) 
				return true;
			else
				return false;
		}
		return false;
	}


	@Override
	public int hashCode() {
		return memberId;
	}
	
}
