package view;

import java.util.Scanner;

import dao.MusicDAO;
import dao.PlayDAO;
import dao.PlayListDAO;

public class AttentionMusic {
	public AttentionMusic() {}

	public AttentionMusic(String attentisong) {
		Scanner sc = new Scanner(System.in);
		MusicDAO mdao = new MusicDAO();
		PlayListDAO pdao = new PlayListDAO();
		PlayDAO plo = new PlayDAO();
		String songname = "";
		String sing = "";
		
		while(true) {
		System.out.println(attentisong);
		System.out.println("1. 재생하기\n2. 가사보기\n3. 좋아요 누르기\n4. 플레이리스트에 담기\n5. 돌아가기");
		System.out.print("무엇을 하실건가요 ? : ");
		int choice = sc.nextInt();
		
		if(choice == 5) {
			System.out.println("돌아갑니다~~");
			break;
		}
		switch (choice) {
		case 1:
			if(!(plo.getPlay() == "")) {
				String re = plo.getPlay();
				System.out.println("현재 "+re+"를(을) 재생중입니다~~");
			}
			String playsong = attentisong;
			if(!(plo.selectplay() == "")) {
				System.out.println("현재 재생중인 곡을 중지하고 이 곡을 재생 시키시겠습니까 ? ");
				System.out.print("1. 수락 / 2. 거절 : ");
				int choices = sc.nextInt();
				if(choices == 1) {
					plo.updateplay(playsong);
					System.out.println(playsong+"(를)을 재생합니다");
				}
				else {
					System.out.println("재생중이신 곡을 다 들으신후 다시 이용해주세요 ~");
				}
			}
			else {
				plo.addPlay(playsong);
				System.out.println(playsong+"(를)을 재생합니다~");
			}
			break;
		case 2:
			System.out.println(mdao.songcomposer(attentisong));
			break;
		case 3:
			if(mdao.likeup(attentisong)) {
				System.out.println(attentisong+" 노래 좋아요 누르기 성공 ♥♥");
				System.out.println("♥♥ 많이많이 눌러주세요 ~~ ! ♥♥");
			}
			else {
				System.out.println("좋아요 누르기 실패했어요 ㅠㅠ 다시 눌러주세요~");
			}
			break;
		case 4:
			if(pdao.checkSong(attentisong)){
				songname += mdao.song(attentisong);
				sing += mdao.sing(attentisong);
				new AddPlayList(songname,sing);
			}
			else {
				System.out.println("이미 플레이리스트에 담긴 노래입니다~!");
			}
			break;
		}
		}
	}
}
