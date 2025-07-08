package array;

public class ArrayDimen4 {
	
	public static void main(String[] args) {
		
		// 2*3 2차원 배열 생성
		int[][] arr = {
				{1, 2, 3},
				{4, 5, 6}
		};
		
		for(int row = 0; row < 2; row++) {
			for(int column = 0; column < 3; column++) {
				System.out.print(arr[row][column] + " ");
			}
			System.out.println(); // 한 행이 끝나면 라인 변경함
		}
	}
}
