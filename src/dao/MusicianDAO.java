package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.MusicanDTO;

public class MusicianDAO {
	PreparedStatement ps;
	Connection conn;
	ResultSet rs;

	public MusicianDAO() {
		conn = DBConnection.getConnection();
	}

	public boolean joinView(MusicanDTO musician) {
		String sql = "insert into musician(musicianid, musicianpw, musicianname, musicianage, musicianphone, musicianaddr)"+ "values(?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, musician.musicianid);
			ps.setString(2, musician.musicianpw);
			ps.setString(3, musician.musicianname);
			ps.setInt(4, musician.musicianage);
			ps.setString(5, musician.musicianphone);
			ps.setString(6, musician.musicianaddr);
			
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			System.out.println("음악가 회원가입 오류"+e);
		}
		return false;
	}

	public boolean checkid(String musicianid) {
		String sql = "select * from musician where musicianid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, musicianid);
			rs =ps.executeQuery();
			
			if(!(rs.next())) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("아이디 중복 오류 "+e);
		}
		return false;
	}

	public boolean login(String musicianid, String musicianpw) {
		String sql = "select * from musician where musicianid = ? and musicianpw = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, musicianid);
			ps.setString(2, musicianpw);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				Session.put("login_id", musicianid);
				
				return true;
			}
		} catch (SQLException e) {
			System.out.println("로그인 실패"+e);
		}
		return false;
	}

	public boolean deleteMy() {
		String sql = "delete from musician where musicianid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Session.get("login_id"));
			
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			System.out.println("뮤지션 삭제 실패");
		}
		return false;
	}

	public boolean checkpw(String pw) {
		String sql = "select * from musician where musicianpw = ? and musicianid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, pw);
			ps.setString(2, Session.get("login_id"));
			rs = ps.executeQuery();
			
			return rs.next();
		} catch (SQLException e) {
			System.out.println("뮤지션 삭제 실패");
		}
		return false;
	}

	public boolean modifyMy(int choice, String change) {
		String [] data = {"musicianpw","musicianname","musicianage"};
		String sql = "update musician set " + data[choice-1] + " = ? where musicianid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, change);
			ps.setString(2, Session.get("login_id"));
			
			return ps.executeUpdate() == 1;
			
		} catch (SQLException e) {
			System.out.println("회원 정보 변경 실패");
		}
		return false;
	}
	
	public boolean checkpws(String pws) {
		String sql = "select * from musician where musicianpw = ? and musicianid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, pws);
			ps.setString(2, Session.get("login_id"));
			rs = ps.executeQuery();
			
			return rs.next();
		} catch (SQLException e) {
			System.out.println("뮤지션 삭제 실패");
		}
		return false;
	}

	public boolean modifyphone(String phoneses) {
		String sql = "update musician set musicianphone = ? where musicianid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, phoneses);
			ps.setString(2, Session.get("login_id"));
			
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			System.out.println("연락처 변경 실패");
		}
		return false;
	}
	
	public boolean checkpwse(String pwse) {
		String sql = "select * from musician where musicianpw = ? and musicianid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, pwse);
			ps.setString(2, Session.get("login_id"));
			rs = ps.executeQuery();
			
			return rs.next();
		} catch (SQLException e) {
			System.out.println("뮤지션 삭제 실패");
		}
		return false;
	}

	public boolean modifyAddr(String addr) {
		String sql = "update musician set musicianaddr = ? where musicianid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, addr);
			ps.setString(2, Session.get("login_id"));
			
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			System.out.println("주소 변경 실패");
		}
		return false;
	}
}
