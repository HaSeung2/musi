package view;

import dao.PlayListDAO;
import dao.Session;
import dto.PlayListDTO;

public class AddPlayList {
	public AddPlayList() {}

	public AddPlayList(String songname, String sing) {
		PlayListDAO pdao = new PlayListDAO();
		PlayListDTO play = new PlayListDTO(songname, sing, Session.get("login_id"));
		
		if(pdao.addPlayList(play)) {
			System.out.println(songname+"(를)을 플레이 리스트에 추가 완료~~!");
		}
		else{
			System.out.println(songname+"(를)을 플레이리스트에 담는데 실패하였습니다. ");
			System.out.println("다시 시도해주세요 ~");
		}
	}
}
