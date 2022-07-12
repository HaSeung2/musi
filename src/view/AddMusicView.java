package view;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import dao.MusicDAO;
import dao.Session;
import dto.MusicDTO;

public class AddMusicView {
	public AddMusicView() {
		Scanner sc = new Scanner(System.in);
		MusicDAO mdao = new MusicDAO();
		
		System.out.print("노래 이름 : ");
		String songname = sc.nextLine();
		System.out.print("가수 : ");
		sc = new Scanner(System.in);
		String sing = sc.nextLine();
		System.out.print("작사 : ");
		sc = new Scanner(System.in);
		String songlyrics = sc.nextLine();
		System.out.print("작곡가: ");
		sc = new Scanner(System.in);
		String songlyricist = sc.nextLine();
		System.out.println("노래 가사 : ");
		sc = new Scanner(System.in);
		String songcomposer = sc.nextLine();
		
		MusicDTO song = new MusicDTO(songname, sing, songlyrics, songlyricist, songcomposer,Session.get("login_id"));
		if(mdao.addMusic(song)) {
			System.out.println(songname+"노래 등록이 완료 되었습니다 ~~~!");
			System.out.println("다음에도 등록하러 와주세요 ~~~");
		}
		else {
			System.out.println("노래 등록에 실패하였습니다 ㅠ_ㅠ");
		}
	}
}
