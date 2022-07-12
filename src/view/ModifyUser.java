package view;

import java.util.Scanner;

import dao.Messages;
import dao.NormalDAO;
import dao.PlayListDAO;

public class ModifyUser {
	public ModifyUser() {
		Scanner sc = new Scanner(System.in);
		NormalDAO ndao = new NormalDAO();
		PlayListDAO pdao = new PlayListDAO();
		Messages mes = new Messages();
		
		System.out.println("1. 비밀번호 변경\n2. 이름 변경\n3. 나이 변경\n4. 연락처 변경\n5. 주소 변경\n6. 회원 탈퇴");
		System.out.print("무엇을 도와드릴까요 ? : ");
		int choice = sc.nextInt();
		
		if(choice == 6) {
			System.out.print("비밀번호를 입력해주세요 : ");
			String pw = sc.next();
			if(ndao.checkpw(pw)) {
				if(pdao.deleteMyMusic()) {
					if(ndao.deleteMy()) {
						System.out.println("♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥");
						System.out.println("회원 탈퇴에 성공하셨습니다 ");
						System.out.println("다음에 다시 방문해주세요 ㅜㅜㅜㅜㅜㅜㅜㅜ");
						System.out.println("♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥");
					}
					else {
						System.out.println("오류발생 다시 시도해주세요~!");
					}
				}
			}
			else {
				System.out.println("비밀번호가 틀립니다.");
			}
		}
		switch (choice) {
		case 1: case 2: case 3: 
			System.out.print("비밀번호를 입력해주세요 : ");
			String pw = sc.next();
			if(ndao.checkpw(pw)) {
				System.out.print("변경하실 내용 : ");
				sc = new Scanner(System.in);
				String change = sc.nextLine();
				if(ndao.modifyMy(choice, change)) {
					System.out.println("회원 정보 변경 완료되었습니다~~!");
				}
			}
			else {
				System.out.println("비밀번호가 틀립니다.");
			}
			break;

		case 4:
			String res = ""; 
			System.out.print("비밀번호를 입력해주세요 : ");
			String pws = sc.next();
			if(ndao.checkpws(pws)) {
				System.out.print("변경하실 연락처 : ");
				String normalphone = sc.next();
				res += mes.phones(normalphone);
				System.out.print("인증번호를 입력해 주세요 : ");
				String num = sc.next();
				if(res.equals(num)) {
					if(ndao.modifyphone(normalphone)) {
						System.out.println("연락처 변경 성공~~");
					}
				}
				else {
					System.out.println("인증번호가 틀립니다!");
				}
			}
			else {
				System.out.println("비밀번호가 틀립니다.");
			}
			break;
		case 5:
			System.out.print("비밀번호를 입력해주세요 : ");
			String pwse = sc.next();
			if(ndao.checkpwse(pwse)) {
				System.out.print("주소 : ");
				sc = new Scanner(System.in);
				String addr = sc.nextLine();
				if(ndao.modifyAddr(addr)) {
					System.out.println("주소 변경 성공!");
				}
			}
			else {
				System.out.println("비밀번호가 틀립니다.");
			}
			break;
		}
	}

}
