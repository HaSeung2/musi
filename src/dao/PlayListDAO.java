package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.PlayListDTO;

public class PlayListDAO {
Connection conn;
ResultSet rs;
PreparedStatement ps;
	public PlayListDAO() {
		conn = DBConnection.getConnection();
	}
	public boolean addPlayList(PlayListDTO play) {
		String sql = "insert into playlist(songname, sing, normalid) values(?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, play.songname);
			ps.setString(2, play.sing);
			ps.setString(3, Session.get("login_id"));
			
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			System.out.println("플레이리스트 담기 오류");
		}
		return false;
	}
	public String myPlayList() {
		String sql = "select * from playlist where normalid = ?";
		String result = "";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Session.get("login_id"));
			rs = ps.executeQuery();
			
			while(rs.next()) {
				result += rs.getString(1)+"\t"+rs.getString(2)+"\n";
			}
			return result;
		} catch (SQLException e) {
			System.out.println("내 플레이리스트 보기 오류");
		}
		return result;
	}
	public boolean deleteMyMusic() {
		String sql = "delete from where normalid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Session.get("login_id"));
			
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
		}
		return false;
	}
	public boolean checkSong(String attentisong) {
		String sql = "select * from playlist where songname = ? and normalid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, attentisong);
			ps.setString(2, Session.get("login_id"));
			rs = ps.executeQuery();
			
			if(!(rs.next())) {
				return true;
			}
		} catch (SQLException e) {
		}
		return false;
	}
}
