package loop;
/*
 * 1부터 5까지 숫자를 출력하시오. 숫자가 3일때는 미출력하시오.
 */
public class Continue {

	public static void main(String[] args) {
		int i = 1;
		while(i <=5) {
			if (i ==3) {
				i++;
				continue;
			}
			System.out.println(i);
			i++;
		}
	}
}
