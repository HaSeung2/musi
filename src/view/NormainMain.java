package view;

import java.util.Scanner;

import dao.MusicDAO;
import dao.PlayDAO;
import dao.PlayListDAO;
import dao.Session;

public class NormainMain {
	public NormainMain() {
		Scanner sc = new Scanner(System.in);
		MusicDAO mdao = new MusicDAO();
		PlayListDAO pdao = new PlayListDAO();
		PlayDAO plo = new PlayDAO();
		
		while(true) {
		System.out.println("===========================================");
		System.out.println("★☆★☆★☆★☆뮤지에 오신걸 환영합니다★☆★☆★☆★☆★☆");
		System.out.println("===========================================");
		
		System.out.println("1. 뮤지 차트\n2. 노래 검색\n3. 내 플레이 리스트\n4. 회원정보 수정\n5. 로그아웃");
		System.out.print("원하시는 번호를 적어주세요 : ");
		int choice = sc.nextInt();
		
		if(choice == 5) {
			Session.put("login_id", null);
			System.out.println("다음에 또 와주세요~!");
		}
		switch (choice) {
		case 1:
			System.out.println("============뮤지 차트 ! !===========");
			System.out.println("음악명"+"\t"+"가수"+"\t"+"작사가"+"\t"+"작곡가"+"\t"+"노래가사"+"\t"+"\t"+"좋아요");
			System.out.println(mdao.getList());
			System.out.println("=================================");
			break;
		case 2:
			System.out.print("어떤 노래를 검색 할까요 ?  : ");
			sc = new Scanner(System.in);
			String songs = sc.nextLine();
			System.out.println("========"+songs+"(으)로 검색한 결과 입니다.=========");
			System.out.println("음악명"+"\t"+"가수"+"\t"+"작사가"+"\t"+"작곡가"+"\t"+"노래가사"+"\t"+"\t"+"좋아요");
			System.out.println(mdao.searchSong(songs));
			if(mdao.searchSong(songs) == "") {
				System.out.println("검색된 결과가 없습니다.");
				break;
			}
			System.out.print("관심있으신 노래명을 적어주세요 : ");
			sc = new Scanner(System.in);
			String attentisong = sc.nextLine();
			new AttentionMusic(attentisong);
			break;
		case 3:
			if(!(plo.getPlay() == "")) {
				String re = plo.getPlay();
				System.out.println("현재 "+re+"를(을) 재생중입니다~~");
			}
			System.out.println("===========내 플레이 리스트===========");
			System.out.println("음악명"+"\t"+"가수");
			System.out.println(pdao.myPlayList());
			System.out.println("==================================");
			System.out.println("1. 재생하기\n2. 플레이리스트에서 삭제하기\n3. 돌아가기");
			System.out.print("무엇을 하시겠습니까 ? : ");
			int choices = sc.nextInt();
			if(choices == 1) {
				System.out.print("재생하실 음악 : ");
				sc = new Scanner(System.in);
				String playsong = sc.nextLine();
				if(!(plo.selectplay() == "")) {
					System.out.println("듣고 계신 노래를 중지하고 이 노래를 재생하시겠습니까 ? ");
					System.out.print("1. 수락  2. 거절 : ");
					int ch = sc.nextInt();
					if(ch == 1) {
						if(plo.updateplay(playsong)) {
							System.out.println(playsong+"(를)을 재생합니다~!");
						}
					}
					else {
						System.out.println("듣고 계신 노래를 다 들으신 후에 다시 와주세용♥");
						break;
					}
				}
			if(plo.selectplay() == "") {
				if(plo.addPlay(playsong)) {
					System.out.println(playsong+"(를)을 재생중입니다~~");
				}
				else {
					System.out.println("재생하는데 오류가 발생하였습니다. 다시 시도해 주세요.");
				}
			}
		}
			break;
		case 4:
			new ModifyUser();
			break;
		}
	}
	}
}
