package view;

import java.util.Scanner;

import dao.MusicianDAO;
import dao.NormalDAO;

public class MusicianLoginView {
	public MusicianLoginView() {
		Scanner sc = new Scanner(System.in);
		MusicianDAO mdao = new MusicianDAO();
		
		System.out.print("아이디 입력 : ");
		String musicianid = sc.next();
		System.out.print("비밀번호 입력 : ");
		String musicianpw = sc.next();
		
		if(mdao.login(musicianid, musicianpw)) {
			
			new MusicianMain();
		}
		else {
			System.out.println("아이디나 비밀번호가 틀립니다. 다시 시도해주세요~");
		}
	}

}
