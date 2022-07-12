package view;

import java.util.Scanner;

import dao.MusicDAO;

public class ModifyMusic {
	public ModifyMusic() {}

	public ModifyMusic(String music) {
		Scanner sc = new Scanner(System.in);
		MusicDAO mdao = new MusicDAO();
		
		System.out.println("\n"+"1. 음악 이름 변경\n2. 가수명 변경\n3. 작사가 변경\n4. 작곡가 변경\n5. 가사 변경\n6. 음악 삭제");
		System.out.print("무엇을 수정 하실건가요 ? : ");
		int choice = sc.nextInt();
		
		if(choice == 6) {
			if(mdao.deleteMusic(music)) {
				System.out.println(music+"(이)가 삭제 되었습니다.");
			}
			else {
				System.out.println("다시 시도해주세요 ~");
			}
			}
		else {
			System.out.print("무엇으로 변경 하실건가요 ? : ");
			sc = new Scanner(System.in);
			String result = sc.nextLine();
			if(mdao.modifyMusic(music, choice, result)) {
				System.out.println(result+"(으)로 변경 완료 되었습니다.");
			}
			else {
				System.out.println("변경 실패! 다시 시도해주세요 ㅜㅜ");
			}
		}
		}
		
	}

