package view;

import java.util.Scanner;

import dao.Messages;
import dao.NormalDAO;
import dto.NormalDTO;

public class NormalJoinView {
	public NormalJoinView() {
		Scanner sc = new Scanner(System.in);
		NormalDAO ndao = new NormalDAO();
		Messages ms = new Messages();
		String rs = "";
		
		System.out.print("아이디 : ");
		String normalid = sc.next();
		if(ndao.checkid(normalid)) {
			System.out.print("비밀번호 : ");
			String normalpw = sc.next();
			System.out.print("이름 : ");
			String normalname = sc.next();
			System.out.print("나이 : ");
			int normalage = sc.nextInt();
			System.out.print("핸드폰 번호 : ");
			String normalphone = sc.next();
			rs += ms.phones(normalphone);
			System.out.print("인증번호를 입력해주세요 : ");
			String phone = sc.next();
			if(phone.equals(rs)) {
				System.out.print("주소 : ");
				sc = new Scanner(System.in);
				String normaladdr = sc.nextLine();
				NormalDTO normal = new NormalDTO(normalid, normalpw, normalname, normalage, normalphone, normaladdr);
				if(ndao.normalJoin(normal)) {
					System.out.println("회원가입 완료 !");
					System.out.println(normalname+"님 환영합니다~~!");
				}
				else {
					System.out.println("회원가입 실패. 다시 시도해주세요!");
				}
			}
			else {
				System.out.println("인증번호가 틀립니다.");
			}
			}
		else {
			System.out.println("중복된 아이디가 있습니다.");
		}
	}

}
