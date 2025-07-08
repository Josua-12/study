package array;

public class ArrayDimen5 {
	
	public static void main(String[] args) {
		
		// 2*3 2차원 배열 생성
		int[][] arr = new int[2][3];
		
		int i = 1;
		
		for(int row = 0; row < arr.length; row++) {
			for(int col =0; col < arr[row].length; col++) {
				arr[row][col] = i++;
			}
		}
		
		for(int row = 0; row < arr.length; row++) {
			for(int col = 0; col < arr[row].length; col++) {
				System.out.print(arr[row][col] + " ");
			}
			System.out.println(); // 한 행이 끝나면 라인 변경함
		}
	}
}
