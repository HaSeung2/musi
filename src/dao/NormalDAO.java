package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.NormalDTO;
public class NormalDAO {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	public NormalDAO() {
		conn = DBConnection.getConnection();
	}
	
	public boolean normalJoin(NormalDTO normal) {
		String sql = "insert into normal(normalid, normalpw, normalname, normalage, normalphone, normaladdr) values(?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, normal.normalid);
			ps.setString(2, normal.normalpw);
			ps.setString(3, normal.normalname);
			ps.setInt(4, normal.normalage);
			ps.setString(5, normal.normalphone);
			ps.setString(6, normal.normaladdr);
			
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			System.out.println("일반회원 회원가입 실패"+e);
		}
		return false;
	}

	public boolean checkid(String normalid) {
		String sql = "select * from normal where normalid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, normalid);
			rs = ps.executeQuery();
			
			if(!(rs.next())) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("아이디 중복체크 오류"+e);
		}
		return false;
	}

	public boolean login(String normalid, String normalpw) {
		String sql = "select * from normal where normalid = ? and normalpw = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, normalid);
			ps.setString(2, normalpw);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				Session.put("login_id", normalid);
				return true;
			}
		} catch (SQLException e) {
			System.out.println("로그인 실패"+e);
		}
		return false;
	}

	public boolean checkpw(String pw) {
		String sql = "select * from normal where normalpw = ? and normalid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, pw);
			ps.setString(2, Session.get("login_id"));
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("비밀번호 체크 오류");
		}
		return false;
	}

	public boolean deleteMy() {
		String sql = "delete from normal where normalid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Session.get("login_id"));
			
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			System.out.println("회원 삭제 실패");
		}
		return false;
	}

	public boolean modifyMy(int choice, String change) {
		String [] data = {"normalpw","normalname","normalage"};
		String sql = "update normal set " + data[choice-1] + " = ? where normalid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, change);
			ps.setString(2, Session.get("login_id"));
			
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
		}
		return false;
	}

	public boolean checkpws(String pws) {
		String sql = "select * from normal where normalpw = ? and normalid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, pws);
			ps.setString(2, Session.get("login_id"));
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("비밀번호 체크 오류");
		}
		return false;
	}

	public boolean modifyphone(String phoneses) {
		String sql = "update normal set normalphone = ? where normalid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, phoneses);
			ps.setString(2, Session.get("login_id"));
			
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
		}
		return false;
	}

	public boolean checkpwse(String pwse) {
		String sql = "select * from normal where normalpw = ? and normalid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, pwse);
			ps.setString(2, Session.get("login_id"));
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("비밀번호 체크 오류");
		}
		return false;
	}

	public boolean modifyAddr(String addr) {
		String sql = "update normal set normaladdr = ? where normalid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, addr);
			ps.setString(2, Session.get("login_id"));
			
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
		}
		return false;
	}
}
