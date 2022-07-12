package view;

import java.util.Scanner;


import dao.Messages;
import dao.MusicianDAO;
import dto.MusicanDTO;

public class MusicianJoinView {
	public MusicianJoinView() {
		Scanner sc = new Scanner(System.in);
		MusicianDAO mdao = new MusicianDAO();
		Messages mes = new Messages();
		String rs = "";
		
		System.out.print("아이디 : ");
		String musicianid = sc.next();
		if(mdao.checkid(musicianid)) {
			System.out.print("비밀번호 : ");
			String musicianpw = sc.next();
			System.out.print("이름 : ");
			String musicianname = sc.next();
			System.out.print("나이 : ");
			int musicianage = sc.nextInt();
			System.out.print("핸드폰 번호 : ");
			String musicianphone = sc.next();
			rs += mes.phone(musicianphone);
			System.out.print("인증번호를 입력해주세요 : ");
			String phone = sc.next();
			if(phone.equals(rs)) {
				System.out.print("주소 : ");
				sc = new Scanner(System.in);
				String musicianaddr = sc.nextLine();
				
				MusicanDTO musician = new MusicanDTO(musicianid, musicianpw, musicianname, musicianage, musicianphone, musicianaddr);
				if(mdao.joinView(musician)) {
					System.out.println("회원가입 완료 !");
					System.out.println(musicianname+"님 환영합니다 ~~ ");
					System.out.println("----------------------------------");
				}
				else {
					System.out.println("회원가입 실패. 다시 시도해주세요~");
				}
				
			}
			else {
				System.out.println("인증번호가 틀립니다.");
			}
			}
		else {
			System.out.println("중복된 아이디 입니다.");
		}
}
}
