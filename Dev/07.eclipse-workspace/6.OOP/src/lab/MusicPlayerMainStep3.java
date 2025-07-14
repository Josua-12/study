package lab;

public class MusicPlayerMainStep3 {
	
	public static void main(String[] args) {
		MusicPlayerData musicPlayer = new MusicPlayerData();
		
		
		// 음악 플레이어 켜기
		musicPlayerOn(musicPlayer);
		
		
		// 볼륨 증가
		volumeUp(musicPlayer);
		volumeUp(musicPlayer);
		
		// 볼륨 감소
		volumeDown(musicPlayer);
		
			
		//음악 플레이어 상태
		musicPlayerStatus(musicPlayer);
		
		
		//음악 플레이어 끄기
		musicPlayerOff(musicPlayer);
		
	}

	static void musicPlayerOff(MusicPlayerData musicPlayer) {
		musicPlayer.isOn = false;
		System.out.println("음악 플레이어를 종료합니다.");
	}

	static void musicPlayerStatus(MusicPlayerData musicPlayer) {
		System.out.println("음악 플레이어 상태 확인");
		if(musicPlayer.isOn) {
			System.out.println("음악 플레이어 ON, 볼륨: " + musicPlayer.volume);
		} else {
			System.out.println("음악 플레이어 OFF");
		}
	}

	static void volumeDown(MusicPlayerData musicPlayer) {
		musicPlayer.volume--;
		System.out.println("음악 플레이어 볼륨 : " + musicPlayer.volume);
	}

	static void volumeUp(MusicPlayerData musicPlayer) {
		musicPlayer.volume++;
		System.out.println("음악 플레이어 볼륨 : " + musicPlayer.volume);
	}

	static void musicPlayerOn(MusicPlayerData musicPlayer) {
		musicPlayer.isOn = true;
		System.out.println("음악 플레이어를 시작합니다.");
	}
}
