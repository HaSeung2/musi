# musi
>Music 프로젝트는 음악가와 일반유저를 따로 관리하여 음악가로 로그인한 유저는 음악을 등록,수정,삭제 할수 있으며 일반 유저는 음악 차트를 보거나 원하는 음악을 검색, 재생할 수 있도록 만들었다.

index 페이지(시작페이지)
```java
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
  //회원가입을 선택하였을 경우
			System.out.println("1. 음악가 회원가입\n2. 일반 회원가입");
			System.out.print("번호를 선택해주세요 : ");
			int choices = sc.nextInt();
			if(choices == 1) {
      //음악가 회원가입
				new MusicianJoinView();
			}
			else {
      //일반 회원가입
				new NormalJoinView();
			}
		break;
	case 2:
  //로그인을 선택한 경우
		System.out.println("1. 음악가 로그인\n2. 일반 로그인");
		System.out.print("번호를 선택해 주세요 : ");
		int num = sc.nextInt();
		if(num == 1) {
    //음악가 로그인
			new MusicianLoginView();
		}
		else {
    //일반 로그인
			new NormalLoginView();
		}
		break;
	}
	}
}
}
```
음악가 회원가입과 일반 회원가입은 데이터가 저장되는 테이블만 다를뿐 로직은 같기 때문에 음악가 회원가입 코드만 보여주도록 하겠다.
휴대폰 문자인증 API를 사용하여 회원가입 할 때 전화번호를 입력하면 인증번호를 사용자 폰에 전송시키고 전송받은 인증번호를 알맞게 입력해야 다음으로 넘어가게 만들었다.
```java
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
      //사용자 전화번호를 같이 넘겨주며 API 사용
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
```
휴대폰 문자 인증 API 코드
```java
package dao;

import java.util.HashMap;
import java.util.Random;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class Messages {

public String phone(String musicianphone) {
	String api_key = "NCSCTVONVQHFNXQD";
    String api_secret = "VU80WHPTTBSEPAAQN4CYO0VCLNJGABCP";
    Message coolsms = new Message(api_key, api_secret);
    Random random = new Random();
    String numStr = "";
    for(int i=0; i<6; i++) {
       String ran = Integer.toString(random.nextInt(10));
       numStr+=ran;
    }          
    HashMap<String, String> params = new HashMap<String, String>();
    //지금 회원가입중인 사용자에게 보내야하기 때문에 musicianphone을 넘겨 받은 것 이다.
    params.put("to", musicianphone);
    params.put("from", "01000000000");
    params.put("type", "SMS");
    params.put("text", "인증번호는 ["+numStr+"] 입니다.");
    params.put("app_version", "test app 1.2"); // application name and version
    try {
		coolsms.send(params); // 메시지 전송
	} catch (CoolsmsException e) {
	}
    return numStr;
}
```
