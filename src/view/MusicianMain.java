package view;

import java.util.Scanner;

import dao.MusicDAO;
import dao.Session;

public class MusicianMain {
	public MusicianMain() {
		Scanner sc = new Scanner(System.in);
		MusicDAO mdao = new MusicDAO();
		
		while(true) {
			System.out.println("-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=");
			System.out.println("♥♡ Musi에 오신걸 환영합니다~~ ♡♥");
			System.out.println("-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=");
			
			System.out.println("1. 음악 등록\n2. 음악 수정\n3. 회원 수정\n4. 로그아웃");
			System.out.print("무엇을 하실 건가요 ? : ");
			int choice = sc.nextInt();

			if(choice == 4) {
				System.out.println("안녕히 가세용 ~~"+"\n");
				Session.put("login_id", null);
				break;
			}
			switch (choice) {
			case 1:
				new AddMusicView();
				break;
			case 2:
				System.out.println("\n"+"음악명"+"\t"+"가수"+"\t"+"작사가"+"\t"+"작곡가"+"\t"+"가사");
				System.out.println("===========================");
				System.out.println(mdao.getMyMusic());
				System.out.println("===========================");
				System.out.print("변경하실 음악의 이름을 써주세요 : ");
				sc = new Scanner(System.in);
				String music = sc.nextLine();
				new ModifyMusic(music);
				break;
			case 3:
				new ModifyMusician();
				break;
			}
		}
	}

}
