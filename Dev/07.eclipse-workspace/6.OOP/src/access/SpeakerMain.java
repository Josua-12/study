package access;

public class SpeakerMain {

	public static void main(String[] args) {
		Speaker speaker = new Speaker(90);
		speaker.showVolumn();
		
		speaker.volumeUp();
		speaker.showVolumn();
		speaker.volumeUp();
		speaker.showVolumn();
		
		speaker.volume = 200;		// 필드에 직접 접근
		speaker.showVolumn();
	}
}
