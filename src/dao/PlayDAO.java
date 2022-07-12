package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayDAO {
ResultSet rs;
PreparedStatement ps;
Connection conn;
	public PlayDAO() {
		conn = DBConnection.getConnection();
	}
	public boolean addPlay(String playsong) {
		String sql = "insert into play(play, normalid) values(?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, playsong);
			ps.setString(2, Session.get("login_id"));
			
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
		}
		return false;
	}
	public String selectplay() {
		String sql = "select * from play where normalid = ?";
		String result = "";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Session.get("login_id"));
			rs = ps.executeQuery();
			
			while(rs.next()) {
				result += rs.getString(1);
			}
			return result;
		} catch (SQLException e) {
		}
		return result;
	}
	public boolean updateplay(String playsong) {
		String sql = "update play set play = ? where normalid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, playsong);
			ps.setString(2, Session.get("login_id"));
			
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
		}
		return false;
	}
	public String getPlay() {
		String sql = "select * from play where normalid = ?";
		String result = "";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Session.get("login_id"));
			rs = ps.executeQuery();
			
			while(rs.next()) {
				result += rs.getString(1);
			}
			return result;
		} catch (SQLException e) {
		}
		return result;
	}

}
