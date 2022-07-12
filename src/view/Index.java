package view;

import java.util.Scanner;

import dao.MusicianDAO;

public class Index {
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	MusicianDAO mdao = new MusicianDAO();
	
	while(true) {
	System.out.println("★☆★☆★☆ Musi에 오신걸 환영합니다~~☆★☆★☆★");
	System.out.println("1. 회원가입\n2. 로그인\n3. 나가기\n");
	System.out.print("무엇을 도와드릴까요? (번호를 선택해주세요) : ");
	int choice = sc.nextInt();
	
	if(choice == 3) {
		System.out.println("다음에 다시 이용해주세요~~!");
		break;
	}
	switch (choice) {
	case 1:
			System.out.println("1. 음악가 회원가입\n2. 일반 회원가입");
			System.out.print("번호를 선택해주세요 : ");
			int choices = sc.nextInt();
			if(choices == 1) {
				new MusicianJoinView();
			}
			else {
				new NormalJoinView();
			}
		break;
	case 2:
		System.out.println("1. 음악가 로그인\n2. 일반 로그인");
		System.out.print("번호를 선택해 주세요 : ");
		int num = sc.nextInt();
		if(num == 1) {
			new MusicianLoginView();
		}
		else {
			new NormalLoginView();
		}
		break;
	}
	}
}
}
