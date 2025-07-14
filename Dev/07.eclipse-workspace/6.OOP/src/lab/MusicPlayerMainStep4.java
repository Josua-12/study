package lab;

public class MusicPlayerMainStep4 {
	
	public static void main(String[] args) {
		MusicPlayer musicPlayer = new MusicPlayer();
		
		
		// 음악 플레이어 켜기
		musicPlayer.musicPlayerOn();
		
		
		// 볼륨 증가
		musicPlayer.volumeUp();
		musicPlayer.volumeUp();
		
		// 볼륨 감소
		musicPlayer.volumeDown();
		
			
		//음악 플레이어 상태
		musicPlayer.musicPlayerStatus();
		
		
		//음악 플레이어 끄기
		musicPlayer.musicPlayerOff();
		
	}

}
