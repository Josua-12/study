package lab;

public class MusicPlayerMainStep2 {
	
	public static void main(String[] args) {
		MusicPlayerData musicPlayer = new MusicPlayerData();
		
		// 음악 플레이어 켜기
		musicPlayer.isOn = true;
		System.out.println("음악 플레이어를 시작합니다.");
		
		// 볼륨 증가
		musicPlayer.volume++;
		System.out.println("음악 플레이어 볼륨 : " + musicPlayer.volume);
		
		// 볼륨 증가
		musicPlayer.volume++;
		System.out.println("음악 플레이어 볼륨 : " + musicPlayer.volume);
		
		// 볼륨 감소
		musicPlayer.volume--;
		System.out.println("음악 플레이어 볼륨 : " + musicPlayer.volume);
			
		//음악 플레이어 상태
		System.out.println("음악 플레이어 상태 확인");
		if(musicPlayer.isOn) {
			System.out.println("음악 플레이어 ON, 볼륨: " + musicPlayer.volume);
		} else {
			System.out.println("음악 플레이어 OFF");
		}
		
		//음악 플레이어 끄기
		musicPlayer.isOn = false;
		System.out.println("음악 플레이어를 종료합니다.");
	}
}
