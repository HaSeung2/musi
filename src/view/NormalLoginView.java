package view;

import java.util.Scanner;

import dao.NormalDAO;

public class NormalLoginView {
	public NormalLoginView() {
		Scanner sc = new Scanner(System.in);
		NormalDAO ndao = new NormalDAO();
		
		System.out.print("아이디 입력 : ");
		String normalid = sc.next();
		System.out.print("비밀번호 입력 : ");
		String normalpw = sc.next();
		
		if(ndao.login(normalid, normalpw)) {
			
			new NormainMain();
		}
		else {
			System.out.println("아이디나 비밀번호가 틀립니다. 다시 시도해주세요~");
		}
	}
	}


